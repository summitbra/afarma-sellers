package com.afarma.sellers.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class SellersResponse implements Serializable {

    @EmbeddedId
    private SellersResponseId sellersResponseId;

    @JsonProperty("trading_name")
    @Column(name = "trading_name")
    private String tradingName;

    @JsonProperty("company_name")
    @Column(name = "company_name")
    private String companyName;

    @JsonProperty("stock")
    @Column(name = "stock")
    private String stock;

    @JsonProperty("active")
    @Column(name = "active")
    private Boolean active;

    @JsonProperty("price")
    @Column(name = "price")
    private Double price;

    @JsonProperty("date_register")
    @Column(name = "date_register")
    private Date dateRegister;

    @JsonProperty("seller_id")
    @Column(name = "seller_id")
    private Long sellerId;

    @JsonProperty("availability")
    @Column(name = "availability")
    private String availability;

    @JsonProperty("description")
    @Column(name = "description", updatable = false, insertable = false)
    private String description;

    public SellersResponse() {
    }

    public SellersResponse(SellersResponseId sellersResponseId, String tradingName, String companyName, String stock, Boolean active, Double price, Date dateRegister, Long sellerId, String availability, String description) {
        this.sellersResponseId = sellersResponseId;
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.stock = stock;
        this.active = active;
        this.price = price;
        this.dateRegister = dateRegister;
        this.sellerId = sellerId;
        this.availability = availability;
        this.description = description;
    }

    public SellersResponseId getSellersResponseId() {
        return sellersResponseId;
    }

    public void setSellersResponseId(SellersResponseId sellersResponseId) {
        this.sellersResponseId = sellersResponseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

}