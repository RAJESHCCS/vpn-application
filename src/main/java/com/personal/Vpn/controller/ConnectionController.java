package com.personal.Vpn.controller;

import com.personal.Vpn.Model.Connection;
import com.personal.Vpn.Model.ServiceProvider;
import com.personal.Vpn.Model.User;
import com.personal.Vpn.services.impl.ConnectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connection")
public class ConnectionController {

@Autowired
    ConnectionServiceImpl connectionService;
@PostMapping("/getconnect")
public HttpEntity<Connection> setConnection(@RequestParam User userId, @RequestParam ServiceProvider serviceProvider){
    Connection connection = connectionService.getConnection(userId,serviceProvider);
    return new ResponseEntity<>(HttpStatus.OK);
}

}

