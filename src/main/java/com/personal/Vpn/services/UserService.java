package com.personal.Vpn.services;

import com.personal.Vpn.Model.User;

public interface UserService {
    public User register(String username, String countryName) throws Exception;
    public User subscribe(Integer userId,Integer serviceProviderId);
}
