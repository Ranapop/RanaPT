package ro.utcn.pt.QueueSimulator.model;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import ro.utcn.pt.QueueSimulator.utilities.Constants;
import ro.utcn.pt.QueueSimulator.utilities.RandomGenerator;

public class TaskGenerator implements Runnable{

	//should these constants not be private, that is given as input?
	private static final int MIN_SLEEP_TIME = 200;
	private static final int MAX_SLEEP_TIME = 2000;
	private BlockingQueue<Task> generatedTasks;
	
	public TaskGenerator(){
		generatedTasks = new LinkedBlockingQueue<>();
	}
	
	@Override
	public void run() {
		
		while(true){//should have some stopping cond... maybe something from the simulator
		//generate some task and then sleep for a random period of time
		long processTime = RandomGenerator.getRandomLongInRange(Constants.MIN_PROCESS_TIME, Constants.MAX_PROCESS_TIME);
		Task task = new Task(processTime);
		int sleepTime = RandomGenerator.getRandomIntInRange(MIN_SLEEP_TIME, MAX_SLEEP_TIME);
		generatedTasks.add(task);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Task generator thread could not sleep");
			e.printStackTrace();
		}
		}
		
	}
	
	public Task getTask(){
		try {
			return generatedTasks.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//runs its own thread and generates tasks and puts them in a blocking
	//queue. The simulator will retrieve these tasks and send them to the 
	//scheduler, and also set their arrival time to the current time of the system
	
}
