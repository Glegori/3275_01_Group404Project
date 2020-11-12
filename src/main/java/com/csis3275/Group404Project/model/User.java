package com.csis3275.Group404Project.model;

public class User  {
	private int id;
	private String username;
	private String password;
	private String reportsFrom;
	private String userType;
	private double total;
	
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getReportsFrom() {
		return reportsFrom;
	}
	public String getUserType() {
		return userType;
	}
	public double getTotal() {
		return total;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setReportsFrom(String reportsFrom) {
		this.reportsFrom = reportsFrom;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}