package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AdminView extends JPanel {
	JComboBox<String> cbxUsers;
	JButton btnEditUser;
	JButton btnNewUser;
	
	public JComboBox<String> getCbxUsers() {
		return cbxUsers;
	}
	public void setCbxUsers(JComboBox<String> cbxUsers) {
		this.cbxUsers = cbxUsers;
	}
	public JButton getBtnEditUser() {
		return btnEditUser;
	}
	public void setBtnEditUser(JButton btnEditUser) {
		this.btnEditUser = btnEditUser;
	}
	public JButton getBtnNewUser() {
		return btnNewUser;
	}
	public void setBtnNewUser(JButton btnNewUser) {
		this.btnNewUser = btnNewUser;
	}

	/**
	 * Create the panel.
	 */
	public AdminView() {
		
		JLabel lblNewLabel = new JLabel("Central do Administrador");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Usu\u00E1rios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(64)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 581, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(lblNewLabel)
					.addGap(45)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(329, Short.MAX_VALUE))
		);
		
		JLabel lblUsurios = new JLabel("Usuários");
		lblUsurios.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cbxUsers = new JComboBox<String>();
		
		btnEditUser = new JButton("Editar");
		
		btnNewUser = new JButton("Novo");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(50)
					.addComponent(lblUsurios)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxUsers, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewUser)
					.addGap(30))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditUser)
						.addComponent(btnNewUser)
						.addComponent(lblUsurios)))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
}
