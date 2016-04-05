package ro.utcn.pt.assign2.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import ro.utcn.pt.assign2.bll.WarehouseBLL;
import ro.utcn.pt.assign2.bll.OrderBLL;
import ro.utcn.pt.assign2.bll.UserBLL;
import ro.utcn.pt.assign2.presentation.model.Order;
import ro.utcn.pt.assign2.presentation.model.User;
import ro.utcn.pt.assign2.presentation.model.Warehouse;
import ro.utcn.pt.assign2.presentation.view.HomeFrame;
import ro.utcn.pt.assign2.utilities.Constants;
import ro.utcn.pt.assign2.utilities.ModelFactory;

public class InsertButtonActionListener implements ActionListener {

	private HomeFrame frame;

	public InsertButtonActionListener(HomeFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// retrieve all data from the table
		JTable table = frame.getJTable();
		if (table.isEditing()) {
			table.getCellEditor().stopCellEditing();
		}
		switch (frame.getViewType()) {
		case Constants.ViewType.USER_VIEW:
			// have to create new user and insert it in the db
			frame.createMessagePopUp(insertUser(table));
			frame.refreshTable(UserBLL.read());
			break;

		case Constants.ViewType.WAREHOUSE_VIEW:
			frame.createMessagePopUp(insertWarehouse(table));
			frame.refreshTable(WarehouseBLL.read());
			break;
		case Constants.ViewType.ORDER_VIEW:
			frame.createMessagePopUp(insertOrder(table));
			frame.refreshTable(OrderBLL.read());
			break;
		}

	}

	private String insertUser(JTable table) {

		Object username = table.getValueAt(table.getRowCount() - 1, 0);
		Object password = table.getValueAt(table.getRowCount() - 1, 1);
		User user = ModelFactory.createUser((String)username,(String)password);
		if (user == null) {
			return Constants.ERROR.INSERT_ERROR;
		} else {
			if (!UserBLL.insert(user)) {
				return Constants.ERROR.INSERT_ERROR;
			} else {
				return Constants.SUCCESS;
			}
		}
	}
	
	private String insertWarehouse(JTable table) {

		Object name = table.getValueAt(table.getRowCount() - 1, 0);
		Object price = table.getValueAt(table.getRowCount() - 1, 1);
		Object quantity = table.getValueAt(table.getRowCount() - 1, 2);
		Warehouse warehouse = ModelFactory.createWarehouse((String)name,(String)price, (String)quantity);
		if (warehouse == null) {
			return Constants.ERROR.INSERT_ERROR;// should be displayed in a pop-up
		} else {
			if (!WarehouseBLL.insert(warehouse)) {
				return Constants.ERROR.INSERT_ERROR;// should be displayed in a
			} else {
				return Constants.SUCCESS;
			}
		}
	}
	
	private String insertOrder(JTable table) {

		Object username = table.getValueAt(table.getRowCount() - 1, 1);
		Object password = table.getValueAt(table.getRowCount() - 1, 2);
		Object name = table.getValueAt(table.getRowCount() - 1, 3);
		Object price = table.getValueAt(table.getRowCount() - 1, 4);
		Object quantity = table.getValueAt(table.getRowCount() - 1, 5);
		Order order = ModelFactory.createOrder((String)username,(String) password,(String)name,(String)price,(String)quantity);
		if (order == null) {
			return Constants.ERROR.INSERT_ERROR;// should be displayed in a pop-up
		} else {
			String status = OrderBLL.insert(order);
			if (status!=null) {
				return status;// should be displayed in a
			} else {
				return Constants.SUCCESS;
			}
		}
	}

}
