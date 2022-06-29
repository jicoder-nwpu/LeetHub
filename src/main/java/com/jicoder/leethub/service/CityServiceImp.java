package com.jicoder.leethub.service;

import com.jicoder.leethub.mapper.CityMapper;
import com.jicoder.leethub.pojo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImp implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public City getCity(int id) {
        return cityMapper.getCityById(id);
    }
}
