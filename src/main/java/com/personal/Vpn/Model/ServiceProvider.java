package com.personal.Vpn.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


public class ServiceProvider {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;
    private  String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn
    private Admin admin;

    @OneToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL)
    List<Country> countryList= new ArrayList<>();

    @ManyToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL)
    List<User> user= new ArrayList<>();
    public  ServiceProvider(){

    }


}
