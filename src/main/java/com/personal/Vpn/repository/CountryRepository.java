package com.personal.Vpn.repository;

import com.personal.Vpn.Model.Connection;
import com.personal.Vpn.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository  extends JpaRepository<Country,Integer> {

}
