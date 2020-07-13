package main.newer.dao;

import main.newer.domain.Jurisdiction;

public interface JurisdictionMapper {
    int deleteByPrimaryKey(Integer jid);

    int insert(Jurisdiction record);

    int insertSelective(Jurisdiction record);

    Jurisdiction selectByPrimaryKey(Integer jid);

    int updateByPrimaryKeySelective(Jurisdiction record);

    int updateByPrimaryKey(Jurisdiction record);
}