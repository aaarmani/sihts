package armani.anderson.sihts;

import armani.anderson.sihts.control.MainCTRL;
import armani.anderson.sihts.view.MainView;

public class Main {

	public static void main(String[] args) {
		MainView mainFrame = new MainView();
		
		mainFrame.setTitle("Simulador de Iterações Humanas para Teste de Software");
		MainCTRL mainCtrl = new MainCTRL(mainFrame);
	}
}
