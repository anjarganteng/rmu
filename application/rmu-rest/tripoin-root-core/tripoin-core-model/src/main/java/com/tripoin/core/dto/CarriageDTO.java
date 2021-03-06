package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "carriage_id", "carriage_no", "carriage_remarks" })
@XmlRootElement(name = "Carriage")
public class CarriageDTO {
	private Integer carriage_id;
	private String carriage_no;
	private String carriage_remarks;

	public CarriageDTO() {}

	public CarriageDTO(Integer carriage_id, String carriage_no,
			String carriage_remarks) {
		super();
		this.carriage_id = carriage_id;
		this.carriage_no = carriage_no;
		this.carriage_remarks = carriage_remarks;
	}

	public Integer getCarriage_id() {
		return carriage_id;
	}

	public void setCarriage_id(Integer carriage_id) {
		this.carriage_id = carriage_id;
	}

	public String getCarriage_no() {
		return carriage_no;
	}

	public void setCarriage_no(String carriage_no) {
		this.carriage_no = carriage_no;
	}

	public String getCarriage_remarks() {
		return carriage_remarks;
	}

	public void setCarriage_remarks(String carriage_remarks) {
		this.carriage_remarks = carriage_remarks;
	}

	@Override
	public String toString() {
		return "CarriageDTO [carriage_id=" + carriage_id + ", carriage_no="
				+ carriage_no + ", carriage_remarks=" + carriage_remarks + "]";
	}	
}
