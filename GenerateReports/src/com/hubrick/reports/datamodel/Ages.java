package com.hubrick.reports.datamodel;

public class Ages {
	
	private String employeeName;
	private int age;
	
	public Ages(String employeeName, int age)
	{
		this.employeeName = employeeName;
		this.age = age;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

}
