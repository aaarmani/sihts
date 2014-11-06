package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import armani.anderson.sihts.model.UserBO;
import armani.anderson.sihts.model.UserVO;
import armani.anderson.sihts.view.LoginView;

public class LoginCTRL implements ActionListener {
	private LoginView loginView = null;
	private MainCTRL mainCTRL = null;
	private JTextField txtLogin = null;
	private JTextField txtPassword = null;
	private JButton btnLogin = null;
	
	public LoginCTRL(LoginView loginView, UserVO user, MainCTRL mainCTRL) {
		this.loginView = loginView;
		this.mainCTRL = mainCTRL;
		
		txtLogin = loginView.getTxtLogin();
		txtPassword = loginView.getPsfPassword();
		btnLogin =loginView.getBtnEntrar();
		
		btnLogin.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {
			if(loginClick() == false) {
				JOptionPane.showMessageDialog(null, "Login ou senha inválido!");
			}
		}
	}
	
	private boolean loginClick() {
		UserVO userVO = new UserVO();
		
		userVO.setLogin(txtLogin.getText());
		userVO.setPassword(txtPassword.getText());
		
		UserBO userBO = new UserBO();
		UserVO userRet = userBO.select(userVO).get(0);
		
		if(userRet != null) {
			mainCTRL.setCurrentPanel("LoginSettings", userRet);
			return true;
		}
		return false;
	}

}
