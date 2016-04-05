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

public class DeleteButtonActionListener implements ActionListener {

	private HomeFrame frame;

	public DeleteButtonActionListener(HomeFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTable table = frame.getJTable();
		switch (frame.getViewType()) {
		case Constants.ViewType.USER_VIEW:
			frame.createMessagePopUp(deleteUser(table));
			frame.refreshTable(UserBLL.read());
			break;

		case Constants.ViewType.WAREHOUSE_VIEW:
			frame.createMessagePopUp(deleteWarehouse(table));
			frame.refreshTable(WarehouseBLL.read());
			break;
		case Constants.ViewType.ORDER_VIEW:
			frame.createMessagePopUp(deleteOrder(table));
			frame.refreshTable(OrderBLL.read());
			break;
		}

	}

	private String deleteUser(JTable table) {
		
		List<Object> dbObjects = UserBLL.read();
		int [] selectedRows = table.getSelectedRows();
		for(int i=0;i<selectedRows.length;i++){
			User dbUser = (User) dbObjects.get(selectedRows[i]);
			if(!UserBLL.delete(dbUser)){
				return Constants.ERROR.DELETE_ERROR;
			}
		}
		return Constants.SUCCESS;
	}

	private String deleteWarehouse(JTable table) {

		List<Object> dbObjects = WarehouseBLL.read();
		int [] selectedRows = table.getSelectedRows();
		for(int i=0;i<selectedRows.length;i++){
			Warehouse dbWarehouse = (Warehouse) dbObjects.get(selectedRows[i]);
			if(!WarehouseBLL.delete(dbWarehouse)){
				return Constants.ERROR.DELETE_ERROR;
			}
		}
		return Constants.SUCCESS;
	}

	private String deleteOrder(JTable table) {

		List<Object> dbObjects = OrderBLL.read();
		int [] selectedRows = table.getSelectedRows();
		for(int i=0;i<selectedRows.length;i++){
			Order dbOrder = (Order) dbObjects.get(selectedRows[i]);
			if(!OrderBLL.delete(dbOrder)){
				return Constants.ERROR.DELETE_ERROR;
			}
		}
		return Constants.SUCCESS;

	}

}
