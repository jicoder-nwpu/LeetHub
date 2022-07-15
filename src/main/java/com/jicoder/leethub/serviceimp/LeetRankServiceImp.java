package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.LeetRankMapper;
import com.jicoder.leethub.pojo.LeetRank;
import com.jicoder.leethub.pojo.LineChartData;
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
    public List<String> getRecentDates(List<LeetRank> ranks) {
        List<String> dates = new ArrayList<>();
        for(int i = 0; i < ranks.size(); i++){
            dates.add(ranks.get(i).getUpdate_time().toString());
        }
        return dates;
    }

    @Override
    public List<LeetRank> getAllRankByType(int user_id, int type) {
        return leetRankMapper.selectAllRankByType(user_id, type);
    }

    @Override
    public List<LeetRank> getAllRank(int user_id) {
        return leetRankMapper.selectAllRank(user_id);
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
    public int selectLatestRank(int user_id, int type) {
        Integer res = leetRankMapper.selectLatestRank(user_id, type);
        if(res == null){
            return -1;
        }
        return res;
    }


    public LineChartData getCharData(User user, int type, int len){
        List<LeetRank> ranks = getRecentRank(user, type, len);
        List<String> rank_dates = getRecentDates(ranks);
        List<Integer> rank_vals = getRecentVals(ranks);
        LineChartData rankData = new LineChartData(rank_dates, rank_vals);
        return rankData;
    }

    @Override
    public List<String> getDailyRankTableHeads() {
        List<String> heads = new ArrayList<>();
        heads.add("Date");
        heads.add("Type");
        heads.add("Rank");
        heads.add("Gap");
        heads.add("Easy");
        heads.add("Medium");
        heads.add("Hard");
        return heads;
    }

    @Override
    public List<String> getContestRankTableHeads() {
        List<String> heads = new ArrayList<>();
        heads.add("Date");
        heads.add("Type");
        heads.add("Description");
        heads.add("ContestRank");
        heads.add("Score");
        heads.add("Rank");
        heads.add("Gap");
        return heads;
    }

    @Override
    public LineChartData getTodaySummay(int user_id) {
        List<String> labels = new ArrayList<>();
        labels.add("Easy");
        labels.add("Medium");
        labels.add("Hard");
        Date update = new Date(new java.util.Date().getTime());
        int easy = selectEasyCount(update, user_id);
        int medium = selectMediumCount(update, user_id);
        int hard = selectHardCount(update, user_id);
        List<Integer> data = new ArrayList<>();
        data.add(easy);
        data.add(medium);
        data.add(hard);
        return new LineChartData(labels, data);
    }

    @Override
    public List<String> getAllRankTableHeads() {
        List<String> heads = new ArrayList<>();
        heads.add("Date");
        heads.add("Type");
        heads.add("Description");
        heads.add("Rank");
        heads.add("Gap");
        return heads;
    }

    @Override
    public List<String> geDailySummaryRankTableHeads() {
        List<String> heads = new ArrayList<>();
        heads.add("Date");
        heads.add("Easy");
        heads.add("Medium");
        heads.add("Hard");
        return heads;
    }

    @Override
    public int selectEasyCount(Date update_time, int user_id) {
        Integer res = leetRankMapper.selectEasyLeetRank(update_time, user_id);
        return res == null ? 0 : res;
    }

    @Override
    public int selectMediumCount(Date update_time, int user_id) {
        Integer res = leetRankMapper.selectMediumLeetRank(update_time, user_id);
        return res == null ? 0 : res;
    }

    @Override
    public int selectHardCount(Date update_time, int user_id) {
        Integer res = leetRankMapper.selectHardLeetRank(update_time, user_id);
        return res == null ? 0 : res;
    }

    @Override
    public int updateEasyCount(int easy_count, Date update_time, int user_id) {
        return leetRankMapper.updateEasyLeetRank(easy_count, update_time, user_id);
    }

    @Override
    public int updateMediumCount(int medium_count, Date update_time, int user_id) {
        return leetRankMapper.updateMediumLeetRank(medium_count, update_time, user_id);
    }

    @Override
    public int updateHardCount(int hard_count, Date update_time, int user_id) {
        return leetRankMapper.updateHardLeetRank(hard_count, update_time, user_id);
    }
}
