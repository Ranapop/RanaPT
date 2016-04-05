package ro.utcn.pt.assign2.presentation.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;


public abstract class AbstractFrame extends JFrame{
	
	protected JPanel mainPanel;
	protected JPanel upperPanel;
	protected JPanel lowerPanel;
	protected SpringLayout springLayout;
	
	public AbstractFrame(String title){
		//set title
		setTitle(title);
		//setting the layout
		BorderLayout myBorderLayout = new BorderLayout();
		setLayout(myBorderLayout);
		//setting the size
		setSize(600,500);
		//enable X button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setting the window at the center of the screen
		setLocationRelativeTo(null);
		mainPanel=new JPanel();
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainPanel.setBackground(new Color(221,160,221));
		upperPanel=new JPanel();
		upperPanel.setBackground(new Color(221,160,221));
		lowerPanel=new JPanel();
		this.add(mainPanel,BorderLayout.CENTER);
		this.add(upperPanel, BorderLayout.NORTH);
		this.add(lowerPanel, BorderLayout.SOUTH);
		upperPanel.setLayout(new GridLayout(1, 3));
		lowerPanel.setLayout(new GridLayout(1, 4));
	    //set layout
		springLayout = new SpringLayout();
		mainPanel.setLayout(springLayout);
		setVisible(true);
	}
}
