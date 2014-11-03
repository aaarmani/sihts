package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class UserView extends JPanel {
	private JTextField txtName;
	private JTextField txtUser;
	private JPasswordField pswPass;
	private JPasswordField pswConfirm;
	private JComboBox<String> comboBox;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnDelete;
	
	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtUser() {
		return txtUser;
	}

	public void setTxtUser(JTextField txtUser) {
		this.txtUser = txtUser;
	}

	public JPasswordField getPswPass() {
		return pswPass;
	}

	public void setPswPass(JPasswordField pswPass) {
		this.pswPass = pswPass;
	}

	public JPasswordField getPswConfirm() {
		return pswConfirm;
	}

	public void setPswConfirm(JPasswordField pswConfirm) {
		this.pswConfirm = pswConfirm;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	/**
	 * Create the panel.
	 */
	public UserView() {
		
		JLabel lblNewLabel = new JLabel("Editar Usuário");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JLabel lblName = new JLabel("Nome");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		pswPass = new JPasswordField();
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
		lblConfirmarSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		
		pswConfirm = new JPasswordField();
		
		JLabel lblFunes = new JLabel("Tipo de Usuário");
		lblFunes.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Projetista", "Testador"}));
		
		btnSave = new JButton("Salvar");
		
		btnCancel = new JButton("Cancelar");
		
		btnDelete = new JButton("Excluir");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(109)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
								.addComponent(lblUsurio, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblConfirmarSenha, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
								.addComponent(lblFunes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtUser, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
								.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
								.addComponent(pswPass)
								.addComponent(pswConfirm, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(btnDelete)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnCancel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSave)))
							.addGap(6)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addComponent(lblNewLabel)
					.addGap(63)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsurio))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(pswPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pswConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConfirmarSenha))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFunes)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnCancel)
						.addComponent(btnDelete))
					.addContainerGap(155, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
