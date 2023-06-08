package businessLogic;

import java.util.List;
import java.util.Random;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import gui.InputDataFrame;
import gui.SimulationFrame;
import model.Task;
import model.Server;

public class SimulationManager implements Runnable {

	public int timeLimit;
	public int maxArrivalTime;
	public int minArrivalTime;
	public int maxProcessingTime;
	public int minProcessingTime;
	public int numberOfServers;
	public int numberOfClients;


	private Scheduler scheduler;
	//private SimulationFrame simulationFrame = new SimulationFrame();
	//private InputDataFrame inputFrame = new InputDataFrame();
	private List<Task> generatedTasks;
	private TimeStrategy timeStrategy;

	private double averageWaitingTime;
	private double averageServiceTime;
	private int peakHour;
	private SimulationFrame simulationFrame;


	public SimulationManager(SimulationFrame sm ) {
		super();
		this.simulationFrame = sm;
		this.timeLimit = 0;
		this.maxArrivalTime = 0;
		this.minArrivalTime = 0;
		this.maxProcessingTime = 0;
		this.minProcessingTime = 0;
		this.numberOfServers = 0;
		this.numberOfClients = 0;
		this.generatedTasks = new ArrayList<Task>();
		this.averageWaitingTime = 0;
		this.averageServiceTime = 0;
		this.peakHour = 0;

	}

	public void start() {
		this.scheduler = new Scheduler(numberOfServers, numberOfClients);
		generateNRandomTasks();
		this.timeStrategy = new TimeStrategy();


	}


	public int getTimeLimit() {
		return timeLimit;
	}


	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}


	public int getMaxArrivalTime() {
		return maxArrivalTime;
	}


	public void setMaxArrivalTime(int maxArrivalTime) {
		this.maxArrivalTime = maxArrivalTime;
	}


	public int getMinArrivalTime() {
		return minArrivalTime;
	}


	public void setMinArrivalTime(int minArrivalTime) {
		this.minArrivalTime = minArrivalTime;
	}


	public int getMaxProcessingTime() {
		return maxProcessingTime;
	}


	public void setMaxProcessingTime(int maxProcessingTime) {
		this.maxProcessingTime = maxProcessingTime;
	}


	public int getMinProcessingTime() {
		return minProcessingTime;
	}


	public void setMinProcessingTime(int minProcessingTime) {
		this.minProcessingTime = minProcessingTime;
	}


	public int getNumberOfServers() {
		return numberOfServers;
	}


	public void setNumberOfServers(int numberOfServers) {
		this.numberOfServers = numberOfServers;
	}


	public int getNumberOfClients() {
		return numberOfClients;
	}


	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}


	public Scheduler getScheduler() {
		return scheduler;
	}


	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}


	public List<Task> getGeneratedTasks() {
		return generatedTasks;
	}


	public void setGeneratedTasks(List<Task> generatedTasks) {
		this.generatedTasks = generatedTasks;
	}


	public double getAverageWaitingTime() {
		return averageWaitingTime;
	}


	public void setAverageWaitingTime(double averageWaitingTime) {
		this.averageWaitingTime = averageWaitingTime;
	}


	public double getAverageServiceTime() {
		return averageServiceTime;
	}


	public void setAverageServiceTime(double averageServiceTime) {
		this.averageServiceTime = averageServiceTime;
	}


	public int getPeakHour() {
		return peakHour;
	}


	public void setPeakHour(int peakHour) {
		this.peakHour = peakHour;
	}

	public void generateNRandomTasks() {

		Random rand = new Random();
		generatedTasks = new ArrayList<Task>();
		for (int i = 0; i < numberOfClients; i++) {

			int arrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
			int serviceTime = rand.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
			Task client = new Task(i, arrivalTime, serviceTime);
			generatedTasks.add(client);


		}

		Collections.sort(generatedTasks, this::compare);
		for (int i = 1; i <= numberOfClients; i++) {
			generatedTasks.get(i - 1).setId(i);
		}

	}

	public int compare(Task t1, Task t2) {
		return t1.getArrivalTime() - t2.getArrivalTime();
	}

	public void run() {

		int currentTime = -1;
		int peakHourTime = 0;
		StringBuilder sf = new StringBuilder();

		while (currentTime < timeLimit) {
			currentTime++;
			
			StringBuilder sb = new StringBuilder();
			sb.append("Time: ");
			sb.append(currentTime);
			sb.append("\n");
			sb.append("Waiting Clients: ");
			for (int i = 0; i < numberOfClients; i++){
				Task task = generatedTasks.get(i);
				sb.append("(" + task.getId() + " " + task.getArrivalTime() + " " + task.getServiceTime() + ") ");
			}

			for (int i = 0; i < numberOfClients; i++) {
				if (generatedTasks.size() > 0) {
					Task task = generatedTasks.get(i);
					averageServiceTime = averageServiceTime + task.getServiceTime();

					if (task.getArrivalTime() == currentTime) {
						scheduler.dispatchTask(task);
						generatedTasks.remove(task);
						numberOfClients--;
						i--;
					}
					if (task.getArrivalTime() != currentTime) {
						break;
					}
				}
			}
			int j=0;
			for (Server s : scheduler.getServers()) {
				j++;
				sb.append("\n");
			//	System.out.println("Queue "+ j+ " : ");
				sb.append("Queue "+j+" : ");
			//	System.out.println( s.toString() + "\n");
				sb.append(s.toString());
				sf.append(sb.toString());
			}

			sb.append("\n");
			averageWaitingTime = timeStrategy.getWaitingTime() / numberOfServers;
			averageServiceTime = averageServiceTime / numberOfClients;

			int currentClients = 0;
			for (Server s : scheduler.getServers()) {
				currentClients = currentClients + s.getTasks().size();

			}
			if (currentClients > peakHour) {
				peakHour = currentClients;
				peakHourTime = currentTime;
			}

			for (Server s : scheduler.getServers()) {
				Task task = s.getTasks().peek();
				if ( task != null) {
					if (task.getServiceTime() == 0) {
						s.getTasks().remove(task);
					} else {
						int serviceTimeCurrent = task.getServiceTime();
						task.setServiceTime(serviceTimeCurrent - 1);
					}
				}
			}

			System.out.println(sb);
			System.out.println("\n");
			simulationFrame.setTextArea(sb.toString());
			try {
			FileWriter fw = new FileWriter("logs.txt");
			fw.write(sf.toString());
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
		System.out.println("The average waiting time is: " + averageWaitingTime);
		System.out.println("\n");
		System.out.println("The average service time is: " + averageServiceTime);
		System.out.println("\n");
		System.out.println("The peak hour for the simulation time interval is: " + peakHourTime);
		
		StringBuilder sb2  = new StringBuilder();
		sb2.append("The average waiting time is: " + averageWaitingTime + "\n" + "The average service time is: " + averageServiceTime + "\n" + "The peak hour for the simulation time interval is: " + peakHourTime);
		simulationFrame.setTextArea(sb2.toString());
		
		
		


	}

}
	

