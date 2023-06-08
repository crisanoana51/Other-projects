package businessLogic;

import java.util.List;


import model.Server;
import model.Task;

public interface Strategy {

	public List<Server> addTask(List<Server> servers, Task newTask);
			
}
