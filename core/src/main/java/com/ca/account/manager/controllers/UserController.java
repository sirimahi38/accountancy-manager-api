package com.ca.account.manager.controllers;

import com.ca.account.manager.billing.enity.Billing;
import com.ca.account.manager.billing.service.BillingService;
import com.ca.account.manager.entity.User;
import com.ca.account.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @RequestMapping("/userdetails/")
    public String insertUsers() {
        User user = new User();
//        user.setId();
        user.setUserName("INDIRA");
        user.setUserRole("Admin");
        user.setUser_access("grantedd");
        User user1 = userService.saveUser(user);

        return ("Bills"+ userService.saveUser(user)+ user1.getUser_access() +
                user1.getUserRole() +
                user1.getUserassgined() + " has been inserted successfully");
    }
    @GetMapping("/userssList/")
    public List<User> getUserssList() {

        return userService.findAll();
    }


}