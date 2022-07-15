package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.Problem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;

@Mapper
public interface ProblemMapper {

    @Insert("insert into problem (title, questionId, type, difficulty, url, content, date)" +
            " values (#{title}, #{questionId}, #{type}, #{difficulty}, #{url}, #{content}, #{date})")
    @Options(useGeneratedKeys = true, keyProperty = "problem_id", keyColumn = "problem_id")
    int insertProblem(Problem problem);

    @Select("select * from problem where questionId=#{questionId}")
    Problem selectProblemByQuestionId(String questionId);

    @Select("select * from problem where problem_id=#{id}")
    Problem selectProblemById(int id);

    @Select("select * from problem where type=" + Problem.DAILYPROBLEM + " and date=#{date}")
    Problem getDailyProblemByDate(Date date);

}
