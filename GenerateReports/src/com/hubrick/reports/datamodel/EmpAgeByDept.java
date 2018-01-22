package com.hubrick.reports.datamodel;

public class EmpAgeByDept implements Comparable<Employees>{
	
	private int departmentNumber;
	private String departmentName;
	private String empName;
	private int age;
	
	public EmpAgeByDept(int departmentNumber, String departmentName, String empName, int age)
	{
		this.departmentNumber = departmentNumber;
		this.departmentName = departmentName;
		this.empName = empName;
		this.age = age;
	}

	public int getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(int departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Employees arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
