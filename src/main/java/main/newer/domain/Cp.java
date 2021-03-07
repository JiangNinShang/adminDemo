package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("商品图片连接")
@Table(name = "Cp")
public class Cp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("编号")
	private int cpid;

	@Column(name = "cid")
	@ApiModelProperty("商品编号")
	private int cid;

	@Column(name = "pid")
	@ApiModelProperty("图片编号")
	private int pid;

	public int getCpid() {
		return cpid;
	}

	public void setCpid(int cpid) {
		this.cpid = cpid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Cp(int cpid, int cid, int pid) {
		super();
		this.cpid = cpid;
		this.cid = cid;
		this.pid = pid;
	}

	public Cp() {
		// TODO Auto-generated constructor stub
	}
}
