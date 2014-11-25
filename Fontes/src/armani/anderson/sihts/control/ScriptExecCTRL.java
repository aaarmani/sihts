package armani.anderson.sihts.control;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
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
	final String REPORT_SCRIPT = "ReportScript.PDF";
	
	private RoboticArm roboticArm = null;
	private ScriptExecView scriptExecView = null;
	private JComboBox<String> cbScript = null;
	
	private JButton btnExec = null;
	private JButton btnReport = null;
	
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
		
		InitializeListScript();
		
		btnExec = this.scriptExecView.getBtnExecute();
		btnReport = this.scriptExecView.getBtnReport();
		
		btnExec.addActionListener(this);
		btnReport.addActionListener(this);		
	}

	private void InitializeListScript() {
		ScriptVO scptVO = null;
		ScriptBO scptBO =  new ScriptBO();
		List<ScriptVO> lstScpt;// = new LinkedList<ScriptVO>();

		lstScpt = scptBO.select(null);
		
		if(lstScpt.size() > 0) {
			for(int i = 0; i < lstScpt.size(); i++) {
				scptVO = lstScpt.get(i);
				
				mapScript.put(scptVO.getName(), scptVO);
				cbScript.addItem(scptVO.getName());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnExec) {
			executeClick();
		}
		else if (obj == btnReport) {
			reportClick();
		}
		
	}

	private void executeClick() {
		//limpa textarea
		this.scriptExecView.getTxtrTextarea().setText("");
		this.scriptExecView.getTxtrTextarea().repaint();
		
		ScriptVO scptVO = null;
		
		scptVO = mapScript.get(cbScript.getSelectedItem());
		
		try {
			ExecuteScript exectScript = new ExecuteScript(roboticArm, scptVO, this.scriptExecView.getTxtrTextarea(), REPORT_SCRIPT);
			exectScript.run();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao rodar Script", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void reportClick() {
		File pdf = new File(DEFAULT_PATH + REPORT_SCRIPT);  
		try {  
		  Desktop.getDesktop().open(pdf);  
		} catch(Exception ex) {  
		  //ex.printStackTrace();
		  JOptionPane.showMessageDialog(null, "Erro ao abrir relatório de Script, o arquivo não existe.", "Erro ao abrir relatório de Script", JOptionPane.ERROR_MESSAGE);
		}
	}

}
