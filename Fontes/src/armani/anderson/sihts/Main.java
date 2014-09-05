package armani.anderson.sihts;

import javax.swing.JFrame;

import armani.anderson.sihts.control.MainCTRL;
import armani.anderson.sihts.report.Report;
import armani.anderson.sihts.view.MainView;

public class Main {

	public static void main(String[] args) {
		MainView mainFrame = new MainView();
		mainFrame.setTitle("Simulador de Iterações Humanas para Teste de Software");
		
		/*Report report = new Report();
		report.Open("TestePDF");
		report.printTitle("Simulador de Iterações Humanas para Teste de Software");
		report.printLine("\n");
		report.printSubTitle("Isso é um Subtitulo");
		report.printLine("\n");
		report.printLine("Printando uma linha");
		report.printLine("\n");
		
		report.createTable("Testando uma tabela", 2);
		report.tableCell("Gasolina");
		report.tableCell("120,00");
		report.tableCell("Comida");
		report.tableCell("200,00");
		report.closeTable();
		report.close();*/
		
		MainCTRL mainCtrl = new MainCTRL(mainFrame);
	}
}
