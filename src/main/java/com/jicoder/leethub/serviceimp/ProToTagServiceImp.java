package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.dao.ProToTagMapper;
import com.jicoder.leethub.service.ProToTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProToTagServiceImp implements ProToTagService {

    @Autowired
    private ProToTagMapper proToTagMapper;

    @Override
    public int insert(int tag_id, int problem_id) {
        return proToTagMapper.insert(tag_id, problem_id);
    }

    @Override
    public int remove(int tag_id, int problem_id) {
        return proToTagMapper.delete(tag_id, problem_id);
    }

}
