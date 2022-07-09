package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.ProToUser;
import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;

@Mapper
public interface ProToUserMapper {

    @Insert("insert into protouser (user_id, problem_id, submit_time) " +
            "values (#{user.user_id}, #{problem.problem_id}, #{submit_time})")
    int insertPU(ProToUser proToUser);

    @Select("select * from protouser where user_id=#{user.user_id} and problem_id=#{problem.problem_id}" +
            " order by submit_time desc limit 1")
    ProToUser selectPUByUserAndProblem(Problem problem, User user);

}
