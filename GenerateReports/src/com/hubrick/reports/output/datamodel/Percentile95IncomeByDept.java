package com.hubrick.reports.output.datamodel;

public class Percentile95IncomeByDept {
	
	private int departmentNumber;
	private String departmentName;
	private double percentile95Income;
	
	public Percentile95IncomeByDept(int departmentNumber, String departmentName, double percentile95Income)
	{
		this.departmentNumber = departmentNumber;
		this.departmentName = departmentName;
		this.percentile95Income = percentile95Income;
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

	public double getPercentile95Income() {
		return percentile95Income;
	}

	public void setPercentile95Income(double percentile95Income) {
		this.percentile95Income = percentile95Income;
	}

}
