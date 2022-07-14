package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.LeetRank;
import com.jicoder.leethub.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

@Mapper
public interface LeetRankMapper {

    @Insert("insert into leetrank (rank_val, type, update_time, user_id, gap, description, contest_rank, score, easy_count, medium_count, hard_count) " +
            "values (#{rank_val}, #{type}, #{update_time}, #{user.user_id}, #{gap}, #{description}, #{contest_rank}, #{score}, #{easy_count}, #{medium_count}, #{hard_count})")
    int insertRank(LeetRank rank);

    @Select("select * from leetrank where type=#{type} and user_id=#{user.user_id} order by rank_id desc limit #{len}")
    List<LeetRank> getRecentRank(User user, int type, int len);


    @Select("select rank_val from leetrank where user_id=#{user_id} and type=#{type} order by rank_id desc limit 1")
    Integer selectLatestRank(int user_id, int type);


    @Select("select * from leetrank where user_id=#{user_id} and type=#{type} order by rank_id desc")
    List<LeetRank> selectAllRankByType(int user_id, int type);


    @Select("select * from leetrank where user_id=#{user_id} order by rank_id desc")
    List<LeetRank> selectAllRank(int user_id);

    @Select("select easy_count from leetrank where type=" + LeetRank.DailyRank + " and update_time=#{update_time} and user_id=#{user_id}")
    Integer selectEasyLeetRank(Date update_time, int user_id);

    @Select("select medium_count from leetrank where type=" + LeetRank.DailyRank + " and update_time=#{update_time} and user_id=#{user_id}")
    Integer selectMediumLeetRank(Date update_time, int user_id);

    @Select("select hard_count from leetrank where type=" + LeetRank.DailyRank + " and update_time=#{update_time} and user_id=#{user_id}")
    Integer selectHardLeetRank(Date update_time, int user_id);

    @Update("update leetrank set easy_count=#{easy_count} where type=" + LeetRank.DailyRank + " and update_time=#{update_time} and user_id=#{user_id}")
    int updateEasyLeetRank(int easy_count, Date update_time, int user_id);

    @Update("update leetrank set medium_count=#{medium_count} where type=" + LeetRank.DailyRank + " and update_time=#{update_time} and user_id=#{user_id}")
    int updateMediumLeetRank(int medium_count, Date update_time, int user_id);

    @Update("update leetrank set hard_count=#{hard_count} where type=" + LeetRank.DailyRank + " and update_time=#{update_time} and user_id=#{user_id}")
    int updateHardLeetRank(int hard_count, Date update_time, int user_id);

}
