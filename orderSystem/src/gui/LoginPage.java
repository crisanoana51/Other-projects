package gui;

import javax.swing.*;

import businessLogic.DeliveryService;
import businessLogic.User;

import java.awt.*;
import java.awt.event.ActionListener;


public class LoginPage extends JFrame {

	/**
	 * The graphical interface for the Login Page
	 */

    private final JLabel titleLabel = new JLabel("LOG IN ");
    private final JLabel instructions2 = new JLabel("Create a new account here!");
    private final JLabel userLabel = new JLabel("Username:");
    private final JLabel passwordLabel = new JLabel("Password:");
    
    private final JTextField username = new JTextField("");
    private final JPasswordField password = new JPasswordField("");

    private static final JButton signIn = new JButton("SIGN IN");
    private final JButton administrator = new JButton("ADMINISTRATOR");
    private final JButton employee = new JButton("EMPLOYEE");
    private final JButton client = new JButton("CLIENT");
    
    private Color backgroundColor = new Color(216,196,190);
    private Color buttonColor = new Color(190,157,148);
    
    private DeliveryService dv;
    EmployeeInterface em;


    public LoginPage( DeliveryService dv){
        init();
        addComponents();
        this.dv = dv;
        em = new EmployeeInterface(dv);
        em.setVisible(false);
        getContentPane().setBackground(backgroundColor);
        dv.addObserver(em);
        addStartListeners();
    }

    private void init(){
        setTitle("Log In");
        setSize(500, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addComponents(){
        addInstructions();
        addUsername();
        addPassword();
        addButtons();
    }

    private void addInstructions(){
        //title
        titleLabel.setBounds(100,15,300,50);
        titleLabel.setVisible(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
        this.add(titleLabel);

        instructions2.setBounds(30,400,450,30);
        instructions2.setVisible(true);
        instructions2.setHorizontalAlignment(SwingConstants.CENTER);
        instructions2.setFont(new Font("Arial", Font.ITALIC, 16));
        this.add(instructions2);

    }

    
    private void addUsername(){
        userLabel.setBounds(150, 120, 100, 30);
        userLabel.setVisible(true);
        userLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(userLabel);

        username.setBounds(150,160, 200, 30);
        username.setHorizontalAlignment(JTextField.CENTER);
        username.setFont(new Font("Arial", Font.BOLD, 15));
        add(username);
    }

    private void addPassword(){
        passwordLabel.setBounds(150, 190, 100, 30);
        passwordLabel.setVisible(true);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(passwordLabel);

        password.setBounds(150,230, 200, 30);
        password.setHorizontalAlignment(JTextField.CENTER);
        password.setFont(new Font("Arial", Font.BOLD, 15));
        add(password);
    }

    private void addButtons(){
        //administrator
        administrator.setBounds(20,310, 140, 40);
        administrator.setFont(new Font("Arial", Font.BOLD, 12));
        administrator.setBackground(buttonColor);
        administrator.setVisible(true);
        administrator.setFocusable(false);
        add(administrator);

        //employee
        employee.setBounds(175,310, 140, 40);
        employee.setFont(new Font("Arial", Font.BOLD, 12));
        employee.setBackground(buttonColor);
        employee.setVisible(true);
        employee.setFocusable(false);
        add(employee);

        //client
        client.setBounds(330,310, 140, 40);
        client.setFont(new Font("Arial", Font.BOLD, 12));
        client.setBackground(buttonColor);
        client.setVisible(true);
        client.setFocusable(false);
        add(client);

        //sign-in
        signIn.setBounds(175,450, 140, 40);
        signIn.setFont(new Font("Arial", Font.BOLD, 12));
        signIn.setBackground(buttonColor);
        signIn.setVisible(true);
        signIn.setFocusable(false);
        add(signIn);

    }

   
    public void addListener(ActionListener listener){
        employee.addActionListener(listener);
        administrator.addActionListener(listener);
        client.addActionListener(listener);
        signIn.addActionListener(listener);
    }

  
    
    public void addStartListeners(){
        addListener( e ->{
            if(e.getSource() == administrator){
                boolean a = dv.isRegistered(username.getText(), password.getText(), "admin");
                if(a == true) {
                	AdministratorInterface adm = new AdministratorInterface(dv);
                    adm.setVisible(true);
                    this.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect Credentials! Try again.","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == employee){
            	boolean a = dv.isRegistered(username.getText(), password.getText(), "employee");
                if( a == true) {
                	
                    em.setVisible(true);
                    //this.setVisible(false);
                }
                else{JOptionPane.showMessageDialog(null, "Incorrect Credentials! Try again.","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == client){
            	boolean a = dv.isRegistered(username.getText(), password.getText(), "client");
                if(a == true) {
               
                    ClientInterface c = new ClientInterface(dv, em);
                    c.setVisible(true);
                    this.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect Credentials! Try again.","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == signIn){
				User u = new User(dv.getNrC(), username.getText(), username.getText(), password.getText(), "client");
				dv.sortUsers(u);
				 ClientInterface c = new ClientInterface(dv, em);
                 c.setVisible(true);
                 this.setVisible(false);
				
				 
            }
            

        });
    }
}
