package com.ca.account.manager.service;

import com.ca.account.manager.entity.User;
import com.ca.account.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ca.account.manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
         return userRepository.save( user);
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public User updateUser(User user) {
        return userRepository.save( user);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

}