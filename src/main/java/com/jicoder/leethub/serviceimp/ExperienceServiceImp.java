package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.ExperienceMapper;
import com.jicoder.leethub.pojo.Experience;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExperienceServiceImp implements ExperienceService {

    @Autowired
    private ExperienceMapper experienceMapper;

    @Override
    public int insert(Experience experience) {
        return experienceMapper.insert(experience);
    }

    @Override
    public List<String> getLabels() {
        List<String> labels = new ArrayList<>();
        labels.add("算法模板");
        labels.add("框架技巧");
        return labels;
    }

    @Override
    public Experience getById(int experience_id) {
        return experienceMapper.selectById(experience_id);
    }

    @Override
    public int update(Experience experience) {
        return experienceMapper.update(experience);
    }

}
