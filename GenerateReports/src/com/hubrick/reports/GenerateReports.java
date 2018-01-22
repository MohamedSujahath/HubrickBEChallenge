package com.hubrick.reports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.hubrick.reports.datamodel.AgeRangeXY;
import com.hubrick.reports.datamodel.Ages;
import com.hubrick.reports.datamodel.Departments;
import com.hubrick.reports.datamodel.EmpAgeByDept;
import com.hubrick.reports.datamodel.Employees;
import com.hubrick.reports.output.datamodel.AverageIncomeByAgeRange;
import com.hubrick.reports.output.datamodel.MedianAgeByDept;
import com.hubrick.reports.output.datamodel.MedianIncomeByDept;
import com.hubrick.reports.output.datamodel.Percentile95IncomeByDept;
import com.hubrick.utils.CSVFileWriter;

public class GenerateReports {
	
	
	/*
	 * ##############################################################################################################################
	 * 
	 * 
	 * Main function of this Java Class
	 * 
	 * 
	 * ##############################################################################################################################
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GenerateReports gr = new GenerateReports();
		gr.startProcessing();
		
	}
	
	/*
	 * ##############################################################################################################################
	 * 
	 * 
	 * Main Processing of reading input files and Calculation for reports starts from this method
	 * 
	 * 
	 * ##############################################################################################################################
	 */
	private void startProcessing()
	{
		
		// Read all the input data CSV Files
		List<Employees> employeesList = readEmployeesCSVInputDataFile();
		
		List<Departments> departmentList = readDepartmentsCSVInputDataFile();
		
		List<Ages> agesList = readAgesCSVInputDataFile();
		
		
		// Calculate Median Income By Department
		List<MedianIncomeByDept> medianIncomeByDeptList = calculateMedianIncomeByDepartment(employeesList, departmentList);
		
		generateReportForMedianIncomeByDept(medianIncomeByDeptList);
		
		
		// Calculate Median Age By Department
		List<MedianAgeByDept> medianAgeByDeptList = calculateMedianAgeByDepartment(employeesList, departmentList, agesList);
		
		generateReportForMedianAgeByDept(medianAgeByDeptList);
		
		
		// Calculate Percentile 95 Income By Department
		List<Percentile95IncomeByDept> percentile95IncomeByDeptList = calculate95percentileIncomeByDept(employeesList, departmentList);
		
		generateReportFor95percentileIncomeByDept(percentile95IncomeByDeptList);
		
		
		// Calculate Average Income By Age Range
		List<AverageIncomeByAgeRange> avgIncomeByAgeRangeList = calculateAvgIncomeByAgeRange(employeesList, agesList);
		
		generateReportForAvgIncomeByAgeRange(avgIncomeByAgeRangeList);
		
				
	}
	
	
	/*
	 * ##############################################################################################################################
	 * 
	 * 
	 * Following 3 methods perform the process of reading the 3 input data CSV files and map them to Java Objects and
	 * populate the Array Lists.
	 * 
	 * 
	 * ##############################################################################################################################
	 */
	private List<Employees> readEmployeesCSVInputDataFile()
	{
		// Employees CSV File
				InputStream is = null;
				try {
					URL url = getClass().getResource("employees.csv");
					System.out.println("Input Data CSV File URL Path : " + url.getPath());
					is = new FileInputStream(new File(url.getPath()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				List<Employees> employeesList = br.lines()
					    .map(mapToEmployees)
					    .collect(Collectors.toList());
				
		return employeesList;
	}
	
	private List<Departments> readDepartmentsCSVInputDataFile()
	{
		// Departments CSV File
				InputStream is2 = null;
				try {
					URL url = getClass().getResource("departments.csv");
					System.out.println("Input Data CSV File URL Path : " + url.getPath());
					is2 = new FileInputStream(new File(url.getPath()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
				
				List<Departments> departmentList = br2.lines()
					    .map(mapToDepartments)
					    .sorted()
					    .collect(Collectors.toList());
				
		return departmentList;
		
	}
	
	private List<Ages> readAgesCSVInputDataFile()
	{
		// Ages CSV File
		InputStream is3 = null;
		try {
			URL url = getClass().getResource("ages.csv");
			System.out.println("Input Data CSV File URL Path : " + url.getPath());
			is3 = new FileInputStream(new File(url.getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader br3 = new BufferedReader(new InputStreamReader(is3));
		
		List<Ages> agesList = br3.lines()
			    .map(mapToAges)
			    .collect(Collectors.toList());
		
		return agesList;
		
	}
	
	
	/*
	 * ##############################################################################################################################
	 * 
	 * 
	 * Following 3 methods perform the process of mapping each line of the input data file to Java Objects.
	 * 
	 * 
	 * ##############################################################################################################################
	 */
	
	public static Function<String, Employees> mapToEmployees = (line) -> {
		  String[] p = line.split(",");
		  return new Employees(Integer.parseInt(p[0]), p[1], p[2], Double.parseDouble(p[3]));
	};
	
	public static Function<String, Departments> mapToDepartments = (line) -> {
		  String[] p = line.split(",");
		  return new Departments(p[0]);
	};
	
	public static Function<String, Ages> mapToAges = (line) -> {
		  String[] p = line.split(",");
		  return new Ages(p[0], Integer.parseInt(p[1]));
	};
	
	
	
	/*
	 * ##############################################################################################################################
	 * 
	 * 
	 * Following 2 methods perform the process of calculation of Median Income By Department and generate report for that.
	 * 
	 * 
	 * ##############################################################################################################################
	 */
	
	private List<MedianIncomeByDept> calculateMedianIncomeByDepartment(List<Employees> employeesList, List<Departments> departmentList)
	{
		
		//Median Income by department
		
				List<MedianIncomeByDept> medianIncomeByDeptList = new ArrayList<MedianIncomeByDept>();
				
				
				Map<Integer, List<Employees>> employeesGroupsMap = employeesList.stream().collect(Collectors.groupingBy(e -> e.getDepartmentNumber()));
				
				//System.out.println("The Employee List grouped by department number: \n" + employeesGroupsMap);
				
				for (Map.Entry<Integer, List<Employees>> entry : employeesGroupsMap.entrySet()) {
				    List<Employees> employeeList = entry.getValue();
				    
				    List<Employees> sortedEmployeeList = employeeList.stream().sorted(Comparator.comparing(Employees::getIncome)).collect(Collectors.toList());
				    //System.out.println("The sorted employee List according to income: " + sortedEmployeeList);
				   
				    if(sortedEmployeeList.size() % 2 == 0)
				    {
				    	// Even
				    	int medianValue1 = sortedEmployeeList.size() / 2;
				    	int medianValue2 = (sortedEmployeeList.size() / 2) + 1;
				    	
				    	Employees emp = sortedEmployeeList.get(medianValue1 - 1);
				    	Employees emp2 = sortedEmployeeList.get(medianValue2 - 1);
				    	
				    	double medianIncome1 = emp.getIncome();
				    	double medianIncome2 = emp2.getIncome();
				    	
				    	double medianIncome = (medianIncome1 + medianIncome2) / 2;
				    	
				    	//System.out.println("Department: " + emp.getDepartmentNumber() + "Median Income :" + medianIncome);
				    	Departments dept = departmentList.get(emp.getDepartmentNumber() - 1);
				    	
				    	MedianIncomeByDept mibd = new MedianIncomeByDept(emp.getDepartmentNumber(), dept.getDepartmentName(), medianIncome);
				    	medianIncomeByDeptList.add(mibd);
				    	
				    }
				    else
				    {
				    	//odd
				    	int medianValue = sortedEmployeeList.size() + 1;
				    	int medianPosition = medianValue / 2;
				    	Employees emp = sortedEmployeeList.get(medianPosition - 1);
				    	double medianIncome = emp.getIncome();
				    	//System.out.println("Department: " + emp.getDepartmentNumber() + "Median Income :" + medianIncome);
				    	Departments dept = departmentList.get(emp.getDepartmentNumber() - 1);
				    	MedianIncomeByDept mibd = new MedianIncomeByDept(emp.getDepartmentNumber(), dept.getDepartmentName(), medianIncome);
				    	medianIncomeByDeptList.add(mibd);
				    }
				    
				}
				
			return medianIncomeByDeptList;
	}
	
	
	private void generateReportForMedianIncomeByDept(List<MedianIncomeByDept> medianIncomeByDeptList)
	{
		// Generating Median Income By department Report
		List<String> medianIncomeHeadersList = new ArrayList<String>();
		
		medianIncomeHeadersList.add("Department No");
		medianIncomeHeadersList.add("Department Name");
		medianIncomeHeadersList.add("Median Income of all Employees in the Dept");

			try {
				generateMedianIncomeCSVOuputReport("MedianIncomeByDepartment.csv", medianIncomeHeadersList, medianIncomeByDeptList);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
	
	/*
	 * ##############################################################################################################################
	 * 
	 * 
	 * Following 2 methods perform the process of calculation of Median Age By Department and generate report for that.
	 * 
	 * 
	 * ##############################################################################################################################
	 */
	private List<MedianAgeByDept> calculateMedianAgeByDepartment(List<Employees> employeesList, List<Departments> departmentList, List<Ages> agesList)
	{
		// Median Employee Age by Department
		
				List<MedianAgeByDept> medianAgeByDeptList = new ArrayList<MedianAgeByDept>();
				
				
				Map<Integer, List<Employees>> employeeGroupsMap = employeesList.stream().collect(Collectors.groupingBy(e -> e.getDepartmentNumber()));
				
				//System.out.println("The Employee List grouped by department number: \n" + employeeGroupsMap);
				
				for (Map.Entry<Integer, List<Employees>> entry : employeeGroupsMap.entrySet()) {
				    List<Employees> employeeList = entry.getValue();
				    
				    //List<Employees> sortedEmployeeList = employeeList.stream().sorted(Comparator.comparing(Employees::getEmployeeName)).collect(Collectors.toList());
				    //System.out.println("The sorted employee List according to income: " + sortedEmployeeList);
				    List<EmpAgeByDept> employeeAgeDeptList = new ArrayList<EmpAgeByDept>();
				    for (Employees employees : employeeList) {
				    	
				    	//System.out.println("Department Number: " + employees.getDepartmentNumber() + "Employee Name : " + employees.getEmployeeName());
				    	
				    	Ages agesObj = agesList.stream().filter(x -> x.getEmployeeName().trim().equalsIgnoreCase(employees.getEmployeeName())).findFirst().get();
				    	
				    	//System.out.println("Age : " + agesObj.getAge()); 
				    	
				    	Departments dept = departmentList.get(employees.getDepartmentNumber() - 1);
				    	
				    	EmpAgeByDept empAgeObj = new EmpAgeByDept(employees.getDepartmentNumber(), dept.getDepartmentName(), employees.getEmployeeName(), agesObj.getAge());
				    	
				    	employeeAgeDeptList.add(empAgeObj);
					}
				    
				    List<EmpAgeByDept> employeeAgeDeptSortedList = employeeAgeDeptList.stream().sorted(Comparator.comparing(EmpAgeByDept::getAge)).collect(Collectors.toList());
				   
				    //System.out.println("Sorted Employee Age Dept List : " + employeeAgeDeptSortedList);
				    
				    if(employeeAgeDeptSortedList.size() % 2 == 0)
				    {
				    	// Even
				    	int medianValue1 = employeeAgeDeptSortedList.size() / 2;
				    	int medianValue2 = (employeeAgeDeptSortedList.size() / 2) + 1;
				    	
				    	EmpAgeByDept emp = employeeAgeDeptSortedList.get(medianValue1 - 1);
				    	EmpAgeByDept emp2 = employeeAgeDeptSortedList.get(medianValue2 - 1);
				    	
				    	int medianAge1 = emp.getAge();
				    	int medianAge2 = emp2.getAge();
				    	
				    	double medianAge = (double) (medianAge1 + medianAge2) / 2;
				    	
				    	//System.out.println("Department: " + emp.getDepartmentNumber() + "Median Age :" + medianAge);
				    	Departments dept = departmentList.get(emp.getDepartmentNumber() - 1);
				    	
				    	MedianAgeByDept mabd = new MedianAgeByDept(emp.getDepartmentNumber(), dept.getDepartmentName(), medianAge);
				    	medianAgeByDeptList.add(mabd);
				    	
				    }
				    else
				    {
				    	//odd
				    	int medianValue = employeeAgeDeptSortedList.size() + 1;
				    	int medianPosition = medianValue / 2;
				    	EmpAgeByDept emp = employeeAgeDeptSortedList.get(medianPosition - 1);
				    	double medianAge = emp.getAge();
				    	//System.out.println("Department: " + emp.getDepartmentNumber() + "Median Age :" + medianAge);
				    	Departments dept = departmentList.get(emp.getDepartmentNumber() - 1);
				    	MedianAgeByDept mabd = new MedianAgeByDept(emp.getDepartmentNumber(), dept.getDepartmentName(), medianAge);
				    	medianAgeByDeptList.add(mabd);
				    }
				    
				}
				
				return medianAgeByDeptList;
		
	}
	
	private void generateReportForMedianAgeByDept(List<MedianAgeByDept> medianAgeByDeptList)
	{
		// Generating Median Age By department Report
				List<String> medianAgeHeadersList = new ArrayList<String>();
				
				medianAgeHeadersList.add("Department No");
				medianAgeHeadersList.add("Department Name");
				medianAgeHeadersList.add("Median Age of all Employees in the Dept");

					try {
						generateMedianAgeCSVOuputReport("MedianAgeByDepartment.csv", medianAgeHeadersList, medianAgeByDeptList);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	}
	
	/*
	 * ##############################################################################################################################
	 * 
	 * 
	 * Following 2 methods perform the process of calculation of 95th percentile of Income by Department and generate report for that.
	 * 
	 * 
	 * ##############################################################################################################################
	 */
	
	private List<Percentile95IncomeByDept> calculate95percentileIncomeByDept(List<Employees> employeesList, List<Departments> departmentList)
	{
		// 95 Percentile income by department
		
					List<Percentile95IncomeByDept> percentile95IncomeByDeptList = new ArrayList<Percentile95IncomeByDept>();
					
					
					Map<Integer, List<Employees>> percentileEmployeesGroupsMap = employeesList.stream().collect(Collectors.groupingBy(e -> e.getDepartmentNumber()));
					
					//System.out.println("The Employee List grouped by department number: \n" + percentileEmployeesGroupsMap);
					
					for (Map.Entry<Integer, List<Employees>> entry : percentileEmployeesGroupsMap.entrySet()) {
					    List<Employees> employeeList = entry.getValue();
					    
					    List<Employees> sortedEmployeeList = employeeList.stream().sorted(Comparator.comparing(Employees::getIncome)).collect(Collectors.toList());
					    //System.out.println("The sorted employee List according to income: " + sortedEmployeeList);
					    
					    //95th percentile calculation
					    int n = sortedEmployeeList.size();
					    
					    double index = n * 0.95;
					    
					    long roundedIndex = Math.round(index);
					    
					    Employees emp = sortedEmployeeList.get((int)roundedIndex);
					    double percentile95Income = emp.getIncome();
					     
					    //System.out.println("The Dept Number: " + emp.getDepartmentNumber() + "Percentile 95 income: " + percentile95Income);
					    
					    Departments dept = departmentList.get(emp.getDepartmentNumber() - 1);
					    
					    Percentile95IncomeByDept p95IncbyDept = new Percentile95IncomeByDept(emp.getDepartmentNumber() , dept.getDepartmentName(), percentile95Income);
					    
					    percentile95IncomeByDeptList.add(p95IncbyDept);
					    
					}
					
			return percentile95IncomeByDeptList;
		
	}
	
	private void generateReportFor95percentileIncomeByDept(List<Percentile95IncomeByDept> percentile95IncomeByDeptList)
	{
		
		// Generating 95th percentile Income By department Report
					List<String> percentile95HeadersList = new ArrayList<String>();
					
					percentile95HeadersList.add("Department No");
					percentile95HeadersList.add("Department Name");
					percentile95HeadersList.add("95th Percentile of all Income in the Dept");

						try {
							generatePercentile95CSVOuputReport("Percentile95IncomeByDepartment.csv", percentile95HeadersList, percentile95IncomeByDeptList);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	}
	
	/*
	 * ##############################################################################################################################
	 * 
	 * 
	 * Following 2 methods perform the process of calculation of Average Income By Age Range and generate report for that.
	 * 
	 * 
	 * ##############################################################################################################################
	 */
	private List<AverageIncomeByAgeRange> calculateAvgIncomeByAgeRange(List<Employees> employeesList, List<Ages> agesList)
	{
		
		// average income by age ranges with factor of ten
		
				List<AgeRangeXY> ageRangeList = new ArrayList<AgeRangeXY>();
				
				AgeRangeXY ageRange = new AgeRangeXY(10, 19);
				AgeRangeXY ageRange1 = new AgeRangeXY(20, 29);
				AgeRangeXY ageRange2 = new AgeRangeXY(30, 39);
				AgeRangeXY ageRange3 = new AgeRangeXY(40, 49);
				AgeRangeXY ageRange4 = new AgeRangeXY(50, 59);
				AgeRangeXY ageRange5 = new AgeRangeXY(60, 69);
				AgeRangeXY ageRange6 = new AgeRangeXY(70, 79);
				AgeRangeXY ageRange7 = new AgeRangeXY(80, 89);
				
				ageRangeList.add(ageRange);
				ageRangeList.add(ageRange1);
				ageRangeList.add(ageRange2);
				ageRangeList.add(ageRange3);
				ageRangeList.add(ageRange4);
				ageRangeList.add(ageRange5);
				ageRangeList.add(ageRange6);
				ageRangeList.add(ageRange7);
				
				List<AverageIncomeByAgeRange> avgIncomeByAgeRangeList = new ArrayList<AverageIncomeByAgeRange>();
				
				for (AgeRangeXY ageRangeXY : ageRangeList) {
					
					List<Ages> filteredAgesList = agesList.stream().filter(x ->  x.getAge() >= ageRangeXY.getAgeRangeX() && x.getAge() <= ageRangeXY.getAgeRangeY()).collect(Collectors.toList());
					double income = 0;
					//System.out.println("filtered Age List size: " + filteredAgesList.size());
					for (Ages ages : filteredAgesList) {
						
						//System.out.println("filtered Age List: " + ages.getEmployeeName());
						List<Employees> filteredEmployeeList = employeesList.stream().filter(x -> x.getEmployeeName().trim().equalsIgnoreCase(ages.getEmployeeName())).collect(Collectors.toList());
						
						for (Employees employees : filteredEmployeeList) {
							
							income += employees.getIncome();
							
						}
						
					}
					
					double avgIncome = income / filteredAgesList.size();
					
					//System.out.println("Average Income : " + avgIncome);
					String ageRangeStr = ageRangeXY.getAgeRangeX() + " - " + ageRangeXY.getAgeRangeY();
					AverageIncomeByAgeRange avgIncomeByAge = new AverageIncomeByAgeRange(ageRangeStr, avgIncome);
					
					avgIncomeByAgeRangeList.add(avgIncomeByAge);
				}
		
		return avgIncomeByAgeRangeList;
	}
	
	private void generateReportForAvgIncomeByAgeRange(List<AverageIncomeByAgeRange> avgIncomeByAgeRangeList)
	{
		
		// Generating Average Income by Age Range Report
		
		List<String> avgIncomeByAgeRangeHeadersList = new ArrayList<String>();
		
		avgIncomeByAgeRangeHeadersList.add("Age Range");
		avgIncomeByAgeRangeHeadersList.add("Average Income in this Age Range");

			try {
				generateAvgIncomeCSVOuputReport("AvgIncomeByAgeRange.csv", avgIncomeByAgeRangeHeadersList, avgIncomeByAgeRangeList);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
	
	/*
	 * ##############################################################################################################################
	 * 
	 * 
	 * Following 4 methods generates the Output reports in the requested format
	 * 
	 * 1. Median Income By Department.csv
	 * 2. Median Age By Department.csv
	 * 3. 95 percentile Income By Department.csv
	 * 4. Average Income By Age Range.csv
	 * 
	 * 
	 * ##############################################################################################################################
	 */
	
	private void generateMedianIncomeCSVOuputReport(String outputReportFileName, List<String> headersList, List<MedianIncomeByDept> data) throws IOException
	{
		File filePath = new File(outputReportFileName);
		
		System.out.println("Generating the output report file :" + filePath);
		
        FileWriter writer = new FileWriter(filePath);

        //for header
        CSVFileWriter.writeLine(writer, headersList);

        for (MedianIncomeByDept d : data) {

            List<String> list = new ArrayList<String>();
            list.add(String.valueOf(d.getDepartmentNumber()));
            list.add(d.getDepartmentName());
            list.add(String.valueOf(d.getMedianIncome()));
  
            CSVFileWriter.writeLine(writer, list);

        }
        
        writer.flush();
        writer.close();
		
	}
	
	private void generateMedianAgeCSVOuputReport(String outputReportFileName, List<String> headersList, List<MedianAgeByDept> data) throws IOException
	{
		File filePath = new File(outputReportFileName);
		
		System.out.println("Generating the output report file :" + filePath);
		
        FileWriter writer = new FileWriter(filePath);

        //for header
        CSVFileWriter.writeLine(writer, headersList);

        for (MedianAgeByDept d : data) {

            List<String> list = new ArrayList<String>();
            list.add(String.valueOf(d.getDepartmentNumber()));
            list.add(d.getDepartmentName());
            list.add(String.valueOf(d.getMedianAge()));
  
            CSVFileWriter.writeLine(writer, list);

        }
        
        writer.flush();
        writer.close();
		
	}
	
	
	
	private void generatePercentile95CSVOuputReport(String outputReportFileName, List<String> headersList, List<Percentile95IncomeByDept> data) throws IOException
	{
		File filePath = new File(outputReportFileName);
		
		System.out.println("Generating the output report file :" + filePath);
		
        FileWriter writer = new FileWriter(filePath);

        //for header
        CSVFileWriter.writeLine(writer, headersList);

        for (Percentile95IncomeByDept d : data) {

            List<String> list = new ArrayList<String>();
            list.add(String.valueOf(d.getDepartmentNumber()));
            list.add(d.getDepartmentName());
            list.add(String.valueOf(d.getPercentile95Income()));
  
            CSVFileWriter.writeLine(writer, list);

        }
        
        writer.flush();
        writer.close();
		
	}
	
	
	
	private void generateAvgIncomeCSVOuputReport(String outputReportFileName, List<String> headersList, List<AverageIncomeByAgeRange> data) throws IOException
	{
		File filePath = new File(outputReportFileName);
		
		System.out.println("Generating the output report file :" + filePath);
		
        FileWriter writer = new FileWriter(filePath);

        //for header
        CSVFileWriter.writeLine(writer, headersList);

        for (AverageIncomeByAgeRange d : data) {

            List<String> list = new ArrayList<String>();
            list.add(d.getAgeRange());
            list.add(String.valueOf(d.getAverageIncome()));
            
            CSVFileWriter.writeLine(writer, list);

        }
        
        writer.flush();
        writer.close();
		
	}

	
	
	
	

}
