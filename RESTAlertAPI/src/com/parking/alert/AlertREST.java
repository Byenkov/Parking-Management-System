package com.parking.alert;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/notifications")
public class AlertREST {
	
	@EJB
	private Notifications notifications;
	
	@GET
	@Produces({"application/json"})
	@Path("/{zone}/json")
	public NotificationList sendNotificationJson(@PathParam("zone") String zone) {
		NotificationList notifList = new NotificationList();
		List list = notifications.getNotificationsByZone(Integer.parseInt(zone));
		notifList.setList(list);
		return notifList;
	}
	
	@GET
	@Produces({"application/xml"})
	@Path("/{zone}/xml")
	public NotificationList sendNotificationXml(@PathParam("zone") String zone) {
		NotificationList notifList = new NotificationList();
		List list = notifications.getNotificationsByZone(Integer.parseInt(zone));
		notifList.setList(list);
		return notifList;
	}

}
