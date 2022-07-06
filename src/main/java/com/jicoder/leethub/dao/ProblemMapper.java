package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.Problem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;

@Mapper
public interface ProblemMapper {

    @Insert("insert into problem (title, questionId, type, difficulty, url, content, date)" +
            " values (#{title}, #{questionId}, #{type}, #{difficulty}, #{url}, #{content}, #{date})")
    int insertProblem(Problem problem);

    @Select("select * from problem where type=" + Problem.DAILYPROBLEM + " and date=#{date}")
    Problem getDailyProblemByDate(Date date);

}
