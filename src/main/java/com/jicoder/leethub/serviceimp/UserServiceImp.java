package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.UserMapper;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(int user_id) {
        return userMapper.getUserById(user_id);
    }
}
