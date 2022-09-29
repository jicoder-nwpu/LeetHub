package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.ProToUser;
import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.User;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ProToUserMapper {

    @Insert("insert into protouser (user_id, problem_id, submit_time) " +
            "values (#{user.user_id}, #{problem.problem_id}, #{submit_time})")
    int insertPU(ProToUser proToUser);

    @Select("select * from protouser where user_id=#{user.user_id} and problem_id=#{problem.problem_id}" +
            " order by submit_time desc limit 1")
    ProToUser selectPUByUserAndProblem(Problem problem, User user);

    @Select("select pu.*, p.* from protouser pu, problem p where pu.problem_id=p.problem_id and user_id=#{user_id} order by submit_time desc")
    @Results({
            @Result(column = "problem_id", property = "problem.problem_id"),
            @Result(column = "title", property = "problem.title"),
            @Result(column = "url", property = "problem.url"),
            @Result(column = "difficulty", property = "problem.difficulty")
    })
    List<ProToUser> selectAllByUser(int user_id);

    @Select("select alias from protouser where user_id=#{user_id} and problem_id=#{problem_id}")
    String selectAliasById(int user_id, int problem_id);

    @Update("update protouser set alias=#{alias} where user_id=#{user_id} and problem_id=#{problem_id}")
    int updateAlias(int user_id, int problem_id, String alias);

    @Update("update protouser set submit_time=#{submit_time} where user_id=#{user.user_id} and problem_id=#{problem.problem_id}")
    int update(ProToUser pu);

    @Select("select * from protouser where user_id=#{user_id} and problem_id=#{problem_id} and submit_time >= #{submit_time}")
    ProToUser selectPUToday(int user_id, int problem_id, Timestamp submit_time);

    @Select("select p.* from protouser pu, problem p where p.problem_id=pu.problem_id and pu.user_id=#{user_id} and submit_time>=#{start_time} and submit_time<#{end_time}")
    List<Problem> selectByDay(int user_id, Timestamp start_time, Timestamp end_time);

}
