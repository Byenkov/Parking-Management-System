package com.parking.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.parking.services.SlotService;



public class ParkingMockMain {
	public static void main(String [] args) throws IOException, MalformedURLException{
		URL url = new URL("http://localhost:8080/ParkProject/SlotServiceImplementation?wsdl");

		QName qname = new QName("http://services.parking.com/", "SlotServiceImplementationService");

		Service service = Service.create(url, qname);
		
		SlotService slotService = service.getPort(SlotService.class);

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
				slotService.setParkingSlotFree(slot);
				break;
			case 2:
				slotService.setParkingSlotTaken(slot);
				break;
			default:
				System.out.println("Wrong input - try again.");
			}
		}
	}

}
