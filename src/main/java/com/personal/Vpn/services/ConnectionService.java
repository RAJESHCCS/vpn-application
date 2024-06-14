package com.personal.Vpn.services;

import com.personal.Vpn.Model.User;

public interface ConnectionService {
    public User getConnection(int userId, String countryName) throws Exception;
    public User disconnect(int userId) throws Exception;
    public User communicate(int senderId,int receivedId) throws Exception;
}


