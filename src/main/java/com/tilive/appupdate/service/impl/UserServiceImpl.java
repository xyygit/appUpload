package com.tilive.appupdate.service.impl;

import com.tilive.appupdate.bean.User;
import com.tilive.appupdate.dao.UserDao;
import com.tilive.appupdate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> getUserList() {
        return userDao.findAll();
    }
}
