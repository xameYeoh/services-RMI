package com.getman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DiceService implements Service {
	JPanel output;
	JComboBox numOfDice;
	JPanel guiPanel;

	public JPanel getGuiPanel() {
		guiPanel = new JPanel(new BorderLayout());
		JPanel inputPanel = new JPanel();
		output = new JPanel();

		JButton button = new JButton("Roll");
		button.addActionListener(new RollEmListener());
		String[] choices = { "1", "2", "3", "4", "5" };
		numOfDice = new JComboBox(choices);

		inputPanel.add(numOfDice);
		inputPanel.add(button);
		guiPanel.add(BorderLayout.NORTH, inputPanel);
		guiPanel.add(BorderLayout.CENTER, output);

		return guiPanel;
	}

	class RollEmListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			output.removeAll();
			guiPanel.validate();

			String selection = (String) numOfDice.getSelectedItem();

			int numOfDiceToRoll = Integer.parseInt(selection);

			for (int i = 0; i < numOfDiceToRoll; i++) {
				int number = (int) (Math.random() * 6 + 1);
				Dice dice = new Dice(number);
				output.add(dice);
			}
			guiPanel.repaint();
			guiPanel.validate();
		}
	}

	class Dice extends JPanel {
		int side;
		static int size;
		static int d;
		static int r;

		Dice(int n) {
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			side = n;
		}

		public void paintComponent(Graphics g) {
			g.setColor(Color.BLACK);
			size = this.getHeight();
			d = size / 3;
			r = d / 2;

			switch (side) {
			case 1: {
				CircleDrawer.paintCenterCenter(g);
				break;
			}
			case 2: {
				CircleDrawer.paintUpperRight(g);
				CircleDrawer.paintLowerLeft(g);
				break;
			}
			case 3: {
				CircleDrawer.paintUpperRight(g);
				CircleDrawer.paintCenterCenter(g);
				CircleDrawer.paintLowerLeft(g);
				break;
			}
			case 4: {
				CircleDrawer.paintUpperLeft(g);
				CircleDrawer.paintUpperRight(g);
				CircleDrawer.paintLowerLeft(g);
				CircleDrawer.paintLowerRigth(g);
				break;
			}
			case 5: {
				CircleDrawer.paintUpperLeft(g);
				CircleDrawer.paintUpperRight(g);
				CircleDrawer.paintCenterCenter(g);
				CircleDrawer.paintLowerLeft(g);
				CircleDrawer.paintLowerRigth(g);
				break;
			}
			case 6: {
				CircleDrawer.paintUpperLeft(g);
				CircleDrawer.paintUpperRight(g);
				CircleDrawer.paintCenterLeft(g);
				CircleDrawer.paintCenterRight(g);
				CircleDrawer.paintLowerLeft(g);
				CircleDrawer.paintLowerRigth(g);
				break;
			}
			default:
				break;
			}

		}

		class CircleDrawer {
			static void paintUpperLeft(Graphics g) {
				g.fillOval(0, 0, d, d);
			}

			static void paintUpperRight(Graphics g) {
				g.fillOval(size - d, 0, d, d);
			}

			static void paintCenterLeft(Graphics g) {
				g.fillOval(0, size / 2 - r, d, d);
			}

			static void paintCenterCenter(Graphics g) {
				g.fillOval(size / 2 - r, size / 2 - r, d, d);
			}

			static void paintCenterRight(Graphics g) {
				g.fillOval(size - d, size / 2 - r, d, d);
			}

			static void paintLowerLeft(Graphics g) {
				g.fillOval(0, size - d, d, d);
			}

			static void paintLowerRigth(Graphics g) {
				g.fillOval(size - d, size - d, d, d);
			}
		}
	}
}
