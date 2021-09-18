package com.getman;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniMusicService implements Service{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyDrawPanel drawPanel;
	
	public JPanel getGuiPanel() {
		JPanel mainPanel = setUp(new JPanel(new BorderLayout()));
		
		return mainPanel;
	}

	static MidiEvent makeEvent(int command, int channel, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(command, channel, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) {
		}

		return event;
	}

	static Color getRandomColor() {
		int r = (int) (Math.random() * 255);
		int g = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);

		return new Color(r, g, b);
	}

	static void drawRandomRectangle(Graphics2D g) {
		int height = (int) (Math.random() * 120 + 10);
		int width = (int) (Math.random() * 120 + 10);
		int x = (int) (Math.random() * 40 + 10);
		int y = (int) (Math.random() * 40 + 10);

		g.fillRect(x, y, height, width);
	}
	
	private JPanel setUp(JPanel mainPanel) {
		JPanel buttonPanel = new JPanel();
		drawPanel = new MyDrawPanel();
		JButton button = new JButton("Play it");
		
		buttonPanel.add(button);
		button.addActionListener(new PlayItListener());
		
		mainPanel.add(BorderLayout.CENTER, drawPanel);
		mainPanel.add(BorderLayout.SOUTH, buttonPanel);
		
		return mainPanel;
	}

	private void setUp(Sequencer sequencer) throws Exception {
		Sequence sequence = new Sequence(Sequence.PPQ, 4);
		int[] eventsIWant = { 127 };

		setUp(sequence);

		sequencer.setSequence(sequence);
		sequencer.setTempoInBPM(220);
		sequencer.addControllerEventListener(drawPanel, eventsIWant);

	}

	private Sequence setUp(Sequence sequence) throws Exception {

		Track track = sequence.createTrack();

		modify(track);

		return sequence;
	}

	private void modify(Track track) throws Exception {
		int randomNote;
		for (int i = 0; i < 100; i += 4) {
			randomNote = (int) (Math.random() * 50) + 30; //ноты начинаются с 1
			
			track.add(makeEvent(176, 1, 127, 0, i)); // in this order sound does not get muffled
			track.add(makeEvent(144, 1, randomNote, 100, i));
			track.add(makeEvent(128, 1, randomNote, 100, i + 2));
		}
	}

	class MyDrawPanel extends JPanel implements ControllerEventListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2L;
		private boolean nextNoteStarted = false;

		public void controlChange(ShortMessage event) {
			nextNoteStarted = true;
			repaint();
		}

		public void paintComponent(Graphics g) {
			if (nextNoteStarted) {

				Graphics2D g2 = (Graphics2D) g;

				g2.setColor(getRandomColor());

				drawRandomRectangle(g2);
				
				nextNoteStarted = false;
			}
		}
	}

	class PlayItListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			try {
				Sequencer sequencer = MidiSystem.getSequencer();
				
				sequencer.open();
				setUp(sequencer);
				sequencer.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

