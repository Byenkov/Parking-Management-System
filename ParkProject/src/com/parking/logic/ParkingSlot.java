package com.parking.logic;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.sql.Timestamp;

@XmlRootElement
public class ParkingSlot implements Serializable { 
	private int id;
  private int sector;
  private Timestamp timeOfTaking;
  private Timestamp timeOfTicket;
  private boolean taken;
  private boolean ticketBought;
  private String street;
  
  public ParkingSlot() {}
  
  public int getId() { return id; }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getSector() { return sector; }
  
  public void setSector(int sector) {
    this.sector = sector;
  }
  
  public boolean isTaken() { return taken; }
  
  public void setTaken(boolean taken) {
    this.taken = taken;
	this.setTimeOfTaking(new Timestamp(System.currentTimeMillis()));
  }
  
  public String getStreet() { return street; }
  
  public void setStreet(String street) {
    this.street = street;
  }

public boolean isTicketBought() {
	return ticketBought;
}

public void setTicketBought(boolean ticketBought) {
	this.ticketBought = ticketBought;
	if (ticketBought) this.setTimeOfTicket(new Timestamp(System.currentTimeMillis()));
	else this.setTimeOfTicket(null);
}

public Timestamp getTimeOfTaking() {
	return timeOfTaking;
}

public void setTimeOfTaking(Timestamp timeOfTaking) {
	this.timeOfTaking = timeOfTaking;
}

public Timestamp getTimeOfTicket() {
	return timeOfTicket;
}

public void setTimeOfTicket(Timestamp timeOfTicket) {
	this.timeOfTicket = timeOfTicket;
}
}