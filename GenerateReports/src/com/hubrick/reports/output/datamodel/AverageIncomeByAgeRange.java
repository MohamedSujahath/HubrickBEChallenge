package com.hubrick.reports.output.datamodel;

public class AverageIncomeByAgeRange {
	
	private String ageRange;
	private double averageIncome;
	
	public AverageIncomeByAgeRange(String ageRange, double averageIncome)
	{
		this.ageRange = ageRange;
		this.averageIncome = averageIncome;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public double getAverageIncome() {
		return averageIncome;
	}

	public void setAverageIncome(double averageIncome) {
		this.averageIncome = averageIncome;
	}

	
	
}
