package com.personal.Vpn.Model;

import jakarta.persistence.*;

@Entity
public class Connection {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Integer id;

    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;
    @ManyToOne
    @JoinColumn
    private  User user;

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }
    public Connection(){

    }
    public Connection(Integer id, ServiceProvider serviceProvider, User user) {
        this.id = id;
        this.serviceProvider = serviceProvider;
        this.user = user;
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
