package armani.anderson.sihts.control;

import java.util.List;

import javax.swing.JTextArea;

import armani.anderson.sihts.model.ScriptVO;
import armani.anderson.sihts.model.ScriptxtestBO;
import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.model.TestxActionBO;
import armani.anderson.sihts.report.Report;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.serial.SerialComm;

public class ExecuteScript implements Runnable{
	ScriptVO script = null;
	JTextArea txtarea = null;
	List<TestVO> lstTests = null;
	RoboticArm roboticArm = null;
	String reportPath = null;
	Report reportDoc = null;
	SerialComm serialReturn = null;
	String strReturn = "";
	
	public ExecuteScript(RoboticArm roboticArm, ScriptVO scptVO, JTextArea txtarea, String reportPath) {
		this.script = scptVO;
		this.txtarea = txtarea;
		this.roboticArm = roboticArm;
		this.reportPath = reportPath;
		
		if(roboticArm == null) {
			throw new IllegalArgumentException("Erro - Braço Robótico não inicializado!");
		}
		else if(this.script == null || txtarea == null) {
			throw new IllegalArgumentException("Erro - Parâmetros nulos na thread Execução de Teste!");
		}
		
		if(loadTests() <= 0) {
			throw new IllegalArgumentException("Este Script não possui Testes");
		}
		
		if(!reportPath.isEmpty()) {
			reportDoc = new Report();
		}
		txtarea.setText(null);
	}

	private int loadTests() {
		int Ret = 0;
		ScriptxtestBO scptxtstBO = new ScriptxtestBO();
		lstTests = scptxtstBO.select(script);
		
		Ret = lstTests.size();
		return Ret;
	}

	@Override
	public void run() {
		
		for(int i = 0; i < lstTests.size(); i++) {
			TestVO testExec = lstTests.get(i);
			
			System.out.println("SCRIPT RUN TST = " + testExec.getName() + "  --- Size =  " + lstTests.size());
			ExecuteTest execTest = new ExecuteTest(roboticArm, testExec, txtarea, reportPath);
			execTest.run();
		}	
	}
}
