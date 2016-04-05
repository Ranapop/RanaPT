package ro.utcn.pt.assign2.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ro.utcn.pt.assign2.bll.UserBLL;
import ro.utcn.pt.assign2.presentation.view.HomeFrame;
import ro.utcn.pt.assign2.utilities.Constants;

public class UserButtonActionListener implements ActionListener {

	HomeFrame homeFrame;
	public UserButtonActionListener(HomeFrame frame) {
		homeFrame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		homeFrame.setViewType(Constants.ViewType.USER_VIEW);
		homeFrame.refreshTable(UserBLL.read());
	}

}
