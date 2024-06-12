package com.personal.Vpn.controller;

import com.personal.Vpn.Model.Admin;
import com.personal.Vpn.Model.ServiceProvider;
import com.personal.Vpn.services.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;

    @PostMapping("/ragister")
    public HttpEntity<Void> ragisterAdmin(@RequestParam String username, @RequestParam String password) {
    Admin admin = adminService.ragister(username,password);
    return new ResponseEntity<>(HttpStatus.OK);

    }
    @PostMapping("/addProvider")
    public ResponseEntity<Void> addserviceProvider(@RequestParam int adminId , @RequestParam String providerName){
        Admin admin = adminService.addServiceProvider(adminId,providerName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/addCountry")
    public ResponseEntity<Void> addCountry(@RequestParam int serviceProviderId,@RequestParam String providerName) throws  Exception
    {
      ServiceProvider serviceProvider = adminService.addCountry(serviceProviderId, providerName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

