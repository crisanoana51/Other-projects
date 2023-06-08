package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
	
	private BlockingQueue<Task> tasks;
	private AtomicInteger waitingPeriod;
	
	public Server() {
		super();
		this.tasks = new LinkedBlockingDeque<Task>();
		this.waitingPeriod = new AtomicInteger();
	}
	
	public BlockingQueue<Task> getTasks() {
		return tasks;
	}



	public void setTasks(BlockingQueue<Task> tasks) {
		this.tasks = tasks;
	}



	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}



	public void setWaitingPeriod(AtomicInteger waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}



	public void addTask(Task newTask) {
		
		this.tasks.add(newTask);
		this.waitingPeriod.addAndGet(newTask.getServiceTime());
	}
	
	public void run() {
		while(true) {
			if(tasks.peek()!=null) {
				Task newTask = tasks.peek();
				if(newTask!=null) {
					try {
						Thread.sleep(newTask.getServiceTime()*1000);
					}catch(InterruptedException ex) {
						System.out.println("Interrupted!");
					}
				}
			}
		}
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder() ;
		for(Task i : tasks) {
			
			sb.append("(" + i.getId() +" "+ i.getArrivalTime() + " " + i.getServiceTime() + ") ");
		}
		
		return sb.toString();
		
	}
}
