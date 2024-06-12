package com.personal.Vpn.Model;

import jakarta.persistence.*;

@Entity
public class Country {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer Id;
    private  String countryName;
    private Integer getCountryCode;

    @OneToOne
    @JoinColumn
    private User user;


    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;





    public Country(Integer id, String countryName, Integer getCountryCode, User user, ServiceProvider serviceProvider) {
        Id = id;
        this.countryName = countryName;
        this.getCountryCode = getCountryCode;
        this.user = user;
        this.serviceProvider = serviceProvider;
    }

    public Country(CountryName countryName, String code) {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }


    public  Country(){

    };




    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getGetCountryCode() {
        return getCountryCode;
    }

    public void setGetCountryCode(Integer getCountryCode) {
        this.getCountryCode = getCountryCode;
    }

}



