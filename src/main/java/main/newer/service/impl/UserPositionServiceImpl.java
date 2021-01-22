package main.newer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.newer.domain.UserPosition;
import main.newer.mapper.UserPositionMapper;
import main.newer.service.UserPositionService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class UserPositionServiceImpl implements UserPositionService {
	
	@Autowired
	UserPositionMapper upm;
	
	@Override
	public List<UserPosition> getbyuid(int uid) {
		Example e = new Example(UserPosition.class);
		Criteria c = e.createCriteria();
		c.andEqualTo("uid", uid);
		List<UserPosition> l = upm.selectByExample(e);
		return l;
	}

}
