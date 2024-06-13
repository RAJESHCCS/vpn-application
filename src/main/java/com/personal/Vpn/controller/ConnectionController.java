package com.personal.Vpn.controller;

import com.personal.Vpn.Model.Connection;
import com.personal.Vpn.Model.CountryName;
import com.personal.Vpn.Model.ServiceProvider;
import com.personal.Vpn.Model.User;
import com.personal.Vpn.services.impl.ConnectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connection")
public class ConnectionController {

@Autowired
    ConnectionServiceImpl connectionService;

    @PostMapping("/getconnect")
    public HttpEntity<Void> setConnection(@RequestParam int userId, @RequestParam String countryName) throws Exception {
        User user = connectionService.getConnection(userId, countryName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/communicate")
    public HttpEntity<Void> communicate(@RequestParam int serverId,int recieverId) throws  Exception{
        User user = connectionService.communicate(serverId,recieverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @DeleteMapping("/disconnect")
//    public HttpEntity<Void> disconnected(@RequestParam int userId) throws  Exception{
//        System.out.println("before calling disconnect method"+userId);
//        User user = connectionService.disconnect(userId);
//        System.out.println("after calling disconnect method");
//        return new ResponseEntity(HttpStatus.OK);

    @DeleteMapping("/disconnect")
    public ResponseEntity<Void> disconnect(@RequestParam int userId) throws Exception{
        //If the given user was not connected to a vpn, throw "Already disconnected" exception.
        //Else, disconnect from vpn, make masked Ip as null, update relevant attributes and return updated user.
        User user = connectionService.disconnect(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

