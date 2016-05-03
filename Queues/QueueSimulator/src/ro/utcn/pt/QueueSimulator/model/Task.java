package ro.utcn.pt.QueueSimulator.model;

public class Task {

	private long arrivalTime;
	private long processTime;
	
	public Task(long processTime){
		
		this.processTime = processTime;
		
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setProcessTime(long processTime) {
		this.processTime = processTime;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public long getProcessTime() {
		return processTime;
	}

	@Override
	public String toString() {
		return "("+arrivalTime + ","+processTime+")";
	}
	
}
