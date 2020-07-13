package main.newer.dao;

import main.newer.domain.Department;
import tk.mybatis.mapper.common.Mapper;

public interface DepartmentMapper extends Mapper<Department>{
    int deleteByPrimaryKey(Integer did);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}