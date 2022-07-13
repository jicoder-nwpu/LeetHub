package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.LeetRank;
import com.jicoder.leethub.pojo.LineChartData;
import com.jicoder.leethub.pojo.User;

import java.util.List;

public interface LeetRankService {

    int insertRank(LeetRank rank);

    List<LeetRank> getRecentRank(User user, int type, int len);

    List<String> getRecentDates(List<LeetRank> ranks);

    List<LeetRank> getAllRankByType(int user_id, int type);

    List<LeetRank> getAllRank(int user_id);

    List<Integer> getRecentVals(List<LeetRank> ranks);

    LineChartData getCharData(User user, int type, int len);

    int selectLatestRank(int user_id, int type);

    List<String> getDailyRankTableHeads();

    List<String> getContestRankTableHeads();

    List<String> getAllRankTableHeads();

}
