package com.parking.rest;

import com.parking.logic.Manager;
import com.tickets.pojo.Ticket;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/ticket")
public class TicketRest
{
  @EJB
  Manager manager;
  
  public TicketRest() {}
  
  @POST
  @Consumes({"application/json"})
  @Path("/post")
  public Response addCat(Ticket ticket)
  {
    String result = "Ticket added : " + ticket.getTicket_type() + "/" + ticket.getMachine_id();
    
    manager.addTicket(ticket);
    
    return Response.status(201).entity(result).build();
  }
}