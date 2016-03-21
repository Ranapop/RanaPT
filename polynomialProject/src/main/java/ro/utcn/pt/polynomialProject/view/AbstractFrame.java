package ro.utcn.pt.polynomialProject.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.css.RGBColor;

public abstract class AbstractFrame extends JFrame{
	
	protected JPanel topPanel;
	protected JPanel centerPanel;
	
	public AbstractFrame(){
		//setting the layout
		setLayout(new GridLayout(2,1,0,0));
		//setting the window at the center of the screen
		setLocationRelativeTo(null);
		//setting the size
		setSize(400,400);
		//enable X button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//adding a panel at the center
		topPanel=new JPanel();
		topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		topPanel.setBackground(new Color(255,116,147));
		add(topPanel);
		
		centerPanel=new JPanel();
		//settig border
		centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		centerPanel.setBackground(Color.pink);
		add(centerPanel);
		setVisible(true);
		setVisible(true);
	}

}