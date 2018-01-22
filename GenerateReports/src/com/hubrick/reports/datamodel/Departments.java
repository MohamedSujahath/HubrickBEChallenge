package com.hubrick.reports.datamodel;

public class Departments implements Comparable<Departments>{

	
	private String departmentName;
	
	public Departments(String departmentName)
	{
		this.departmentName = departmentName;
		
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public int compareTo(Departments dept) {
		// TODO Auto-generated method stub
		return departmentName.compareTo(dept.getDepartmentName());
	}
	
	
}
