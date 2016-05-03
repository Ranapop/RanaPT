package ro.utcn.pt.QueueSimulator.view;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ro.utcn.pt.QueueSimulator.model.Task;
import ro.utcn.pt.QueueSimulator.utilities.Constants;

public class Frame extends JFrame{

	private JPanel upperPanel;
	private JPanel mainPanel;
	private ArrayList<JPanel> contentPanels;
	private JTextField startSim;
	private JTextField endSim;
	private JTextField minProc;
	private JTextField maxProc;
	private JTextField minTaskInt;
	private JTextField maxTaskInt;
	private JTextField minNoServers;
	private JTextField maxNoServers;
	private JTextField minNoTasksPerTime;
	private JTextField maxNoTasksPerTime;
	private JTextField maxNoTasksPerServer;
	private JButton start;
	private JButton addAccButton;
	public Frame(String title)
	{
		super(title);
	
		setSize(600, 600);
 		setLayout(new GridLayout(2,1));
 		setLocationRelativeTo(null);
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		//create the panel that will contain the input data
 		upperPanel = new JPanel();
 		addInputComponents(upperPanel);
 		//create the main panel(will contain sub-panels with the servers)
 		mainPanel = new JPanel();
 		//create the panels for the servers
 		contentPanels = new ArrayList<>();
 		for(int i=0;i<Constants.MAX_NO_OF_SERVERS;i++){

 	 		JPanel contentPanel = new JPanel();
 	 		contentPanels.add(contentPanel);
 		}
 		GridLayout gridLayout = new GridLayout(1,Constants.MAX_NO_OF_SERVERS);
 		mainPanel.setLayout(gridLayout);
 		//actually add the panels
 		for(int i=0;i<Constants.MAX_NO_OF_SERVERS;i++){
 			contentPanels.get(i).add(new JTextField("Server #"+i+" closed"));
 			mainPanel.add(contentPanels.get(i));
 		}
 		add(upperPanel);
 		add(mainPanel);
		setVisible(true);
		
    }
	//panel pentru fiecare task 
	
	public void updatePanel(int panelId, Task[] tasks ){
		
		JPanel contentPanel = contentPanels.get(panelId);
		contentPanel.removeAll();
		contentPanel.revalidate();
		JList<Task> list=new JList<Task>(tasks);
		if(tasks.length!=0){
			JScrollPane sp=new JScrollPane(list);
			contentPanel.add(sp);
		}
		contentPanel.repaint();
	    contentPanel.revalidate();
	}
	
	private void addInputComponents(JPanel panel){
		
		///add 2 sub-panels
		panel.setLayout(new GridLayout(1, 2));
		panel.setBorder(new EmptyBorder(20,20,20,20));
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		//add stuff on the left panel
		left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
		//simulation time
		JLabel startTimeLabel = new JLabel("Start of simulation time");
		JLabel endTimeLabel = new JLabel("End of simulation time");
		startSim = new JTextField(String.valueOf(Constants.SIMULATION_START));
		endSim = new JTextField(String.valueOf(Constants.SIMULATION_END));
		left.add(startTimeLabel);
		left.add(startSim);
		left.add(endTimeLabel);
		left.add(endSim);
		//process time
		JLabel minProcessLabel = new JLabel("Min process time");
		JLabel maxProcessLabel = new JLabel("Max process time");
		minProc = new JTextField(String.valueOf(Constants.MIN_PROCESS_TIME));
		maxProc = new JTextField(String.valueOf(Constants.MAX_PROCESS_TIME));
		left.add(minProcessLabel);
		left.add(minProc);
		left.add(maxProcessLabel);
		left.add(maxProc);
		//tasks interval
	    //JLabel startTimeLabel = new JLabel("Start of simulation time");
		JLabel minTasksIntLabel = new JLabel("Min time between tasks");
		JLabel maxTasksIntLabel = new JLabel("Max time between tasks");
		minTaskInt = new JTextField(String.valueOf(Constants.MIN_INTERVAL_BETWEEN_TASKS));
		maxTaskInt = new JTextField(String.valueOf(Constants.MAX_INTERVAL_BETWEEN_TASKS));
		left.add(minTasksIntLabel);
		left.add(minTaskInt);
		left.add(maxTasksIntLabel);
		left.add(maxTaskInt);
		//add stuff on the right panel
		//no of servers
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		JLabel minNoOfServersLabel = new JLabel("Min no of servers");
		JLabel maxNoOfServersLabel = new JLabel("Max no of servers");
		minNoServers = new JTextField(String.valueOf(Constants.MIN_NO_OF_SERVERS));
		maxNoServers = new JTextField(String.valueOf(Constants.MAX_NO_OF_SERVERS));
		right.add(minNoOfServersLabel);
		right.add(minNoServers);
		right.add(maxNoOfServersLabel);
		right.add(maxNoServers);
		//no of tasks/time
		JLabel minNoOfTasksPerTimeLabel = new JLabel("Min no of tasks at a time");
		JLabel maxNoOfTasksPerTimeLabel = new JLabel("Max no of tasks at a time");
		minNoTasksPerTime = new JTextField(String.valueOf(Constants.MIN_NO_OF_TASKS_PER_SECOND));
		maxNoTasksPerTime= new JTextField(String.valueOf(Constants.MAX_NO_OF_TASKS_PER_SECOND));
		right.add(minNoOfTasksPerTimeLabel);
		right.add(minNoTasksPerTime);
		right.add(maxNoOfTasksPerTimeLabel);
		right.add(maxNoTasksPerTime);
		//max no of tasks/server
		JLabel maxNoOfTasksPerServer = new JLabel("Max no of tasks per server");
		maxNoTasksPerServer= new JTextField(String.valueOf(Constants.MAX_NO_OF_TASKS_PER_SECOND));
		right.add(maxNoOfTasksPerServer);
		right.add(maxNoTasksPerServer);
		//button for start
		JLabel startLabel = new JLabel("Click to start simulation:");
		start = new JButton("Start");
		right.add(startLabel);
		right.add(start);
		panel.add(left);
		panel.add(right);
		///add text fields for the data to be inputed
		//JTextLabel 
	}
	
	public void addButtonListener(ActionListener a){
		start.addActionListener(a);
	}
	public ArrayList<String> getFieldValues(){
		ArrayList<String> values = new ArrayList<>();
		//start simulation time
		String startSimTime = startSim.getText();
		String endSimTime = endSim.getText();
		String minProcTime = minProc.getText();
		String maxProcTime = maxProc.getText();
		String minTasksInt = minTaskInt.getText();
		String maxTasksInt = maxTaskInt.getText();
		String minNOOfServers = minNoServers.getText();
		String maxNoOfservers = maxNoServers.getText();
		String minNOOfTasksPerTime = minNoTasksPerTime.getText();
		String maxNOOfTasksPerTime = maxNoTasksPerTime.getText();
		String maxNOOfTasksPerServer = maxNoTasksPerServer.getText();
		
		if(startSimTime.equals("")||endSimTime.equals("")||minProcTime.equals("")||maxProcTime.equals("")){
			return null;
		}
		if(minTasksInt.equals("")||maxTasksInt.equals("")){
			return null;
		}
		if(minNOOfServers.equals("")||maxNoOfservers.equals("")){
			return null;
		}
		if(minNOOfTasksPerTime.equals("")||maxNOOfTasksPerTime.equals("")||maxNOOfTasksPerServer.equals("")){
			return null;
		}
		values.add(startSimTime);
		values.add(endSimTime);
		values.add(minProcTime);
		values.add(maxProcTime);
		values.add(minTasksInt);
		values.add(maxTasksInt);
		values.add(minNOOfServers);
		values.add(maxNoOfservers);
		values.add(minNOOfTasksPerTime);
		values.add(maxNOOfTasksPerTime);
		values.add(maxNOOfTasksPerServer);
		return values;
	}
	
	public void displayOutputData(String outputData){
		
		
		mainPanel.removeAll();
		mainPanel.revalidate();
		//add a JLabel for display
		JLabel text = new JLabel(outputData);
		JScrollPane output = new JScrollPane(text);
		mainPanel.add(output);
		mainPanel.repaint();
		mainPanel.revalidate();	
		
	}
}
