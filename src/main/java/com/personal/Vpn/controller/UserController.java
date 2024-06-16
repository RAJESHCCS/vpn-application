package com.personal.Vpn.controller;

import com.personal.Vpn.Model.User;
import com.personal.Vpn.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String countryName) throws Exception {
        System.out.println(username + " is username, " + password + " is password, and countryName is " + countryName);
        User user = userService.register(username, password, countryName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(@RequestParam int userId, @RequestParam int serviceProviderId) throws Exception {
        User user = userService.subscribe(userId, serviceProviderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
