package com.personal.Vpn.Model;

import jakarta.persistence.*;

@Entity

public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Long originIp;
    private String maskedIp;
    private boolean connection= false;

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

    public Long getOriginIp() {
        return originIp;
    }

    public void setOriginIp(Long originIp) {
        this.originIp = originIp;
    }

    public String isMaskedIp() {
        return maskedIp;
    }

    public void setMaskedIp(String maskedIp) {
        this.maskedIp = maskedIp;
    }


}