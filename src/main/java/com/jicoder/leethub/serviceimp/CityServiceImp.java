package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.CityMapper;
import com.jicoder.leethub.pojo.City;
import com.jicoder.leethub.service.CityService;
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
