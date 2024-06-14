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
    UserRepository userRepository;
    @Autowired
    ConnectionRepository connectionRepository;
    @Autowired
    ServiceProviderRepository serviceProviderRepository;
    @Override
    public User getConnection(int userId, String countryName) throws Exception {

//        User user = userRepository.findById(userId).get();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        if (user.getMaskedIp() != null){
            throw new Exception("Already connected");
        }

        if (countryName.equalsIgnoreCase(user.getOriginalCountry().getCountryName().toString())){
            return user;
        }

        if (user.getServiceProviderList() == null){
            throw new Exception("Unable to connect");
        }

        List<ServiceProvider> serviceProviderList = user.getServiceProviderList();
        ServiceProvider serviceProviderWithLowestId = null;
        int lowestId = Integer.MAX_VALUE;
        Country country = null;
        for (ServiceProvider serviceProvider : serviceProviderList){
            List<Country> countryList = serviceProvider.getCountryList();
            for (Country country1 : countryList){
                if (countryName.equalsIgnoreCase(country1.getCountryName().toString()) && lowestId>serviceProvider.getId()){
                    lowestId = serviceProvider.getId();
                    serviceProviderWithLowestId = serviceProvider;
                    country = country1;
                }
            }
        }

        if (serviceProviderWithLowestId != null){
            Connection connection = new Connection();
            connection.setUser(user);
            connection.setServiceProvider(serviceProviderWithLowestId);
            user.setMaskedIp(country.getGetCountryCode() + "." + serviceProviderWithLowestId.getId() + "." + userId);
            user.setConnection(true);
            user.getConnectionList().add(connection);

            serviceProviderWithLowestId.getConnectionList().add(connection);
            userRepository.save(user);
            serviceProviderRepository.save(serviceProviderWithLowestId);
        }
        else{
            throw new Exception("Unable to connect");
        }
        return user;
    }


//        return null;
//    }

    @Override
    public User disconnect(int userId) throws Exception {

        //If the given user was not connected to a vpn, throw "Already disconnected" exception.
        //Else, disconnect from vpn, make masked Ip as null, update relevant attributes and return updated user.
        User user = userRepository.findById(userId).get();
        if (!user.getConnection()){
            throw new Exception("Already disconnected");
        }
        user.setMaskedIp(null);
        user.setConnection(false);
        userRepository.save(user);
        return user;

    }
    @Override
    public User communicate(int senderId, int receiverId) throws Exception {

        User sender = userRepository.findById(senderId).get();
        User receiver = userRepository.findById(receiverId).get();
        if (receiver.getMaskedIp()!=null){
            String maskedIp = receiver.getMaskedIp();
            String code = maskedIp.substring(0,3);
            code = code.toUpperCase();
            if (code.equals(sender.getOriginalCountry().getGetCountryCode())) return sender;
            String countryName = "";
            CountryName[] countryNames = CountryName.values();
            for(CountryName countryName1 : countryNames){
                if (countryName1.toCode().toString().equals(code)){
                    countryName = countryName1.toString();
                }
            }
            try {
                sender = getConnection(senderId,countryName);
            }catch (Exception e){
                throw new Exception("Cannot establish communication");
            }
            if (!sender.getConnection()){
                throw new Exception("Cannot establish communication");
            }
            return sender;
        }
        if (sender.getOriginalCountry().equals(receiver.getOriginalCountry())){
            return sender;
        }
        String countryName = receiver.getOriginalCountry().getCountryName().toString();
        try {
            sender = getConnection(senderId,countryName);
        }catch (Exception e){
            if (!sender.getConnection()) throw new Exception("Cannot establish communication");
        }
        return sender;
    }
}
