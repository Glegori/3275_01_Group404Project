package com.csis3275.Group404Project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class rates {

    @JsonProperty("EUR")
    private String EUR;

    @JsonProperty("USD")
    private String USD;

    @JsonProperty("PKR")
    private String PKR;

    @JsonProperty("INR")
    private String INR;

    public rates(){

    }

    public String getEUR() {
        return EUR;
    }

    public void setEUR(String EUR) {
        this.EUR = EUR;
    }

    public String getUSD() {
        return USD;
    }

    public void setUSD(String usd) {
        this.USD = usd;
    }

    public String getPKR() {
        return PKR;
    }

    public void setPKR(String pkr) {
        this.PKR = pkr;
    }

    public String getINR() {
        return INR;
    }

    public void setINR(String inr) {
        this.INR = inr;
    }

}
