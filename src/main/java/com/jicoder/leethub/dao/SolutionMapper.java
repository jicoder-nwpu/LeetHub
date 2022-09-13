package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.Solution;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface SolutionMapper {

    @Insert("insert into solution (title, context, update_time, type, user_id, problem_id) " +
            "values (#{title}, #{context}, #{update_time}, #{type}, #{user.user_id}, #{problem.problem_id})")
    @Options(useGeneratedKeys = true, keyProperty = "solution_id", keyColumn = "solution_id")
    int insert(Solution solution);

    @Update("update solution set title=#{title}, context=#{context}, update_time=#{update_time}, type=#{type}" +
            " where user_id=#{user.user_id} and problem_id=#{problem.problem_id}")
    int update(Solution solution);

    @Select("select * from solution where user_id=#{user_id} and problem_id=#{problem_id}")
    Solution getByPidAndUid(int problem_id, int user_id);

    @Select("select s.*, u.*, p.title as ptitle, p.difficulty, p.url from solution s, user u, problem p where s.solution_id=#{solution_id} and s.user_id = u.user_id and s.problem_id = p.problem_id")
    @Results(value = {
            @Result(column = "username", property = "user.username"),
            @Result(column = "ptitle", property = "problem.title"),
            @Result(column = "difficulty", property = "problem.difficulty"),
            @Result(column = "url", property = "problem.url"),
            @Result(column = "problem_id", property = "problem.problem_id")
    })
    Solution getById(int solution_id);

    @Select("select s.*, p.title as ptitle, p.difficulty, p.url from solution s, problem p where user_id=#{user_id} and s.problem_id = p.problem_id order by update_time desc")
    @Results(value = {
            @Result(column = "ptitle", property = "problem.title"),
            @Result(column = "difficulty", property = "problem.difficulty"),
            @Result(column = "url", property = "problem.url"),
            @Result(column = "problem_id", property = "problem.problem_id")
    })
    List<Solution> getAllByUid(int user_id);

    @Select("select s.*, p.title as ptitle, p.difficulty from solution s, problem p where s.type=#{type} and user_id=#{user_id} and s.problem_id = p.problem_id and s.solution_id != #{solution_id} order by update_time desc limit #{count}")
    @Results(value = {
            @Result(column = "ptitle", property = "problem.title"),
            @Result(column = "difficulty", property = "problem.difficulty")
    })
    List<Solution> getLatestByUid(int user_id, int solution_id, int count, int type);

}
