package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.User;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

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

    @Select("select * from user where username=#{username}")
    User getUserByName(String username);

    @Insert("insert into user (username, password, signup_time, dailyp_count, email)" +
            " values (#{username}, #{password}, #{signup_time}, #{dailyp_count}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "user_id", keyColumn = "user_id")
    int insertUser(User user);

    @Select("select user_id, username, email from user")
    List<User> selectAllUser();

}
