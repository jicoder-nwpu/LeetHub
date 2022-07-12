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
        Problem res = problemMapper.selectProblemByQuestionId(problem.getQuestionId());
        if(res != null){
            return res.getProblem_id();
        }
        int status = problemMapper.insertProblem(problem);
        return problem.getProblem_id();
    }

    @Override
    public Problem getDailyProblem() {
        Date today = new Date(System.currentTimeMillis());
        return problemMapper.getDailyProblemByDate(today);
    }

}
