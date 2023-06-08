package businessLogic;

import java.io.Serializable;


public abstract class MenuItem implements Serializable {
	
	public abstract String getTitle();
	public abstract double getRating();
	public abstract int getCalories();
	public abstract double getProteins();
	public abstract double getFats(); 
	public abstract double getSodium();
	public abstract double getPrice();
	
	
	/**
	 * Method that constructs the necessary string for serailization
	 * @return string for serialization
	 */
	public String printData() {
		StringBuilder sb = new StringBuilder();
		sb.append("MenuItem: ");
		sb.append("title: " + getTitle() + "/");
		sb.append("rating: " + getRating() + "/");
		sb.append("number of calories: " + getCalories() + "/");
		sb.append("proteins: " + getProteins() + "/");
		sb.append("fats: " + getFats() + "/");
		sb.append("sodium: " + getSodium() + "/");
		sb.append("price: " + getPrice() + "/");
		
		return sb.toString();
	}
	

	

}
