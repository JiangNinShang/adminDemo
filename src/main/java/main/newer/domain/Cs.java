package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("商品尺码连接")
@Table(name = "Cs")
public class Cs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("编号")
	private int csid;
	@Column(name = "cid")
	@ApiModelProperty("商品编号")
	private int cid;
	@Column(name = "sid ")
	@ApiModelProperty("尺码编号")
	private int sid;

	public Cs() {
	}

	public Cs(int csid, int cid, int sid) {
		super();
		this.csid = csid;
		this.cid = cid;
		this.sid = sid;
	}

	public int getCsid() {
		return csid;
	}

	public void setCsid(int csid) {
		this.csid = csid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Cs [csid=" + csid + ", cid=" + cid + ", sid=" + sid + "]";
	}

}
