package main.newer.dao;

import main.newer.domain.Jurisdiction;
import tk.mybatis.mapper.common.Mapper;

public interface JurisdictionMapper extends Mapper<Jurisdiction>{
    int deleteByPrimaryKey(Integer jid);

    int insert(Jurisdiction record);

    int insertSelective(Jurisdiction record);

    Jurisdiction selectByPrimaryKey(Integer jid);

    int updateByPrimaryKeySelective(Jurisdiction record);

    int updateByPrimaryKey(Jurisdiction record);
}