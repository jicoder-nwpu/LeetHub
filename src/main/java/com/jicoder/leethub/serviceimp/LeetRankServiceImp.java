package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.LeetRankMapper;
import com.jicoder.leethub.pojo.LeetRank;
import com.jicoder.leethub.service.LeetRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeetRankServiceImp implements LeetRankService {

    @Autowired
    private LeetRankMapper leetRankMapper;

    @Override
    public int insertRank(LeetRank rank) {
        return leetRankMapper.insertRank(rank);
    }
}
