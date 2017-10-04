package com.parking.alert;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.parking.logic.Notification;

@Singleton
@Local
@Startup
public class Notifications {
	
	private ArrayList<Notification> notifications;
	
	@PostConstruct
	public void init(){
		notifications = new ArrayList<Notification>();
	}

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public void addNotification(Notification notification){
		System.out.println("Adding notif: " + notification.getDescription());
		notifications.add(notification);
	}
	
	public ArrayList<Notification> getNotificationsByZone(int zone){
		 ArrayList<Notification> list = new ArrayList<Notification>();
		 for (Notification notif : notifications) if (notif.getZone() == zone) list.add(notif);
		 notifications.removeAll(list);
		 return list;
	}

}
