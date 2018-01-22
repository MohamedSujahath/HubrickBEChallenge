package com.hubrick.reports.output.datamodel;

public class MedianAgeByDept {
	
	private int departmentNumber;
	private String departmentName;
	private double medianAge;
	
	public MedianAgeByDept(int departmentNumber, String departmentName, double medianAge)
	{
		this.departmentNumber = departmentNumber;
		this.departmentName = departmentName;
		this.medianAge = medianAge;
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

	public double getMedianAge() {
		return medianAge;
	}

	public void setMedianAge(double medianAge) {
		this.medianAge = medianAge;
	}
	
}
