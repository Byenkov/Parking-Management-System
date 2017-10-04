package com.parking.services;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.parking.logic.Manager;
import com.parking.logic.ParkingSlot;

@WebService(endpointInterface="com.parking.services.WebServiceAPI")
@Stateless
public class WebServiceAPIimpl implements WebServiceAPI{
	
	@EJB
	private Manager manager;

	@WebMethod
	public Integer[] getSlotIds() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (ParkingSlot slot : manager.getSlots()){
			list.add(slot.getId());
		}
		Integer[] array = list.toArray(new Integer[list.size()]);
		return array;
	}

	@WebMethod
	public Integer[] getFreeSlotIds() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (ParkingSlot slot : manager.getSlots()){
			if (!slot.isTaken()) list.add(slot.getId());
		}
		Integer[] array = list.toArray(new Integer[list.size()]);
		return array;
	}

	@WebMethod
	public Integer[] getSlotsWithTickets() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (ParkingSlot slot : manager.getSlots()){		
			if (slot.isTicketBought()) list.add(slot.getId());
		}
		Integer[] array = list.toArray(new Integer[list.size()]);
		return array;
	}

	@WebMethod
	public Integer[] getSlotsWithoutTickets() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (ParkingSlot slot : manager.getSlots()){		
			if (!slot.isTicketBought() && slot.isTaken()) list.add(slot.getId());
		}
		Integer[] array = list.toArray(new Integer[list.size()]);
		return array;
	}

}
