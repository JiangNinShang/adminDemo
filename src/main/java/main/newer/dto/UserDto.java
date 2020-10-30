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
	@ApiModelProperty("电子邮箱")
	private String mail;
	@ApiModelProperty("年龄")
	private Integer age;
	@ApiModelProperty("联系电话")
	private String phone;
	@ApiModelProperty("性别")
	private String sex;

	public UserDto() {
	}

	public UserDto(String uname, String upwd, boolean auto, String mail, Integer age, String phone, String sex) {
		super();
		this.uname = uname;
		this.upwd = upwd;
		this.auto = auto;
		this.mail = mail;
		this.age = age;
		this.phone = phone;
		this.sex = sex;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public boolean isAuto() {
		return auto;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "UserDto [uname=" + uname + ", upwd=" + upwd + ", auto=" + auto + ", mail=" + mail + ", age=" + age
				+ ", phone=" + phone + ", sex=" + sex + "]";
	}

	
	
}
