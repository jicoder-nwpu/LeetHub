package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.UserMapper;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(int user_id) {
        return userMapper.getUserById(user_id);
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.getUserByNameAnsPass(username, password);
        return user;
    }

    @Override
    public int updateDailyPCount(int user_id, int count) {
        return userMapper.updateDailyPCount(user_id, count);
    }

    @Override
    public int selectDailyPCount(int user_id) {
        Integer res = userMapper.selectDailyPCount(user_id);
        if(res == null){
            return 1024;
        }
        return res;
    }

    @Override
    public List<String> getRankTableHeads() {
        List<String> heads = new ArrayList<>();
        heads.add("Date");
        heads.add("Type");
        heads.add("Rank");
        heads.add("Gap");
        heads.add("Easy");
        heads.add("Medium");
        heads.add("Hard");
        return heads;
    }
}
