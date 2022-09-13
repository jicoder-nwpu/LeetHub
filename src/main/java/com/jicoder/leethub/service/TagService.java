package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.Tag;

import java.util.List;

public interface TagService {

    int insert(Tag tag);

    List<Tag> getAllTagsByUserId(int user_id);

    List<Tag> getTagByUserAndProblem(int user_id, int problem_id);

    List<Tag> getUnusedTags(int user_id, int problem_id);

    int getByNameAndUserId(String name, int user_id);

    int deleteById(int tag_id);

    int updateTagName(String name, int tag_id);

    int getCountSum(int user_id);

    int countAddOne(int tag_id);

    int countSubOne(int tag_id);

}
