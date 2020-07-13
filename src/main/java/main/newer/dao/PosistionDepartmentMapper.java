package main.newer.dao;

import main.newer.domain.PosistionDepartment;
import tk.mybatis.mapper.common.Mapper;

public interface PosistionDepartmentMapper extends Mapper<PosistionDepartment> {
    int deleteByPrimaryKey(Integer pdid);

    int insert(PosistionDepartment record);

    int insertSelective(PosistionDepartment record);

    PosistionDepartment selectByPrimaryKey(Integer pdid);

    int updateByPrimaryKeySelective(PosistionDepartment record);

    int updateByPrimaryKey(PosistionDepartment record);
}