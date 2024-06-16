package com.personal.Vpn.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String originIp;  // Changed to String
    private String maskedIp;
    private Boolean connection = false;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Country originalCountry;

    @ManyToMany
    @JoinTable
    private List<ServiceProvider> serviceProviderList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Connection> connectionList = new ArrayList<>();

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public User(Integer id, String username, String password, String originIp, String maskedIp, boolean connection) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.originIp = originIp;
        this.maskedIp = maskedIp;
        this.connection = connection;
    }

    public User() {
    }

    public String getMaskedIp() {
        return maskedIp;
    }

    public boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginIp() {  // Changed to String
        return originIp;
    }

    public void setOriginIp(String originIp) {  // Changed to String
        this.originIp = originIp;
    }

    public String isMaskedIp() {
        return maskedIp;
    }

    public void setMaskedIp(String maskedIp) {
        this.maskedIp = maskedIp;
    }

    public Country getOriginalCountry() {
        return originalCountry;
    }

    public void setOriginalCountry(Country originalCountry) {
        this.originalCountry = originalCountry;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    public Boolean getConnection() {
        return connection;
    }

    public void setConnection(Boolean connection) {
        this.connection = connection;
    }
}
