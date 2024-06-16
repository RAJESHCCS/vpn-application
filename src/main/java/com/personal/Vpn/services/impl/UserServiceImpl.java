package com.personal.Vpn.services.impl;

import com.personal.Vpn.Model.Country;
import com.personal.Vpn.Model.CountryName;
import com.personal.Vpn.Model.ServiceProvider;
import com.personal.Vpn.Model.User;
import com.personal.Vpn.repository.CountryRepository;
import com.personal.Vpn.repository.ServiceProviderRepository;
import com.personal.Vpn.repository.UserRepository;
import com.personal.Vpn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository3;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository3;

    @Autowired
    private CountryRepository countryRepository3;

    @Override
    public User register(String username, String password, String countryName) throws Exception {
        System.out.println("User called register method");

        String countryNameUpper = countryName.toUpperCase();
        if (!countryNameUpper.equals("IND") && !countryNameUpper.equals("CHI") &&
                !countryNameUpper.equals("USA") && !countryNameUpper.equals("JPN")) {
            throw new Exception("Country not found");
        }

        Country country = new Country(CountryName.valueOf(countryNameUpper), CountryName.valueOf(countryNameUpper).toCode());

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setConnection(false);
        country.setUser(user);
        user.setOriginalCountry(country);

        user = userRepository3.save(user);
        String countryCode = user.getOriginalCountry().getCountryCode();
        if (countryCode == null) {
            throw new Exception("Country code is null");
        }
//        user.setOriginIp(Long.valueOf(countryCode + "." + user.getId()));
//        user.setOriginIp(Long.valueOf(countryCode + "." + user.getId()));
        user.setOriginIp(countryCode + "." + user.getId());  // Store as String


        user = userRepository3.save(user);
        return user;
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) throws Exception {
        Optional<ServiceProvider> serviceProviderOpt = serviceProviderRepository3.findById(serviceProviderId);
        Optional<User> userOpt = userRepository3.findById(userId);

        if (!serviceProviderOpt.isPresent() || !userOpt.isPresent()) {
            throw new Exception("User or ServiceProvider not found");
        }

        ServiceProvider serviceProvider = serviceProviderOpt.get();
        User user = userOpt.get();

        user.getServiceProviderList().add(serviceProvider);
        serviceProvider.getUsers().add(user);

        serviceProviderRepository3.save(serviceProvider);
        return user;
    }
}
