package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.Experience;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("select * from experience where user_id=#{user_id}")
    List<Experience> getAll(int user_id);

    @Select("select * from experience where user_id=#{user_id} and experience_id!=#{experience_id} and type=1")
    List<Experience> getRecent(int user_id, int experience_id);

    @Select("select * from experience where user_id=#{user_id} and label=#{label}")
    List<Experience> selectByLabel(int user_id, String label);

    @Select("select * from experience where user_id=#{user_id} and title like #{name}")
    List<Experience> selectByTitle(int user_id, String name);

}
