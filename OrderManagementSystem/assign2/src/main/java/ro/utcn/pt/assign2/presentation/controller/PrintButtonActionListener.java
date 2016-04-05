package ro.utcn.pt.assign2.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import ro.utcn.pt.assign2.bll.OrderBLL;
import ro.utcn.pt.assign2.presentation.model.Order;
import ro.utcn.pt.assign2.presentation.view.HomeFrame;
import ro.utcn.pt.assign2.utilities.Constants;

public class PrintButtonActionListener implements ActionListener {

	HomeFrame frame;
	public PrintButtonActionListener(HomeFrame frame) {
		 this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("order.txt","UTF-8");
			switch (frame.getViewType()) {
			case Constants.ViewType.USER_VIEW:
				frame.createMessagePopUp(Constants.ERROR.UNSELECTED_ORDER);
				break;
			case Constants.ViewType.WAREHOUSE_VIEW:
				frame.createMessagePopUp(Constants.ERROR.UNSELECTED_ORDER);
				break;
			case Constants.ViewType.ORDER_VIEW:
				int selectedRow = frame.getJTable().getSelectedRow();
				if (selectedRow==-1){
					frame.createMessagePopUp(Constants.ERROR.UNSELECTED_ORDER);
				}else{
					//print
					List<Object> orders = OrderBLL.read();
					Order order = (Order) orders.get(selectedRow);
					writer.println(order);
				}
				break;
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			frame.createMessagePopUp(Constants.ERROR.PRINT_ERROR);
			e.printStackTrace();
		}
		finally{
			writer.close();
		}
	}
	
}
