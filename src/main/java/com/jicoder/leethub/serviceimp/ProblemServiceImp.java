package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.ProblemMapper;
import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ProblemServiceImp implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public int insertProblem(Problem problem) {
        return problemMapper.insertProblem(problem);
    }

    @Override
    public Problem getDailyProblem(Date date) {
        return problemMapper.getDailyProblemByDate(date);
    }

}
