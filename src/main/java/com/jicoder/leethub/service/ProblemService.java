package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.Problem;

import java.sql.Date;

public interface ProblemService {

    int insertProblem(Problem problem);

    Problem getDailyProblem(Date date);

}