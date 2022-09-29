package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.ProToUser;
import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.Record;
import com.jicoder.leethub.pojo.User;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface ProToUserService {

    int insertPU(ProToUser proToUser);

    boolean hasDailyProblemRecord(Problem problem, User user);

    List<ProToUser> getAllByUser(int user_id);

    List<Record> getAllRecord(List<ProToUser> list, int user_id);

    String selectAliasById(int user_id, int problem_id);

    int updateAlias(int user_id, int problem_id, String alias);

    List<Problem> getByDay(int user_id, Timestamp start_time, Timestamp end_time);

}
