package com.parking.rest;

import javax.ejb.Remote;
import javax.ws.rs.core.Response;

@Remote
public interface RestAPIRemote {

	public Response sendNotificationJson();
	
	public Response sendNotificationXml();
}
