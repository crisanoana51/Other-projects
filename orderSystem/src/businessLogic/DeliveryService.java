package businessLogic;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import dataAccess.FileWriter;
import dataAccess.Serializator;
import gui.EmployeeInterface;

import static java.util.stream.Collectors.toList;

/**
 * Class that contains the most important methods for the application
 * @author Oana
 *
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing{
	
	Map<Order, List<MenuItem>> orderListMap;
	
	ArrayList<MenuItem> productList;
	ArrayList<Order> orders;
	ArrayList<User> clients;
	ArrayList<User> admins;
	ArrayList<User> employee;
	int nrC;
	int nrA;
	int nrE;
	
	FileWriter fw;
	Observable observable;
	
	Serializator<MenuItem> menuItemSerializator;
	Serializator<Order> orderSerializator;
	Serializator<User> clientSerializator;
	
	public DeliveryService() {
		super();
		this.orderListMap = new HashMap< Order, List<MenuItem>>();
		this.productList = new ArrayList<>();
		this.orders = new ArrayList<>();
		this.clients = new ArrayList<>();
		this.fw = new FileWriter();
		this.observable = new Observable();
		this.menuItemSerializator = new Serializator("MenuItem");
		this.orderSerializator = new Serializator("Order");
		this.clientSerializator = new Serializator("User");
		this.admins = new ArrayList<>();
		this.employee = new ArrayList<>();
		nrC=0;
		nrA=0;
		nrE=0;
		createAdm();
		//readCSV();
	}
	
	
	public int getNrC() { return nrC;}
	
	public Map<Order, List<MenuItem>> getOrderListMap() {
		return orderListMap;
	}
	public void setOrderListMap(Map<Order, List<MenuItem>> orderListMap) {
		this.orderListMap = orderListMap;
	}
	public ArrayList<MenuItem> getProductList() {
		return productList;
	}
	public void setProductList(ArrayList<MenuItem> productList) {
		this.productList = menuItemSerializator.deserialize();
	}
	public ArrayList<Order> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orderSerializator.deserialize();
	}
	public ArrayList<User> getUsers() {
		return clients;
	}
	public void setUsers(ArrayList<User> clients) {
		this.clients = clientSerializator.deserialize();
	}
	public FileWriter getFw() {
		return fw;
	}
	public void setFw(FileWriter fw) {
		this.fw = fw;
	}
	public Serializator<MenuItem> getMenuItemSerializator() {
		return menuItemSerializator;
	}
	public void setMenuItemSerializator(Serializator<MenuItem> menuItemSerializator) {
		this.menuItemSerializator = new Serializator("products");
	}
	public Serializator<Order> getOrderSerializator() {
		return orderSerializator;
	}
	public void setOrderSerializator(Serializator<Order> orderSerializator) {
		this.orderSerializator = new Serializator("orders");
	}
	public Serializator<User> getUserSerializator() {
		return clientSerializator;
	}
	public void setUserSerializator(Serializator<User> clientSerializator) {
		this.clientSerializator = new Serializator("clients");
	}
	
	public <T >boolean wellformed (T t) {
		if(t!=null) {
			return true;
		}
		return false;
	}
	
	
	public void sortUsers(User user)
	{
		if(user.getCateg().equals("client")) {
			clients.add(user);
			nrC++;
		}
		if(user.getCateg().equals("admin")) {
			admins.add(user);
			nrA++;
		}
		if(user.getCateg().equals("employee")) {
			employee.add(user);
			nrE++;
		}
	}
	
	public boolean isRegistered(String username, String password, String categ) {
		
		if(categ.equals("client")) {
			for(User i : clients) {
				if(username.equals(i.getUsername()) && password.equals(i.getPassword())) {
					return true;
				}
			}
		}
		
		if(categ.equals("admin")) {
			for(User i : admins) {
				if(username.equals(i.getUsername()) && password.equals(i.getPassword())) {
					return true;
				}
			}
		}
		
		if(categ.equals("employee")) {
			for(User i : employee) {
				if(username.equals(i.getUsername()) && password.equals(i.getPassword())) {
					return true;
				}
			}
		}
		
		return false;
		
	}
	public void addUser(User client) {
		if(wellformed(client)) {
			clients.add(client);
			fw.update(orders, productList, clients);
			JOptionPane.showMessageDialog(null, "User added!");
		}
	}
	
	public void addProduct(MenuItem product) {
		
		System.out.println(productList.size() + " ");
		assert wellformed(product);
			
				productList.add(product);
				fw.update(orders, productList, clients);
			
			System.out.println(productList.size() + " ");
			JOptionPane.showMessageDialog(null, "Product added!");
		
	}
	
	public void deleteProduct(MenuItem product) {
		assert wellformed(product);
			for(MenuItem mi : productList ) {
				
				if(mi.getTitle().equals(product.getTitle())) {
					productList.remove(mi);
					JOptionPane.showMessageDialog(null, "Product deleted!");
					break;
				}
				
			}
			fw.update(orders, productList, clients);
		
	}
	public void editProduct(MenuItem product) {
		
		assert wellformed(product); 
			for(MenuItem mi : productList ) {
				
				if(mi.getTitle().equals(product.getTitle())) {
					productList.remove(mi);
					productList.add(product);
					JOptionPane.showMessageDialog(null, "Update successfull!");
				}
				
			}
			fw.update(orders,productList, clients);
		
		
	}
	
	public void addOrder(int idC, List<MenuItem> products, int orderID, EmployeeInterface em) {
			
		if(products.size()!=0) {
			Order order = new Order(orderID,idC,java.util.Calendar.getInstance().getTime(), products);
			orders.add(order);
			orderListMap.put(order, products);
			StringBuilder s = new StringBuilder();
			s.append("New Order\n" + "Order " + orderID);
			setChanged();
	        notifyObservers(s.toString());
	        em.setVisible(false);
			JOptionPane.showMessageDialog(null, "Order placed!");
		}
		
	}
	
	public int getOrderIndex() { 
		
		return orders.size() + 1;
		}
	
	public void removeOrder(Order order) {
		orderListMap.remove(order);
	}
	
	 public JTable filterProduct(String[] GUIElements) {
		System.out.println(GUIElements[0]);
        List<MenuItem> productsF = new ArrayList<MenuItem>();
        if (!GUIElements[0].equals("")) {
            productsF = productList.stream().filter(p -> (p.getTitle().contains(GUIElements[0]))).collect(toList());    //title
        } else if (!GUIElements[1].equals("")) {
            productsF = productList.stream().filter(p -> (GUIElements[1].equals(String.valueOf(p.getRating())))).collect(toList());   //rating
        } else if (!GUIElements[2].equals("")) {
        	productsF = productList.stream().filter(p -> (GUIElements[2].equals(String.valueOf(p.getCalories())))).collect(toList());    //calories
        } else if (!GUIElements[3].equals("")) {
        	productsF = productList.stream().filter(p -> (GUIElements[3].equals(String.valueOf(p.getProteins())))).collect(toList());  //proteins
        } else if (!GUIElements[4].equals("")) {
        	productsF = productList.stream().filter(p -> (GUIElements[4].equals(String.valueOf(p.getFats())))).collect(toList());    //fats
          
        } else if (!GUIElements[5].equals("")) {
        	productsF = productList.stream().filter(p -> (GUIElements[5].equals(String.valueOf(p.getSodium())))).collect(toList());  //sodium

        } else if (!GUIElements[6].equals("")) {
        	productsF = productList.stream().filter(p -> (GUIElements[6].equals(String.valueOf(p.getPrice())))).collect(toList());    //price
        }
        
        System.out.println(productsF.toString());
        
        return menuTable(productsF);
       
    }
	
	
	public void administratorMenu( ArrayList<MenuItem> products, String title)
	{
		CompositeProduct c = new CompositeProduct(title, products);
		productList.add(c);
	}
	
	public JTable orderTable(Map<Order, List<MenuItem>> orders) {
		JTable table;
		String columns[]= {"Order Id", "User", "Date", "Products"};
		 String rows[][] = new String[100][4];
		 int i= -1;
		for (Map.Entry<Order, List<MenuItem>> orderListEntry : orders.entrySet()) {
          
           rows[i][0] = String.valueOf(orderListEntry.getKey().getOrderID());
           rows[i][1] = String.valueOf(orderListEntry.getKey().getClientID());
           rows[i][2] = orderListEntry.getKey().getOrderDate().toString();
           
           rows[i][3] = orderListEntry.getValue().toString();
           i++;
            
        }
		
		table= new JTable(rows,columns);
		return table;
	}
	
	public void generateReport1(String startHour, String endHour) {
		System.out.print("ok");
		 String[] start = startHour.split(":");
	     String[] end = endHour.split(":");
	     
		
		   List<Order> ordersR = new ArrayList<>();
	        ordersR = orders.stream().filter(o -> (o.getOrderDate().getHours() > Integer.parseInt(start[0]) && o.getOrderDate().getHours() <Integer.parseInt(end[0]))).collect(Collectors.toList());
	        StringBuilder sb = new StringBuilder();
	        sb.append("Orders between " + start[0] + " and " + end[0] + "\n");
	        for(Order i : ordersR) {
	        	
	        	sb.append(i.getOrderID() +" ");
	        	sb.append("\n");
	        }
	        
	        fw.writeTextFile("Raport1.txt", sb.toString());
	        
	

     
	        
	}
	
	public void generateReport2(int nrTimes) {
		
		Map<MenuItem,Long> map = new HashMap<>();
        Map<MenuItem,Long> mapFinal = new HashMap<>();
        
        for(Map.Entry<Order,List<MenuItem>> o: orderListMap.entrySet()) {
	            map =  o.getValue().stream() .collect(Collectors.groupingBy( Function.identity(),
	                                        Collectors.counting()));
	             
	       
	             
	           map .entrySet().forEach( element -> {
	                                        if (mapFinal.get(element.getKey()) != null) {
	                                        	
	                                        	 long number = mapFinal.get(element.getKey()) + element.getValue() ;
	                                        	 MenuItem m = element.getKey();
		                                         mapFinal.put(m , number);
		                                            
	                                          
	                                        } else {
	                                        	 MenuItem m = element.getKey();
	                                        	 long number = element.getValue() ;
	                                        	 mapFinal.put(m, number);
	                                        }
	                                    });
	            }
        
        StringBuilder sb = new StringBuilder();
        sb.append("The products ordered more than a specified number of times so far where n = "+ nrTimes + "\n");
        for(   Map.Entry<MenuItem,Long> i: mapFinal.entrySet()){
        	 if( i.getValue() > nrTimes){
                 sb.append( i.getKey().getTitle());
        	 }
        }
        
        fw.writeTextFile("Raport2.txt", sb.toString());
        
	}
	
	public void generateReport3(double price, int nrTimes) {
		Map<Integer,Long> mapClients = new HashMap<>();


        mapClients = orders.stream()
                .filter(o -> o.totalPrice() > price)
                .collect(Collectors.groupingBy(Order::getClientID,
                Collectors.counting()));
        
        StringBuilder sb = new StringBuilder();
        sb.append("the clients that have ordered more than a specified number of times so far and the\r\n"
        		+ "value of the order was higher than a specified amount is: \n");
        for( Map.Entry<Integer,Long> i: mapClients.entrySet()) {
        	if( i.getValue() >= nrTimes) {
        	sb.append("The client with id = 1 has ordered "+ i.getValue()+ " times" + "\n");	
        	}
        }
        
        fw.writeTextFile("Raport3.txt", sb.toString());

	}
	
	public void generateReport4( int day, int month, int year) {
			
		int nrOrders=0;
		  Map<Order, List<MenuItem>> ordersR = new HashMap<Order, List<MenuItem>>();
	        ordersR = orderListMap.entrySet().stream().filter(o -> o.getKey().getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth() == day &&  o.getKey().getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == month &&  o.getKey().getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == year)
	        		.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
	        
	        nrOrders = ordersR.size();
	     
	        //count nr of times 
	        Map<MenuItem,Long> map = new HashMap<>();
	        Map<MenuItem,Long> mapFinal = new HashMap<>();
	        
	      
	       
	        for(Map.Entry<Order,List<MenuItem>> o: ordersR.entrySet()) {
		            map =  o.getValue().stream() .collect(Collectors.groupingBy( Function.identity(),
		                                        Collectors.counting()));
		             
		       
		             
		           map .entrySet().forEach( element -> {
		                                        if (mapFinal.get(element.getKey()) != null) {
		                                        	
		                                        	 long number = mapFinal.get(element.getKey()) + element.getValue() ;
		                                        	 MenuItem m = element.getKey();
			                                         mapFinal.put(m , number);
			                                            
		                                          
		                                        } else {
		                                        	 MenuItem m = element.getKey();
		                                        	 long number = element.getValue() ;
		                                        	 mapFinal.put(m, number);
		                                        }
		                                    });
		      }
	        
	        StringBuilder sb = new StringBuilder();
	        sb.append("the products ordered within " +day+"."+month+"."+year+" with the number of times they have\r\n"
	        		+ "been ordered\n");
	        for(   Map.Entry<MenuItem,Long> i: mapFinal.entrySet()){
	       
	                 sb.append( i.getKey().getTitle() + " " + i.getValue()/nrOrders + "\n");
	        	 }
	    
	        
	        fw.writeTextFile("Raport4.txt", sb.toString());
	        
	        
	        
	}
	
	public JTable menuTable(List<MenuItem> list) {
		JTable table;
        Field[] fields =list.get(0).getClass().getDeclaredFields();
        int n=fields.length;
        String[] columnTitle = new String[n];
        Object[][] rows = new Object[productList.size()][n];
       
        int col=0;
        int row=-1;

        for(Field field : fields) {

        	field.setAccessible(true);
            columnTitle[col]=field.getName();
            col++;
        }

        for(MenuItem t : list) {
            row++;
            col=0;
            for(Field field : fields) {
            	field.setAccessible(true);
                try {
                    rows[row][col]=field.get(t);
                    col++;
                } catch (IllegalArgumentException e) {
                   
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    
                    e.printStackTrace();
                }

            }
        }
       table = new JTable(rows,columnTitle);
       return table;
	}
	
	
	public JTable createViewTable() {
		return menuTable(productList);
	}

	public void readCSV() {
		List<String[]> lines = null; 
		try {
			lines = Files.lines(Paths.get("products.csv")) .skip(1) .map(line -> line.split(",")) .toList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set<BaseProduct> seen = new HashSet<>();
		List<String[]> prod =   lines.stream()
		  .filter(p -> !seen.add(new BaseProduct( p[0],Float.parseFloat(p[1]), Integer.parseInt(p[2]),
                  Integer.parseInt(p[3]), Integer.parseInt(p[4]), Integer.parseInt(p[5]), Integer.parseInt(p[6]))))
		  .distinct()
		  .collect(toList());
		
		Iterator<BaseProduct> i = seen.iterator();
        while (i.hasNext()) {
  
          productList.add(i.next());
        }
	
	}
	
	

    public MenuItem findProduct(String title) {
        for(MenuItem m : productList){
            if(m.getTitle().equals(title)){
                return m;
            }
        }
        return null;
}
	
	
	private void createAdm() {
		User u = new User( 0, "Ion", "ion12", "abc", "admin");
		sortUsers(u);
		
		User e = new User( 0, "Ioana", "ioana3", "floare", "employee");
		sortUsers(e);
		
		User c = new User ( 0, "oana", "oana1", "oanabanana", "client");
		sortUsers(c);
	}


	@Override
	public void addOrder(User client, List<MenuItem> products, int orderID) {
		// TODO Auto-generated method stub
		
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


