package armani.anderson.sihts;

import javax.swing.JFrame;

import armani.anderson.sihts.control.MainCTRL;
import armani.anderson.sihts.view.MainFrame;

public class Main {

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setTitle("Simulador de Iterações Humanas para Teste de Software");
		
		MainCTRL mainCtrl = new MainCTRL(mainFrame);
	}
}
