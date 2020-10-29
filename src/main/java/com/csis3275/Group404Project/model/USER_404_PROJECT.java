package com.csis3275.Group404Project.models;

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

    @Column(name = "REPORTTO")
    private String reportTO;

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
        System.out.println("I am in entity" + userName);
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

    public String getReportTO() {
        return reportTO;
    }

    public void setReportTO(String reportTo) {
        this.reportTO = reportTo;
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
