package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businessLogic.SimulationManager;


public class InputDataFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	SimulationFrame simulationFrame = new SimulationFrame();
	
	private JLabel titleLabel;
	private JLabel timeLabel;
	private JLabel timeLabelMin;
	private JLabel timeLabelMax;
	private JTextField timeMinField;
	private JTextField timeMaxField;

	private JLabel serviceLabel;
	private JLabel serviceLabelMin;
	private JLabel serviceLabelMax;
	private JTextField serviceMinField;
	private JTextField serviceMaxField;

	private JLabel nbQueues;
	private JTextField nbQueuesField;
	
	private JLabel nbClients;
	private JTextField nbClientsField;

	private JLabel simulation;
	private JTextField simulationField;

	private JButton startButton;

	private Color backgroundColor = new Color(249,203,156);

	public InputDataFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(150, 100, 700, 900);
		this.getContentPane().setBackground(backgroundColor);
		this.getContentPane().setLayout(null);

		
		Font biggerFont = new Font("Times New Roman", Font.PLAIN, 26);
		Font hugeFont = new Font("Times New Roman",Font.PLAIN,36);
		
		titleLabel = new JLabel("Queue Management System");
		titleLabel.setFont(hugeFont);
		titleLabel.setBounds(140,100,500,50);
		getContentPane().add(titleLabel);
		
		timeLabel = new JLabel("Arrival Interval: ");
		timeLabel.setFont(biggerFont);
		timeLabel.setBounds(50, 200, 300, 30);
		getContentPane().add(timeLabel);

		timeLabelMin = new JLabel("min : ");
		timeLabelMin.setFont(biggerFont);
		timeLabelMin.setBounds(280, 200, 70, 30);
		getContentPane().add(timeLabelMin);

		timeMinField = new JTextField();
		timeMinField.setBounds(340, 200, 30, 30);
		getContentPane().add(timeMinField);

		timeLabelMax = new JLabel("max : ");
		timeLabelMax.setFont(biggerFont);
		timeLabelMax.setBounds(380, 200, 70, 30);
		getContentPane().add(timeLabelMax);

		timeMaxField = new JTextField();
		timeMaxField.setBounds(440, 200, 30, 30);
		getContentPane().add(timeMaxField);

		serviceLabel = new JLabel("Service duration: ");
		serviceLabel.setFont(biggerFont);
		serviceLabel.setBounds(50, 250, 250, 30);
		getContentPane().add(serviceLabel);

		serviceLabelMin = new JLabel("min : ");
		serviceLabelMin.setFont(biggerFont);
		serviceLabelMin.setBounds(280, 250, 70, 30);
		getContentPane().add(serviceLabelMin);

		serviceMinField = new JTextField();
		serviceMinField.setBounds(340, 250, 30, 30);
		getContentPane().add(serviceMinField);

		serviceLabelMax = new JLabel("max : ");
		serviceLabelMax.setFont(biggerFont);
		serviceLabelMax.setBounds(380, 250, 70, 30);
		getContentPane().add(serviceLabelMax);

		serviceMaxField = new JTextField();
		serviceMaxField.setBounds(440, 250, 30, 30);
		getContentPane().add(serviceMaxField);

		nbQueues = new JLabel("Number of queues:");
		nbQueues.setFont(biggerFont);
		nbQueues.setBounds(50, 300, 300, 30);
		getContentPane().add(nbQueues);

		nbQueuesField = new JTextField();
		nbQueuesField.setBounds(310, 295, 40, 40);
		getContentPane().add(nbQueuesField);
		
		nbClients = new JLabel("Number of clients:");
		nbClients.setFont(biggerFont);
		nbClients.setBounds(50, 350, 300, 30);
		getContentPane().add(nbClients);

		nbClientsField = new JTextField();
		nbClientsField.setBounds(310, 345, 40, 40);
		getContentPane().add(nbClientsField);

		simulation = new JLabel("Simulation interval : ");
		simulation.setFont(biggerFont);
		simulation.setBounds(50, 400, 300, 30);
		getContentPane().add(simulation);

		simulationField = new JTextField();
		simulationField.setBounds(310, 400, 40, 40);
		getContentPane().add(simulationField);
		
		startButton = new JButton("Start Simulation");
		startButton.setBounds(240, 560, 200, 80);
		getContentPane().add(startButton);
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				SimulationManager sm = new SimulationManager(simulationFrame);
				;
				sm.setNumberOfClients(Integer.parseInt(nbClientsField.getText()));
				sm.setNumberOfServers(Integer.parseInt(nbQueuesField.getText()));
				sm.setTimeLimit(Integer.parseInt(simulationField.getText()));
				sm.setMinArrivalTime(Integer.parseInt(timeMinField.getText()));
				sm.setMaxArrivalTime(Integer.parseInt(timeMaxField.getText()));
				sm.setMinProcessingTime(Integer.parseInt(serviceMinField.getText()));
				sm.setMaxProcessingTime(Integer.parseInt(serviceMaxField.getText()));
				
				sm.start();
				simulationFrame.setVisible(true);
				Thread t =new Thread(sm);
				t.run();
				
					}catch(IllegalArgumentException ex) {
						System.out.println("Invalid input!");
					}
			}
				});
		
		
		
		
	}
	
	public String getTimeMin()
	{
		return timeMinField.getText();
	}
	
	public String getTimeMax()
	{
		return timeMaxField.getText();
	}
	
	public String getServiceTimeMin()
	{
		return serviceMinField.getText();
	}
	
	public String getServiceTimeMax()
	{
		return serviceMaxField.getText();
	}
	
	public String getNumberOfQueues()
	{
		return nbQueuesField.getText();
	}
	
	public String getSimulationTime()
	{
		return simulationField.getText();
	}
	
	
}

