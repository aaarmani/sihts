package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import armani.anderson.sihts.model.ActionVO;
import armani.anderson.sihts.model.ReturnVO;
import armani.anderson.sihts.model.ScriptBO;
import armani.anderson.sihts.model.ScriptVO;
import armani.anderson.sihts.model.ScriptxtestBO;
import armani.anderson.sihts.model.ScriptxtestVO;
import armani.anderson.sihts.model.TestBO;
import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.model.TestxActionBO;
import armani.anderson.sihts.model.TestxActionVO;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.ActPositionView;
import armani.anderson.sihts.view.ScriptView;

public class ScriptCTRL implements ActionListener, ListSelectionListener {
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
	private Map<Integer, TestVO> mapScriptTests = null;
	private Map<Integer, ActPositionView> mapTestActionViews = null;
	
	private ScriptVO currentScript = null;
	
	public ScriptCTRL(ScriptView scriptView, RoboticArm roboticArm) {
		this.scriptView = scriptView;
		this.roboticArm = roboticArm;
		
		mapScript = new HashMap<String, ScriptVO>();
		mapTest = new HashMap<String, TestVO>();
		mapScriptTests = new HashMap<Integer, TestVO>();
		mapTestActionViews = new HashMap<Integer, ActPositionView>();
		
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
		btnRun.setVisible(false);
		
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		btnDelete.addActionListener(this);
		btnRun.addActionListener(this);
		btnAddTest.addActionListener(this);
		
		lstScript.addListSelectionListener(this);
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
				mapTest.put(lstTst.get(i).getName(), lstTst.get(i));
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
			ScriptxtestBO scpxtstBO = new ScriptxtestBO();
			
			for(int i = 0; i < mapScriptTests.size(); i++) {
				ScriptxtestVO scpxtstVO= new ScriptxtestVO();
				
				scpxtstVO.setScriptId((int)scptVO.getId());
				scpxtstVO.setTestId((int)mapScriptTests.get(i).getId());
				scpxtstVO.setIndex(i);
				
				scpxtstBO.insert(scpxtstVO);
			}
			
			initializeListScript();
			clearFields();
		}
		
	}

	private void btnCancelClick() {
		clearFields();
	}

	private void btnDeleteClick() {
		
	}

	private void btnAddTestClick() {
		String testName = lstTests.getSelectedValue(); 
		
		if(testName != null) {
			System.out.println("Test Selected = " + testName);
			
			addToScriptListTestPanel(testName);
			
			btnRun.setVisible(true);
		}
	}

	private void btnRunClick() {
		
	}
	
	//################## Métodos ##################
	private void addToScriptListTestPanel(String tstName) {
		TestVO tstVO = mapTest.get(tstName);
		
		Integer index = mapScriptTests.size();
		mapScriptTests.put(index, tstVO);
		
		ActPositionView actPosView = new ActPositionView(index, tstVO.getName());
		mapTestActionViews.put(index, actPosView);
		
		ScriptxTestCTRL scptxtstCTRL = new ScriptxTestCTRL(actPosView, this.scriptView.getPnTestActive(), mapScriptTests, mapTestActionViews);
		
		/*for(int i = 0; i < mapTestActions.size(); i++) {
			System.out.println("TST ACT = " + mapTestActions.get(i).getName());
		}*/
		
	}

	private void clearFields() {
		txtName.setText(null);
		txtDescription.setText(null);
		
		//limpar maps
		mapScriptTests.clear();
		mapTestActionViews.clear();
		this.scriptView.getPnTestActive().removeAll();
		this.scriptView.updateUI();
		
		//Inicializa a visualização dos botoes
		btnDelete.setVisible(false);
		btnRun.setVisible(false);
		
		//limpa seleção da lista
		lstScript.clearSelection();
		
		//reseta teste corrente
		this.currentScript = null;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object objLst = e.getSource();
		
		if ((objLst == lstScript) && (lstScript.getSelectedIndex() >= 0)) {
			this.scriptView.getPnTestActive().removeAll();
			//mapTestActionViews.clear();
			
			if(e.getValueIsAdjusting() == false) {
				
				System.out.println("INDEX = " + lstScript.getSelectedIndex());
				
				ScriptVO scptVO = new ScriptVO();
				scptVO.setName(vctScript.get(lstScript.getSelectedIndex()));
				
				ScriptBO scptBO = new ScriptBO();
				scptVO = scptBO.select(scptVO).get(0);
				
				currentScript = scptVO;
				
				//pega dados dos Testes da tela
				ScriptxtestBO tstxactBO = new ScriptxtestBO();
				Vector<String> vctTestName = tstxactBO.selectTests(scptVO);
				
				for(int i = 0; i < vctTestName.size(); i++) {
					String tstName = vctTestName.elementAt(i);
					
					addToScriptListTestPanel(tstName);
				}

				//clearFields();
				txtName.setText(scptVO.getName());
				txtDescription.setText(scptVO.getDescription());
				
				
				//pega ID do retorno e seta no dropdown
				
				//Inicializa a visualização dos botoes
				btnDelete.setVisible(true);
				btnRun.setVisible(true);
			}
		}
	}
}
