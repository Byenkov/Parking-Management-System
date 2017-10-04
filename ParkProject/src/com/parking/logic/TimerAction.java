package com.parking.logic;

import java.io.Serializable;
import java.sql.Timestamp;

public class TimerAction implements Serializable {
	  private int slotId;
	  private Type action;
	  private Timestamp takingTime;
	public int getSlotId() {
		return slotId;
	}
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	public Type getAction() {
		return action;
	}
	public void setAction(Type action) {
		this.action = action;
	}
	public Timestamp getTakingTime() {
		return takingTime;
	}
	public void setTakingTime(Timestamp takingTime) {
		this.takingTime = takingTime;
	}
}