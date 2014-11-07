package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.org.apache.bcel.internal.generic.LSTORE;

import armani.anderson.sihts.model.ActionBO;
import armani.anderson.sihts.model.ActionVO;
import armani.anderson.sihts.model.ReturnBO;
import armani.anderson.sihts.model.ReturnVO;
import armani.anderson.sihts.model.TestBO;
import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.model.TestxActionBO;
import armani.anderson.sihts.model.TestxActionVO;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.ActPositionView;
import armani.anderson.sihts.view.ReturnView;
import armani.anderson.sihts.view.TestActionView;
import armani.anderson.sihts.view.TestView;

public class TestCTRL implements ActionListener, ListSelectionListener {
	private RoboticArm roboticArm;
	private TestView testView;
	
	JList<String> jlstAction = null;
	JList<String> jlstTest = null;
	
	Vector<String> vctAction = null;
	Vector<String> vctReturn = null;
	Vector<String> vctTest = null;
	
	Map<String, ActionVO> mapActions = null;
	Map<Integer, ActionVO> mapTestActions = null;
	Map<Integer, ActPositionView> mapTestActionViews = null;
	Map<String, ReturnVO> mapTestReturns = null;
	
	JComboBox<String> cbTests = null;
	
	TestVO currentTest = null;
	
	public TestCTRL(TestView testView, RoboticArm roboticArm) {
		this.testView = testView;
		this.roboticArm = roboticArm;
		
		//Inicializa Listas de Ações de um Teste
		mapActions = new HashMap<String, ActionVO>();
		mapTestActions = new HashMap<Integer, ActionVO>();
		mapTestActionViews = new HashMap<Integer, ActPositionView>();
		mapTestReturns = new HashMap<String, ReturnVO>();
		
		jlstAction = this.testView.getLstAction();
		jlstTest = this.testView.getLstTests();
		cbTests =  this.testView.getCbReturn();
		
		InitializeTestList();
		InitializeActionList();
		InitializeComboReturn();
		
		//Inicializa a visualização dos botoes
		this.testView.getBtnDelete().setVisible(false);
		this.testView.getBtnExecute().setVisible(false);
		
		//inicializa as acoes
		this.testView.getBtnExecute().addActionListener(this);
		this.testView.getBtnDelete().addActionListener(this);
		this.testView.getBtnCancel().addActionListener(this);
		this.testView.getBtnSave().addActionListener(this);
		this.testView.getBtnActionAdd().addActionListener(this);
		
		this.testView.getLstTests().addListSelectionListener(this);
		
	}

	private void InitializeActionList() {
		ActionBO actBO = new ActionBO();
		vctAction = new Vector<String>();
		
		List<ActionVO> lstAct = actBO.select(null);
		if(lstAct != null) {
			for(int i = 0; i < lstAct.size(); i++) {
				vctAction.add(lstAct.get(i).getName());
				mapActions.put(lstAct.get(i).getName(), lstAct.get(i));
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

	private void InitializeComboReturn() {
		ReturnVO retVO = null;
		ReturnBO retBO =  new ReturnBO();
		List<ReturnVO> lstTst = new LinkedList<ReturnVO>();
		vctReturn = new Vector<String>();
		
		lstTst = retBO.select(null);
		
		if(lstTst.size() > 0) {
			cbTests.removeAllItems();
			
			for(int i = 0; i < lstTst.size(); i++) {
				retVO = lstTst.get(i);
				
				mapTestReturns.put(retVO.getName(), retVO);
				cbTests.addItem(retVO.getName());
				
				vctReturn.add(retVO.getName());
			}
		}		
	}	
	//############ EVENTOS ############
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object objLst = e.getSource();
		
		if ((objLst == this.testView.getLstTests()) && (jlstTest.getSelectedIndex() >= 0)) {
			this.testView.getPnTestActive().removeAll();
			//mapTestActionViews.clear();
			
			if(e.getValueIsAdjusting() == false) {
				
				System.out.println("INDEX = " + jlstTest.getSelectedIndex());
				
				TestVO tstVO = new TestVO();
				tstVO.setName(vctTest.get(jlstTest.getSelectedIndex()));
				
				TestBO tstBO = new TestBO();
				tstVO = tstBO.select(tstVO).get(0);
				
				currentTest = tstVO;
				
				//pega dados das ações da tela
				TestxActionBO tstxactBO = new TestxActionBO();
				Vector<String> vctActName = tstxactBO.selectActions(tstVO);
				
				for(int i = 0; i < vctActName.size(); i++) {
					String actName = vctActName.elementAt(i);
					
					addToTestListActionPanel(actName);
				}
				
				for(int i = 0; i < mapTestReturns.size(); i++) {
					ReturnVO retVO = mapTestReturns.get(vctReturn.get(i));
					
					if(retVO.getId() == tstVO.getReturnId()) {
						this.testView.getCbReturn().setSelectedIndex(i);
						break;
					}
				}
				
				//clearFields();
				this.testView.getTxtName().setText(tstVO.getName());
				this.testView.getTxtDesc().setText(tstVO.getDescription());
				
				
				//pega ID do retorno e seta no dropdown
				
				//Inicializa a visualização dos botoes
				this.testView.getBtnDelete().setVisible(true);
				this.testView.getBtnExecute().setVisible(true);
			}
		}
	}
	
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
	}

	private void executeClick() {
		System.out.println("Execute");
	}
	
	private void deleteClick() {
		System.out.println("Delete");
		
		if(currentTest == null) {
			return;
		}
		
		//deletar realmente??
		if(JOptionPane.showConfirmDialog(null, "Deseja excluir realmente este Teste?", "Exclusão de Teste",0, JOptionPane.WARNING_MESSAGE) > 0) {
			//não deve deletar o Teste
			return;
		}
		
		TestxActionBO tstxactBO = new TestxActionBO();
		tstxactBO.delete(currentTest);
		
		TestBO tstBO = new TestBO();
		tstBO.delete(currentTest);
		
		clearFields();
		InitializeTestList();
		
		this.testView.getBtnDelete().setVisible(false);
		this.testView.getBtnExecute().setVisible(false);
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
		
		ReturnVO retVO = mapTestReturns.get(cbTests.getSelectedItem());
		tstVO.setReturnId(retVO.getId());
		
		if(!tstVO.getName().isEmpty()) {
			TestBO tstBO = new TestBO();
			if(tstBO.insert(tstVO) == true) {
				//insert testxaction
				TestxActionBO txaBO = new TestxActionBO();
				
				for(int i = 0; i < mapTestActions.size(); i++) {
					TestxActionVO txaVO= new TestxActionVO();
					
					txaVO.setTestId((int)tstVO.getId());
					txaVO.setActionId((int)mapTestActions.get(i).getId());
					txaVO.setIndex(i);
					
					txaBO.insert(txaVO);
				}
				
				InitializeTestList();
				clearFields();
			}
		}
	}

	private void addAction() {
		String actName = this.testView.getLstAction().getSelectedValue();
		
		if(actName != null) {
			System.out.println("Position Selected = " + actName);
			
			addToTestListActionPanel(actName);
			
			this.testView.getBtnExecute().setVisible(true);
		}
		
	}
	//################## Métodos ##################
	private void addToTestListActionPanel(String actName) {
		ActionVO actVO = mapActions.get(actName);
		
		Integer index = mapTestActions.size();
		mapTestActions.put(index, actVO);
		
		ActPositionView actPosView = new ActPositionView(index, actVO.getName());
		mapTestActionViews.put(index, actPosView);
		
		TestxActionCTRL tstxactCTRL = new TestxActionCTRL(actPosView, this.testView.getPnTestActive(), mapTestActions, mapTestActionViews);
		
		/*for(int i = 0; i < mapTestActions.size(); i++) {
			System.out.println("TST ACT = " + mapTestActions.get(i).getName());
		}*/
		
	}

	
	private void clearFields() {
		this.testView.getTxtName().setText(null);
		this.testView.getTxtDesc().setText(null);
		
		//limpar maps
		mapTestActions.clear();
		mapTestActionViews.clear();
		this.testView.getPnTestActive().removeAll();
		this.testView.updateUI();
		
		//Inicializa a visualização dos botoes
		this.testView.getBtnDelete().setVisible(false);
		this.testView.getBtnExecute().setVisible(false);
		
		//limpa seleção da lista
		jlstTest.clearSelection();
		
		//reseta teste corrente
		this.currentTest = null;
	}
}
