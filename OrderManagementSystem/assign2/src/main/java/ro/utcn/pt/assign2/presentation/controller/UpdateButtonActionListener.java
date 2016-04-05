package ro.utcn.pt.assign2.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;

import ro.utcn.pt.assign2.bll.OrderBLL;
import ro.utcn.pt.assign2.bll.UserBLL;
import ro.utcn.pt.assign2.bll.WarehouseBLL;
import ro.utcn.pt.assign2.presentation.model.Order;
import ro.utcn.pt.assign2.presentation.model.User;
import ro.utcn.pt.assign2.presentation.model.Warehouse;
import ro.utcn.pt.assign2.presentation.view.HomeFrame;
import ro.utcn.pt.assign2.utilities.Constants;
import ro.utcn.pt.assign2.utilities.ModelFactory;

public class UpdateButtonActionListener implements ActionListener {

	private HomeFrame frame;

	public UpdateButtonActionListener(HomeFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTable table = frame.getJTable();
		if (table.isEditing()) {
			table.getCellEditor().stopCellEditing();
		}
		switch (frame.getViewType()) {
		case Constants.ViewType.USER_VIEW:
			System.out.println("Should update a user!");
			frame.createMessagePopUp(updateUser(table));
			frame.refreshTable(UserBLL.read());
			break;

		case Constants.ViewType.WAREHOUSE_VIEW:
			frame.createMessagePopUp(updateWarehouse(table));
			frame.refreshTable(WarehouseBLL.read());
			break;
		case Constants.ViewType.ORDER_VIEW:
			frame.createMessagePopUp(updateOrder(table));
			frame.refreshTable(OrderBLL.read());
			break;
		}

	}

	private String updateUser(JTable table) {

		int rowIndex = 0;
		List<Object> dbObjects = UserBLL.read();
		for (Object dbObject : dbObjects) {
			User dbUser = (User) dbObject;
			Object username = table.getValueAt(rowIndex, 0);
			Object password = table.getValueAt(rowIndex, 1);
			User user = ModelFactory.createUser((String) username, (String) password);
			if (!dbUser.equals(user)) {
				if (user == null) {
					return Constants.ERROR.UPDATE_ERROR;
				} else {
					boolean status = UserBLL.update(dbUser, user);
					if (status == false) {
						return Constants.ERROR.UPDATE_ERROR;
					}
				}
			}
			rowIndex++;
		}
		return Constants.SUCCESS;
	}

	private String updateWarehouse(JTable table) {

		int rowIndex = 0;
		List<Object> dbObjects = WarehouseBLL.read();
		for (Object dbObject : dbObjects) {
			Warehouse dbWarehouse = (Warehouse) dbObject;
			Object name = table.getValueAt(rowIndex, 0);
			Object price = table.getValueAt(rowIndex, 1);
			Object quantity = table.getValueAt(rowIndex, 2);
			Warehouse warehouse = ModelFactory.createWarehouse(name.toString(),price.toString(),quantity.toString());
			if (!dbWarehouse.equals(warehouse)) {
				if (warehouse == null) {
					return Constants.ERROR.UPDATE_ERROR;
				} else {
					boolean status = WarehouseBLL.update(dbWarehouse, warehouse);
					if (status == false) {
						return Constants.ERROR.UPDATE_ERROR;
					}
				}
			}
			rowIndex++;
		}
		return Constants.SUCCESS;
	}

	private String updateOrder(JTable table) {

		int rowIndex = 0;
		List<Object> objects = OrderBLL.read();
		for (Object dbObject : objects) {
			Order dbOrder = (Order) dbObject;
			Object username = table.getValueAt(rowIndex, 1);
			Object password = table.getValueAt(rowIndex, 2);
			Object name = table.getValueAt(rowIndex, 3);
			Object price = table.getValueAt(rowIndex, 4);
			Object quantity = table.getValueAt(rowIndex, 5);
			Order order = ModelFactory.createOrder(username.toString(), password.toString(), name.toString(),
					price.toString(), quantity.toString());

			if (!dbOrder.equals(order)) {
				if (order == null) {
					return Constants.ERROR.UPDATE_ERROR;
				} else {
					boolean status = OrderBLL.update(dbOrder, order);
					if (status == false) {
						return Constants.ERROR.UPDATE_ERROR;
					}
				}
			} 
			rowIndex++;
		}
		return Constants.SUCCESS;

	}

}
