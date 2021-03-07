package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("图片")
@Table(name = "Picture")
public class Picture implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("编号")
	private Integer pid;
	@Column(name = "name")
	@ApiModelProperty("名称")
	private String name;
	@Column(name = "url")
	@ApiModelProperty("路径")
	private String url;
	
	public Picture() {
	}

	@Override
	public String toString() {
		return "Picture [pid=" + pid + ", name=" + name + ", url=" + url + "]";
	}

	public Picture(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public Picture(Integer pid, String name, String url) {
		super();
		this.pid = pid;
		this.name = name;
		this.url = url;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
