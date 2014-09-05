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
import armani.anderson.sihts.model.TestBO;
import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.TestActionView;
import armani.anderson.sihts.view.TestView;

public class TestCTRL implements ActionListener {
	private RoboticArm roboticArm;
	private TestView testView;
	
	JList<String> jlstAction = null;
	JList<String> jlstEspecial = null;
	JList<String> jlstTest = null;
	
	Vector<String> vctAction = null;
	Vector<String> vctEspecial = null;
	Vector<String> vctTest = null;
	
	Map<Integer, ActionVO> mapTestActions = null;
	Map<Integer, TestActionView> mapTestActionViews = null;

	
	public TestCTRL(TestView testView, RoboticArm roboticArm) {
		this.testView = testView;
		this.roboticArm = roboticArm;
		
		//Inicializa Listas de Ações de um Teste
		mapTestActions = new HashMap<Integer, ActionVO>();
		mapTestActionViews = new HashMap<Integer, TestActionView>();
		
		jlstAction = this.testView.getLstAction();
		jlstEspecial = this.testView.getLstEspecial();
		jlstTest = this.testView.getLstTests();
		
		InitializeTestList();
		InitializeActionList();
		InitializeEspecialList();
		
		
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


	private void InitializeEspecialList() {
		
		
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
		
		//limpar dados do test selecionado
			//id
			//Map de Test
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
			}
		}
		
	}

	private void addEspecial() {
		System.out.println("AddEspecial");
	}


	private void addAction() {
		System.out.println("AddAction");
		
	}
}
