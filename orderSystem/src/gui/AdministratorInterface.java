package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import businessLogic.BaseProduct;
import businessLogic.DeliveryService;
import businessLogic.MenuItem;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The graphical user interface for the Administrator
 */
public class AdministratorInterface extends JFrame {

    private final JLabel title = new JLabel("ADMINISTRATOR");
    private final JLabel idL = new JLabel("Title of the product that you want to edit/delete:");
    private final JLabel titleL = new JLabel("Title:");
    private final JLabel ratingL = new JLabel("Rating:");
    private final JLabel caloriesL = new JLabel("Calories:");
    private final JLabel proteinL = new JLabel("Protein:");
    private final JLabel fatL = new JLabel("Fat:");
    private final JLabel sodiumL = new JLabel("Sodium:");
    private final JLabel priceL = new JLabel("Price:");

    private final JTextField idF = new JTextField();
    private final JTextField nameF = new JTextField();
    private final JTextField ratingF = new JTextField();
    private final JTextField caloriesF = new JTextField();
    private final JTextField proteinF = new JTextField();
    private final JTextField fatF = new JTextField();
    private final JTextField sodiumF = new JTextField();
    private final JTextField priceF = new JTextField();

    
    private final JLabel menuL = new JLabel("Title:");
    private final JLabel dishL1 = new JLabel("Dish 1");
    private final JLabel dishL2 = new JLabel("Dish 2:");
    private final JLabel dishL3 = new JLabel("Dish 3:");
    private final JLabel dishL4 = new JLabel("Dish 4:");
    private final JLabel newRatingL = new JLabel("Rating: ");

    private final JTextField menuF = new JTextField();
    private final JTextField dishF1 = new JTextField();
    private final JTextField dishF2 = new JTextField();
    private final JTextField dishF3 = new JTextField();
    private final JTextField dishF4 = new JTextField();
    private final JTextField newRatingF = new JTextField();

    
    private final JLabel startTimeL = new JLabel("Start Time:");
    private final JLabel endTimeL = new JLabel("End Time:");
    private final JLabel prodNrL = new JLabel("<html>Minimum number <BR>of times: </html>");
    private final JLabel ordNrL = new JLabel("Orders min number:");
    private final JLabel valueL = new JLabel("Minimum value:");
    private final JLabel dayL = new JLabel("Specified day:");

    private final JTextField starTimeF = new JTextField();
    private final JTextField endTimeF = new JTextField();
    private final JTextField prodNrF = new JTextField();
    private final JTextField ordNrF = new JTextField();
    private final JTextField valueF = new JTextField();
    private final JTextField dayF = new JTextField();
    private final JTextField monthF = new JTextField();
    private final JTextField yearF = new JTextField();    


    private final JButton importButton = new JButton("IMPORT PRODUCTS");
    private final JButton back = new JButton("BACK");
    private final JButton create = new JButton("CREATE");
    private final JButton edit = new JButton("EDIT");
    private final JButton delete = new JButton("DELETE");
    private final JButton createMenu = new JButton("CREATE MENU");
    private final JButton reportButton1 = new JButton("GENERATE REPORT 1");
    private final JButton reportButton2 = new JButton("GENERATE REPORT 2");
    private final JButton reportButton3 = new JButton("GENERATE REPORT 3");
    private final JButton reportButton4 = new JButton("GENERATE REPORT 4");

    private final JPanel managePanel = new JPanel();
    private final JPanel createPanel = new JPanel();
    private final JPanel reportPanel1 = new JPanel();
    private final JPanel reportPanel2 = new JPanel();
    private final JPanel reportPanel3 = new JPanel();
    private final JPanel reportPanel4 = new JPanel();
    
    private Color backgroundColor = new Color(216,196,190);
    
    static DeliveryService dv;

    public AdministratorInterface(DeliveryService dv){
        init();
        addComponents();
        this.dv = dv;
        addAdministratorListeners();

    }

    private void addComponents(){
        addTitle();
        addButtons();
        addCreatePanel();
        addManagePanel();
        addFilterPanel1();
        addFilterPanel2();
        addFilterPanel3();
        addFilterPanel4();
    }

    private void init(){
        this.setSize(1200, 700);
        getContentPane().setBackground(backgroundColor);
        this.setTitle("Administrator Window");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void addTitle(){
        title.setBounds(450,15,300,50);
        title.setVisible(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
        this.add(title);
    }

    private void addButtons(){
        //import
        importButton.setBounds(450,80, 270, 50);
        importButton.setFont(new Font("Arial", Font.BOLD, 20));
        importButton.setVisible(true);
        importButton.setFocusable(false);
        this.add(importButton);
       
        back.setBounds(30, 615, 80,30);
        back.setVisible(true);
        back.setFocusable(false);
        this.add(back);
    }
    

    private void addManagePanel(){
        managePanel.setBounds(30,150,350,450);
        managePanel.setBackground(new Color(223,207,203));
        managePanel.setLayout(null);
        managePanel.setVisible(true);
        this.add(managePanel);

        
        titleL.setBounds(55,30,50,20);
        titleL.setVisible(true);
        managePanel.add(titleL);

        nameF.setBounds(140,30,100,25);
        nameF.setVisible(true);
        managePanel.add(nameF);

        ratingL.setBounds(55,80,50,20);
        ratingL.setVisible(true);
        managePanel.add(ratingL);

        ratingF.setBounds(140,80,100,25);
        ratingF.setVisible(true);
        managePanel.add(ratingF);

        caloriesL.setBounds(55,130,50,20);
        caloriesL.setVisible(true);
        managePanel.add(caloriesL);

        caloriesF.setBounds(140,130,100,25);
        caloriesF.setVisible(true);
        managePanel.add(caloriesF);

        proteinL.setBounds(55,180,50,20);
        proteinL.setVisible(true);
        managePanel.add(proteinL);

        proteinF.setBounds(140,180,100,25);
        proteinF.setVisible(true);
        managePanel.add(proteinF);

        fatL.setBounds(55,230,50,20);
        fatL.setVisible(true);
        managePanel.add(fatL);

        fatF.setBounds(140,230,100,25);
        fatF.setVisible(true);
        managePanel.add(fatF);

        sodiumL.setBounds(55,280,50,20);
        sodiumL.setVisible(true);
        managePanel.add(sodiumL);

        sodiumF.setBounds(140,280,100,25);
        sodiumF.setVisible(true);
        managePanel.add(sodiumF);

        priceL.setBounds(55,330,50,20);
        priceL.setVisible(true);
        managePanel.add(priceL);

        priceF.setBounds(140,330,100,25);
        priceF.setVisible(true);
        managePanel.add(priceF);

       
        idL.setBounds(10,370,300,25);
        idL.setVisible(true);
        managePanel.add(idL);

        idF.setBounds(280,370,50,25);
        idF.setVisible(true);
        managePanel.add(idF);

        create.setBounds(20,410,100,25);
        managePanel.setVisible(true);
        managePanel.add(create);

        edit.setBounds(130,410,100,25);
        edit.setVisible(true);
        managePanel.add(edit);

        delete.setBounds(240,410,100,25);
        delete.setVisible(true);
        managePanel.add(delete);


    }

    private void addCreatePanel(){
        createPanel.setBounds(420,150,300,450);
        createPanel.setBackground(new Color(223,207,203));
        createPanel.setLayout(null);
        createPanel.setVisible(true);
        this.add(createPanel);

        
        menuL.setBounds(35,30,80,20);
        menuL.setVisible(true);
        createPanel.add(menuL);

        menuF.setBounds(120,30,100,25);
        menuF.setVisible(true);
        createPanel.add(menuF);

        dishL1.setBounds(35,80,50,20);
        dishL1.setVisible(true);
        createPanel.add(dishL1);

        dishF1.setBounds(120,80,100,25);
        dishF1.setVisible(true);
        createPanel.add(dishF1);

        dishL2.setBounds(35,130,50,20);
        dishL2.setVisible(true);
        createPanel.add(dishL2);

        dishF2.setBounds(120,130,100,25);
        dishF2.setVisible(true);
        createPanel.add(dishF2);

        dishL3.setBounds(35,180,50,20);
        dishL3.setVisible(true);
        createPanel.add(dishL3);

        dishF3.setBounds(120,180,100,25);
        dishF3.setVisible(true);
        createPanel.add(dishF3);

        dishL4.setBounds(35,230,50,20);
        dishL4.setVisible(true);
        createPanel.add(dishL4);

        dishF4.setBounds(120,230,100,25);
        dishF4.setVisible(true);
        createPanel.add(dishF4);

        newRatingL.setBounds(35,280,80,20);
        newRatingL.setVisible(true);
        createPanel.add(newRatingL);

        newRatingF.setBounds(120,280,100,25);
        newRatingF.setVisible(true);
        createPanel.add(newRatingF);

        createMenu.setBounds(60,380,170,25);
        createPanel.setVisible(true);
        createPanel.add(createMenu);
    }

    private void addFilterPanel1(){
        reportPanel1.setBounds(750,150,400,100);
        reportPanel1.setBackground(new Color(223,207,203));
        reportPanel1.setLayout(null);
        reportPanel1.setVisible(true);
        this.add(reportPanel1);

        startTimeL.setBounds(30,30,100,20);
        startTimeL.setVisible(true);
        reportPanel1.add(startTimeL);

        starTimeF.setBounds(20,60,100,25);
        starTimeF.setVisible(true);
        reportPanel1.add(starTimeF);

        endTimeL.setBounds(140,30,100,20);
        endTimeL.setVisible(true);
        reportPanel1.add(endTimeL);

        endTimeF.setBounds(130,60,100,25);
        endTimeF.setVisible(true);
        reportPanel1.add(endTimeF);

        reportButton1.setBounds(240,60,150,25);
        reportButton1.setVisible(true);
        reportButton1.setFont(new Font("Arial", Font.BOLD, 11));
        reportPanel1.add(reportButton1);
    }

    private void addFilterPanel2(){
        reportPanel2.setBounds(750,270,400,100);
        reportPanel2.setBackground(new Color(223,207,203));
        reportPanel2.setLayout(null);
        reportPanel2.setVisible(true);
        this.add(reportPanel2);

        prodNrL.setBounds(30, 40,150,25);
        prodNrL.setVisible(true);
        reportPanel2.add(prodNrL);

        prodNrF.setBounds(190,40,40,25);
        prodNrF.setVisible(true);
        reportPanel2.add(prodNrF);

        reportButton2.setBounds(240,40,150,25);
        reportButton2.setVisible(true);
        reportButton2.setFont(new Font("Arial", Font.BOLD, 11));
        reportPanel2.add(reportButton2);


    }

    private void addFilterPanel3(){
        reportPanel3.setBounds(750,390,400,100);
        reportPanel3.setBackground(new Color(223,207,203));
        reportPanel3.setLayout(null);
        reportPanel3.setVisible(true);
        this.add(reportPanel3);

        ordNrL.setBounds(20,30,120,20);
        ordNrL.setVisible(true);
        reportPanel3.add(ordNrL);

        ordNrF.setBounds(20,60,80,25);
        ordNrF.setVisible(true);
        reportPanel3.add(ordNrF);

        valueL.setBounds(150,30,120,20);
        valueL.setVisible(true);
        reportPanel3.add(valueL);

        valueF.setBounds(150,60,80,25);
        valueF.setVisible(true);
        reportPanel3.add(valueF);

        reportButton3.setBounds(240,60,150,25);
        reportButton3.setVisible(true);
        reportButton3.setFont(new Font("Arial", Font.BOLD, 11));
        reportPanel3.add(reportButton3);
    }

    private void addFilterPanel4(){
        reportPanel4.setBounds(750,500,400,100);
        reportPanel4.setBackground(new Color(223,207,203));
        reportPanel4.setLayout(null);
        reportPanel4.setVisible(true);
        this.add(reportPanel4);

        dayL.setBounds(20,20,120,20);
        dayL.setVisible(true);
        reportPanel4.add(dayL);
  
        dayF.setBounds(20,50,25,25);
        dayF.setVisible(true);
        reportPanel4.add(dayF);
        
        
        monthF.setBounds(60,50,25,25);
        monthF.setVisible(true);
        reportPanel4.add(monthF);
        
        yearF.setBounds(100,50,25,25);
        yearF.setVisible(true);
        reportPanel4.add(yearF);

        reportButton4.setBounds(240,50,150,25);
        reportButton4.setVisible(true);
        reportButton4.setFont(new Font("Arial", Font.BOLD, 11));
        reportPanel4.add(reportButton4);

    }

    
    public void addListener(ActionListener listener) {
        importButton.addActionListener(listener);
        create.addActionListener(listener);
        edit.addActionListener(listener);
        delete.addActionListener(listener);
        createMenu.addActionListener(listener);
        reportButton1.addActionListener(listener);
        reportButton2.addActionListener(listener);
        reportButton3.addActionListener(listener);
        reportButton4.addActionListener(listener);
        back.addActionListener(listener);
    }


    
    public String[] getProductFields(){
        String[] s = new String[7];
        s[0] = nameF.getText();
        s[1] = ratingF.getText();
        s[2] = caloriesF.getText();
        s[3] = proteinF.getText();
        s[4] = fatF.getText();
        s[5] = sodiumF.getText();
        s[6] = priceF.getText();

        return s;
    }

    

    public String[] getDishes(){
        String[] s = new String[4];

        s[0] = dishF1.getText();
        s[1] = dishF2.getText();
        s[2] = dishF3.getText();
        s[3] = dishF4.getText();

        return s;
    }
   

    public int getFilterField2(){ return Integer.parseInt(prodNrF.getText());}

    public String[] getFilterField3(){
        String[] s = new String[2];
        s[0] =  ordNrF.getText();
        s[1] = valueF.getText();
        return s;
    }


  
    
    public void addAdministratorListeners(){

       addListener( e->{
            if(e.getSource() == importButton) {
                dv.readCSV();
            }
            else if(e.getSource() == create){
                String[] f = getProductFields();
                BaseProduct b = new BaseProduct( f[0], Float.parseFloat(f[1]), Integer.parseInt(f[2]), Integer.parseInt(f[3]), Integer.parseInt(f[4]), Integer.parseInt(f[5]), Double.parseDouble(f[6]));
                System.out.println(b.getTitle());
                dv.addProduct(b);
            }
            else if(e.getSource() == edit){
                String[] f =getProductFields();
                BaseProduct b =  new BaseProduct(f[0], Float.parseFloat(f[1]), Integer.parseInt(f[2]), Integer.parseInt(f[3]), Integer.parseInt(f[4]), Integer.parseInt(f[5]), Double.parseDouble(f[6]));
                dv.editProduct(b);
            }
            else if(e.getSource() == delete){
                dv.deleteProduct(dv.findProduct(idF.getText()));
            }
            else if( e.getSource() ==createMenu){
                String[] dishes = getDishes();
                String name = menuF.getText();
                ArrayList<MenuItem> prod = new ArrayList<>();
                for(String i: dishes) {
                	prod.add(dv.findProduct(i) );
                }
     
                dv.administratorMenu(prod, name);
            }
            else if(e.getSource() ==reportButton1){
             
                dv.generateReport1(starTimeF.getText(), endTimeF.getText());
            }
            else if(e.getSource() ==reportButton2){
                dv.generateReport2(getFilterField2());
            }
            else if(e.getSource() == reportButton3){
                String[] s = getFilterField3();
               dv.generateReport3( Double.parseDouble(s[1]), Integer.parseInt(s[0])  );
            }
            else if(e.getSource() == reportButton4){
               dv.generateReport4(Integer.parseInt(dayF.getText()), Integer.parseInt(monthF.getText()),Integer.parseInt(yearF.getText()));
            }
            else if(e.getSource() == back){
            	LoginPage loginPage = new LoginPage(dv);
                loginPage.setVisible(true);
               this.setVisible(false);
            }
        });
    }
}
