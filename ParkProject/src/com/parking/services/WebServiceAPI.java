package com.parking.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WebServiceAPI {
	
	@WebMethod
	public Integer[] getSlotIds();
	
	@WebMethod
	public Integer[] getFreeSlotIds();
	
	@WebMethod
	public Integer[] getSlotsWithTickets();
	
	@WebMethod
	public Integer[] getSlotsWithoutTickets();
}
