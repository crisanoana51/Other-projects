package gui;
import javax.swing.*;
import java.awt.*;



public class SimulationFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane;
	

	private Color backgroundColor = new Color(249,203,156);

	public SimulationFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(150, 100, 700, 900);
		this.getContentPane().setBackground(backgroundColor);
		this.getContentPane().setLayout(null);
		
		textArea = new JTextArea(5,650);
		scrollPane= new JScrollPane(textArea); 
		
		addTextArea();
		
	}

	private void addTextArea(){
		textArea.setBounds(30, 90, 420, 440);
		textArea.setEditable(false);
		//textArea.setBackground(new Color(123,81,86));
		//textArea.setForeground(new Color(255, 255, 255));
		textArea.setFont(new Font("Times New Roman", Font.BOLD , 15));

		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(30, 100, 420, 444);
		getContentPane().add(scrollPane);
		setVisible(true);
	}

	public void setTextArea(String s){
		textArea.append(s);
	}

}
