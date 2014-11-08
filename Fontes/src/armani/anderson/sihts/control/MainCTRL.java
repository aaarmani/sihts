package armani.anderson.sihts.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sun.security.action.GetLongAction;
import armani.anderson.sihts.model.ConfigurationVO;
import armani.anderson.sihts.model.UserVO;
import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.ActionView;
import armani.anderson.sihts.view.AdminView;
import armani.anderson.sihts.view.ConfigurationView;
import armani.anderson.sihts.view.DefaultView;
import armani.anderson.sihts.view.LoginView;
import armani.anderson.sihts.view.MainView;
import armani.anderson.sihts.view.PositionView;
import armani.anderson.sihts.view.ReturnView;
import armani.anderson.sihts.view.ScriptExecView;
import armani.anderson.sihts.view.ScriptView;
import armani.anderson.sihts.view.TestExecView;
import armani.anderson.sihts.view.TestView;
import armani.anderson.sihts.view.UserView;
/**
 * <p>Classe controlador para a tela principal do software, responsável pelo controle de Menus, Inicialização do Braço Robótico e 
 * Inicialização dos métodos callback dos CONTROLERS das páginas
 * 
 * @version v00.01
 * @author armani
 */
public class MainCTRL implements ActionListener, MouseListener{
	public final String PN_RETURN		= "ReturnSettings";
	public final String PN_POSITION		= "PositionSettings";
	public final String PN_ACTION		= "ActionSettings";
	public final String PN_CONFIG		= "Configurations";
	public final String PN_TEST			= "TestSettings";
	public final String PN_TEST_EXEC	= "TestExecute";
	public final String PN_SCRIPT		= "ScriptSettings";
	public final String PN_SCRIPT_EXEC	= "ScriptExecute";
	public final String PN_ADM			= "AdminSettings";
	public final String PN_USER			= "UserSettings";
	public final String PN_DEFAULT		= "DefaultSettings";
	public final String PN_LOGIN		= "LoginSettings";
	
	String PROPERTIES_STR_SERIAL = "";
	
	MainView mainFrame = null;
	Map<String, JPanel> mapPanel = null;
	
	RoboticArm roboticArm = null;

	UserVO currentUser = null;
	
	JPanel pnCur = null;
	PositionCTRL posCtrl = null;
	ActionCTRL actCtrl = null;
	ReturnCTRL retCtrl = null;
	ConfigurationCTRL confCtrl = null;
	TestCTRL testCtrl = null;
	TestExecCTRL testExecCtrl = null;
	ScriptCTRL scriptCtrl = null;
	ScriptExecCTRL scriptExecCtrl = null;
	AdminCTRL adminCtrl = null;
	UserCTRL userCtrl = null;
	LoginCTRL loginCtrl = null;
	/**
	 * Método contrutor da classe controlador do frame principal
	 * @param mainFrame2
	 */
	public MainCTRL(MainView mainFrame2) {
		this.mainFrame = mainFrame2;
		mainFrame.setVisible(true);

		mapPanel = new HashMap<String, JPanel>();
		
		ReturnView pnReturn = new ReturnView();
		PositionView pnPosition = new PositionView('P');
		ActionView pnAction = new ActionView();
		ConfigurationView pnConfiguration = new ConfigurationView();
		TestView pnTest = new TestView();
		TestExecView pnTestExec = new TestExecView();
		ScriptView pnScript = new ScriptView();
		ScriptExecView pnScriptExec = new ScriptExecView();
		AdminView pnAdmin = new AdminView();
		UserView pnUser = new UserView();
		DefaultView pnDefault = new DefaultView();
		LoginView pnLogin = new LoginView();
		
		mapPanel.put(PN_POSITION, pnPosition);
		mapPanel.put(PN_ACTION, pnAction);
		mapPanel.put(PN_RETURN, pnReturn);
		mapPanel.put(PN_CONFIG, pnConfiguration);
		mapPanel.put(PN_TEST, pnTest);
		mapPanel.put(PN_TEST_EXEC, pnTestExec);
		mapPanel.put(PN_SCRIPT, pnScript);
		mapPanel.put(PN_SCRIPT_EXEC, pnScriptExec);
		mapPanel.put(PN_ADM, pnAdmin);
		mapPanel.put(PN_USER, pnUser);
		mapPanel.put(PN_DEFAULT, pnDefault);
		mapPanel.put(PN_LOGIN, pnLogin);
				
		//read config.properties and sets configuration
		configInitialize();
		
		if(initializeRoboticArm() == false) {
			//enableMenu(false, currentUser.getType_level());
			
			//inicializa o painel de configuração do sistema
			setCurrentPanel(PN_CONFIG, currentUser);
		}
		
		this.mainFrame.getMntmAction().addActionListener(this);
		this.mainFrame.getMntmReturn().addActionListener(this);
		this.mainFrame.getMntmPosition().addActionListener(this);
		this.mainFrame.getMntmConfig().addActionListener(this);
		this.mainFrame.getMntmSair().addActionListener(this);
		this.mainFrame.getMntmNewTst().addActionListener(this);
		this.mainFrame.getMntmExecutarTst().addActionListener(this);
		this.mainFrame.getMntmNewScpt().addActionListener(this);
		this.mainFrame.getMntmExecutarScpt().addActionListener(this);
		this.mainFrame.getMntmUser().addActionListener(this);
		this.mainFrame.getMntmAdm().addActionListener(this);
		this.mainFrame.getMntmLogout().addActionListener(this);
		
		this.mainFrame.getLblIcon1().addMouseListener(this);
		this.mainFrame.getLblIcon2().addMouseListener(this);
		this.mainFrame.getLblIcon3().addMouseListener(this);
		this.mainFrame.getLblIcon4().addMouseListener(this);
		this.mainFrame.getLblIcon5().addMouseListener(this);
		
		setCurrentPanel(PN_LOGIN, currentUser);
	}

	/**
	 * Método de inicialização dos parâmetros pré-configurados
	 * Caso não exista arquivo de configuração cria um novo e inicia na tela de configuração
	 */
	private void configInitialize() {
		ConfigurationVO configVO = new ConfigurationVO();
		
		PROPERTIES_STR_SERIAL = configVO.getValue("prop.serial.interface.name");
	}

	/**
	 * <p> Método que habilita/desabilita menus
	 * Habilita/Desabilita menus que utilizam o braço robótico
	 */
	public void enableMenu(boolean enable, int type) {
		System.out.println("ENABLE = " + enable + "  Type = " + type);
		if(enable == true) {
			//habilita menus
			this.mainFrame.getMenuBarra().setVisible(true);
			this.mainFrame.getPnTop().setVisible(true);
			
			switch(type) {
			case 0: //Admin
				this.mainFrame.getMntmAdm().setVisible(true);
				this.mainFrame.getMntmNewScpt().setVisible(true);
				this.mainFrame.getMntmNewTst().setVisible(true);
				this.mainFrame.getMntmPosition().setVisible(true);
				this.mainFrame.getMntmReturn().setVisible(true);
				this.mainFrame.getMntmAction().setVisible(true);
				break;
			case 1: //Projetista
				this.mainFrame.getMntmAdm().setVisible(false);
				this.mainFrame.getMntmNewScpt().setVisible(true);
				this.mainFrame.getMntmNewTst().setVisible(true);
				this.mainFrame.getMntmPosition().setVisible(true);
				this.mainFrame.getMntmReturn().setVisible(true);
				this.mainFrame.getMntmAction().setVisible(true);
				break;
			case 2: //Testador
				this.mainFrame.getMntmAdm().setVisible(false);
				this.mainFrame.getMntmNewScpt().setVisible(false);
				this.mainFrame.getMntmNewTst().setVisible(false);
				this.mainFrame.getMntmPosition().setVisible(false);
				this.mainFrame.getMntmReturn().setVisible(false);
				this.mainFrame.getMntmAction().setVisible(false);
				break;
			}
			
		}
		else {
			//desabilita menus
			this.mainFrame.getMenuBarra().setVisible(false);
			this.mainFrame.getPnTop().setVisible(false);
		}
	}

	/**
	 * <p> Método que inicializa o braço robótico
	 * @return true - Braço Robótico Inicializado false - Braço não inicializado
	 */
	private boolean initializeRoboticArm() {
		try {
			roboticArm = new Al5b(PROPERTIES_STR_SERIAL);
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
	void setCurrentPanel(String strPnCur, UserVO user) {
		if(pnCur != null) {
			mainFrame.getPnCenter().remove(pnCur);
			posCtrl = null;
		}
		
		pnCur = mapPanel.get(strPnCur);
		
		if(pnCur == null)
			return;
		
		mainFrame.getPnCenter().add(pnCur);
		mainFrame.getPnCenter().setSize(pnCur.getSize());
		mainFrame.getPnCenter().updateUI();
		
		if (strPnCur == PN_POSITION){
			if(posCtrl == null)
				posCtrl = new PositionCTRL((PositionView) pnCur, roboticArm);	
		}
		else if (strPnCur == PN_ACTION) {
			if(actCtrl == null)
				actCtrl = new ActionCTRL((ActionView) pnCur, roboticArm);
		}
		else if (strPnCur == PN_RETURN) {
			if(retCtrl == null)
				retCtrl = new ReturnCTRL((ReturnView) pnCur);
		}
		else if (strPnCur == PN_CONFIG) {
			if(confCtrl == null)
				confCtrl = new ConfigurationCTRL((ConfigurationView)pnCur);
		}
		else if (strPnCur == PN_TEST) {
			if(testCtrl == null)
				testCtrl = new TestCTRL((TestView) pnCur, roboticArm);
		}
		else if (strPnCur == PN_TEST_EXEC) {
			if(testExecCtrl == null)
				testExecCtrl = new TestExecCTRL((TestExecView) pnCur, roboticArm);
		}
		else if (strPnCur == PN_SCRIPT) {
			if(scriptCtrl == null)
				scriptCtrl = new ScriptCTRL((ScriptView) pnCur, roboticArm);
		}
		else if (strPnCur == PN_SCRIPT) {
			if(scriptExecCtrl == null)
				scriptExecCtrl = new ScriptExecCTRL((ScriptExecView) pnCur, roboticArm);
		}
		else if (strPnCur == PN_ADM) {
			if(adminCtrl == null)
				adminCtrl = new AdminCTRL((AdminView) pnCur, user, this);
		}
		else if (strPnCur == PN_USER) {
			if(userCtrl == null)
				userCtrl = new UserCTRL((UserView) pnCur, user, this);
		}
		else if (strPnCur == PN_LOGIN) {
			if(loginCtrl == null)
				loginCtrl = new LoginCTRL((LoginView) pnCur, user, this);
			enableMenu(false, 0);
		}
		
		System.out.println("Set frame " + strPnCur);
	}

	
	@Override
	/**
	 * <p>Método de tratamento de eventos de click de botões e menus
	 */
	public void actionPerformed(ActionEvent e) {
		Object objSource = e.getSource(); 
		
		if(objSource == this.mainFrame.getMntmReturn()) {
			setCurrentPanel(PN_RETURN, currentUser);
		}
		else if(objSource == this.mainFrame.getMntmPosition()) {
			setCurrentPanel(PN_POSITION, currentUser);
		}
		else if(objSource == this.mainFrame.getMntmAction()) {
			setCurrentPanel(PN_ACTION, currentUser);
		}
		else if(objSource == this.mainFrame.getMntmNewTst()) {
			setCurrentPanel(PN_TEST, currentUser);
		}
		else if(objSource == this.mainFrame.getMntmExecutarTst()) {
			setCurrentPanel(PN_TEST_EXEC, currentUser);
		}
		else if(objSource == this.mainFrame.getMntmNewScpt()) {
			setCurrentPanel(PN_SCRIPT, currentUser);
		}
		else if(objSource == this.mainFrame.getMntmExecutarScpt()) {
			setCurrentPanel(PN_SCRIPT_EXEC, currentUser);
		}
		else if(objSource == this.mainFrame.getMntmAdm()) {
			setCurrentPanel(PN_ADM, currentUser);
		}
		else if(objSource == this.mainFrame.getMntmConfig()) {
			setCurrentPanel(PN_CONFIG, currentUser);
		}
		else if(objSource == this.mainFrame.getMntmUser()) {
			setCurrentPanel(PN_USER, currentUser);
		}
		else if(objSource == this.mainFrame.getMntmLogout()) {
			this.currentUser = null;
			setCurrentPanel(PN_LOGIN, currentUser);
		}
		else if(objSource == this.mainFrame.getMntmSair()) {
			System.exit(0);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj == this.mainFrame.getLblIcon1()) {
			setCurrentPanel(PN_DEFAULT, currentUser);
		}
		else if(obj == this.mainFrame.getLblIcon2()) {
			setCurrentPanel(PN_CONFIG, currentUser);
		}
		else if(obj == this.mainFrame.getLblIcon3()) {
			setCurrentPanel(PN_TEST_EXEC, currentUser);
		}
		else if(obj == this.mainFrame.getLblIcon4()) {
			setCurrentPanel(PN_SCRIPT_EXEC, currentUser);
		}
		else if(obj == this.mainFrame.getLblIcon5()) {
			//if(currentUser.getType_level() > 0)
				//setCurrentPanel(PN_USER, currentUser);
			//else
				setCurrentPanel(PN_ADM, currentUser);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
