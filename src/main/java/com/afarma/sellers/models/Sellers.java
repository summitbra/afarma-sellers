package com.afarma.sellers.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sellers")
public class Sellers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("trading_name")
    @Column(name = "trading_name")
    private String tradingName;

    @JsonProperty("company_name")
    @Column(name = "company_name")
    private String companyName;

    @JsonProperty("cpf")
    @Column(name = "cpf")
    private String cpf;

    @JsonProperty("cnpj")
    @Column(name = "cnpj")
    private String cnpj;

    @JsonProperty("email")
    @Column(name = "email")
    private String email;

    @JsonProperty("phone")
    @Column(name = "phone")
    private String phone;

    @JsonProperty("category")
    @Column(name = "category")
    private String category;

    @Column(name = "status")
    private boolean status;

    @Column(name = "situation")
    private boolean situation;

    public Sellers() {
    }

    public Sellers(String tradingName, String companyName, String cpf, String cnpj, String email, String phone, String category, boolean status) {
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.email = email;
        this.phone = phone;
        this.category = category;
        this.status = status;
    }

    public Sellers(Long id, String tradingName, String companyName, String cpf, String cnpj, String email,
                   String phone, String category, boolean status, boolean situation) {
        this.id = id;
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.email = email;
        this.phone = phone;
        this.category = category;
        this.status = status;
        this.situation = situation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isSituation() {
        return situation;
    }

    public void setSituation(boolean situation) {
        this.situation = situation;
    }
}