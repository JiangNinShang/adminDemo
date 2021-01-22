package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("权限职位")
@Table(name = "Jp")
public class Jp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("编号")
	private int jpid;
	@ApiModelProperty("权限编号")
	@Column(name = "jid")
	private Integer jid;
	@ApiModelProperty("职位编号")
	@Column(name = "pid")
	private Integer pid;
	
	public Jp() {
		// TODO Auto-generated constructor stub
	}

	public int getJpid() {
		return jpid;
	}

	public void setJpid(int jpid) {
		this.jpid = jpid;
	}

	public Integer getJid() {
		return jid;
	}

	public void setJid(Integer jid) {
		this.jid = jid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Jp(int jpid, Integer jid, Integer pid) {
		super();
		this.jpid = jpid;
		this.jid = jid;
		this.pid = pid;
	}

	
	
}
