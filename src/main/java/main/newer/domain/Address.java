package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("地址")
@Table(name = "Address")
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@ApiModelProperty("员工编号")
	private int aid;
	@Column(name = "address")
	@ApiModelProperty("地址")
	private String address;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(int aid, String address) {
		super();
		this.aid = aid;
		this.address = address;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
