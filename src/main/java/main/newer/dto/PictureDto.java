package main.newer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("图片dto")
public class PictureDto {
	/**
	 * 
	 */
	@ApiModelProperty("编号")
	private Integer pid;
	@ApiModelProperty("名字")
	private String name;
	@ApiModelProperty("路径")
	private String url;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PictureDto [pid=" + pid + ", name=" + name + ", url=" + url + "]";
	}

	public PictureDto(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

}
