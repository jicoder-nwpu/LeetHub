package com.jicoder.leethub.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProToTagMapper {

    @Insert("insert into prototag (tag_id, problem_id) values (#{tag_id}, #{problem_id})")
    int insert(int tag_id, int problem_id);

}
