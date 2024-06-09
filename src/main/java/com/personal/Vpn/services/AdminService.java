package com.personal.Vpn.services;

import com.personal.Vpn.Model.Admin;
import com.personal.Vpn.Model.ServiceProvider;

public interface AdminService {

    public Admin ragister();
    public Admin addServiceProvider();
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception;

}
