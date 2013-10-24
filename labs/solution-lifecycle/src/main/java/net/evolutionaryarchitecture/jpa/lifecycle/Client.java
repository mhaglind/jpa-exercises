package net.evolutionaryarchitecture.jpa.lifecycle;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Client {

	public static void main(String[] args) {
		createWindow();
		createWindow();
	}

	private static void createWindow() {
		JFrame frame = new JFrame("Person CRUD");
		frame.getContentPane().add(new PersonPanel());
		frame.pack();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				JpaUtil.getEntityManagerFactory().close();

			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
