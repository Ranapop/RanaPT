package ro.utcn.pt.QueueSimulator.model;
import java.util.ArrayList;

import ro.utcn.pt.QueueSimulator.utilities.Constants;
import ro.utcn.pt.QueueSimulator.view.Frame;

public class Scheduler {
	private Frame frame;//must be sent to the servers
	private ArrayList<Server> servers;
	private int maxNoOfTasks;
	private long peekTime;
	
	public Scheduler(Frame frame) {
		this.frame = frame;
		servers = new ArrayList<Server>();
		//add an initial number of servers
		for(int i=0;i<Constants.MIN_NO_OF_SERVERS;i++){
			Server server = new Server(i,frame);
			Thread th = new Thread(server);
			th.start();
			servers.add(server);
		}
	}

	public ArrayList<Server> getServers() {
		return servers;
	}

	public void setServer(ArrayList<Server> servers) {
		this.servers = servers;
	}
	
	public ArrayList<Task[]> getTasks(){
		
		ArrayList<Task[]> arrayOfTasks = new ArrayList<Task[]>();
		for(Server server: servers){
			arrayOfTasks.add(server.getTasks());
		}
		return arrayOfTasks;
	}
	public void dispatchTasksOnServer(ArrayList<Task> tasks){
		
		for(Task task: tasks){
			
			//update the peek hour if necessary
			if(updateMaxNoOfTasks()){
				peekTime = task.getArrivalTime();
			}
			///put the tasks on the server with the minimum waiting time
			Server server = getServerWithMinNoOfClients();
			if(server==null){
				//is it possible?
				System.out.println("There was no server to place the task on");
			}else{

				server.addTask(task);		
			}
		}
	}
	
	private boolean updateMaxNoOfTasks(){
		int totalNoOfTasks = 0;
		for(Server server: servers){
			totalNoOfTasks+=server.getNoOfTasks();
		}
		if(maxNoOfTasks<totalNoOfTasks){
			maxNoOfTasks = totalNoOfTasks;
			return true;
		}
		return false;
	}
	private Server getServerWithMinNoOfClients(){
		
		
		int minNoOfClients = Integer.MAX_VALUE;
		Server bestServer = null;
		for(Server server: servers){
			
			if(minNoOfClients>server.getNoOfTasks()){
				minNoOfClients = server.getNoOfTasks();
				bestServer = server;
			}
		}
		///if there are still servers closed and the max no of clients was reached, open a new one
		if(servers.size()<Constants.MAX_NO_OF_SERVERS&&bestServer.getNoOfTasks()>=Constants.MAX_NO_OF_TASKS_PER_SERVER){
			Server server = new Server(servers.size(),frame);
			Thread th = new Thread(server);
			th.start();
			servers.add(server);
		}
		return bestServer;
	}
	
	public boolean isDone(){//returns true if every server is empty
		
		for(Server server: servers){
			if(!server.isEmpty()){
				return false;
			}
		}
		return true;
	}
	
	public long getPeekTime(){
		return peekTime;
	}
	
}