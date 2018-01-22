package com.hubrick.reports.output.datamodel;

public class MedianIncomeByDept {
	
	private int departmentNumber;
	private String departmentName;
	private double medianIncome;
	
	public MedianIncomeByDept(int departmentNumber, String departmentName, double medianIncome)
	{
		this.departmentNumber = departmentNumber;
		this.departmentName = departmentName;
		this.medianIncome = medianIncome;
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

	public double getMedianIncome() {
		return medianIncome;
	}

	public void setMedianIncome(double medianIncome) {
		this.medianIncome = medianIncome;
	}
	
}
