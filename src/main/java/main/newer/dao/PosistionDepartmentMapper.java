package main.newer.dao;

import main.newer.domain.PosistionDepartment;

public interface PosistionDepartmentMapper {
    int deleteByPrimaryKey(Integer pdid);

    int insert(PosistionDepartment record);

    int insertSelective(PosistionDepartment record);

    PosistionDepartment selectByPrimaryKey(Integer pdid);

    int updateByPrimaryKeySelective(PosistionDepartment record);

    int updateByPrimaryKey(PosistionDepartment record);
}