package com.getman;

import java.rmi.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ServiceBrowser {
	JPanel mainPanel;
	JComboBox serviceList;
	ServiceServer server;
	
	public static void main(String[] args) {
		new ServiceBrowser().buildGui();
	}

	void buildGui() {
		JFrame frame = new JFrame("RMI Browser");
		mainPanel = new JPanel(new BorderLayout());
		frame.getContentPane().add(mainPanel);

		Object[] services = getServicesList();

		serviceList = new JComboBox(services);
		frame.getContentPane().add(BorderLayout.NORTH, serviceList);
		serviceList.addActionListener(new ComboBoxListener());

		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	Object[] getServicesList() {
		Object[] services = null;

		try {
			server = (ServiceServer) Naming.lookup("rmi://localhost/ServiceServer");
			System.out.println("Service found");

			services = server.getServicesArray();
			System.out.println("Services array received");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("URL is not correct");
		} catch (NotBoundException e) {
			System.out.println("The corresponding service name was not found");
		}

		return services;
	}

	void loadSelectedService(Object selected) {
		try {
			Service currentService = server.getService(selected);
			mainPanel.removeAll();
			mainPanel.add(currentService.getGuiPanel());
			mainPanel.validate();
			mainPanel.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	class ComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object selection = serviceList.getSelectedItem();
			loadSelectedService(selection);
		}
	}
}
