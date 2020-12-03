package com.csis3275.Group404Project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRate {

    private String date;
    private String base;
    private   rates rates;

    public CurrencyRate(){

    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public rates getrates() {
        return rates;
    }

    public void setrates(rates rates) {
        this.rates = rates;
    }



}
