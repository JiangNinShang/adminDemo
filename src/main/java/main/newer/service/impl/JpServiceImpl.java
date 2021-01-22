package main.newer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import main.newer.domain.Jp;
import main.newer.mapper.JpMapper;
import main.newer.service.JpService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class JpServiceImpl implements JpService{
	@Autowired
	JpMapper jm;

	@Override
	public List<Jp> getbypid(int pid) {
		Example e = new Example(Jp.class);
		Criteria c = e.createCriteria();
		c.andEqualTo("pid", pid);
		List<Jp> list = jm.selectByExample(e);
		return list;
	}

}
