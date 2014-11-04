package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import armani.anderson.sihts.model.UserBO;
import armani.anderson.sihts.model.UserVO;
import armani.anderson.sihts.view.UserView;

public class UserCTRL implements ActionListener {
	private static final int ADMIN = 0;
	private static final int PROJ = 1;
	private static final int TEST = 2;
	
	private UserView userView;
	private UserVO user;
	
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnDelete;
	private JComboBox<String> cbxType;
	private JTextField txtName;
	private JTextField txtUser;
	private JPasswordField txtPsw;
	private JPasswordField txtConfirm;
	private MainCTRL mainCtrl;

	public UserCTRL(UserView userView, UserVO user, MainCTRL mainCtrl) {
		this.userView = userView;
		this.user = user;
		this.mainCtrl = mainCtrl;
		
		if(userView == null) {
			throw new IllegalArgumentException("Inválida passagem de parâmetro NULO para Visualização");
		}
		
		if(user == null) {
			throw new IllegalArgumentException("Usuário NULL é inválido");
		}
		
		initComponents();
		showUser(this.user);
	}

	private void initComponents() {
		txtName = userView.getTxtName();
		txtUser = userView.getTxtUser();
		txtPsw = userView.getPswPass();
		txtConfirm = userView.getPswConfirm();
		
		btnSave = userView.getBtnSave();
		btnCancel = userView.getBtnCancel();
		btnDelete = userView.getBtnDelete();
		
		cbxType = userView.getComboBox();
		
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		btnDelete.addActionListener(this);
		
		if(user.getType_level() != ADMIN) {
			btnDelete.setVisible(false);
		}
		else {
			btnDelete.setVisible(true);
		}
	}

	private void showUser(UserVO user) {
		txtName.setText(user.getName());
		txtUser.setText(user.getLogin());
		txtPsw.setText(user.getPassword());
		txtConfirm.setText(user.getPassword());
		
		cbxType.setSelectedIndex(getCbxIndex(user.getType_level()));
	}

	private int getCbxIndex(int type) {
		int Ret = 2;
		switch(type){
			case ADMIN:
				Ret = 0;
				break;
			case PROJ:
				Ret = 1;
				break;
			case TEST:
				Ret = 2;
				break;
		}
		return Ret;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnSave) {
			btnSaveClick();
		}
		else if (obj == btnCancel) {
			btnCancelClick();
		}
		else if (obj == btnDelete) {
			btnDeleteClick();
		}
 	}
	
	private void btnSaveClick() {
		if(cbxType.getSelectedIndex() < 0) {
			showError("Selecione um tipo");
		}
		else if(txtName.getText().isEmpty() || txtPsw.getText().isEmpty() || txtConfirm.getText().isEmpty()) {
			showError("Preencha todos os campos!");
			
		}
		else if (!txtPsw.getText().equals(txtConfirm.getText())) {
			showError("Erro na confirmação da Senha");
		}
		else {
			user.setName(txtName.getText());
			user.setLogin(txtUser.getText());
			user.setPassword(txtPsw.getText());
			user.setType(cbxType.getSelectedItem().toString());
			user.setType_level(cbxType.getSelectedIndex() + 1);
			
			UserBO userBO = new UserBO();
			boolean Ret = false;
			String action;
			if(this.user.getId() >= 0) {
				Ret = userBO.update(this.user);
				action = "atualizado";
			}
			else {
				Ret = userBO.insert(this.user);
				action = "inserido";
			}
			
			if(Ret == true) {
				JOptionPane.showMessageDialog(null, "Usuário " + action + " com sucesso");
			}
		}
	}

	private void btnCancelClick() {
		clearFields();
		mainCtrl.setCurrentPanel("DefaultSettings", null);
	}

	private void btnDeleteClick() {
		UserBO usrBO = new UserBO();
		
		//confirmação
		if(usrBO.delete(this.user)) {
			clearFields();
		}
	}

	
	private void clearFields() {
		
	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "User Erro", JOptionPane.ERROR_MESSAGE);
	}
}
