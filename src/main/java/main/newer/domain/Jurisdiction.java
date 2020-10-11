package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("权限")
@Table(name = "Jurisdiction")
public class Jurisdiction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("权限编号")
	private Integer jid;
	@Column(name = "jname")
	@ApiModelProperty("权限名称")
	private String jname;
	@Column(name = "jpath")
	@ApiModelProperty("权限路径")
	private String jpath;
	@Column(name = "state")
	@ApiModelProperty("权限状态")
	private Integer state;
	
	public Integer getJid() {
		return jid;
	}

	public void setJid(Integer jid) {
		this.jid = jid;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public String getJpath() {
		return jpath;
	}

	public void setJpath(String jpath) {
		this.jpath = jpath;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Jurisdiction() {
	}

	public Jurisdiction(Integer jid, String jname, String jpath, Integer state) {
		super();
		this.jid = jid;
		this.jname = jname;
		this.jpath = jpath;
		this.state = state;
	}
	
}
