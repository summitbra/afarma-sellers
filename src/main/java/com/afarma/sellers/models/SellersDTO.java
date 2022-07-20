package com.afarma.sellers.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SellersDTO implements Serializable {

    @JsonProperty("description")
    private String description;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("dateIni")
    private String dateIni;

    @JsonProperty("dateFin")
    private String dateFin;

    public SellersDTO() {
    }

    public SellersDTO(String description, Boolean active, String dateIni, String dateFin) {
        this.description = description;
        this.active = active;
        this.dateIni = dateIni;
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDateIni() {
        return dateIni;
    }

    public void setDateIni(String dateIni) {
        this.dateIni = dateIni;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "SellersDTO{" +
                "description='" + description + '\'' +
                ", active=" + active +
                ", dateIni='" + dateIni + '\'' +
                ", dateFin='" + dateFin + '\'' +
                '}';
    }
}