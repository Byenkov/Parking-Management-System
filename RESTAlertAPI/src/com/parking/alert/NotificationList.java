package com.parking.alert;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.parking.logic.Notification;

	@XmlRootElement(name = "notificationList")
	@XmlSeeAlso({Notification.class})
	public class NotificationList {

	    private List<Object> list;

	    public List<Object> getList() {
	        return list;
	    }

	    public void setList(List<Object> list) {
	        this.list = list;
	    }

	}