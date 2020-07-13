package main.newer.dao;

import main.newer.domain.PositionJurisdiction;
import tk.mybatis.mapper.common.Mapper;

public interface PositionJurisdictionMapper extends Mapper<PositionJurisdiction>{
    int deleteByPrimaryKey(Integer pjid);

    int insert(PositionJurisdiction record);

    int insertSelective(PositionJurisdiction record);

    PositionJurisdiction selectByPrimaryKey(Integer pjid);

    int updateByPrimaryKeySelective(PositionJurisdiction record);

    int updateByPrimaryKey(PositionJurisdiction record);
}