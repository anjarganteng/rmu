package com.tripoin.core.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "menu", "responseCode", "responseMsg", "result" })
@XmlRootElement(name = "Menus")
public class Menus {	
	@XmlElement(name = "Menu", required = true)
	private List<MenuDTO> master_menu;
	
	@XmlElement(name = "responseCode", required = true)
	private String response_code;
	
	@XmlElement(name = "responseMsg", required = true)
	private String response_msg;
	
	@XmlElement(name = "result", required = true)
	private String RESULT;

	public List<MenuDTO> getMaster_menu() {
		return master_menu;
	}

	public void setMaster_menu(List<MenuDTO> master_menu) {
		this.master_menu = master_menu;
	}

	public String getResponse_code() {
		return response_code;
	}

	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}

	public String getResponse_msg() {
		return response_msg;
	}

	public void setResponse_msg(String response_msg) {
		this.response_msg = response_msg;
	}

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String rESULT) {
		RESULT = rESULT;
	}

	@Override
	public String toString() {
		return "Menus [master_menu=" + master_menu + ", response_code="
				+ response_code + ", response_msg=" + response_msg
				+ ", RESULT=" + RESULT + "]";
	}
}