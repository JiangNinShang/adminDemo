package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("职位")
@Table(name = "Dp")
public class Dp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("编号")
	private Integer did;
	@ApiModelProperty("部门编号")
	@Column(name = "deid")
	private Integer deid;
	@ApiModelProperty("职位编号")
	@Column(name = "pid")
	private Integer pid;
	
	public Dp() {
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public Integer getDeid() {
		return deid;
	}

	public void setDeid(Integer deid) {
		this.deid = deid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Dp(Integer did, Integer deid, Integer pid) {
		super();
		this.did = did;
		this.deid = deid;
		this.pid = pid;
	}
	
	
}
