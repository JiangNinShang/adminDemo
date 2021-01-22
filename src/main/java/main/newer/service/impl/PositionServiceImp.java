package main.newer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import main.newer.domain.Position;
import main.newer.mapper.PositionMapper;
import main.newer.service.PositionService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class PositionServiceImp implements PositionService{

	@Autowired
	PositionMapper pm;
	
	@Override
	public List<Position> getbypid(int pid) {
		Example e = new Example(Position.class);
		Criteria c = e.createCriteria();
		c.andEqualTo("pid", pid);
		List<Position> list = pm.selectByExample(e);
		return list;
	}

}
