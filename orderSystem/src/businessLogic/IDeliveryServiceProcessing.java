package businessLogic;

import java.util.List;

/**
 * interface which contains the methods from DeliveryService
 * @author Oana
 *
 */

public interface IDeliveryServiceProcessing {
	/**
	 * 
	 * @param product
	 * @pre product!=NULL
	 * @post productList.size@post == productList.size@post+1
	 */
	void addProduct(MenuItem product);
	
	/**
	 * 
	 * @param product
	 * @pre product!=NULL
	 * @post productList.size@post == productList.size@post-1
	 */
	void deleteProduct(MenuItem product);
	
	/**
	 * 
	 * @param product
	 * * @pre product!=NULL
	 * @post productList.size@post == productList.size@post
	 */
	void editProduct(MenuItem product);
	
	
	
	/**
	 * 
	 * @param client
	 * @pre client!=NULL
	 * @post clients.size@post == clients.size@post+1
	 */
	void addUser(User client);
	
	
	/**
	 * 
	 * @param client
	 * @param products
	 * @param orderID
	 * @pre idC!=NULL
	 * @pre products!=NULL
	 * @pre orderID!=Null
	 * @post products.size@post == products.size@post+1
	 */
	void addOrder(User client, List<MenuItem> products, int orderID);
	
	/**
	 * 
	 * @param order
	 * @pre order!=NULL
	 * @post orderListMap.size@post = orderListMap.size@post -1
	 */
	void removeOrder(Order order);
	
}

















