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
import armani.anderson.sihts.model.ScriptVO;
import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.model.TestxActionBO;
import armani.anderson.sihts.report.Report;
import armani.anderson.sihts.serial.Al5b;
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
		
	}

	@Override
	public void run() {
		
	}
}
