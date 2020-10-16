package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("关联")
@Table(name = "Ua")
public class Ua implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("编号")
	private int uaid;
	@Column(name = "uid")
	@ApiModelProperty("员工编号")
	private int uid;
	@Column(name = "aid")
	@ApiModelProperty("地址编号")
	private int aid;
	public int getUaid() {
		return uaid;
	}
	public void setUaid(int uaid) {
		this.uaid = uaid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public Ua(int uaid, int uid, int aid) {
		super();
		this.uaid = uaid;
		this.uid = uid;
		this.aid = aid;
	}
	
	public Ua() {
		// TODO Auto-generated constructor stub
	}
}
