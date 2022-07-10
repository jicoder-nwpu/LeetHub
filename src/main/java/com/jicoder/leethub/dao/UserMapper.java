package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from User where user_id=#{user_id}")
    User getUserById(int user_id);

    @Select("select dailyp_count from user where user_id=#{user_id}")
    Integer selectDailyPCount(int user_id);

    @Select("select * from user where username=#{username} and password=#{password}")
    User getUserByNameAnsPass(String username, String password);

    @Update("update user set dailyp_count=#{dailyp_count} where user_id=#{user_id}")
    int updateDailyPCount(int user_id, int dailyp_count);

}
