package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.User;

import java.util.List;

public interface ProblemService {

    int insertProblem(Problem problem);

    Problem getDailyProblem();

    Problem getProblemById(int id);

    List<Problem> getSolutionProblems(List<Problem> problems, User user);

    List<Problem> getNoSolutionProblems(List<Problem> problems, User user);

}
