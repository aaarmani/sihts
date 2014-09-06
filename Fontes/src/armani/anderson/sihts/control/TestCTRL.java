package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JList;

import armani.anderson.sihts.model.ActionBO;
import armani.anderson.sihts.model.ActionVO;
import armani.anderson.sihts.model.ReturnBO;
import armani.anderson.sihts.model.ReturnVO;
import armani.anderson.sihts.model.TestBO;
import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.TestActionView;
import armani.anderson.sihts.view.TestView;

public class TestCTRL implements ActionListener {
	private RoboticArm roboticArm;
	private TestView testView;
	
	JList<String> jlstAction = null;
	JList<String> jlstReturn = null;
	JList<String> jlstTest = null;
	
	Vector<String> vctAction = null;
	Vector<String> vctReturn = null;
	Vector<String> vctTest = null;
	
	Map<Integer, ActionVO> mapTestActions = null;
	Map<Integer, TestActionView> mapTestActionViews = null;
	Map<Integer, ReturnVO> mapTestReturns = null;
	
	public TestCTRL(TestView testView, RoboticArm roboticArm) {
		this.testView = testView;
		this.roboticArm = roboticArm;
		
		//Inicializa Listas de Ações de um Teste
		mapTestActions = new HashMap<Integer, ActionVO>();
		mapTestActionViews = new HashMap<Integer, TestActionView>();
		
		jlstAction = this.testView.getLstAction();
		jlstReturn = this.testView.getLstReturn();
		jlstTest = this.testView.getLstTests();
		
		InitializeTestList();
		InitializeActionList();
		InitializeReturnList();
		
		
		//Inicializa a visualização dos botoes
		this.testView.getBtnDelete().setVisible(false);
		this.testView.getBtnExecute().setVisible(false);
		
		//inicializa as acoes
		this.testView.getBtnExecute().addActionListener(this);
		this.testView.getBtnDelete().addActionListener(this);
		this.testView.getBtnCancel().addActionListener(this);
		this.testView.getBtnSave().addActionListener(this);
		this.testView.getBtnActionAdd().addActionListener(this);
		this.testView.getBtnEspecialAdd().addActionListener(this);
		
		
	}

	private void InitializeReturnList() {
		ReturnBO retBO = new ReturnBO();
		vctReturn = new Vector<String>();
		
		List<ReturnVO> lstRet = retBO.select(null);
		if(lstRet != null) {
			for(int i = 0; i < lstRet.size(); i++) {
				vctReturn.add(lstRet.get(i).getName());
			}

			jlstReturn.setListData(vctReturn);
		}
	}

	private void InitializeActionList() {
		ActionBO actBO = new ActionBO();
		vctAction = new Vector<String>();
		
		List<ActionVO> lstAct = actBO.select(null);
		if(lstAct != null) {
			for(int i = 0; i < lstAct.size(); i++) {
				vctAction.add(lstAct.get(i).getName());
			}
			System.out.println("SIZE VECT = " + vctAction.size());
			jlstAction.setListData(vctAction);
		}
	}


	private void InitializeTestList() {
		TestBO tstBO = new TestBO();
		vctTest = new Vector<String>();
		
		List<TestVO> lstTst = tstBO.select(null);
		if(lstTst != null) {
			for(int i = 0; i < lstTst.size(); i++) {
				vctTest.add(lstTst.get(i).getName());
			}
			System.out.println("SIZE VECT = " + vctTest.size());
			jlstTest.setListData(vctTest);
		}
	}

	
	//############ EVENTOS ############ 
	@Override
	public void actionPerformed(ActionEvent e) {
		Object objBtn = e.getSource();
		
		if(objBtn == this.testView.getBtnExecute()) {
			executeClick();
		}
		else if (objBtn == this.testView.getBtnDelete()) {
			deleteClick();
		}
		else if (objBtn == this.testView.getBtnCancel()) {
			cancelClick();
		}
		else if (objBtn == this.testView.getBtnSave()) {
			saveClick();
		}
		else if (objBtn == this.testView.getBtnActionAdd()) {
			addAction();
		}
		else if (objBtn == this.testView.getBtnEspecialAdd()) {
			addEspecial();
		}
	}

	private void executeClick() {
		System.out.println("Execute");
	}
	
	private void deleteClick() {
		System.out.println("Delete");
	}
	
	private void cancelClick() {
		System.out.println("Cancel");

		clearFields();
	}
	
	private void saveClick() {
		System.out.println("Save");
		TestVO tstVO = new TestVO();
		
		tstVO.setName(this.testView.getTxtName().getText());
		tstVO.setDescription(this.testView.getTxtDesc().getText());
		
		if(!tstVO.getName().isEmpty()) {
			TestBO tstBO = new TestBO();
			if(tstBO.insert(tstVO) == true) {
				//insert testxaction
				
				
				InitializeTestList();
				clearFields();
			}
		}
		
	}

	private void addEspecial() {
		System.out.println("AddEspecial");
	}


	private void addAction() {
		System.out.println("AddAction");
		
	}
	
	//################## Métodos ##################
	private void clearFields() {
		this.testView.getTxtName().setText(null);
		this.testView.getTxtDesc().setText(null);
		
		//limpar maps
	}
}
