package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.ProToUserMapper;
import com.jicoder.leethub.pojo.*;
import com.jicoder.leethub.service.ProToUserService;
import com.jicoder.leethub.service.SolutionService;
import com.jicoder.leethub.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProToUserServiceImp implements ProToUserService {

    @Autowired
    private ProToUserMapper proToUserMapper;

    @Autowired
    private SolutionService solutionService;

    @Override
    public int insertPU(ProToUser proToUser) {
        if(proToUserMapper.selectPUByUserAndProblem(proToUser.getProblem(), proToUser.getUser()) != null){
            proToUserMapper.update(proToUser);
            if(proToUserMapper.selectPUToday(proToUser.getUser().getUser_id(), proToUser.getProblem().getProblem_id(), new Timestamp(Utils.getStartOfToday())) != null){
                return 0;
            }
            return 1;
        }
        proToUserMapper.insertPU(proToUser);
        return 1;
    }

    public boolean hasDailyProblemRecord(Problem problem, User user){
        if(problem == null){
            return false;
        }
        ProToUser res = proToUserMapper.selectPUByUserAndProblem(problem, user);
        if(res == null){
            return false;
        }else{
            return Utils.isToday(res.getSubmit_time().getTime());
        }
    }

    @Override
    public List<ProToUser> getAllByUser(int user_id) {
        return proToUserMapper.selectAllByUser(user_id);
    }

    @Override
    public List<Record> getAllRecord(List<ProToUser> list, int user_id) {
        List<Record> ans = new ArrayList<>();
        for(ProToUser pu : list){
            Problem p = pu.getProblem();
            Solution s = solutionService.getByPidAndUid(p.getProblem_id(), user_id);
            if(s == null){
                ans.add(new Record(p.getTitle(), p.getDifficulty(), pu.getSubmit_time(), p.getUrl(), p.getProblem_id(), null, pu.getAlias()));
            }else if(s.getType() == Solution.SOLUTION_DRAFT_TYPE){
                ans.add(new Record(p.getTitle(), p.getDifficulty(), pu.getSubmit_time(), p.getUrl(), p.getProblem_id(), -1, pu.getAlias()));
            }else{
                ans.add(new Record(p.getTitle(), p.getDifficulty(), pu.getSubmit_time(), p.getUrl(), p.getProblem_id(), s.getSolution_id(), pu.getAlias()));
            }
        }
        return ans;
    }

    @Override
    public String selectAliasById(int user_id, int problem_id) {
        return proToUserMapper.selectAliasById(user_id, problem_id);
    }

    @Override
    public int updateAlias(int user_id, int problem_id, String alias) {
        return proToUserMapper.updateAlias(user_id, problem_id, alias);
    }

    @Override
    public List<Problem> getByDay(int user_id, Timestamp start_time, Timestamp end_time) {
        return proToUserMapper.selectByDay(user_id, start_time, end_time);
    }
}
