package com.test.guru99.bankingProject;

public class LoginInformation {

	private double caseNum;
	private String userName;
	private String password;
	private String result;
	
	public LoginInformation(double caseNum, String userName, String password, String result) {
		
		this.caseNum = caseNum;
		this.userName = userName;
		this.password = password;
		this.result = result;
	}

	public double getCaseNum() {
		return caseNum;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getResult() {
		return result;
	}
}
