package main.newer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("员工dto")
public class UserDto {

	/**
	 * 
	 */
	@ApiModelProperty("员工名称")
	private String uname;
	@ApiModelProperty("密码")
	private String upwd;
	@ApiModelProperty("自动登录")
	private boolean auto;

	public UserDto() {
	}

	@Override
	public String toString() {
		return "UserDto [Uname=" + uname + ", Upwd=" + upwd + ", auto=" + auto + "]";
	}

	public UserDto(String uname, String upwd, boolean auto) {
		super();
		this.uname = uname;
		this.upwd = upwd;
		this.auto = auto;
	}

	public String getUname() {
		return uname;
	}

	public void setUName(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public boolean getAuto() {
		return auto;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}

}
