package main.newer.domain;

import java.io.Serializable;

public class Departmen implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Did;
	private String Dname;
	private Integer principal;
	private int sum;
	
	public int getDid() {
		return Did;
	}

	public void setDid(int did) {
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

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public Departmen() {
	}

	public Departmen(int did, String dname, Integer principal, int sum) {
		super();
		Did = did;
		Dname = dname;
		this.principal = principal;
		this.sum = sum;
	}
	
}
