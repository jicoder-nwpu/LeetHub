package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.ProToUserMapper;
import com.jicoder.leethub.pojo.ProToUser;
import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ProToUserService;
import com.jicoder.leethub.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProToUserServiceImp implements ProToUserService {

    @Autowired
    private ProToUserMapper proToUserMapper;

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
}
