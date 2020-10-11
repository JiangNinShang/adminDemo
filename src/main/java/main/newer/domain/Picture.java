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
	@Column(name = "pPath")
	@ApiModelProperty("路径")
	private String pPath;
	
	public Picture() {
	}

	@Override
	public String toString() {
		return "Picture [pid=" + pid + ", pPath=" + pPath + "]";
	}

	public Picture(Integer pid, String pPath) {
		super();
		this.pid = pid;
		this.pPath = pPath;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getpPath() {
		return pPath;
	}

	public void setpPath(String pPath) {
		this.pPath = pPath;
	}

	
}
