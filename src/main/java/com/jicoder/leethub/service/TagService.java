package com.jicoder.leethub.service;

import com.jicoder.leethub.pojo.Tag;

import java.util.List;

public interface TagService {

    int insert(Tag tag);

    List<Tag> getAllTagsByUserId(int user_id);

    int deleteById(int tag_id);

}
