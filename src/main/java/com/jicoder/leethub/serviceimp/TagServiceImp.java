package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.TagMapper;
import com.jicoder.leethub.pojo.Tag;
import com.jicoder.leethub.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImp implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int insert(Tag tag) {
        if (tagMapper.selectByNameAndUserId(tag.getName(), tag.getUser().getUser_id()) != null){
            return -2;
        }
        return tagMapper.insert(tag);
    }

    @Override
    public List<Tag> getAllTagsByUserId(int user_id) {
        return tagMapper.selectAllByUserId(user_id);
    }

    @Override
    public int deleteById(int tag_id) {
        return tagMapper.deleteById(tag_id);
    }


}
