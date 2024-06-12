package com.personal.Vpn.services.impl;

import com.personal.Vpn.Model.Connection;
import com.personal.Vpn.Model.CountryName;
import com.personal.Vpn.Model.ServiceProvider;
import com.personal.Vpn.Model.User;
import com.personal.Vpn.repository.ConnectionRepository;
import com.personal.Vpn.repository.ServiceProviderRepository;
import com.personal.Vpn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;



public class ConnectionServiceImpl {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ConnectionRepository connectionRepository;
    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    public Connection getConnection(int userId, ServiceProvider serviceProvider) {
        User user = userRepository.findById(userId).get();
        if(user.getMaskedIp()!=null) {
            throw new Exception("already connected");
        }
        if (CountryName.equalsIgnoreCase(user.getOriginalCountry().getCountryName().toString())){
            return user;

        if(CountryName.equas;)

        if (user.getServiceProviderList() == null){
            throw new Exception("Unable to connect");
        }
    }
}
