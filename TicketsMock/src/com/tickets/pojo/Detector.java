package com.tickets.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Detector {
	private int id;
	private boolean state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
}
