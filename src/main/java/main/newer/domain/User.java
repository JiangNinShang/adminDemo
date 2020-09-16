package main.newer.domain;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer uid;

	private String uname;

	private String password;

	private String head;

	private int phone;

	private String sex;

	private Integer age;

	private Integer addressId;

	private String mail;

	private Integer deleted;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
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
		return "User [uid=" + uid + ", uname=" + uname + ", password=" + password + ", head=" + head + ", phone="
				+ phone + ", sex=" + sex + ", age=" + age + ", addressId=" + addressId + ", mail=" + mail + ", deleted="
				+ deleted + ", state=" + state + "]";
	}

	public User(Integer uid, String uname, String password, String head, int phone, String sex, Integer age,
			Integer addressId, String mail, Integer deleted, Integer state) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.password = password;
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