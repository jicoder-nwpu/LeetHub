package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from User where user_id=#{user_id}")
    User getUserById(int user_id);

}
