package ro.utcn.pt.polynomialProject.controller;

import ro.utcn.pt.polynomialProject.view.FrameDataRetriever;
import ro.utcn.pt.polynomialProject.view.PolynomialFrame;

public class Controller {

	public Controller() {

		PolynomialFrame polynomialFrame = new PolynomialFrame();
		FrameDataRetriever frameDataRetriever = new FrameDataRetriever(polynomialFrame);
		ButtonActionListener buttonActionListener = new ButtonActionListener(polynomialFrame, frameDataRetriever);
		polynomialFrame.addButtonActionListener(buttonActionListener);
	}
}
