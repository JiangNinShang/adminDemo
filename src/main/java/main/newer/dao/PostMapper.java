package main.newer.dao;

import main.newer.domain.Post;
import tk.mybatis.mapper.common.Mapper;

public interface PostMapper extends Mapper<Post>{
    int deleteByPrimaryKey(Integer pid);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
}