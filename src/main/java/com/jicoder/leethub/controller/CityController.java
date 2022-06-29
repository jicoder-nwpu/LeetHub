package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.City;
import com.jicoder.leethub.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("/city/{id}")
    public City getCity(@PathVariable int id){
        City city = cityService.getCity(id);
        System.out.println(city.toString());
        return city;
    }
}
