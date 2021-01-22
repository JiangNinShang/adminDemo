package main.newer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.newer.domain.Jurisdiction;
import main.newer.mapper.JurisdictionMapper;
import main.newer.service.JurisdictionService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class JurisdictionServiceImpl implements JurisdictionService {

	@Autowired
	JurisdictionMapper jm;

	@Override
	public List<Jurisdiction> getbuJid(int jid) {
		Example e = new Example(Jurisdiction.class);
		Criteria c = e.createCriteria();
		c.andEqualTo("jid", jid);
		List<Jurisdiction> list = jm.selectByExample(e);
		return list;
	}

}
