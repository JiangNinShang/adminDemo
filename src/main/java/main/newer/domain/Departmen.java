package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("部门")
@Table(name = "Departmen")
public class Departmen implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("部门编号")
	private Integer Did;
	@Column(name = "Dname")
	@ApiModelProperty("部门名称")
	private String Dname;
	@Column(name = "principal")
	@ApiModelProperty("负责人")
	private Integer principal;
	
	public Integer getDid() {
		return Did;
	}

	public void setDid(Integer did) {
		Did = did;
	}

	public String getDname() {
		return Dname;
	}

	public void setDname(String dname) {
		Dname = dname;
	}

	public Integer getPrincipal() {
		return principal;
	}

	public void setPrincipal(Integer principal) {
		this.principal = principal;
	}


	public Departmen() {
	}

	public Departmen(Integer did, String dname, Integer principal) {
		super();
		Did = did;
		Dname = dname;
		this.principal = principal;
	}
	
}
