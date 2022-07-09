package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.LeetRank;
import com.jicoder.leethub.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LeetRankMapper {

    @Insert("insert into leetrank (rank_val, type, update_time, user_id) " +
            "values (#{rank_val}, #{type}, #{update_time}, #{user.user_id})")
    int insertRank(LeetRank rank);

    @Select("select * from leetrank where type=#{type} and user_id=#{user.user_id} order by rank_id desc limit #{len}")
    List<LeetRank> getRecentRank(User user, int type, int len);

}
