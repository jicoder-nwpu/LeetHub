package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreMapper {

    @Insert("insert into score (score_val, description, update_time, user_id, gap) " +
            "values (#{score_val}, #{description}, #{update_time}, #{user.user_id}, #{gap})")
    int insert(Score score);

    @Select("select score_val from score where user_id=#{user_id} order by score_id desc limit 1")
    Integer selectCurScore(int user_id);

    @Select("select * from score where user_id=#{user_id} order by score_id desc limit " + Score.IndexScoreNum)
    List<Score> selectRecentScore(int user_id);

    @Select("select * from score where user_id=#{user_id} order by score_id desc")
    List<Score> selectAllScore(int user_id);

}
