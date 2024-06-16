package com.personal.Vpn.services.impl;

import com.personal.Vpn.Model.*;
import com.personal.Vpn.repository.ConnectionRepository;
import com.personal.Vpn.repository.ServiceProviderRepository;
import com.personal.Vpn.repository.UserRepository;
import com.personal.Vpn.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Override
    public User getConnection(int userId, String countryName) throws Exception {
        System.out.println("calling getConnection method with userId and countryName: " + userId + " and " + countryName);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        if (user.getMaskedIp() != null) {
            throw new Exception("Already connected");
        }

        Country originalCountry = user.getOriginalCountry();
        if (originalCountry != null && countryName.equalsIgnoreCase(originalCountry.getCountryName())) {
            return user;
        }

        if (user.getServiceProviderList() == null || user.getServiceProviderList().isEmpty()) {
            throw new Exception("No service providers available for the user");
        }

        //ServiceProvider serviceProviderWithLowestId = null;
        List<ServiceProvider> serviceProviderList = user.getServiceProviderList();
        ServiceProvider serviceProviderWithLowestId = null;
        int lowestId = Integer.MAX_VALUE;
        Country targetCountry = null;

        for (ServiceProvider serviceProvider : user.getServiceProviderList()) {
            for (Country country : serviceProvider.getCountryList()) {
                if (countryName.equalsIgnoreCase(country.getCountryName()) && serviceProvider.getId() < lowestId) {
                    lowestId = serviceProvider.getId();
                    serviceProviderWithLowestId = serviceProvider;
                    targetCountry = country;
                }
            }
            System.out.println("serviceProvider ->      "+serviceProvider);
        }
        System.out.println(serviceProviderWithLowestId+"    <- this is serviceProviderWithLowestId");
        if (serviceProviderWithLowestId != null) {
            Connection connection = new Connection();
            connection.setUser(user);
            connection.setServiceProvider(serviceProviderWithLowestId);
            user.setMaskedIp(targetCountry.getCountryName() + "." + serviceProviderWithLowestId.getId() + "." + userId);
            user.setConnection(true);
            user.getConnectionList().add(connection);

            serviceProviderWithLowestId.getConnectionList().add(connection);
            userRepository.save(user);
            serviceProviderRepository.save(serviceProviderWithLowestId);
        } else {
            throw new Exception("Unable to connect");
        }

        return user;
    }

    @Override
    public User disconnect(int userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        if (!user.getConnection()) {
            throw new Exception("Already disconnected");
        }

        user.setMaskedIp(null);
        user.setConnection(false);
        userRepository.save(user);
        return user;
    }

    @Override
    public User communicate(int senderId, int receiverId) throws Exception {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new Exception("Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new Exception("Receiver not found"));

        if (receiver.getMaskedIp() != null) {
            String maskedIp = receiver.getMaskedIp();
            String code = maskedIp.substring(0, 3).toUpperCase();
            if (code.equals(sender.getOriginalCountry().getCountryName())) return sender;

            String countryName = getCountryNameByCode(code);

            sender = getConnection(senderId, countryName);

            if (!sender.getConnection()) {
                throw new Exception("Cannot establish communication");
            }
            return sender;
        }

        if (sender.getOriginalCountry().equals(receiver.getOriginalCountry())) {
            return sender;
        }

        String countryName = receiver.getOriginalCountry().getCountryName();
        sender = getConnection(senderId, countryName);

        if (!sender.getConnection()) {
            throw new Exception("Cannot establish communication");
        }
        return sender;
    }

    private String getCountryNameByCode(String code) throws Exception {
        for (CountryName countryName : CountryName.values()) {
            if (countryName.toCode().equals(code)) {
                return countryName.name();
            }
        }
        throw new Exception("Country code not recognized");
    }
}
