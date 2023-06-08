package main;

import businessLogic.DeliveryService;
import gui.LoginPage;

public class AppStart {
	
	public static void main(String[] args) {
		
		DeliveryService dv = new DeliveryService();
		LoginPage lg = new LoginPage(dv);
		lg.setVisible(true);
	}
}
