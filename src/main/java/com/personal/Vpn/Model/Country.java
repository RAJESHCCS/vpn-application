package com.personal.Vpn.Model;

import jakarta.persistence.*;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String countryName;

    private String countryCode;  // Changed to String for consistency

    @OneToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;

    // Default constructor
    public Country() {
    }

    // Constructor with parameters
    public Country(CountryName countryName, String countryCode) {
        this.countryName = countryName.toString();
        this.countryCode = countryCode;
    }

    // Constructor with all fields
    public Country(Integer id, String countryName, String countryCode, User user, ServiceProvider serviceProvider) {
        this.id = id;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.user = user;
        this.serviceProvider = serviceProvider;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
}
