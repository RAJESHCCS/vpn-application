package com.personal.Vpn.controller;

import com.personal.Vpn.Model.CountryName;
import com.personal.Vpn.Model.User;
import com.personal.Vpn.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
    UserServiceImpl userService;

    @PostMapping("/ragister")
    public HttpEntity<Void> Ragisteruser(@RequestParam String username, @RequestParam String password,@RequestParam String countryName) throws  Exception{
        User user  = userService.register(username,password,countryName);
        return new ResponseEntity(HttpStatus.OK);

}
    @PostMapping("/subscribe")
    public HttpEntity<Void> subscribe(@RequestParam int userId, @RequestParam int serviceProviderId){
        User user = userService.subscribe(userId,serviceProviderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

