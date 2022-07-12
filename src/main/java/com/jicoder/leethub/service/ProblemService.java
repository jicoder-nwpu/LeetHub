package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.Problem;

public interface ProblemService {

    int insertProblem(Problem problem);

    Problem getDailyProblem();

}
