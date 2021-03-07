package main.newer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import main.newer.config.ResultJSON;
import main.newer.dto.PictureDto;
import main.newer.service.PictureService;

@CrossOrigin(origins = "*", maxAge = 3600) // @CrossOrigin所有网站都可以跨域访问
@RestController
@RequestMapping("Clothing")
public class ClothingController {
	@Autowired
	PictureService ps;
	
	@ApiOperation("图片上传")
	@PostMapping("/tpsc")
	@RequestMapping("tpsc")
	public ResultJSON tpsc(PictureDto p) {
		System.out.println(p.toString());
		return ResultJSON.ok();
	}

	@ApiOperation("图片上传")
	@PostMapping("/wqnmd")
	@RequestMapping("wqnmd")
	public ResultJSON wqnmd(@RequestBody PictureDto[] p) {
		ps.add(p);
		return ResultJSON.ok();
	}
}
