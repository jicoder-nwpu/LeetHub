package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.LeetRank;
import com.jicoder.leethub.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LeetRankMapper {

    @Insert("insert into leetrank (rank_val, type, update_time, user_id, gap) " +
            "values (#{rank_val}, #{type}, #{update_time}, #{user.user_id}, #{gap})")
    int insertRank(LeetRank rank);

    @Select("select * from leetrank where type=#{type} and user_id=#{user.user_id} order by rank_id desc limit #{len}")
    List<LeetRank> getRecentRank(User user, int type, int len);


    @Select("select rank_val from leetrank where user_id=#{user_id} and type=#{type} order by rank_id desc limit 1")
    Integer selectLatestRank(int user_id, int type);


    @Select("select * from leetrank where user_id=#{user_id} and type=#{type} order by rank_id desc")
    List<LeetRank> selectAllRankByType(int user_id, int type);


    @Select("select * from leetrank where user_id=#{user_id} order by rank_id desc")
    List<LeetRank> selectAllRank(int user_id);

}
