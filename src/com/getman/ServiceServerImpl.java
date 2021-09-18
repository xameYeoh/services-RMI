package com.getman;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer {
	HashMap services; 
	
	ServiceServerImpl() throws RemoteException {
		setUpServices();
	};
	
	private void setUpServices() {
		services = new HashMap();
		services.put("Dice Rolling Service", new DiceService());
		services.put("Day of the Week Service", new DayOfTheWeekService());
		services.put("Visual Music Service", new MiniMusicService());
	}

	public Object[] getServicesArray() {
		System.out.println("in remote");
		Object[] servicesArray = services.keySet().toArray(); 
		return servicesArray;
	}

	public Service getService(Object objectKey) {
		Service theService = (Service) services.get(objectKey);
		return theService;
	}
	
	public static void main(String[] args) {
		try {
			Naming.rebind("ServiceServer", new ServiceServerImpl());
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Remote service is running");
	}
}
