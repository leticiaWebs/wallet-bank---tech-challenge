package com.walletbank.wbcontadigital.services;

import com.walletbank.wbcontadigital.entities.User;
import com.walletbank.wbcontadigital.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public List<User> findAll() {
        return null;
    }
}
