package com.parking.logic;

import java.io.Serializable;
import java.util.HashMap;

public class Report implements Serializable{
	private HashMap<Integer, Integer> slotsByDetections;
	private HashMap<Integer, Integer> zonesByDetections;
	private HashMap<Integer, Integer> slotsByTickets;
	private HashMap<Integer, Integer> zonesByTickets;
	private HashMap<Integer, Integer> slotsByTicketsNotBought;
	private HashMap<Integer, Integer> zonesByTicketsNotBought;
	private HashMap<Integer, Integer> slotsByTicketsOverTime;
	private HashMap<Integer, Integer> zonesByTicketsOverTimes;
	private int detections;
	private int tickets;
	private int ticketsOverTime;
	private int ticketsNotBought;
	
	public Report() {
		setSlotsByDetections(new HashMap<Integer, Integer>());
		setZonesByDetections(new HashMap<Integer, Integer>());
		setSlotsByTickets(new HashMap<Integer, Integer>());
		setZonesByTickets(new HashMap<Integer, Integer>());
		setSlotsByTicketsNotBought(new HashMap<Integer, Integer>());
		setZonesByTicketsNotBought(new HashMap<Integer, Integer>());
		setSlotsByTicketsOverTime(new HashMap<Integer, Integer>());
		setZonesByTicketsOverTimes(new HashMap<Integer, Integer>());
		setDetections(0);	
		setTickets(0);		
		setTicketsOverTime(0);	
		setTicketsNotBought(0);
	}
	
	public void startSlotStorage(ParkingSlot slot){
		slotsByDetections.put(slot.getId(), 0);
		slotsByTickets.put(slot.getId(), 0);
		zonesByDetections.put(slot.getSector(), 0);
		zonesByTickets.put(slot.getSector(), 0);
		slotsByTicketsNotBought.put(slot.getId(), 0);
		slotsByTicketsOverTime.put(slot.getId(), 0);
		zonesByTicketsNotBought.put(slot.getSector(), 0);
		zonesByTicketsOverTimes.put(slot.getSector(), 0);
	}

	public HashMap<Integer, Integer> getSlotsByDetections() {
		return slotsByDetections;
	}

	public void setSlotsByDetections(HashMap<Integer, Integer> slotsByDetections) {
		this.slotsByDetections = slotsByDetections;
	}

	public HashMap<Integer, Integer> getZonesByDetections() {
		return zonesByDetections;
	}

	public void setZonesByDetections(HashMap<Integer, Integer> zonesByDetections) {
		this.zonesByDetections = zonesByDetections;
	}

	public HashMap<Integer, Integer> getSlotsByTickets() {
		return slotsByTickets;
	}

	public void setSlotsByTickets(HashMap<Integer, Integer> slotsByTickets) {
		this.slotsByTickets = slotsByTickets;
	}

	public HashMap<Integer, Integer> getZonesByTickets() {
		return zonesByTickets;
	}

	public void setZonesByTickets(HashMap<Integer, Integer> zonesByTickets) {
		this.zonesByTickets = zonesByTickets;
	}

	public int getDetections() {
		return detections;
	}

	public void setDetections(int detections) {
		this.detections = detections;
	}

	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	public int getTicketsOverTime() {
		return ticketsOverTime;
	}

	public void setTicketsOverTime(int ticketsOverTime) {
		this.ticketsOverTime = ticketsOverTime;
	}

	public int getTicketsNotBought() {
		return ticketsNotBought;
	}

	public void setTicketsNotBought(int ticketsNotBought) {
		this.ticketsNotBought = ticketsNotBought;
	}

	public HashMap<Integer, Integer> getSlotsByTicketsNotBought() {
		return slotsByTicketsNotBought;
	}

	public void setSlotsByTicketsNotBought(HashMap<Integer, Integer> slotsByTicketsNotBought) {
		this.slotsByTicketsNotBought = slotsByTicketsNotBought;
	}

	public HashMap<Integer, Integer> getZonesByTicketsNotBought() {
		return zonesByTicketsNotBought;
	}

	public void setZonesByTicketsNotBought(HashMap<Integer, Integer> zonesByTicketsNotBought) {
		this.zonesByTicketsNotBought = zonesByTicketsNotBought;
	}

	public HashMap<Integer, Integer> getSlotsByTicketsOverTime() {
		return slotsByTicketsOverTime;
	}

	public void setSlotsByTicketsOverTime(HashMap<Integer, Integer> slotsByTicketsOverTime) {
		this.slotsByTicketsOverTime = slotsByTicketsOverTime;
	}

	public HashMap<Integer, Integer> getZonesByTicketsOverTimes() {
		return zonesByTicketsOverTimes;
	}

	public void setZonesByTicketsOverTimes(HashMap<Integer, Integer> zonesByTicketsOverTimes) {
		this.zonesByTicketsOverTimes = zonesByTicketsOverTimes;
	}
}
