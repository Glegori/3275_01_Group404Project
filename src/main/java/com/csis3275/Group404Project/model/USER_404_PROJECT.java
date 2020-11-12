package com.csis3275.Group404Project.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_404_PROJECT")
public class USER_404_PROJECT {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int ID;

    //private String USERNAME;
    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "REPORTSFROM")
    private String reportsFrom;

    @Column(name = "USERTYPE")
    private String userType;

    @Column(name = "TOTAL")
    private double total;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getReportsFrom() {
        return reportsFrom;
    }

    public void setReportsFrom(String reportsFrom) {
        this.reportsFrom = reportsFrom;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
