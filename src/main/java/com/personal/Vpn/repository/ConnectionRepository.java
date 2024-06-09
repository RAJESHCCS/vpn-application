package com.personal.Vpn.repository;

import com.personal.Vpn.Model.Connection;
import com.personal.Vpn.Model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection,Integer> {
}
