package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.ProblemMapper;
import com.jicoder.leethub.dao.SolutionMapper;
import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemServiceImp implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private SolutionMapper solutionMapper;

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

    @Override
    public Problem getProblemById(int id) {
        return problemMapper.selectProblemById(id);
    }

    @Override
    public List<Problem> getSolutionProblems(List<Problem> problems, User user) {
        List<Problem> ans = new ArrayList<>();
        for(Problem p : problems){
            if(solutionMapper.getByPidAndUid(p.getProblem_id(), user.getUser_id()) != null){
                ans.add(p);
            }
        }
        return ans;
    }

    @Override
    public List<Problem> getNoSolutionProblems(List<Problem> problems, User user) {
        List<Problem> ans = new ArrayList<>();
        for(Problem p : problems){
            if(solutionMapper.getByPidAndUid(p.getProblem_id(), user.getUser_id()) == null){
                ans.add(p);
            }
        }
        return ans;
    }

}
