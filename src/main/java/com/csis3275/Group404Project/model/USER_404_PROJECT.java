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
    private String reportsFROM;

    @Column(name = "USERTYPE")
    private String userType;

    @Column(name = "TOTAL")
    private String total;

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

    public String getReportsFROM() {
        return reportsFROM;
    }

    public void setReportsFROM(String reportTo) {
        this.reportsFROM = reportTo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
