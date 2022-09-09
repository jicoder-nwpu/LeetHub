package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.Solution;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SolutionMapper {

    @Insert("insert into solution (title, context, update_time, user_id, problem_id) " +
            "values (#{title}, #{context}, #{update_time}, #{user.user_id}, #{problem.problem_id})")
    int insert(Solution solution);

    @Update("update solution set title=#{title}, context=#{context}, update_time=#{update_time}" +
            " where user_id=#{user.user_id} and problem_id=#{problem.problem_id}")
    int update(Solution solution);

    @Select("select * from solution where user_id=#{user_id} and problem_id=#{problem_id}")
    Solution getByPidAndUid(int problem_id, int user_id);

    @Select("select * from solution where solution_id=#{solution_id}")
    Solution getById(int solution_id);

}
