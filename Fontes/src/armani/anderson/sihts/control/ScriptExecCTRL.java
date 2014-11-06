package armani.anderson.sihts.control;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import armani.anderson.sihts.model.ScriptBO;
import armani.anderson.sihts.model.ScriptVO;
import armani.anderson.sihts.model.TestBO;
import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.ScriptExecView;
import armani.anderson.sihts.view.TestExecView;


public class ScriptExecCTRL implements ActionListener {
	final char FS = File.separatorChar;
	final String REPORT_FOLDER = "report";
	final String DEFAULT_PATH = System.getProperty("user.dir") + FS + REPORT_FOLDER + FS;
	
	private RoboticArm roboticArm = null;
	private ScriptExecView scriptExecView = null;
	private JComboBox<String> cbScript = null;
	
	private Map<String, ScriptVO> mapScript = null;

	public ScriptExecCTRL(ScriptExecView scriptExecView, RoboticArm roboticArm) {
		this.roboticArm = roboticArm;
		this.scriptExecView = scriptExecView;
		this.cbScript = scriptExecView.getComboBox();
		
		cbScript.removeAllItems();
		this.scriptExecView.getTxtrTextarea().setText("");
		
		//Inicializa Map
		mapScript = new HashMap<String, ScriptVO>();
		
		//Initialize botao de relatorio desabilitado
		//this.testExecView.getBtnReport().setVisible(false);
		
		InitializeListTest();
		
		this.scriptExecView.getBtnExecute().addActionListener(this);
		this.scriptExecView.getBtnReport().addActionListener(this);		
	}

	private void InitializeListTest() {
		ScriptVO scptVO = null;
		ScriptBO scptBO =  new ScriptBO();
		List<ScriptVO> lstTst = new LinkedList<ScriptVO>();

		lstTst = scptBO.select(null);
		
		if(lstTst.size() > 0) {
			for(int i = 0; i < lstTst.size(); i++) {
				scptVO = lstTst.get(i);
				
				mapScript.put(scptVO.getName(), scptVO);
				cbScript.addItem(scptVO.getName());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == this.scriptExecView.getBtnExecute()) {
			executeClick();
		}
		else if (obj == this.scriptExecView.getBtnReport()) {
			reportClick();
		}
		
	}

	private void executeClick() {
		//limpa textarea
		this.scriptExecView.getTxtrTextarea().setText("");
		
		ScriptVO scptVO = null;
		
		scptVO = mapScript.get(cbScript.getSelectedItem());
		
		try {
			ExecuteScript exectTest = new ExecuteScript(roboticArm, scptVO, this.scriptExecView.getTxtrTextarea(), "ReportTest.PDF");
			exectTest.run();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao rodar Teste", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void reportClick() {
		File pdf = new File(DEFAULT_PATH + "ReportScript.PDF");  
		try {  
		  Desktop.getDesktop().open(pdf);  
		} catch(Exception ex) {  
		  ex.printStackTrace();  
		  JOptionPane.showMessageDialog(null, "Erro no Desktop: " + ex);  
		}
	}

}
