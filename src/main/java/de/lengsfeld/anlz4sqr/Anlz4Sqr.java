package de.lengsfeld.anlz4sqr;

import de.lengsfeld.anlz4sqr.connect.FSConnect;
import de.lengsfeld.anlz4sqr.connect.FSConnector;

import java.awt.*;

public class Anlz4Sqr {

	public static void main(String[] args) {
		FSConnector fsConnect = FSConnect.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
