package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("商品")
@Table(name = "Clothing")
public class Clothing implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("编号")
	private int cid;
	@Column(name = "cname")
	@ApiModelProperty("商品名")
	private String cname;
	@Column(name = "video")
	@ApiModelProperty("视频介绍")
	private String video;
	@Column(name = "info")
	@ApiModelProperty("介绍")
	private String info;
	@Column(name = "repertory")
	@ApiModelProperty("库存")
	private int repertory;

	public Clothing() {
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getRepertory() {
		return repertory;
	}

	public void setRepertory(int repertory) {
		this.repertory = repertory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Clothing(int cid, String cname, String video, String info, int repertory) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.video = video;
		this.info = info;
		this.repertory = repertory;
	}

}