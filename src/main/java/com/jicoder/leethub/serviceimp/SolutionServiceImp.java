package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.SolutionMapper;
import com.jicoder.leethub.pojo.Solution;
import com.jicoder.leethub.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionServiceImp implements SolutionService {

    @Autowired
    private SolutionMapper solutionMapper;

    @Override
    public int insert(Solution solution) {
        return solutionMapper.insert(solution);
    }

    @Override
    public int update(Solution solution) {
        return solutionMapper.update(solution);
    }

    @Override
    public Solution getByPidAndUid(int problem_id, int user_id) {
        return solutionMapper.getByPidAndUid(problem_id, user_id);
    }

    @Override
    public Solution getById(int solution_id) {
        return solutionMapper.getById(solution_id);
    }
}
