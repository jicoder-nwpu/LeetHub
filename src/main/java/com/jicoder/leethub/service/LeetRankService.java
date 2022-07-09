package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.LeetRank;
import com.jicoder.leethub.pojo.User;

import java.sql.Date;
import java.util.List;

public interface LeetRankService {

    int insertRank(LeetRank rank);

    List<LeetRank> getRecentRank(User user, int len);

    List<Date> getRecentDates(List<LeetRank> ranks);

    List<Integer> getRecentVals(List<LeetRank> ranks);

}
