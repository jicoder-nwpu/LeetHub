package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.User;

import java.util.List;

public interface UserService {

    User getUser(int user_id);

    User login(String username, String password);

    int updateDailyPCount(int user_id, int count);

    int selectDailyPCount(int user_id);

    User getUserByName(String username);

    int inserUser(User user);

}
