package com.parking.logic;

import com.parking.crud.DAO;
import com.parking.crud.ParkingSlotPOJO;
import com.sun.scenario.animation.shared.TimerReceiver;

import java.sql.Timestamp;
import com.tickets.pojo.Ticket;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.jms.JMSException;





@Singleton
@Local
@Startup
public class Manager
{
  private DAO dao;
  private ArrayList<ParkingSlot> parkingSlots;
  private Report report;
  
  @Resource
  private TimerService timerService;
  private Timer timer;
  
  @Inject
  private CommunicateSender communicateSender;
  
  public Manager() {}
  
  @PostConstruct
  public void setupParking()
  {
    dao = new DAO();
    parkingSlots = new ArrayList();
    List<ParkingSlotPOJO> slots = dao.read();
    report = new Report();
    for (int i = 0; i < slots.size(); i++) {
      ParkingSlotPOJO ps = (ParkingSlotPOJO)slots.get(i);
      ParkingSlot parkingSlot = new ParkingSlot();
      parkingSlot.setId(ps.getId());
      parkingSlot.setSector(ps.getZone());
      parkingSlot.setStreet(ps.getStreet());
      parkingSlot.setTaken(false);
      parkingSlots.add(parkingSlot);
      report.startSlotStorage(parkingSlot);
    }
  }
  
  @Lock(LockType.READ)
  public ArrayList<ParkingSlot> getSlots() {
    return parkingSlots;
  }
  
  @Lock(LockType.READ)
  public Report getReport() {
    return report;
  }
  
  @Lock(LockType.WRITE)
  public void addTicket(Ticket ticket) {
    ParkingSlot parkingSlot;
    for (int i = parkingSlots.size() - 1; i >= 0; i--){
    	/*
    	 * Check if
    	 * 	ticket is not already bought for the slot
    	 * 	slot is taken
    	 * 	slot is taken for less than 10 minutes
    	 */
    	if (!parkingSlots.get(i).isTicketBought() && ticket.getMachine_id() == parkingSlots.get(i).getSector() && parkingSlots.get(i).isTaken() && (new Timestamp(System.currentTimeMillis()).getTime() - parkingSlots.get(i).getTimeOfTaking().getTime() < 60000)){
    		//Update Report
    		report.setTickets(report.getTickets() + 1);
    		HashMap<Integer, Integer> sbt = report.getSlotsByTickets();
    		sbt.put(parkingSlots.get(i).getId(), sbt.get(parkingSlots.get(i).getId()) + 1);
    		report.setSlotsByTickets(sbt);
    		HashMap<Integer, Integer> zbt = report.getZonesByTickets();
    		zbt.put(parkingSlots.get(i).getSector(), zbt.get(parkingSlots.get(i).getSector()) + 1);
    		report.setZonesByTickets(zbt);
    		
    		//Setup Timer
    		parkingSlot = parkingSlots.get(i);
    		parkingSlot.setTicketBought(true);
    		System.out.println("Adding timer for ticket: " + parkingSlots.get(i).getId() + " / sector: " + ticket.getMachine_id() + "/ for " + ticket.getTicket_type() + " hours...");
    		//add timer for Number_Of_Hours + 10 minutes
    		//the timer will check if the car has left before the timeout
    		//if not - it will inform the zone manager
    		TimerAction action = new TimerAction();
    		action.setAction(Type.TICKET_TIMEOUT);
    		action.setSlotId(parkingSlot.getId());
    		action.setTakingTime(parkingSlot.getTimeOfTaking());
    		timerService.createSingleActionTimer(ticket.getTicket_type()*360000+parkingSlot.getTimeOfTicket().getTime() + 60000, new TimerConfig(action, true));
    		break;
    	}
    }
  }
  
  @Lock(LockType.WRITE)
  public void carDetected(int id)
  {
	if (!parkingSlots.get(id).isTaken()) {
		//Update Report
		report.setDetections(report.getDetections() + 1);
		HashMap<Integer, Integer> sbd = report.getSlotsByDetections();
		sbd.put(id, sbd.get(id) + 1);
		report.setSlotsByDetections(sbd);
		HashMap<Integer, Integer> zbd = report.getZonesByDetections();
		zbd.put(parkingSlots.get(id).getSector(), zbd.get(parkingSlots.get(id).getSector()) + 1);
		report.setZonesByDetections(zbd);
		
		//Setup Timer
	    System.out.println("Car detected at " + id + "...");
	    ParkingSlot parkingSlot = (ParkingSlot)parkingSlots.get(id);
	    parkingSlot.setTaken(true);
	    TimerAction action = new TimerAction();
		action.setAction(Type.TICKET_CHECK);
		action.setSlotId(parkingSlot.getId());
		action.setTakingTime(parkingSlot.getTimeOfTaking());
	    timerService.createSingleActionTimer(600000, new TimerConfig(action, true));
	}
  }
  
  @Lock(LockType.WRITE)
  public void carLeft(int id) {
    System.out.println("Car left at " + id + "...");
    ParkingSlot parkingSlot = (ParkingSlot)parkingSlots.get(id);
    parkingSlot.setTaken(false);
    parkingSlot.setTicketBought(false);
    System.out.println(((ParkingSlot)parkingSlots.get(id)).isTaken());
  }
  
  @Timeout
  public void timeout(Timer timer) throws JMSException {
	TimerAction timerAction = ((TimerAction) timer.getInfo());
    if (timerAction.getAction() == Type.TICKET_TIMEOUT ){
    	/*
    	 * Timeout for ticket time
    	 * Check if
    	 * 	place still taken
    	 *  if so check whether it is still the same car
    	 */
    	if (parkingSlots.get(timerAction.getSlotId()).isTaken() && timerAction.getTakingTime() == parkingSlots.get(timerAction.getSlotId()).getTimeOfTaking()) {
    		//Update Report
    		report.setTicketsOverTime(report.getTicketsOverTime() + 1);
    		HashMap<Integer, Integer> sbt = report.getSlotsByTicketsOverTime();
    		sbt.put(timerAction.getSlotId(), sbt.get(timerAction.getSlotId()) + 1);
    		report.setSlotsByTicketsOverTime(sbt);
    		HashMap<Integer, Integer> zbt = report.getZonesByTicketsOverTimes();
    		zbt.put(parkingSlots.get(timerAction.getSlotId()).getSector(), zbt.get(parkingSlots.get(timerAction.getSlotId()).getSector()) + 1);
    		report.setZonesByTickets(zbt);
    		
    		//Execute Timer
    		ParkingSlot slot = parkingSlots.get(timerAction.getSlotId());
    		slot.setTicketBought(false);
    		Notification notification = new Notification();
    		notification.setProbablePlace(timerAction.getSlotId());
    		notification.setZone(parkingSlots.get(timerAction.getSlotId()).getSector());
    		notification.setDescription("Staying over time");
    		communicateSender.sendCommunicate(notification);
    		System.out.println("Staying over time: " + parkingSlots.get(timerAction.getSlotId()).getSector() + "/" + timerAction.getSlotId());
    	}
    }
	if (timerAction.getAction() == Type.TICKET_CHECK ){
		/*
		 * Timeout for buying a ticket
		 * Check if
		 * 	same car occupies the slot
		 * 	ticket is bought
		 */
		if (parkingSlots.get(timerAction.getSlotId()).getTimeOfTaking() == timerAction.getTakingTime() && !parkingSlots.get(timerAction.getSlotId()).isTicketBought()) {
			//Update Report
    		report.setTicketsNotBought(report.getTicketsNotBought() + 1);
    		HashMap<Integer, Integer> sbt = report.getSlotsByTicketsNotBought();
    		sbt.put(timerAction.getSlotId(), sbt.get(timerAction.getSlotId()) + 1);
    		report.setSlotsByTicketsNotBought(sbt);
    		HashMap<Integer, Integer> zbt = report.getZonesByTicketsNotBought();
    		zbt.put(parkingSlots.get(timerAction.getSlotId()).getSector(), zbt.get(parkingSlots.get(timerAction.getSlotId()).getSector()) + 1);
    		report.setZonesByTicketsNotBought(zbt);
    		
    		//Execute Timer
			Notification notification = new Notification();
			notification.setProbablePlace(timerAction.getSlotId());
    		notification.setZone(parkingSlots.get(timerAction.getSlotId()).getSector());
    		notification.setDescription("Ticekt not bought");
			communicateSender.sendCommunicate(notification);
			System.out.println("Ticket not bought: " + parkingSlots.get(timerAction.getSlotId()).getSector() + "/" + timerAction.getSlotId());
		}
	}
  }

  public void stopAll()
  {
    for (Object obj : timerService.getTimers()) {
      Timer t = (Timer)obj;
      t.cancel();
    }
  }
}