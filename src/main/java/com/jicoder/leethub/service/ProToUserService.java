package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.ProToUser;
import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.User;

import java.sql.Timestamp;

public interface ProToUserService {

    int insertPU(ProToUser proToUser);

    boolean hasDailyProblemRecord(Problem problem, User user);

}
