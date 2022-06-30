package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CityMapper {

    @Select("select * from city where id=#{id}")
    City getCityById(int id);
}
