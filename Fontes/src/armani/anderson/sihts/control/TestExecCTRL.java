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
import javax.swing.JTextArea;

import armani.anderson.sihts.model.ActionVO;
import armani.anderson.sihts.model.TestBO;
import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.model.TestxActionBO;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.TestExecView;

public class TestExecCTRL implements ActionListener {
	final char FS = File.separatorChar;
	final String REPORT_FOLDER = "report";
	final String DEFAULT_PATH = System.getProperty("user.dir") + FS + REPORT_FOLDER + FS;
	
	private RoboticArm roboticArm = null;
	private TestExecView testExecView = null;
	private JComboBox<String> cbTest = null;
	
	private Map<String, TestVO> mapTest = null;

	public TestExecCTRL(TestExecView testExecView, RoboticArm roboticArm) {
		this.roboticArm = roboticArm;
		this.testExecView = testExecView;
		this.cbTest = testExecView.getComboBox();
		
		cbTest.removeAllItems();
		this.testExecView.getTxtrTextarea().setText("");
		
		//Inicializa Map
		mapTest = new HashMap<String, TestVO>();
		
		//Initialize botao de relatorio desabilitado
		//this.testExecView.getBtnReport().setVisible(false);
		
		InitializeListTest();
		
		this.testExecView.getBtnExecute().addActionListener(this);
		this.testExecView.getBtnReport().addActionListener(this);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == this.testExecView.getBtnExecute()) {
			executeClick();
		}
		else if (obj == this.testExecView.getBtnReport()) {
			reportClick();
		}
		
	}

	private void executeClick() {
		//limpa textarea
		this.testExecView.getTxtrTextarea().setText("");
		
		TestVO tstVO = null;
		
		tstVO = mapTest.get(cbTest.getSelectedItem());
		
		try {
			this.testExecView.getTxtrTextarea().setText(null); //clean textarea
			ExecuteTest exectTest = new ExecuteTest(roboticArm, tstVO, this.testExecView.getTxtrTextarea(), "ReportTest.PDF", false);
			exectTest.run();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao rodar Teste", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void reportClick() {
		File pdf = new File(DEFAULT_PATH + "ReportTest.PDF");  
		try {  
		  Desktop.getDesktop().open(pdf);  
		} catch(Exception ex) {  
		  ex.printStackTrace();  
		  JOptionPane.showMessageDialog(null, "Erro no Desktop: " + ex);  
		}
	}
}
