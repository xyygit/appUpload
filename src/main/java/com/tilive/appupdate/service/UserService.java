package com.tilive.appupdate.service;

import com.tilive.appupdate.bean.User;

import java.util.List;

public interface UserService {
    /**
     * 保存用户对象
     *
     * @param user
     */
    void save(User user);

    /**
     * 获取所有用户对象
     *
     * @return
     */
    List<User> getUserList();

}
