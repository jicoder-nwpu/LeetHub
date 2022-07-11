package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.LeetRankMapper;
import com.jicoder.leethub.pojo.LeetRank;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.LeetRankService;
import com.jicoder.leethub.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeetRankServiceImp implements LeetRankService {

    @Autowired
    private LeetRankMapper leetRankMapper;

    @Override
    public int insertRank(LeetRank rank) {
        return leetRankMapper.insertRank(rank);
    }

    @Override
    public List<LeetRank> getRecentRank(User user, int type, int len) {
        List<LeetRank> res = leetRankMapper.getRecentRank(user, type, len);
        Utils.reverseList(res);
        return res;
    }

    @Override
    public List<Date> getRecentDates(List<LeetRank> ranks) {
        List<Date> dates = new ArrayList<>();
        for(int i = 0; i < ranks.size(); i++){
            dates.add(ranks.get(i).getUpdate_time());
        }
        return dates;
    }

    @Override
    public List<LeetRank> getAllRank(int type) {
        return leetRankMapper.selectAllRank(type);
    }

    @Override
    public List<Integer> getRecentVals(List<LeetRank> ranks) {
        List<Integer> vals = new ArrayList<>();
        for (int i = 0; i < ranks.size(); i++){
            vals.add(ranks.get(i).getRank_val());
        }
        return vals;
    }

    @Override
    public int selectLatestRank(int type) {
        Integer res = leetRankMapper.selectLatestRank(type);
        if(res == null){
            return -1;
        }
        return res;
    }
}
