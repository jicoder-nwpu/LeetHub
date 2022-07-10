package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.User;

public interface UserService {

    User getUser(int user_id);

    User login(String username, String password);

    int updateDailyPCount(int user_id, int count);

    int selectDailyPCount(int user_id);

}
