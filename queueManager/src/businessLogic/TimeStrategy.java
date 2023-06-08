package businessLogic;

import java.util.ArrayList;
import java.util.List;

import model.Server;
import model.Task;

public class TimeStrategy implements Strategy {

	private float waitingTime=0;
	
	
	public float getWaitingTime() {
		return waitingTime;
	}


	public void setWaitingTime(float waitingTime) {
		this.waitingTime = waitingTime;
	}


	public List<Server> addTask(List<Server> servers, Task newTask) {
		int minWait = Integer.MAX_VALUE;
		
		for(Server s : servers) {
			if(s.getWaitingPeriod().intValue() < minWait) {
				
				minWait=s.getWaitingPeriod().intValue();
			}
		}
		

		for(Server s : servers) {
			if(s.getWaitingPeriod().intValue()==minWait) {
				
				waitingTime=waitingTime + minWait + newTask.getServiceTime();
				s.addTask(newTask);
				break;
				
			}
		}
		List<Server> servers2 = new ArrayList<>(servers);
		return servers2;
	}
}
