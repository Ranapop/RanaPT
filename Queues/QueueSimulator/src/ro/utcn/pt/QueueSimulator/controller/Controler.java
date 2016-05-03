package ro.utcn.pt.QueueSimulator.controller;

import ro.utcn.pt.QueueSimulator.model.Simulator;
import ro.utcn.pt.QueueSimulator.model.TaskGenerator;
import ro.utcn.pt.QueueSimulator.view.Frame;

public class Controler {

	private Frame frame;
	public Controler(){
		frame = new Frame("Queues");
		frame.addButtonListener(new ButtonListener(frame,this));
	}
	
	public void start(){
		TaskGenerator taskGenerator = new TaskGenerator();
		Thread thTaskGenerator = new Thread(taskGenerator);
		thTaskGenerator.start();
		Simulator sim=new Simulator(taskGenerator,frame);
		Thread th =new Thread(sim);
		th.start();
	}
	
}
