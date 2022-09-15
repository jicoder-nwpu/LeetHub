package com.jicoder.leethub.dao;

import com.jicoder.leethub.pojo.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {

    @Insert("insert into tag (name, count, user_id) values (#{name}, #{count}, #{user.user_id})")
    int insert(Tag tag);

    @Update("update tag set count=#{count} where tag_id=#{tag_id}")
    int updateCount(int count, int tag_id);

    @Update("update tag set name=#{name} where tag_id=#{tag_id}")
    int updateName(String name, int tag_id);

    @Select("select sum(count) from tag where user_id=#{user_id}")
    Integer selectCountSum(int user_id);

    @Select("select * from tag where user_id=#{user_id}")
    List<Tag> selectAllByUserId(int user_id);

    @Select("select * from tag where user_id=#{user_id} and name=#{name}")
    Tag selectByNameAndUserId(String name, int user_id);

    @Select("select * from tag where tag_id in (select tag_id from prototag where problem_id=#{problem_id} and tag_id in (select tag_id from tag where user_id=#{user_id}))")
    List<Tag> selectTagByUserAndProblem(int user_id, int problem_id);

    @Select("select * from tag where tag_id not in (select tag_id from prototag where problem_id=#{problem_id} and tag_id in (select tag_id from tag where user_id=#{user_id}))" +
            " and user_id=#{user_id}")
    List<Tag> selectUnUsedTagByUserAndProblem(int user_id, int problem_id);

    @Delete("delete from tag where tag_id=#{tag_id}")
    int deleteById(int tag_id);

    @Update("update tag set count=count + 1 where tag_id=#{tag_id}")
    int countAddOne(int tag_id);

    @Update("update tag set count=count - 1 where tag_id=#{tag_id}")
    int countSubOne(int tag_id);

}
