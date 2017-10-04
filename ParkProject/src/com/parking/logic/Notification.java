package com.parking.logic;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Notification implements Serializable{
	 private int probablePlace;
	 private int zone;
	 private String description;
	public int getProbablePlace() {
		return probablePlace;
	}
	public void setProbablePlace(int probablePlace) {
		this.probablePlace = probablePlace;
	}
	public int getZone() {
		return zone;
	}
	public void setZone(int zone) {
		this.zone = zone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	 

}