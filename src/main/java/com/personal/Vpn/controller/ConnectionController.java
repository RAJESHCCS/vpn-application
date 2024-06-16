package com.personal.Vpn.controller;

import com.personal.Vpn.Model.User;
import com.personal.Vpn.services.impl.ConnectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connection")
public class ConnectionController {

    @Autowired
    private ConnectionServiceImpl connectionService;

    @PostMapping("/getconnect")
    public ResponseEntity<Void> setConnection(@RequestParam int userId, @RequestParam String countryName) throws Exception {
        connectionService.getConnection(userId, countryName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/communicate")
    public ResponseEntity<Void> communicate(@RequestParam int serverId, @RequestParam int receiverId) throws Exception {
        connectionService.communicate(serverId, receiverId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/disconnect")
    public ResponseEntity<Void> disconnect(@RequestParam int userId) throws Exception {
        connectionService.disconnect(userId);
        return ResponseEntity.ok().build();
    }
}
