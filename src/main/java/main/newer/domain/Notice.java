package main.newer.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("通知")
@Table(name = "Notice")
public class Notice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("通知编号")
	private Integer nid;
	@Column(name = "time")
	@ApiModelProperty("通知时间")
	private Date time;
	@Column(name = "uid")
	@ApiModelProperty("通知人编号")
	private Integer uid;
	@Column(name = "info")
	@ApiModelProperty("通知信息")
	private String info;
	@Column(name = "zpbm")
	@ApiModelProperty("通知部门")
	private String zpbm;
	public Notice() {
	}
	public Notice(Integer nid, Date time, Integer uid, String info, String zpbm) {
		super();
		this.nid = nid;
		this.time = time;
		this.uid = uid;
		this.info = info;
		this.zpbm = zpbm;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getZpbm() {
		return zpbm;
	}
	public void setZpbm(String zpbm) {
		this.zpbm = zpbm;
	}
	
}
