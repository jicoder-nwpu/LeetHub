package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.ProToUser;
import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.Record;
import com.jicoder.leethub.pojo.User;

import java.sql.Timestamp;
import java.util.List;

public interface ProToUserService {

    int insertPU(ProToUser proToUser);

    boolean hasDailyProblemRecord(Problem problem, User user);

    List<ProToUser> getAllByUser(int user_id);

    List<Record> getAllRecord(List<ProToUser> list, int user_id);

}
