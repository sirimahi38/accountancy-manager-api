package com.ca.account.manager.service;





import com.ca.account.manager.billing.enity.Billing;
import com.ca.account.manager.entity.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);

    public void deleteUser (User user);

    public User updateUser(User user);
    public User findById(long id);

    public List<User> findAll();
}


