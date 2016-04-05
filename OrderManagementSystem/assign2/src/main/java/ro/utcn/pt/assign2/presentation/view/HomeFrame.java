package ro.utcn.pt.assign2.presentation.view;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ro.utcn.pt.assign2.bll.UserBLL;
import ro.utcn.pt.assign2.bll.WarehouseBLL;
import ro.utcn.pt.assign2.presentation.model.Product;
import ro.utcn.pt.assign2.presentation.model.User;
import ro.utcn.pt.assign2.utilities.Constants;

public class HomeFrame extends AbstractFrame {

	private int MAX_NO_OF_COLUMNS = 10;
	private JTable jTable;
	private JButton userButton;
	private JButton productButton;
	private JButton orderButton;
	private JButton deleteButton;
	private JButton insertButton;
	private JButton updateButton;
	private JButton printButton;
	private String viewType;
	
	public HomeFrame(String title) {
		super(title);
		userButton = new JButton("User");
		productButton = new JButton("Warehouse");
		orderButton = new JButton("Order");
		deleteButton = new JButton("Delete");
		insertButton = new JButton("Insert");
		updateButton = new JButton("Update");
		printButton = new JButton("Print");
		//add upper buttons
		upperPanel.add(userButton);
		upperPanel.add(productButton);
		upperPanel.add(orderButton);
		//add bottom buttons
		lowerPanel.add(insertButton);
		lowerPanel.add(updateButton);
		lowerPanel.add(deleteButton);
		lowerPanel.add(printButton);
		// add table
		ArrayList<Object> objects = UserBLL.read();
		jTable = createGeneralTable(objects);
		JScrollPane scrollPane = new JScrollPane(jTable);
		jTable.setFillsViewportHeight(true);
		mainPanel.add(scrollPane);
		//default view type
		viewType = Constants.ViewType.USER_VIEW;
		setVisible(true);
	}

	public JTable createGeneralTable(List<Object> data) {

		DefaultTableModel tableModel = new DefaultTableModel();
		if(!addColumnNames(tableModel,data.get(0))){
			return null;
		}
		if(!addColumnValues(tableModel, data));
		JTable table = new JTable();
		table.setModel(tableModel);
		return table;
	}
	
	private boolean addColumnNames( DefaultTableModel model,Object obj){
		Class<?> classObj = obj.getClass();
		try {
			Field[] fields = classObj.getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				fields[i].setAccessible(true);
				if(fields[i].getType()==User.class||fields[i].getType()==Product.class){//should test for the types
					//go recursively
					Object objField = fields[i].get(obj);
					Class<?> fieldClassObj = objField.getClass();
					Field[] subFields = fieldClassObj.getDeclaredFields();
					for (int j = 0; j < subFields.length; j++) {
						subFields[j].setAccessible(true);
						model.addColumn(subFields[j].getName());
					}
				}else{
					model.addColumn(fields[i].getName());
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private boolean addColumnValues(DefaultTableModel model,List<Object> data){
		Vector<Object> emptyRow = new Vector<>();
		for (Object obj : data) {
			Class<?> classInstance = obj.getClass();
			Field[] fields = classInstance.getDeclaredFields();
			Object[] rowData = new Object[MAX_NO_OF_COLUMNS];
			try {
				int columnId=0;
				for (int i = 0; i < fields.length; i++) {
					fields[i].setAccessible(true);
					if(fields[i].getType()==User.class||fields[i].getType()==Product.class){
						Object objField = fields[i].get(obj);
						Class<?> fieldClassObj = objField.getClass();
						Field[] subFields = fieldClassObj.getDeclaredFields();
						for (int j = 0; j < subFields.length; j++) {
							subFields[j].setAccessible(true);
							rowData[columnId] = subFields[j].get(objField);
							columnId++;
						}
					}else{
						rowData[columnId] = fields[i].get(obj);
						columnId++;
					}
					emptyRow.addElement("NULL");
					
				}
				model.addRow(rowData);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
		model.addRow(emptyRow);
		return true;
		
	}
	
	/************* ADD BUTTON ACTION LISTENERS **************/
	public void addUserActionlistener(ActionListener a) {
		userButton.addActionListener(a);
	}
	public void addProductActionlistener(ActionListener a) {
		productButton.addActionListener(a);
	}
	public void addOrderActionlistener(ActionListener a) {
		orderButton.addActionListener(a);
	}
	public void addInsertButtonActionListener(ActionListener a){
		insertButton.addActionListener(a);
	}
	public void addUpdateButtonActionListener(ActionListener a){
		updateButton.addActionListener(a);;
	}
	public void addDeleteButtonActionListener(ActionListener a){
		deleteButton.addActionListener(a);
	}
	public void addPrintButtonActionListener(ActionListener a){
		printButton.addActionListener(a);
	}
	
	public void refreshTable(List<Object> data){

		mainPanel.removeAll();
		jTable = createGeneralTable(data);
		JScrollPane scrollPane = new JScrollPane(jTable);
		jTable.setFillsViewportHeight(true);
		mainPanel.add(scrollPane);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	
	public JTable getJTable(){
		return jTable;
	}
	
	public void createMessagePopUp(String message){
		JOptionPane.showMessageDialog(this, message);
	}
}
