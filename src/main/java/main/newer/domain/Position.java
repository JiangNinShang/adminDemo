package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("职位")
@Table(name = "Position")
public class Position implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("职位编号")
	private Integer pid;
	@Column(name = "pname")
	@ApiModelProperty("职位名称")
	private String pname;
	@Column(name = "salary")
	@ApiModelProperty("职位薪资")
	private Integer salary;
	@Column(name = "state")
	@ApiModelProperty("职位状态")
	private Integer state;
	
	public Position() {
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	public Position(Integer pid, String pname, Integer salary, Integer state) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.salary = salary;
		this.state = state;
	}
	
}
