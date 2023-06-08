package gui;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import businessLogic.DeliveryService;
import businessLogic.MenuItem;
import businessLogic.Order;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


/**
 * The graphical user interface for the Client 
 */
public class ClientInterface extends JFrame {

    private final JLabel title = new JLabel("CLIENT");
    private final JLabel instructions = new JLabel("Introduce the name of the product:");

    private final JButton nameB = new JButton("Name");
    private final JButton ratingB = new JButton("Rating");
    private final JButton caloriesB = new JButton("Calories");
    private final JButton proteinB = new JButton("Protein");
    private final JButton fatB = new JButton("Fat");
    private final JButton sodiumB = new JButton("Sodium");
    private final JButton priceB = new JButton("Price");

    private final JTextField nameF = new JTextField();
    private final JTextField ratingF = new JTextField();
    private final JTextField caloriesF = new JTextField();
    private final JTextField proteinF = new JTextField();
    private final JTextField fatF = new JTextField();
    private final JTextField sodiumF = new JTextField();
    private final JTextField priceF = new JTextField();
    private final JTextField prodName = new JTextField();

    private final JButton viewProducts= new JButton("VIEW PRODUCTS");
    private final JButton filterProducts= new JButton("FILTER PRODUCTS");
    private final JButton newOrder = new JButton("PLACE ORDER");
    private final JButton addCart = new JButton("ADD TO CART");
    private final JButton back = new JButton("BACK");
    
    private  final JPanel panel1 = new JPanel();
    private  final JPanel panel2 = new JPanel();
    private  final JPanel panel3 = new JPanel();
    
    private Color backgroundColor = new Color(216,196,190);
    
    private DeliveryService dv;

    private JScrollPane scrollBar;
    
    private ArrayList<MenuItem> cart;
    private String[] filters = new String[7];
    private EmployeeInterface em;
    double total = 0;
    StringBuilder sbprod = new StringBuilder();


    public ClientInterface(DeliveryService dv, EmployeeInterface em){
        init();
        addComponents();
        this.dv = dv;
        this.cart = new ArrayList<MenuItem>();
        this.em = em;
        getContentPane().setBackground(backgroundColor);
        addClientListeners();
    }

    private void init(){
        this.setSize(1200, 700);
        this.setTitle("Client Window");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addComponents(){
        addTitle();
        addBackButton();;
        addViewProductsPanel();
        addFilterPanel();
        addOrderPanel();

    }

    private void addTitle(){
        title.setBounds(420,15,300,50);
        title.setVisible(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(title);
    }

    private void addViewProductsPanel(){
        panel1.setBounds(30,70,1100,170);
        panel1.setBackground(new Color(223,207,203));
        
        panel1.setLayout(null);
        panel1.setVisible(true);
        this.add(panel1);

        viewProducts.setBounds(20, 90, 150,35);
        viewProducts.setVisible(true);
        viewProducts.setFocusable(false);
        viewProducts.addActionListener( e-> {
            if(e.getSource() == viewProducts){

            }
        });
        panel1.add(viewProducts);
    }


    public void addProductTable( JTable table){
        table.setFillsViewportHeight(true);
        scrollBar = new JScrollPane(table);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(200,30,880,120);
    }

    public void addFilteredTable( JTable table){
        table.setFillsViewportHeight(true);
        JScrollPane scrollBar = new JScrollPane(table);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(200,70,880,120);
        panel2.add(scrollBar);
    }

    private void addFilterPanel(){
        panel2.setBounds(30,270,1100,200);
        panel2.setBackground(new Color(223,207,203));
        panel2.setLayout(null);
        panel2.setVisible(true);
        this.add(panel2);


        nameB.setBounds(20,20,80,25);
        nameB.setVisible(true);
        panel2.add(nameB);

        nameF.setBounds(110,20,80,25);
        nameF.setVisible(true);
        panel2.add(nameF);

        ratingB.setBounds(200,20,80,25);
        ratingB.setVisible(true);
        panel2.add(ratingB);

        ratingF.setBounds(290,20,80,25);
        ratingF.setVisible(true);
        panel2.add(ratingF);

        caloriesB.setBounds(380,20,80,25);
        caloriesB.setVisible(true);
        panel2.add(caloriesB);

        caloriesF.setBounds(470,20,80,25);
        caloriesF.setVisible(true);
        panel2.add(caloriesF);

        proteinB.setBounds(560,20,80,25);
        proteinB.setVisible(true);
        panel2.add(proteinB);

        proteinF.setBounds(650,20,80,25);
        proteinF.setVisible(true);
        panel2.add(proteinF);

        fatB.setBounds(740,20,80,26);
        fatB.setVisible(true);
        panel2.add(fatB);

        fatF.setBounds(830,20,80,25);
        fatF.setVisible(true);
        panel2.add(fatF);

        sodiumB.setBounds(920,20,80,25);
        sodiumB.setVisible(true);
        panel2.add(sodiumB);

        sodiumF.setBounds(1010,20,80,25);
        sodiumF.setVisible(true);
        panel2.add(sodiumF);

        priceB.setBounds(20,70,80,25);
        priceB.setVisible(true);
        panel2.add(priceB);

        priceF.setBounds(110,70,80,25);
        priceF.setVisible(true);
        panel2.add(priceF);

        filterProducts.setBounds(20, 110, 150,35);
        filterProducts.setVisible(true);
        filterProducts.setFocusable(false);
        panel2.add(filterProducts);

    }


    private void addOrderPanel() {
        panel3.setBounds(30, 500, 1100, 100);
        panel3.setBackground(new Color(223,207,203));
        panel3.setLayout(null);
        panel3.setVisible(true);
        this.add(panel3);

        instructions.setBounds(50,30,220,30);
        instructions.setVisible(true);
        panel3.add(instructions);

        prodName.setBounds(280,35,80,25);
        prodName.setVisible(true);
        panel3.add(prodName);

        addCart.setBounds(430,35,200,25);
        addCart.setVisible(true);
        panel3.add(addCart);

        newOrder.setBounds(730,35,200,25);
        newOrder.setVisible(true);
        panel3.add(newOrder);

    }

    private void addBackButton(){
        back.setBounds(30, 615, 80,30);
        back.setVisible(true);
        back.setFocusable(false);
        this.add(back);
    }


    public void addListeners(ActionListener listener){
        viewProducts.addActionListener(listener);
        newOrder.addActionListener(listener);
        filterProducts.addActionListener(listener);
        addCart.addActionListener(listener);
        back.addActionListener(listener);
        nameB.addActionListener(listener);
        ratingB.addActionListener(listener);
        caloriesB.addActionListener(listener);
        proteinB.addActionListener(listener);
        fatB.addActionListener(listener);
        sodiumB.addActionListener(listener);
        priceB.addActionListener(listener);
    }

    public JButton getFilterProducts() {
        return filterProducts;
    }

    public String getNameF() {
        return nameF.getText();
    }

    public String getRatingF() {
        return ratingF.getText();
    }

    public String getCaloriesF() {
        return caloriesF.getText();
    }

    public String getProteinF() {
        return proteinF.getText();
    }

    public String getFatF() {
        return fatF.getText();
    }

    public String getSodiumF() {
        return sodiumF.getText();
    }

    public String getPriceF() {
        return priceF.getText();
    }

    public int getProductId(){
        return Integer.parseInt(prodName.getText());
    }

    public JButton getView(){
        return viewProducts;
    }

    public JButton getAddCart() {
        return addCart;
    }

    public JButton getNewOrder() {
        return newOrder;
    }

    public JButton getCaloriesB() {
        return caloriesB;
    }

    public JButton getFatB() {
        return fatB;
    }

    public JButton getNameB() {
        return nameB;
    }

    public JButton getPriceB() {
        return priceB;
    }

    public JButton getProteinB() {
        return proteinB;
    }

    public JButton getRatingB() {
        return ratingB;
    }

    public JButton getSodiumB() {
        return sodiumB;
    }

    public void setTableView(){
        panel1.add(scrollBar);
    }

    public JButton getBack() {
        return back;
    }
    
    public void addClientListeners(){
    addListeners( e ->{
    	
    
        if(e.getSource() == addCart){
           
            MenuItem m = dv.findProduct(prodName.getText());
            sbprod.append(m.getTitle()+" "+ m.getPrice() + "\n");
            total = total + m.getPrice();
            cart.add(m);
        }
        else if(e.getSource() == newOrder){

            int orderId = dv.getOrderIndex();
            int userId = dv.getNrC();
            Order order = new Order(orderId, userId, new Date(), cart);
            dv.addOrder(userId, cart, orderId, em);
            
            StringBuilder sb1 = new StringBuilder();
			sb1.append("Invoice for order with ID: ");
			sb1.append(orderId);
			sb1.append("\n");

			
			StringBuilder sb3 = new StringBuilder();
			sb3.append("\n TOTAL: ");
			sb3.append(total);
			
			StringBuilder fileName = new StringBuilder();
			fileName.append("OrderBill");
			fileName.append(".txt");
			
			FileWriter fw;
			try {
				fw = new FileWriter(fileName.toString());
				fw.write(sb1.toString());
				fw.write(sbprod.toString());
				fw.write(sb3.toString());
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
        }
        else if(e.getSource() == nameB){
            filters[0] = nameF.getText();
            System.out.print(filters[0]+" "+ nameF.getText());
        }
        else if(e.getSource() ==ratingB){
            filters[1] = ratingF.getText();
        }
        else if(e.getSource() == caloriesB){
           filters[2] =caloriesF.getText();
        }
        else if(e.getSource() == proteinB){
            filters[3] = proteinF.getText();;
        }
        else if(e.getSource() == sodiumB){
            filters[4] = sodiumF.getText();
        }
        else if(e.getSource() == fatB){
            filters[5] = fatF.getText();
        }
        else if(e.getSource() == priceB){
            filters[6] = priceF.getText();
        }
        else if(e.getSource() == filterProducts){
            JTable table = dv.filterProduct(filters);
            
          
            addFilteredTable(table);
         
        }
        else if(e.getSource() == viewProducts){
            JTable table =dv.createViewTable();
            addProductTable(table);
            setTableView();
            //.setVisible(false);
        }
        else if(e.getSource() == back){
        	LoginPage lg = new LoginPage(dv);
    		lg.setVisible(true);
            this.setVisible(false);
        }

    });
}
}
