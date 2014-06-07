package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import armani.anderson.sihts.model.ConfigurationVO;
import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.ActionView;
import armani.anderson.sihts.view.ConfigurationView;
import armani.anderson.sihts.view.MainView;
import armani.anderson.sihts.view.PositionView;
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
	public final String PN_ACTION		= "ActionSettings";
	public final String PN_CONFIG		= "Configurations";
	
	MainView mainFrame = null;
	Map<String, JPanel> mapPanel = null;
	
	RoboticArm roboticArm = null;

	JPanel pnCur = null;
	PositionCTRL posCtrl = null;
	ActionCTRL actCtrl = null;
	ConfigurationCTRL confCtrl = null;
	
	
	/**
	 * Método contrutor da classe controlador do frame principal
	 * @param mainFrame2
	 */
	public MainCTRL(MainView mainFrame2) {
		this.mainFrame = mainFrame2;
		mainFrame.setVisible(true);

		mapPanel = new HashMap<String, JPanel>();
		
		PositionView pnObject = new PositionView('O');
		PositionView pnPosition = new PositionView('P');
		ActionView pnAction = new ActionView();
		ConfigurationView pnConfiguration = new ConfigurationView();
		
		mapPanel.put(PN_OBJECT, pnObject);
		mapPanel.put(PN_POSITION, pnPosition);
		mapPanel.put(PN_ACTION, pnAction);
		mapPanel.put(PN_CONFIG, pnConfiguration);
				
		//read config.properties and sets configuration
		configInitialize();
		
		if(initializeRoboticArm() == false) {
			enableMenu(false);
		}
		
		this.mainFrame.getMntmAction().addActionListener(this);
		this.mainFrame.getMntmObjeto().addActionListener(this);
		this.mainFrame.getMntmPosition().addActionListener(this);
		this.mainFrame.getMntmConfig().addActionListener(this);
		this.mainFrame.getMntmSair().addActionListener(this);
	}

	/**
	 * Método de inicialização dos parâmetros pré-configurados
	 * Caso não exista arquivo de configuração cria um novo e inicia na tela de configuração
	 */
	private void configInitialize() {
		//ConfigurationVO configVo = null;

		//configVo = new ConfigurationVO();
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
		
		if ((strPnCur == PN_OBJECT) || (strPnCur == PN_POSITION)) {
			posCtrl = new PositionCTRL((PositionView) pnCur, roboticArm);	
		}
		else if (strPnCur == PN_ACTION) {
			actCtrl = new ActionCTRL((ActionView)pnCur, roboticArm);
		}
		else if (strPnCur == PN_CONFIG) {
			confCtrl = new ConfigurationCTRL((ConfigurationView)pnCur);
		}
		
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
		else if(objSource == this.mainFrame.getMntmAction()) {
			setCurrentPanel(PN_ACTION);
		}
		else if(objSource == this.mainFrame.getMntmConfig()) {
			setCurrentPanel(PN_CONFIG);
		}
		else if(objSource == this.mainFrame.getMntmSair()) {
			System.exit(0);
		}
		
	}
}
