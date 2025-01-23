package com.banking.service;

import com.banking.entity.User;
import com.banking.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    public void printUsers(){
        userRepository.printUsers();
    }
    public User login(String username, String password){
        return userRepository.login(username, password);
    }
    public boolean createUser(String username, String password, String contact){
       return userRepository.createUser(username, password, contact);
    }
    public Double checkBankBalance(String userId){
        return userRepository.checkBankBalance(userId);
    }
}
