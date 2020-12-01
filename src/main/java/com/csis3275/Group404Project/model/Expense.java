package com.csis3275.Group404Project.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Getters and setters for the expense class.
 *
 *
 */
@Entity
@Table(name = "EXPENSE_404_PROJECT")
public class Expense{

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int id;

	@Column(name = "EXPENSENAME")
	private String expenseName;

	@Column(name = "EXPENSECOST")
	private double expenseCost;

	@Column(name = "DATE")
	private String date;

	@Column(name = "EXPENSETYPE")
	private String expenseType;

	@Column(name = "EXPENSESTATUS")
	private String expenseStatus;

	@Column(name = "EXPENSEDESC")
	private String expenseDesc;

	@Column(name = "BILLIMAGE")
	private String billImage;

	@Column(name = "USER")
	private String user;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public double getExpenseCost() {
		return expenseCost;
	}
	public void setExpenseCost(double expenseCost) {
		this.expenseCost = expenseCost;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getExpenseStatus() {
		return expenseStatus;
	}
	public void setExpenseStatus(String expenseStatus) {
		this.expenseStatus = expenseStatus;
	}

	public String getExpenseDesc() {
		return expenseDesc;
	}
	public void setExpenseDesc(String expenseDesc) {
		this.expenseDesc = expenseDesc;
	}

	public String getBillImage() {
		return billImage;
	}
	public void setBillImage(String billImage) {
		this.billImage = billImage;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
