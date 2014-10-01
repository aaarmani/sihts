package armani.anderson.sihts.control;

import gnu.io.PortInUseException;

import java.io.IOException;
import java.util.List;
import java.util.TooManyListenersException;

import javax.swing.JTextArea;

import armani.anderson.sihts.model.ActionListBO;
import armani.anderson.sihts.model.ActionVO;
import armani.anderson.sihts.model.ConfigurationVO;
import armani.anderson.sihts.model.PositionVO;
import armani.anderson.sihts.model.ReturnBO;
import armani.anderson.sihts.model.ReturnVO;
import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.model.TestxActionBO;
import armani.anderson.sihts.report.Report;
import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.serial.SerialComm;

public class ExecuteTest implements Runnable{
	TestVO test = null;
	JTextArea txtarea = null;
	List<ActionVO> lstActions = null;
	RoboticArm roboticArm = null;
	String reportPath = null;
	Report reportDoc = null;
	SerialComm serialReturn = null;
	String strReturn = "";
	
	public ExecuteTest(RoboticArm roboticArm, TestVO test, JTextArea txtarea, String reportPath) {
		this.test = test;
		this.txtarea = txtarea;
		this.roboticArm = roboticArm;
		this.reportPath = reportPath;
		
		if(roboticArm == null) {
			throw new IllegalArgumentException("Erro - Braço Robótico não inicializado!");
		}
		else if(test == null || txtarea == null) {
			throw new IllegalArgumentException("Erro - Parâmetros nulos na thread Execução de Teste!");
		}
		
		if(loadActions() <= 0) {
			throw new IllegalArgumentException("Este Teste não possui Ações");
		}
		
		if(!reportPath.isEmpty()) {
			reportDoc = new Report();
		}
	}

	private int loadActions() {
		int Ret = 0;
		TestxActionBO tstactBO = new TestxActionBO();
		lstActions = tstactBO.select(test);
		
		Ret = lstActions.size();
		return Ret;
	}

	@Override
	public void run() {
		//Inicializa a serial
		InitializeSerialReturn();
		
		System.out.println("Exec Test RUN...");
		txtarea.setText("");
		txtarea.append("### RODANDO TESTE " + test.getName() + " ###" + "\n");
		
		//Abri relatório
		initializeReport();
		
		//Pega retorno esperado para o teste
		ReturnVO retVO = new ReturnVO();
		ReturnBO retBO = new ReturnBO();
		
		retVO.setId(test.getReturnId());
		List<ReturnVO> lstRetVO = retBO.select(retVO);
		if(lstRetVO.size() > 0) {
			strReturn = lstRetVO.get(0).getText();
		}
		
		//Roda ações em sequência
		for(int i = 0; i < lstActions.size(); i++) {
			ActionListBO actLstBO = new ActionListBO();
			
			//Pega lista de posições
			ActionVO actVO = lstActions.get(i);
			List<PositionVO> lstPosVO = actLstBO.selectPositionsVO(actVO);
			
			
			System.out.println("AÇÃO = " + actVO.getName());
			txtarea.append("  AÇÃO [ " + actVO.getName() + " ]" + "\n");
			
			//linha de teste no relatório
			insertReportLine("AÇÃO: " + actVO.getName(), "OK");
			
			executePositions(lstPosVO);
		}
		
		//verifica retorno
		String testReturn = serialGetString();
		if(testReturn.contains(strReturn)) {
			insertReportLine("RETORNO", "OK");
		}
		else
		{
			insertReportLine("RETORNO", "ERRO");
		}
		
		closeReport();
	}

	private void initializeReport() {
		reportDoc.Open(reportPath);
		reportDoc.printTitle("Simulador de Iterações Humanas para Teste de Software");
		reportDoc.printLine("\n");
		reportDoc.printSubTitle("Relatório de execução de Teste");
		reportDoc.printLine("\n");
		reportDoc.printLine("Teste: " + test.getName());
		if(!test.getDescription().isEmpty())
			reportDoc.printLine("Descrição: " + test.getDescription());
		reportDoc.printLine("\n");
		reportDoc.createTable("Ações e Retorno", 2);
		
	}
	
	private void insertReportLine(String cell1, String cell2) {
		reportDoc.tableCell(cell1);
		reportDoc.tableCell(cell2);
	}
	
	private void closeReport() {
		reportDoc.closeTable();
		reportDoc.close();
	}

	private void executePositions(List<PositionVO> lstPosVO) {
		
		for(int i = 0; i < lstPosVO.size(); i++) {
			PositionVO posVO = lstPosVO.get(i);
			System.out.println("Executando POS " + posVO.getName());
			txtarea.append("	Posição = " + posVO.getName() + "\n");
			txtarea.repaint();
			
			roboticArm.sendPosition(Al5b.ARTC_BASE, posVO.getPositionArtc1(), 500);
			delay(300);
			roboticArm.sendPosition(Al5b.ARTC_OMBRO, posVO.getPositionArtc2(), 500);
			delay(300);
			roboticArm.sendPosition(Al5b.ARTC_COTOVELO, posVO.getPositionArtc3(), 500);
			delay(300);
			roboticArm.sendPosition(Al5b.ARTC_PULSO, posVO.getPositionArtc4(), 500);
			delay(300);
			roboticArm.sendPosition(Al5b.ARTC_PINCA, posVO.getPositionArtc5(), 500);
			delay(2000);

		}
	}
	
	//SERIAL
	private void InitializeSerialReturn() {
		serialReturn = new SerialComm();
		ConfigurationVO configVO = new ConfigurationVO();
		
		try {
			System.out.println(configVO.getValue("prop.serial.debug.name"));
			
			serialReturn.open(configVO.getValue("prop.serial.debug.name"));
			serialReturn.setParameters(SerialComm.BAUD_115200, SerialComm.DATABITS_8, SerialComm.STOPBITS_1, SerialComm.PARITY_NONE);
		} catch (PortInUseException e) {
			throw new IllegalArgumentException("Serial de Retorno em uso.");
			
		} catch (IOException | TooManyListenersException e2) {
			throw new IllegalArgumentException("Erro físico na serial de Retorno");
		}
		
		System.out.println("Serial de Retorno Aberta com sucesso!");
	}
	
	private String serialGetString() {
		String Ret = "";
		byte[] bt = null;
		try {
			bt = serialReturn.txRx("".getBytes(), 0, 10000);
			Ret = new String(bt);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERRO IOException no TXRX");
		}
		return Ret;
	}
	
	/**
	 * Método de tempo de espera
	 * @param time
	 */
	private void delay(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
