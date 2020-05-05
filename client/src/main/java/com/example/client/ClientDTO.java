package com.example.client;


import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Client Model")
public class ClientDTO implements Comparable<ClientDTO>{

    @ApiModelProperty(value = "Client id")
    private Integer id;

    @ApiModelProperty(value = "Client first name")
    private String firstName;

    @ApiModelProperty(value = "Client last name")
    private String lastName;

    @ApiModelProperty(value = "Client job")
    private String job;

    @ApiModelProperty(value = "Client address")
    private String address;

    @ApiModelProperty(value = "Client email")
    private String email;

    @ApiModelProperty(value = "Client phone")
    private String phone;

    @ApiModelProperty(value = "Client city")
    private String city;

    @ApiModelProperty(value = "Client country")
    private String  country;

    @ApiModelProperty(value = "Client creation date in the system")
    private LocalDate creationDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public int compareTo(ClientDTO o) {
        return this.lastName.compareToIgnoreCase(o.getLastName());
    }

}











