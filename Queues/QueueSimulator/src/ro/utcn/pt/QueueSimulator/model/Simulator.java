package ro.utcn.pt.QueueSimulator.model;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import ro.utcn.pt.QueueSimulator.utilities.Constants;
import ro.utcn.pt.QueueSimulator.utilities.RandomGenerator;
import ro.utcn.pt.QueueSimulator.view.Frame;

public class Simulator implements Runnable{
	
	private DateTime currTime;
	private TaskGenerator taskGenerator;
	private Interval interval;
	private Scheduler scheduler;
	private Frame frame;
	
	public Simulator(TaskGenerator taskGenerator, Frame frame){
		
		this.taskGenerator = taskGenerator;
		this.frame = frame;
		interval = new Interval(Constants.SIMULATION_START, Constants.SIMULATION_END);
		currTime = new DateTime(interval.getStart());
		scheduler = new Scheduler(frame);
	}
	
	private ArrayList<Task> getTasks(){
		ArrayList<Task> tasks = new ArrayList<>();
		///from the generated tasks, take a random number to go to the server
		int noOfTasks = RandomGenerator.getRandomIntInRange(Constants.MIN_NO_OF_TASKS_PER_SECOND
				, Constants.MAX_NO_OF_TASKS_PER_SECOND);
		for(int i=0;i<noOfTasks;i++){
			Task task = taskGenerator.getTask();
			if(task!=null){
				///set the arrival time of the tasks
				task.setArrivalTime(currTime.getMillis());
				tasks.add(task);
			}
		}
		return tasks;
		
	}
	
	public void run() {
		
		System.out.println("Start simulation\n");
		while(currTime.isBefore(interval.getEnd())){
			
			///get some tasks
			ArrayList<Task> tasks = getTasks();
			///pass the tasks to the scheduler
			scheduler.dispatchTasksOnServer(tasks);
			long sleepingPeriod =(long) RandomGenerator.getRandomLongInRange((int)Constants.MIN_INTERVAL_BETWEEN_TASKS,(int) Constants.MAX_INTERVAL_BETWEEN_TASKS);
			///sleep
			try {
				Thread.sleep(sleepingPeriod);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Simulator couldn't take a nap :(");
				e.printStackTrace();
			}
			///update current time
			currTime = currTime.plus(sleepingPeriod);
			
		}

		while(!scheduler.isDone()){
			//do nothing :? wait for it to end
		}
		System.out.println("End simulation\n");
		//display the output
		String outputData = "<html>";
		StringBuilder stringBuilder = new StringBuilder(outputData);
		ArrayList<Server> servers = scheduler.getServers();
		for(Server server: servers){
			
			stringBuilder.append("Server #"+server.getId());
			stringBuilder.append("<br>");
			stringBuilder.append("Average waiting time "+server.getAverageWaitingTime());
			stringBuilder.append("<br>");
			stringBuilder.append("Average service time "+server.getAverageServiceTime());
			stringBuilder.append("<br>");
			stringBuilder.append("Empty queue time "+server.getEmptyQueueTime());
			stringBuilder.append("<br><br>");
		}
		stringBuilder.append("Peek time: "+scheduler.getPeekTime()+"<br>");
		stringBuilder.append("</html>");
		frame.displayOutputData(stringBuilder.toString());
	}

}

