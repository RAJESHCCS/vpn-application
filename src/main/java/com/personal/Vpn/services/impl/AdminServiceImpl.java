package com.personal.Vpn.services.impl;

import com.personal.Vpn.Model.Admin;
import com.personal.Vpn.Model.Country;
import com.personal.Vpn.Model.CountryName;
import com.personal.Vpn.Model.ServiceProvider;
import com.personal.Vpn.repository.AdminRepository;
import com.personal.Vpn.repository.CountryRepository;
import com.personal.Vpn.repository.ServiceProviderRepository;
import com.personal.Vpn.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ServiceProviderRepository serviceProviderRepository;
    @Autowired
    CountryRepository countryRepository;
    @Override
    public Admin ragister(String username, String password){
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        adminRepository.save(admin);
        return admin;
        // try , if it is possible to save direct username , pwd in repo

    }
    @Override
    public  Admin addServiceProvider(int adminId,String providerName){
        Admin admin = adminRepository.findById(adminId).get();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName(providerName);
        serviceProvider.setAdmin(admin);
        List<ServiceProvider> serviceProvidersList = admin.getServiceProviders();
        serviceProvidersList.add(serviceProvider);
        admin.setServiceProviders(serviceProvidersList);
        adminRepository.save(admin);
        return admin;
    }
    @Override
    public ServiceProvider addCountry(int serviceProviderId,String countryName) throws  Exception {
        String countryName1 = countryName.toUpperCase();
        if (!countryName1.equals("IND") && !countryName1.equals("CHI") && !countryName1.equals("USA") && !countryName1.equals("JPN"))
            throw new Exception("country not found");
        ServiceProvider serviceProvider = serviceProviderRepository.findById(serviceProviderId).get();
        Country country = new Country(CountryName.valueOf(countryName1),CountryName.valueOf(countryName1).toCode());
        country.setServiceProvider(serviceProvider);
        serviceProvider.getCountryList().add(country);
        serviceProviderRepository.save(serviceProvider);
        return serviceProvider;
    }
}
