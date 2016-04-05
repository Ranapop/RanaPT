package ro.utcn.pt.assign2.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ro.utcn.pt.assign2.bll.WarehouseBLL;
import ro.utcn.pt.assign2.presentation.view.HomeFrame;
import ro.utcn.pt.assign2.utilities.Constants;

public class WarehouseButtonActionListener implements ActionListener{
	HomeFrame homeFrame;
	public WarehouseButtonActionListener(HomeFrame frame) {
		homeFrame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		homeFrame.setViewType(Constants.ViewType.WAREHOUSE_VIEW);
		homeFrame.refreshTable(WarehouseBLL.read());
	}

}
