package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import armani.anderson.sihts.model.UserBO;
import armani.anderson.sihts.model.UserVO;
import armani.anderson.sihts.view.AdminView;

public class AdminCTRL implements ActionListener {
	MainCTRL mainCtrl = null;
	AdminView admView = null;
	JButton btnEdit = null;
	JButton btnNew = null;
	JComboBox<String> cbxUsers = null;
	List<UserVO> lstUsers = null;
	
	UserVO currentUser = null;
	
	
	public AdminCTRL(AdminView admView, UserVO userVO, MainCTRL mainCtrl) {
		this.currentUser = userVO;
		
		this.mainCtrl = mainCtrl;
		this.admView = admView;
		btnEdit = this.admView.getBtnEditUser();
		btnNew = this.admView.getBtnNewUser();
		cbxUsers = this.admView.getCbxUsers();
		
		userList();
		
		btnEdit.addActionListener(this);
		btnNew.addActionListener(this);
	}

	private void userList() {
		UserBO userBO = new UserBO();
		lstUsers = userBO.select(null);
		
		cbxUsers.removeAllItems();
		
		Iterator<UserVO> lstIt = lstUsers.iterator();
		while(lstIt.hasNext()) {
			UserVO usr = (UserVO)lstIt.next();
			System.out.println("USR NAME = " + usr.getName());
			
			cbxUsers.addItem(usr.getName());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnNew) {
			btnNewClick();
		}
		else if(obj == btnEdit) {
			btnEditClick();
		}
	}

	private void btnNewClick() {
		UserVO usr = new UserVO();
		this.mainCtrl.setCurrentPanel("UserSettings", usr);
	}

	private void btnEditClick() {
		UserVO usr = getUser();
		
		if(usr != null) {
			this.mainCtrl.setCurrentPanel("UserSettings", usr);
		}
	}

	private UserVO getUser() {
		Iterator<UserVO> it = lstUsers.iterator();
		String name = (String) cbxUsers.getSelectedItem();

		while(it.hasNext()) {
			UserVO usr = it.next();
			
			System.out.println("SELECIONA = " + name + " -- " + usr.getName());
			if(name.equalsIgnoreCase(usr.getName())) {
				return usr;
			}
		}
		return null;
	}

}
