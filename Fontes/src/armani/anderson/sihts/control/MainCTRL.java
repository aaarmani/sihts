package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.MainFrame;
import armani.anderson.sihts.view.panelCrudPosition;

public class MainCTRL implements ActionListener{
	public final String PN_OBJECT		= "ObjectSettings";
	public final String PN_POSITION		= "PositionSettings";
	
	MainFrame mainFrame = null;
	Map<String, JPanel> mapPanel = null;
	
	RoboticArm roboticArm = null;

	public MainCTRL(MainFrame mainFrame2) {
		this.mainFrame = mainFrame2;
		mainFrame.setVisible(true);

		mapPanel = new HashMap<String, JPanel>();
		
		panelCrudPosition pnObject = new panelCrudPosition('O');
		panelCrudPosition pnPosition = new panelCrudPosition('P');
		
		mapPanel.put(PN_OBJECT, pnObject);
		mapPanel.put(PN_POSITION, pnPosition);
		
		//################### teste Armani
		try {
			roboticArm = new Al5b("/dev/tty.usbserial");
		} catch (Exception e1) {
			//roboticArm.close();
			JOptionPane.showMessageDialog(null, "Erro na inicialização", e1.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		
		//#########################
		
		this.mainFrame.getMntmObjeto().addActionListener(this);
		this.mainFrame.getMntmPosition().addActionListener(this);
	}


	void setCurrentPanel(String strPnCur) {
		JPanel pnCur = mapPanel.get(strPnCur);
		mainFrame.getPnCenter().add(pnCur);
		PositionCTRL posCtrl = new PositionCTRL((panelCrudPosition) pnCur, roboticArm);
		System.out.println("Set frame " + strPnCur);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object objSource = e.getSource(); 
		
		if(objSource == this.mainFrame.getMntmObjeto()) {
			setCurrentPanel(PN_OBJECT);
		}
		else if(objSource == this.mainFrame.getMntmPosition()) {
			setCurrentPanel(PN_POSITION);
		}
	}
}
