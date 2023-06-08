package businessLogic;

import java.util.ArrayList;
import java.util.List;

import model.Server;
import model.Task;

public class Scheduler {

	private List<Server> servers;
	private int maxNoServers;
	private int maxTasksPerServer;
	private Strategy strategy;
	
	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public int getMaxNoServers() {
		return maxNoServers;
	}

	public void setMaxNoServers(int maxNoServers) {
		this.maxNoServers = maxNoServers;
	}

	public int getMaxTasksPerServer() {
		return maxTasksPerServer;
	}

	public void setMaxTasksPerServer(int maxTasksPerServer) {
		this.maxTasksPerServer = maxTasksPerServer;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public Scheduler(int maxNoServers, int  maxTasksPerServer) {
		this.maxNoServers = maxNoServers;
		this.maxTasksPerServer = maxTasksPerServer;
		servers = new ArrayList<>();
		strategy = new TimeStrategy();
		for(int i=0; i<maxNoServers;i++) {
			Server s = new Server();
			Thread thread = new Thread(s);
			servers.add(s);
			thread.start();
			
		}
			
	}
	
	public void dispatchTask(Task t) {
		List<Server> old =  servers;
		servers = strategy.addTask(old, t);
		
	}
}
