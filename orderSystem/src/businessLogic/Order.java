package businessLogic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

	private int orderID;
	private int clientID;
	private Date orderDate;
	private List<MenuItem> orderedProducts;
	
	public Order(int orderID, int clientID, Date orderDate, List<MenuItem> orderedProducts) {
		super();
		this.orderID = orderID;
		this.clientID = clientID;
		this.orderDate = orderDate;
		this.orderedProducts = orderedProducts;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<MenuItem> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<MenuItem> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}
	
	public double totalPrice() {
		
		double totalPrice=0;
		
		for(MenuItem i : orderedProducts)
		{
			totalPrice = totalPrice + i.getPrice();
		}
		
		return totalPrice;
	}
	
}
