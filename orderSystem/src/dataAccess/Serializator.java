package dataAccess;

import businessLogic.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;


public class Serializator<T> {

	String className;
	StringBuilder fileName = new StringBuilder();
	
	public Serializator(String className) {
		this.className = className;
		fileName.append(className);
		fileName.append(".txt");
	}
	
	/**
     * Method that serializes all the data from DeliveryService
     * @param data - arrayList with the data that needs to be serialized
     */
	
	public void serialize(ArrayList<T> data) {
		
		try {
			FileOutputStream file = new FileOutputStream(fileName.toString());
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			out.writeObject(data);
			
			out.close();
			file.close();
			
			System.out.println("Object has been serialized");
			
		}catch(IOException ex) {
			System.out.println("IOException is caught");
		}
	}
	
	/**
     *  Method that deserializes all the data
     * @return the deserialized data
     */
	public ArrayList<T> deserialize() {
		
		try {
			FileInputStream file = new FileInputStream(fileName.toString());
			ObjectInputStream in = new ObjectInputStream(file);
			
			ArrayList<T> data = new ArrayList<T>();
			data = (ArrayList<T>) in.readObject();
			
			in.close();
			file.close();
			
			System.out.println("Object has been deserialized");	
			return data;
			
		}catch(IOException ex){
			System.out.println("IOException is caught!");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<T>();
	
	}
	
	
}
