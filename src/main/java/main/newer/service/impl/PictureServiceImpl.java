package main.newer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.newer.domain.Picture;
import main.newer.dto.PictureDto;
import main.newer.mapper.PictureMapper;
import main.newer.service.PictureService;
@Service
public class PictureServiceImpl implements PictureService{
	@Autowired
	PictureMapper pm;
	@Override
	public String add(PictureDto[] p) {
		for(int  i = 0;i<p.length;i++) {
			pm.insert(new Picture(p[i].getName(),p[i].getUrl()));
		}
		return null;
	}

}
