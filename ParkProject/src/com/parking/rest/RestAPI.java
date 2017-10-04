package com.parking.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.parking.logic.Manager;
import com.parking.logic.Notification;
import com.parking.logic.ParkingSlot;
import com.parking.logic.ResponseList;
import com.tickets.pojo.Ticket;

@Path("/api")
@Stateless
@ManagedBean
public class RestAPI {
	
	@EJB
	private Manager manager;
	
	@GET
	@Produces({"application/json"})
	@Path("/slots/json")
	public ResponseList sendFullParkingSlotListJSON() {
		ArrayList list = new ArrayList();
		list = manager.getSlots();
		ResponseList responseList = new ResponseList();
		responseList.setList(list);
		return responseList;
	}
	
	@GET
	@Produces({"application/xml"})
	@Path("/slots/xml")
	public ResponseList sendFullParkingSlotListXML() {
		ArrayList list = new ArrayList();
		list = manager.getSlots();
		ResponseList responseList = new ResponseList();
		responseList.setList(list);
		return responseList;
	}
	
	@GET
	@Produces({"application/xml"})
	@Path("/ids/xml")
	public ResponseList sendSlotIdsXml() {
		ArrayList list = new ArrayList();
		for (ParkingSlot slot : manager.getSlots()){
			list.add(slot.getId());
		}
		ResponseList responseList = new ResponseList();
		responseList.setList(list);
		return responseList;
	}
	
	@GET
	@Produces({"application/json"})
	@Path("/ids/json")
	public ResponseList sendSlotIdsJSON() {
		ArrayList list = new ArrayList();
		for (ParkingSlot slot : manager.getSlots()){
			list.add(slot.getId());
		}
		ResponseList responseList = new ResponseList();
		responseList.setList(list);
		return responseList;
	}
	
	@GET
	@Produces({"application/xml"})
	@Path("/free/xml")
	public ResponseList sendFreeSlotIdsXml() {
		ArrayList list = new ArrayList();
		for (ParkingSlot slot : manager.getSlots()){
			if (!slot.isTaken()) list.add(slot.getId());
		}
		ResponseList responseList = new ResponseList();
		responseList.setList(list);
		return responseList;
	}
	
	@GET
	@Produces({"application/json"})
	@Path("/free/json")
	public ResponseList sendFreeSlotIdsJSON() {
		ArrayList list = new ArrayList();
		for (ParkingSlot slot : manager.getSlots()){
			if (!slot.isTaken()) list.add(slot.getId());
		}
		ResponseList responseList = new ResponseList();
		responseList.setList(list);
		return responseList;
	}
	
	@GET
	@Produces({"application/xml"})
	@Path("/tickets/xml")
	public ResponseList slotsWithTicketsXml() {
		ArrayList list = new ArrayList();
		for (ParkingSlot slot : manager.getSlots()){		
			if (slot.isTicketBought()) list.add(slot.getId());
		}
		ResponseList responseList = new ResponseList();
		responseList.setList(list);
		return responseList;
	}
	
	@GET
	@Produces({"application/json"})
	@Path("/tickets/json")
	public ResponseList slotsWithTicketsJSON() {
		ArrayList list = new ArrayList();
		for (ParkingSlot slot : manager.getSlots()){			
			if (slot.isTicketBought()) list.add(slot.getId());
		}
		ResponseList responseList = new ResponseList();
		responseList.setList(list);
		return responseList;
	}
	
	@GET
	@Produces({"application/xml"})
	@Path("/notickets/xml")
	public ResponseList slotsWithoutTicketsXml() {
		ArrayList list = new ArrayList();
		for (ParkingSlot slot : manager.getSlots()){		
			if (!slot.isTicketBought() && slot.isTaken()) list.add(slot.getId());
		}
		ResponseList responseList = new ResponseList();
		responseList.setList(list);
		return responseList;
	}
	
	@GET
	@Produces({"application/json"})
	@Path("/notickets/json")
	public ResponseList slotsWithoutTicketsJSON() {
		ArrayList list = new ArrayList();
		for (ParkingSlot slot : manager.getSlots()){			
			if (!slot.isTicketBought() && slot.isTaken()) list.add(slot.getId());
		}
		ResponseList responseList = new ResponseList();
		responseList.setList(list);
		return responseList;
	}
}
