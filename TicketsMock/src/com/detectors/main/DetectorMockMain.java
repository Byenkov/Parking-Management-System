package com.detectors.main;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Date;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.tickets.pojo.Detector;
import com.tickets.pojo.Ticket;

@SuppressWarnings("deprecation")
public class DetectorMockMain {
	public static void main(String [] args) throws IOException{
		int slot;
        String slotS;
        int choice;
        String choiceS;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true){
			System.out.println("Type slot ID:");
	        slotS = br.readLine();
			try{
		        slot = Integer.parseInt(slotS);
				}catch(NumberFormatException e){
					System.out.println("Wrong Format - try again.");
					continue;
				}
			System.out.println("Type 1 if slot is set to available;\nType 2 if slot is set to taken:");
			choiceS = br.readLine();
			try{
		        choice = Integer.parseInt(choiceS);
				}catch(NumberFormatException e){
					System.out.println("Wrong Format! Try again.");
					continue;
				}

			switch(choice){
			case 1:
				Detector detector = new Detector();
				detector.setId(slot);
				detector.setState(false);
				postDetector(detector);
				break;
			case 2:
				Detector detector1 = new Detector();
				detector1.setId(slot);
				detector1.setState(true);
				postDetector(detector1);
				break;
			default:
				System.out.println("Wrong input - try again.");
			}
		}
	}
	
	private static void postDetector(Detector detector){
		try {

			Client client = Client.create();

			WebResource webResource = client
				   .resource("http://localhost:8080/ParkingLogics/rest/detector/post");
			String input = "{\"id\":"+detector.getId()+",\"state\":"+detector.isState()+"}";
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

