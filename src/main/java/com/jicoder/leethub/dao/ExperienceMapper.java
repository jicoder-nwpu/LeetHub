package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.Experience;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ExperienceMapper {

    @Insert("insert into experience (title, content, user_id, label, type) " +
            "values (#{title}, #{content}, #{user.user_id}, #{label}, #{type})")
    @Options(useGeneratedKeys = true, keyProperty = "experience_id", keyColumn = "experience_id")
    int insert(Experience experience);

    @Select("select * from experience where experience_id=#{experience_id}")
    Experience selectById(int experience_id);

    @Update("update experience set title=#{title}, content=#{content}, label=#{label}, type=#{type} where experience_id=#{experience_id}")
    int update(Experience experience);

}
