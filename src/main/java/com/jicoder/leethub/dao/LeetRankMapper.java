package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.LeetRank;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeetRankMapper {

    @Insert("insert into leetrank (rank_val, type, update_time, user_id) " +
            "values (#{rank_val}, #{type}, #{update_time}, #{user.user_id})")
    int insertRank(LeetRank rank);

}
