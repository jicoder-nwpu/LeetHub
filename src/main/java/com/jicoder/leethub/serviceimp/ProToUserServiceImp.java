package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.ProToUserMapper;
import com.jicoder.leethub.pojo.*;
import com.jicoder.leethub.service.ProToUserService;
import com.jicoder.leethub.service.SolutionService;
import com.jicoder.leethub.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return proToUserMapper.insertPU(proToUser);
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
            ans.add(new Record(p.getTitle(), p.getDifficulty(), pu.getSubmit_time(), p.getUrl(), p.getProblem_id(), s == null ? null : s.getSolution_id()));
        }
        return ans;
    }
}
