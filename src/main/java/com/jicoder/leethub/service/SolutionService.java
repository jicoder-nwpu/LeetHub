package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.Solution;

public interface SolutionService {

    int insert(Solution solution);

    int update(Solution solution);

    Solution getByPidAndUid(int problem_id, int user_id);

    Solution getById(int solution_id);

}
