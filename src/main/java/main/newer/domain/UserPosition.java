package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("关联")
@Table(name = "UserPosition")
public class UserPosition implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("编号")
	private  Integer udid;
	@Column(name = "uid")
	@ApiModelProperty("用户编号")
	private Integer uid;
	@Column(name = "pid")
	@ApiModelProperty("职位编号")
	private Integer pid;
	
	public Integer getUdid() {
		return udid;
	}

	public void setUdid(Integer udid) {
		this.udid = udid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public UserPosition(Integer udid, Integer uid, Integer pid) {
		super();
		this.udid = udid;
		this.uid = uid;
		this.pid = pid;
	}

	public UserPosition() {
	}
	
}
