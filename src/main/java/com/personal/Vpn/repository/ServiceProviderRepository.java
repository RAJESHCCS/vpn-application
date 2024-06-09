package com.personal.Vpn.repository;

import com.personal.Vpn.Model.Admin;
import com.personal.Vpn.Model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider,Integer> {

}
