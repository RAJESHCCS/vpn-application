package com.personal.Vpn.Model;

import jakarta.persistence.*;

public class Connection {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Integer id;

    public Connection(Integer id, ServiceProvider serviceProvider, User user) {
        this.id = id;
        this.serviceProvider = serviceProvider;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    @ManyToOne
    @JoinColumn
    private  User user;

    public Connection(){

    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
