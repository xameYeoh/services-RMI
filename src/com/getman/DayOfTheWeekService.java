package com.getman;
import javax.swing.*;
import java.text.*;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DayOfTheWeekService implements Service{
	private JComboBox months;
	private JTextField dayField;
	private JTextField yearField;
	private JLabel outputLabel;
	
	public JPanel getGuiPanel() {
		JPanel panel = modify(new JPanel());
		return panel;
	}
	private JPanel modify(JPanel panel) {
		
		DateFormatSymbols dateStuff = new DateFormatSymbols();
		months = new JComboBox(dateStuff.getMonths());
		JButton button = new JButton("Calculate");
		button.addActionListener(new DayOfTheWeekListener());
		outputLabel = new JLabel("Week day");
		dayField = new JTextField(8);
		yearField = new JTextField(8);
		
		JPanel choicePanel = new JPanel(new GridLayout(3,2));
		choicePanel.add(new JLabel("Month"));
		choicePanel.add(months);
		choicePanel.add(new JLabel("Day"));
		choicePanel.add(dayField);
		choicePanel.add(new JLabel("Year"));
		choicePanel.add(yearField);
		
		panel.add(choicePanel);
		panel.add(button);
		panel.add(outputLabel);
		
		return panel;
	}
	
	class DayOfTheWeekListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			int month = months.getSelectedIndex();
			int day = Integer.parseInt(dayField.getText());
			int year = Integer.parseInt(yearField.getText());
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day);
			
			Date date = calendar.getTime();
			
			String weekDay = (new SimpleDateFormat("EEEE")).format(date);
			
			outputLabel.setText(weekDay);
		}
	}
}
