package com.hubrick.reports.datamodel;

public class Employees implements Comparable<Employees>{
	
	private int departmentNumber;
	private String employeeName;
	private String sex;
	private double income; 
	
	public Employees(int departmentNumber, String employeeName, String sex, double income)
	{
		this.departmentNumber = departmentNumber;
		this.employeeName = employeeName;
		this.sex = sex;
		this.income = income;
	}

	public int getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(int departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income)   {
		this.income = income;
	}

	@Override
	public int compareTo(Employees o) {
		// TODO Auto-generated method stub
		return 0;
	}


	

	
	
}
