package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("员工")
@Table(name = "User")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("员工编号")
	private Integer uid;
	@Column(name = "uname")
	@ApiModelProperty("员工名称")
	private String uname;
	@Column(name = "upwd")
	@ApiModelProperty("密码")
	private String upwd;
	@Column(name = "head")
	@ApiModelProperty("头像")
	private Integer head;
	@Column(name = "phone")
	@ApiModelProperty("联系电话")
	private Integer phone;
	@Column(name = "sex")
	@ApiModelProperty("性别")
	private String sex;
	@Column(name = "age")
	@ApiModelProperty("年龄")
	private Integer age;
	@Column(name = "addressId")
	@ApiModelProperty("地址编号")
	private Integer addressId;
	@Column(name = "mail")
	@ApiModelProperty("电子邮箱")
	private String mail;
	@Column(name = "deleted")
	@ApiModelProperty("在线状态")
	private Integer deleted;
	@Column(name = "state")
	@ApiModelProperty("状态")
	private Integer state;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname == null ? null : uname.trim();
	}

	

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail == null ? null : mail.trim();
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Integer getHead() {
		return head;
	}

	public void setHead(Integer head) {
		this.head = head;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", head=" + head + ", phone="
				+ phone + ", sex=" + sex + ", age=" + age + ", addressId=" + addressId + ", mail=" + mail + ", deleted="
				+ deleted + ", state=" + state + "]";
	}

	public User(Integer uid, String uname, String upwd, Integer head, Integer phone, String sex, Integer age,
			Integer addressId, String mail, Integer deleted, Integer state) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
		this.head = head;
		this.phone = phone;
		this.sex = sex;
		this.age = age;
		this.addressId = addressId;
		this.mail = mail;
		this.deleted = deleted;
		this.state = state;
	}

	public User() {
	}
}