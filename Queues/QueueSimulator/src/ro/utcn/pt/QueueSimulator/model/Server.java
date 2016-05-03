package ro.utcn.pt.QueueSimulator.model;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.joda.time.LocalTime;
import org.joda.time.Period;

import ro.utcn.pt.QueueSimulator.view.Frame;

public class Server implements Runnable {
	
	private int id;
	private BlockingQueue<Task> queue;
    private AtomicLong waitingTime; 
    private AtomicInteger noOfTasks;
    private long totalServiceTime =0;
    private long totalWaitingTime=0;
    private long totalEmptyTime=0;
    private int totalNoOfTasks=0;
    private boolean wasEmpty;
    private LocalTime timeOfQueueEmpty;
    private Frame frame;
    
    public Server(int id,Frame frame)
    {      
    	this.id = id;
    	this.frame = frame;
    	queue=new LinkedBlockingQueue<Task>();
    	waitingTime=new AtomicLong(0); 
    	noOfTasks = new AtomicInteger(0);
    }
    
	public AtomicLong getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(AtomicLong waitingTime) {
		this.waitingTime = waitingTime;
	}

	public void run() {
		// TODO Auto-generated method stub
		Task curT = null;
		while (true){
		try {
			curT = queue.take();
			System.out.println("Start processing task : " +curT);
			Thread.sleep(curT.getProcessTime());
			//if the queue becomes empty, remember the time
			if(isEmpty()){
				wasEmpty = true;
				timeOfQueueEmpty = new LocalTime();
			}else{
				wasEmpty = false;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//should update the frame, as some task disappeared
		frame.updatePanel(id, getTasks());
	    waitingTime.addAndGet((-1)*curT.getProcessTime());
		noOfTasks.addAndGet(-1);
		}
	}
	
	public void addTask(Task t){
		//check if the server was empty
		if(wasEmpty){
			LocalTime currentTime = new LocalTime();
			Period emptyServerInterval = new Period(timeOfQueueEmpty,currentTime);
			totalEmptyTime += emptyServerInterval.getMillis();
		}
		//add the task to the queue
		queue.add(t);
		///update the interface
		frame.updatePanel(id, getTasks());
		//increase the total waiting & service time and the no of tasks
		totalWaitingTime +=waitingTime.get();
		totalServiceTime+=t.getProcessTime();
		totalNoOfTasks++;
		//update waiting time & current no of tasks
		waitingTime.addAndGet(t.getProcessTime());
		noOfTasks.addAndGet(1);
	}
	
	
	public Task[] getTasks(){
		Task[] tasks=new Task[queue.size()];
		queue.toArray(tasks);
		return tasks;
		
	}
	
	public int getNoOfTasks(){
		
		return noOfTasks.get();
		
	}
	
	public int getId(){
		return id;
	}
	public BlockingQueue<Task> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Task> queue) {
		this.queue = queue;
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public long getAverageWaitingTime(){
		return totalWaitingTime/totalNoOfTasks;
	}
	
	public long getAverageServiceTime(){
		return totalServiceTime/totalNoOfTasks;
	}
	
	public long getEmptyQueueTime(){
		return totalEmptyTime;
	}
  
}
