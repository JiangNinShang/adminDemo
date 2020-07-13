package main.newer.dao;

import main.newer.domain.Userposistion;
import tk.mybatis.mapper.common.Mapper;

public interface UserposistionMapper extends Mapper<Userposistion> {
    int deleteByPrimaryKey(Integer upid);

    int insert(Userposistion record);

    int insertSelective(Userposistion record);

    Userposistion selectByPrimaryKey(Integer upid);

    int updateByPrimaryKeySelective(Userposistion record);

    int updateByPrimaryKey(Userposistion record);
}