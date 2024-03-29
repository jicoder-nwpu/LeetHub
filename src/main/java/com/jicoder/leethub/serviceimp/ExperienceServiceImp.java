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
        labels.add("总结梳理");
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

    @Override
    public List<Experience> getAll(int user_id) {
        return experienceMapper.getAll(user_id);
    }

    @Override
    public List<Experience> getRecent(int user_id, int experience_id) {
        return experienceMapper.getRecent(user_id, experience_id);
    }

    @Override
    public List<Experience> getByLabel(int user_id, String label) {
        return experienceMapper.selectByLabel(user_id, label);
    }

    @Override
    public List<Experience> getByName(int user_id, String name) {
        return experienceMapper.selectByTitle(user_id, name);
    }

}
