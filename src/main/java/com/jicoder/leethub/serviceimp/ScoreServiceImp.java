package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.ScoreMapper;
import com.jicoder.leethub.pojo.Score;
import com.jicoder.leethub.service.ScoreService;
import com.jicoder.leethub.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImp implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public int insert(Score score) {
        return scoreMapper.insert(score);
    }

    @Override
    public int getCurScore(int user_id) {
        Integer res = scoreMapper.selectCurScore(user_id);
        if(res == null){
            return -1;
        }
        return res;
    }

    @Override
    public List<Score> getRecentScore(int user_id) {
        List<Score> scores = scoreMapper.selectRecentScore(user_id);
        Utils.reverseList(scores);
        return scores;
    }

    @Override
    public List<Integer> getScoreVals(List<Score> scores) {
        List<Integer> vals = new ArrayList<>();
        for(int i = 0; i < scores.size(); i++){
            vals.add(scores.get(i).getScore_val());
        }
        return vals;
    }

    @Override
    public List<String> getDates(List<Score> scores) {
        List<String> dates = new ArrayList<>();
        for(int i = 0; i < scores.size(); i++){
            dates.add(scores.get(i).getUpdate_time().toString().split(" ")[0]);
        }
        return dates;
    }

    @Override
    public List<String> getRankTableHeads() {
        List<String> heads = new ArrayList<>();
        heads.add("Date");
        heads.add("Description");
        heads.add("Score");
        heads.add("Gap");
        return heads;
    }

    @Override
    public List<Score> getAllScore(int user_id) {
        return scoreMapper.selectAllScore(user_id);
    }

}
