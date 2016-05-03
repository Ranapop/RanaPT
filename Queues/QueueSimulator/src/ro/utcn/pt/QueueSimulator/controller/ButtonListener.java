package ro.utcn.pt.QueueSimulator.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ro.utcn.pt.QueueSimulator.utilities.Constants;
import ro.utcn.pt.QueueSimulator.view.Frame;

public class ButtonListener implements ActionListener{

	private Frame frame;
	private Controler controler;
	public ButtonListener(Frame frame,Controler controler) {
		this.frame = frame;
		this.controler = controler;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//take all the fields from the frame and set them to the Constants
		retrieveInputData();
		//start the simulation
		controler.start();
		
	}
	
	private void retrieveInputData(){
		ArrayList<String> fieldValues = frame.getFieldValues();
		if(fieldValues==null){
			JOptionPane.showMessageDialog(frame, "Error! All fields must be completed");
		}else{
			//try to transform the fields into integers & longs
			try{
				Constants.SIMULATION_START = Long.valueOf(fieldValues.get(0));
				Constants.SIMULATION_END  = Long.valueOf(fieldValues.get(1));
				Constants.MIN_PROCESS_TIME = Long.valueOf(fieldValues.get(2));
				Constants.MAX_PROCESS_TIME = Long.valueOf(fieldValues.get(3));
				Constants.MIN_INTERVAL_BETWEEN_TASKS = Long.valueOf(fieldValues.get(4));
				Constants.MAX_INTERVAL_BETWEEN_TASKS = Long.valueOf(fieldValues.get(5));
				Constants.MIN_NO_OF_SERVERS = Integer.valueOf(fieldValues.get(6));
				Constants.MAX_NO_OF_SERVERS = Integer.valueOf(fieldValues.get(7));
				Constants.MIN_NO_OF_TASKS_PER_SECOND = Integer.valueOf(fieldValues.get(8));
				Constants.MAX_NO_OF_TASKS_PER_SECOND = Integer.valueOf(fieldValues.get(9));
				Constants.MAX_NO_OF_TASKS_PER_SERVER = Integer.valueOf(fieldValues.get(10));
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(frame, "Error! Fields are not in the required format!");
			}
			
		}
	}
}
