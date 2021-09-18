package com.getman;
import javax.swing.*;

public class DayServiceTest {
	public static void main(String[] args) {
		DiceService service = new DiceService();
		
		JFrame frame = new JFrame();
		frame.getContentPane().add(service.getGuiPanel());
		frame.setSize(400,400);
		frame.setVisible(true);
	}
	
	
}
