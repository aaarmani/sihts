package armani.anderson.sihts.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import armani.anderson.sihts.model.ScriptBO;
import armani.anderson.sihts.model.ScriptVO;
import armani.anderson.sihts.model.TestBO;
import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.ScriptView;

public class ScriptCTRL implements ActionListener {
	private ScriptView scriptView = null;
	private RoboticArm roboticArm = null;
	
	private JTextField txtName = null;
	private JTextField txtDescription = null;
	
	private JButton btnSave = null;
	private JButton btnCancel = null;
	private JButton btnDelete = null;
	private JButton btnRun = null;
	private JButton btnAddTest = null;
	
	private JList<String> lstScript = null;
	private JList<String> lstTests = null;
	
	private Map<String, ScriptVO> mapScript = null;
	private Map<String, TestVO> mapTest = null;
	private Vector<String> vctTest;
	private Vector<String> vctScript;
	
	
	
	public ScriptCTRL(ScriptView scriptView, RoboticArm roboticArm) {
		this.scriptView = scriptView;
		this.roboticArm = roboticArm;
		
		mapScript = new HashMap<String, ScriptVO>();
		mapTest = new HashMap<String, TestVO>();
		
		txtName = this.scriptView.getTxtName();
		txtDescription = this.scriptView.getTxtDesc();
		
		btnSave = this.scriptView.getBtnSave();
		btnCancel = this.scriptView.getBtnCancel();
		btnDelete = this.scriptView.getBtnDelete();
		btnRun = this.scriptView.getBtnExecute();
		btnAddTest = this.scriptView.getBtnTestAdd();
		
		lstScript = this.scriptView.getLstScript();
		lstTests = this.scriptView.getLstTest();
		
		initializeListScript();
		initializeListTeste();
		
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		btnDelete.addActionListener(this);
		btnRun.addActionListener(this);
		btnAddTest.addActionListener(this);
	}

	private void initializeListScript() {
		ScriptBO scpBO = new ScriptBO();
		vctScript = new Vector<String>();
		
		List<ScriptVO> lstScp = scpBO.select(null);
		if(lstScp != null) {
			for(int i = 0; i < lstScp.size(); i++) {
				vctScript.add(lstScp.get(i).getName());
			}
			
			if(!vctScript.isEmpty())
				lstScript.setListData(vctScript);
		}
		
	}

	private void initializeListTeste() {
		TestBO tstBO = new TestBO();
		vctTest = new Vector<String>();
		
		List<TestVO> lstTst = tstBO.select(null);
		if(lstTst != null) {
			for(int i = 0; i < lstTst.size(); i++) {
				vctTest.add(lstTst.get(i).getName());
			}
			if(!vctTest.isEmpty())
				lstTests.setListData(vctTest);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnSave) {
			btnSaveClick();
		}
		else if (obj == btnCancel) {
			btnCancelClick();
		}
		else if (obj == btnDelete) {
			btnDeleteClick();
		}
		else if (obj == btnAddTest) {
			btnAddTestClick();
		}
		else if (obj == btnRun) {
			btnRunClick();
		}
		
	}

	private void btnSaveClick() {
		ScriptVO scptVO = new ScriptVO();
		
		scptVO.setName(txtName.getText());
		scptVO.setDescription(txtDescription.getText());
		
		ScriptBO scptBO = new ScriptBO();
		if(scptBO.insert(scptVO) == true) {
			//Scriptx
			
		}
		
	}

	private void btnCancelClick() {
		clearFields();
		
	}

	private void btnDeleteClick() {
		// TODO Auto-generated method stub
		
	}

	private void btnAddTestClick() {
		// TODO Auto-generated method stub
		
	}

	private void btnRunClick() {
		// TODO Auto-generated method stub
		
	}
	
	//Other Methods
	private void clearFields() {
		txtName.setText(null);
		txtDescription.setText(null);
	}
}
