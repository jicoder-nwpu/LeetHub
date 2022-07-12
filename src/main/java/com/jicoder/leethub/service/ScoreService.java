package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.Score;

import java.util.List;

public interface ScoreService {

    int insert(Score score);

    int getCurScore(int user_id);

    List<Score> getRecentScore(int user_id);

    List<Integer> getScoreVals(List<Score> scores);

    List<String> getDates(List<Score> scores);

    List<String> getRankTableHeads();

    List<Score> getAllScore(int user_id);

}
