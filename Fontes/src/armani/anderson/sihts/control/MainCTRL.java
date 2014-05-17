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
/**
 * <p>Classe controlador para a tela principal do software, responsável pelo controle de Menus, Inicialização do Braço Robótico e 
 * Inicialização dos métodos callback dos CONTROLERS das páginas
 * 
 * @version v00.01
 * @author armani
 */
public class MainCTRL implements ActionListener{
	public final String PN_OBJECT		= "ObjectSettings";
	public final String PN_POSITION		= "PositionSettings";
	
	MainFrame mainFrame = null;
	Map<String, JPanel> mapPanel = null;
	
	RoboticArm roboticArm = null;

	JPanel pnCur = null;
	PositionCTRL posCtrl = null;
	
	/**
	 * Método contrutor da classe controlador do frame principal
	 * @param mainFrame2
	 */
	public MainCTRL(MainFrame mainFrame2) {
		this.mainFrame = mainFrame2;
		mainFrame.setVisible(true);

		mapPanel = new HashMap<String, JPanel>();
		
		panelCrudPosition pnObject = new panelCrudPosition('O');
		panelCrudPosition pnPosition = new panelCrudPosition('P');
		
		mapPanel.put(PN_OBJECT, pnObject);
		mapPanel.put(PN_POSITION, pnPosition);
		
		if(initializeRoboticArm() == false) {
			enableMenu(false);
		}
		
				
		this.mainFrame.getMntmObjeto().addActionListener(this);
		this.mainFrame.getMntmPosition().addActionListener(this);
	}

	/**
	 * <p> Método que habilita/desabilita menus
	 * Habilita/Desabilita menus que utilizam o braço robótico
	 */
	private void enableMenu(boolean enable) {
		if(enable == true) {
			//habilita menus
		}
		else {
			//desabilita menus
		}
	}

	/**
	 * <p> Método que inicializa o braço robótico
	 * @return true - Braço Robótico Inicializado false - Braço não inicializado
	 */
	private boolean initializeRoboticArm() {
		try {
			roboticArm = new Al5b("/dev/tty.usbserial");
			return true;
		} catch (Exception e1) {
			if(roboticArm != null) {
				roboticArm.close();	
			}
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro na inicialização", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	/**
	 * Método que seta o panel que está sendo utilizado no panel central
	 * @param strPnCur - Painel a ser setado no centro do Frame Principal
	 */
	void setCurrentPanel(String strPnCur) {
		if(pnCur != null) {
			mainFrame.getPnCenter().remove(pnCur);
			posCtrl = null;
		}
		
		pnCur = mapPanel.get(strPnCur);
		mainFrame.getPnCenter().add(pnCur);
		mainFrame.getPnCenter().setSize(pnCur.getSize());
		mainFrame.getPnCenter().updateUI();
		
		posCtrl = new PositionCTRL((panelCrudPosition) pnCur, roboticArm);
		System.out.println("Set frame " + strPnCur);
	}

	
	@Override
	/**
	 * <p>Método de tratamento de eventos de click de botões e menus
	 */
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
