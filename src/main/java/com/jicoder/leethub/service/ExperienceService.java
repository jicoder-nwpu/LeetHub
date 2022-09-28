package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.Experience;
import com.jicoder.leethub.pojo.User;

import java.util.List;

public interface ExperienceService {

    int insert(Experience experience);

    List<String> getLabels();

    Experience getById(int experience_id);

    int update(Experience experience);

    List<Experience> getAll(int user_id);

    List<Experience> getRecent(int user_id, int experience_id);

    List<Experience> getByLabel(int user_id, String label);

    List<Experience> getByName(int user_id, String name);

}
