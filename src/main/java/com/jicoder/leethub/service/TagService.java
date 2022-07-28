package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.Tag;

import java.util.List;

public interface TagService {

    int insert(Tag tag);

    List<Tag> getAllTagsByUserId(int user_id);

    int getByNameAndUserId(String name, int user_id);

    int deleteById(int tag_id);

    int updateTagName(String name, int tag_id);

    int getCountSum(int user_id);

}
