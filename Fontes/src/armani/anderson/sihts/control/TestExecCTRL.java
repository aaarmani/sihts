package armani.anderson.sihts.control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;

import armani.anderson.sihts.model.TestBO;
import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.TestExecView;

public class TestExecCTRL {
	private TestExecView testExecView = null;
	private JComboBox<String> cbTest = null;
	
	private Map<String, TestVO> mapTest = null;
	
	
	public TestExecCTRL(TestExecView testExecView, RoboticArm roboticArm) {
		this.testExecView = testExecView;
		this.cbTest = testExecView.getComboBox();
		
		cbTest.removeAllItems();
		
		//Inicializa Map
		mapTest = new HashMap<String, TestVO>();
		
		//Initialize botao de relatorio desabilitado
		this.testExecView.getBtnReport().setVisible(false);
		
		InitializeListTest();
	}

	private void InitializeListTest() {
		TestVO tstVO = null;
		TestBO testBO =  new TestBO();
		List<TestVO> lstTst = new LinkedList<TestVO>();

		lstTst = testBO.select(null);
		
		if(lstTst.size() > 0) {
			for(int i = 0; i < lstTst.size(); i++) {
				tstVO = lstTst.get(i);
				
				mapTest.put(tstVO.getName(), tstVO);
				cbTest.addItem(tstVO.getName());
			}
		}
	}
}
