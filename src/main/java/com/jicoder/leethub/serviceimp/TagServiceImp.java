package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.TagMapper;
import com.jicoder.leethub.pojo.Tag;
import com.jicoder.leethub.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    public List<Tag> getTagByUserAndProblem(int user_id, int problem_id) {
        return tagMapper.selectTagByUserAndProblem(user_id, problem_id);
    }

    @Override
    public List<Tag> getUnusedTags(int user_id, int problem_id) {
        return tagMapper.selectUnUsedTagByUserAndProblem(user_id, problem_id);
    }

    @Override
    public int getByNameAndUserId(String name, int user_id) {
        if (tagMapper.selectByNameAndUserId(name, user_id) != null){
            return -1;
        }
        return 1;
    }

    @Override
    public int deleteById(int tag_id) {
        return tagMapper.deleteById(tag_id);
    }

    @Override
    public int updateTagName(String name, int tag_id) {
        return tagMapper.updateName(name, tag_id);
    }

    @Override
    public int getCountSum(int user_id) {
        return tagMapper.selectCountSum(user_id);
    }


}
