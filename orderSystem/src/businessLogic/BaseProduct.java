package businessLogic;

public class BaseProduct extends MenuItem {
	

	//private static final long serialVersionUID = 1L;
	
	private String title;
	private double rating;
	private int calories;
	private double proteins;
	private double fats;
	private double sodium;
	private double price;
	
	public BaseProduct(String title, double rating, int calories, double proteins, double fats, double sodium, double price) {
		
		super();
		this.title = title;
		this.rating = rating;
		this.calories = calories;
		this.proteins = proteins;
		this.fats = fats;
		this.sodium = sodium;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public double getProteins() {
		return proteins;
	}

	public void setProteins(double proteins) {
		this.proteins = proteins;
	}

	public double getFats() {
		return fats;
	}

	public void setFats(double fats) {
		this.fats = fats;
	}

	public double getSodium() {
		return sodium;
	}

	public void setSodium(double sodium) {
		this.sodium = sodium;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	

}
