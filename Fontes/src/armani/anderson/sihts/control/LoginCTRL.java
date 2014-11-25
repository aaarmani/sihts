package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import armani.anderson.sihts.model.UserBO;
import armani.anderson.sihts.model.UserVO;
import armani.anderson.sihts.view.LoginView;

public class LoginCTRL implements ActionListener, KeyListener {
	private LoginView loginView = null;
	private MainCTRL mainCTRL = null;
	private JTextField txtLogin = null;
	private JTextField txtPassword = null;
	private JButton btnLogin = null;

	/**
	 * Método construtor do LoginCTRL
	 * @param loginView - Tela de login
	 * @param user - Usuário
	 * @param mainCTRL - Referência a MainCTRL para retorno após status
	 */
	public LoginCTRL(LoginView loginView, UserVO user, MainCTRL mainCTRL) {
		this.loginView = loginView;
		this.mainCTRL = mainCTRL;
		
		txtLogin = loginView.getTxtLogin();
		txtPassword = loginView.getPsfPassword();
		btnLogin =loginView.getBtnEntrar();
		
		btnLogin.addActionListener(this);
		txtPassword.addKeyListener(this);
		
		mainCTRL.enableMenu(false, 0);
	}

	/**
	 * Método de tratamento de ações de click de botões
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {
			login();
		}
	}
	
	/**
	 * Método chamado para executar e testar login do usuário
	 */
	private void login() {
		if(loginClick() == false) {
			JOptionPane.showMessageDialog(null, "Usuário ou senha inválido!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Método que realiza o login
	 * @return - True logado False erro
	 */
	private boolean loginClick() {
		UserVO userVO = new UserVO();
		
		userVO.setLogin(txtLogin.getText());
		userVO.setPassword(txtPassword.getText());
		
		UserBO userBO = new UserBO();
		try {
			List<UserVO> lstUserVO = userBO.select(userVO);
			if(lstUserVO != null && lstUserVO.size() >= 1) {
				UserVO userRet = lstUserVO.get(0);
				
				if(userRet != null) {
					txtLogin.setText(null);
					txtPassword.setText(null);
					
					mainCTRL.enableMenu(true, userRet.getType_level());
					mainCTRL.setCurrentPanel("DefaultSettings", userRet);
					return true;
				}
			}
		}catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Usuário ou senha inválido!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * Método para tratamento de tipo de tecla
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	/**
	 * Método para tratamento de pressionamento de tecla
	 */
	@Override
	public void keyPressed(KeyEvent e) {
	}
	
	/**
	 * Método para tratamento de ação ao pressionar e soltar uma tecla
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 10) {
			login();
		}
	}
}
