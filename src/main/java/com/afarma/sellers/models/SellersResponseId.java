package com.afarma.sellers.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class SellersResponseId implements Serializable {


    @JsonProperty("Id")
    @Column(name = "id")
    private Long Id;

    @JsonProperty("description")
    @Column(name = "description")
    private String description;

    public SellersResponseId() {
    }

    public SellersResponseId(Long id, String description) {
        Id = id;
        this.description = description;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}