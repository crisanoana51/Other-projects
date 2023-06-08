package businessLogic;

import java.util.List;


/**
 * Class representing the product composed of other base products kept in a list
 */
public class CompositeProduct extends MenuItem {

	
	//private static final long serialVersionUID = 1L;
	
	private String title;
	private List<MenuItem> components;
	
	
	
	
	public CompositeProduct(String title, List<MenuItem> components) {
		super();
		this.title = title;
		this.components = components;
	}
	
	


	public List<MenuItem> getComponents() {
		return components;
	}




	public void setComponents(List<MenuItem> components) {
		this.components = components;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getTitle() {
		return title;
	}

	/**
	 * Methods used for computing the details of the composed product
	 */
	public double getRating() {
		double rating = 0;
		int nrComponents=0;
		for(MenuItem product : components) {
			rating=rating + product.getRating();
			nrComponents++;
		}
		
		return rating/nrComponents;
	}

	
	public int getCalories() {
		
		int sumCalories=0;
		for(MenuItem product : components) {
			sumCalories=sumCalories + product.getCalories();
		}
		
		return sumCalories;
	}

	
	public double getProteins() {
		double sumProteins=0;
		for(MenuItem product : components) {
			sumProteins=sumProteins + product.getProteins();
		}
		
		return sumProteins;
	}

	
	public double getFats() {
		double sumFats=0;
		for(MenuItem product : components) {
			sumFats=sumFats + product.getFats();
		}
		
		return sumFats;
	}

	
	public double getSodium() {
		
		double sumSodium=0;
		for(MenuItem product : components) {
			sumSodium=sumSodium + product.getSodium();
		}
		
		return sumSodium;
	}


	public double getPrice() {
		double total=0;
		for(MenuItem product : components) {
			total=total + product.getFats();
		}
		
		return total;
	}
	
	

}
