package dataAccess;

import java.io.IOException;
import java.util.ArrayList;

import businessLogic.Order;
import businessLogic.User;
import businessLogic.MenuItem;


/**
 * Method that write into a file or creates a new one and writes the given string - writeTextFile
 * Method for updating the data
 * @param text - string needed to be written
 * @param fileName - the name of the file in which the information will be placed
 */

public class FileWriter {

	
	
	public void update(ArrayList<Order> orderList, ArrayList<MenuItem> productsList, ArrayList<User> clientList) {
		Serializator<Order> orderSerializator;
	Serializator<MenuItem> menuItemSerializator;
	Serializator<User> clientSerializator;
		
		orderSerializator = new Serializator<Order>("orders");
		menuItemSerializator = new Serializator<MenuItem>("products");
		clientSerializator = new Serializator<User>("clients");
		
		orderSerializator.serialize(orderList); 
		menuItemSerializator.serialize(productsList);
		clientSerializator.serialize(clientList); 
	}
	
	
	public void writeTextFile(String name, String text) {
		 try {
			java.io.FileWriter file = new java.io.FileWriter(name);
			file.write(text);
			  file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
