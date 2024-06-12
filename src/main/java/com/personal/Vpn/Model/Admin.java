package com.personal.Vpn.Model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@RestController("/ragister")
@Entity
public class Admin {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String Username;
    private  String Password;

    @OneToMany(mappedBy = "admin", cascade= CascadeType.ALL)
    List<ServiceProvider> serviceProviders = new ArrayList<>();


    public Admin(){
    };
    public Admin(Integer id,String Username,String Password,List<ServiceProvider> serviceProviders){
        this.id = id;
        this.Username = Username;
        this.Password = Password;
        this.serviceProviders= serviceProviders;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    public List<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(List<ServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }
}
