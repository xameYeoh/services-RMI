package com.getman;
import java.awt.*;
import javax.swing.*;

public class TestCircle {
	public static void main(String[] args) {
		new TestCircle().go();
	}
	
	void go() {
		JFrame frame = new JFrame();
		MyPanel panel = new MyPanel();
		frame.getContentPane().add(panel);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	class MyPanel extends JPanel{
		
		MyPanel(){
			this.setSize(300, 300);
		}
		public void paintComponent(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillOval(0, 0, 50, 50);
			g.fillOval(50, 50, 50, 50);
		}
	}
}
