package ro.utcn.pt.assign2.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ro.utcn.pt.assign2.bll.OrderBLL;
import ro.utcn.pt.assign2.presentation.view.HomeFrame;
import ro.utcn.pt.assign2.utilities.Constants;

public class OrderButtonActionListener implements ActionListener{
	HomeFrame homeFrame;
	public OrderButtonActionListener(HomeFrame frame) {
		homeFrame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		homeFrame.setViewType(Constants.ViewType.ORDER_VIEW);
		homeFrame.refreshTable(OrderBLL.read());
	}

}
