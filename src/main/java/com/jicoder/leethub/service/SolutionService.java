package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.Solution;

import java.util.List;

public interface SolutionService {

    int insert(Solution solution);

    int update(Solution solution);

    Solution getByPidAndUid(int problem_id, int user_id);

    Solution getById(int solution_id);

    List<Solution> getAllByUid(int user_id);

    List<Solution> getLatestByUid(int user_id, int solution_id, int count);

}
