package main.newer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("尺码")
@Table(name = "Size")
@Data
public class Size implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ApiModelProperty("编号")
	private int sid;
	@Column(name = "size")
	@ApiModelProperty("大小")
	private String size;
	@Column(name = "discount")
	@ApiModelProperty("折扣")
	private double discount;
	@Column(name = "price")
	@ApiModelProperty("价格")
	private double price;

	public Size() {
	}

	public Size(int sid, String size, double discount, double price) {
		super();
		this.sid = sid;
		this.size = size;
		this.discount = discount;
		this.price = price;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}


	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Size [sid=" + sid + ", size=" + size +  ", discount=" + discount + ", price=" + price
				+ "]";
	}
}
