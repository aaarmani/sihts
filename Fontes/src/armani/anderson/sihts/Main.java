package armani.anderson.sihts;

import javax.swing.JFrame;

import armani.anderson.sihts.control.MainCTRL;
import armani.anderson.sihts.view.MainFrame;

public class Main {

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		
		MainCTRL mainCtrl = new MainCTRL(mainFrame);
	}
}
