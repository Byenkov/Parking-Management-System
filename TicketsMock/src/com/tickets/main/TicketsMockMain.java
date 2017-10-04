package com.tickets.main;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Date;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.tickets.pojo.Ticket;

@SuppressWarnings("deprecation")
public class TicketsMockMain {
	public static void main(String [] args) throws IOException{
		int id;
		String idS;
		int type;
		String typeS;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true){
			System.out.println("Type Ticket Machine ID:");
			idS = br.readLine();
			try{
				id = Integer.parseInt(idS);
				}catch(NumberFormatException e){
					System.out.println("Wrong Format - try again.");
					continue;
				}
			System.out.println("Type ticket time (1/2/3/4 hrs):");
			typeS = br.readLine();
			try{
				type = Integer.parseInt(typeS);
				if (type < 0 || type > 4) throw new NumberFormatException();
				}catch(NumberFormatException e){
					System.out.println("Wrong Format! Try again.");
					continue;
				}
			
			Ticket ticket = new Ticket();
			ticket.setMachine_id(id);
			ticket.setTicket_type(type);
			
			postTicket(ticket);
		}
	}
	
	private static void postTicket(Ticket ticket){
		try {

			Client client = Client.create();

			WebResource webResource = client
				   .resource("http://localhost:8080/ParkProject/rest/ticket/post");
			String input = "{\"ticket_type\":"+ticket.getTicket_type()+",\"machine_id\":"+ticket.getMachine_id()+"}";
			ClientResponse response = webResource.type("application/json")
				   .post(ClientResponse.class, input);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }
	 }
}

