package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ConfigurationView extends JPanel {
	private JTextField txtInterfaceName;
	private JTextField txtInterfaceBaud;
	private JTextField txtInterfaceParity;
	private JTextField txtInterfaceDataBits;
	private JTextField txtInterfaceStopBits;
	private JTextField txtDebugName;
	private JTextField txtDebugBaud;
	private JTextField txtDebugParity;
	private JTextField txtDebugDataBits;
	private JTextField txtDebugStopBits;
	private JTextField txtDBType;
	private JTextField txtDBUser;
	private JTextField txtDBDataBase;
	private JTextField txtDBPassword;
	private JButton btnSave;
	
	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JTextField getTxtInterfaceName() {
		return txtInterfaceName;
	}

	public void setTxtInterfaceName(JTextField txtInterfaceName) {
		this.txtInterfaceName = txtInterfaceName;
	}

	public JTextField getTxtInterfaceBaud() {
		return txtInterfaceBaud;
	}

	public void setTxtInterfaceBaud(JTextField txtInterfaceBaud) {
		this.txtInterfaceBaud = txtInterfaceBaud;
	}

	public JTextField getTxtInterfaceParity() {
		return txtInterfaceParity;
	}

	public void setTxtInterfaceParity(JTextField txtInterfaceParity) {
		this.txtInterfaceParity = txtInterfaceParity;
	}

	public JTextField getTxtInterfaceDataBits() {
		return txtInterfaceDataBits;
	}

	public void setTxtInterfaceDataBits(JTextField txtInterfaceDataBits) {
		this.txtInterfaceDataBits = txtInterfaceDataBits;
	}

	public JTextField getTxtInterfaceStopBits() {
		return txtInterfaceStopBits;
	}

	public void setTxtInterfaceStopBits(JTextField txtInterfaceStopBits) {
		this.txtInterfaceStopBits = txtInterfaceStopBits;
	}

	public JTextField getTxtDebugName() {
		return txtDebugName;
	}

	public void setTxtDebugName(JTextField txtDebugName) {
		this.txtDebugName = txtDebugName;
	}

	public JTextField getTxtDebugBaud() {
		return txtDebugBaud;
	}

	public void setTxtDebugBaud(JTextField txtDebugBaud) {
		this.txtDebugBaud = txtDebugBaud;
	}

	public JTextField getTxtDebugParity() {
		return txtDebugParity;
	}

	public void setTxtDebugParity(JTextField txtDebugParity) {
		this.txtDebugParity = txtDebugParity;
	}

	public JTextField getTxtDebugDataBits() {
		return txtDebugDataBits;
	}

	public void setTxtDebugDataBits(JTextField txtDebugDataBits) {
		this.txtDebugDataBits = txtDebugDataBits;
	}

	public JTextField getTxtDebugStopBits() {
		return txtDebugStopBits;
	}

	public void setTxtDebugStopBits(JTextField txtDebugStopBits) {
		this.txtDebugStopBits = txtDebugStopBits;
	}

	public JTextField getTxtDBType() {
		return txtDBType;
	}

	public void setTxtDBType(JTextField txtDBType) {
		this.txtDBType = txtDBType;
	}

	public JTextField getTxtDBUser() {
		return txtDBUser;
	}

	public void setTxtDBUser(JTextField txtDBUser) {
		this.txtDBUser = txtDBUser;
	}

	public JTextField getTxtDBDataBase() {
		return txtDBDataBase;
	}

	public void setTxtDBDataBase(JTextField txtDBDataBase) {
		this.txtDBDataBase = txtDBDataBase;
	}

	public JTextField getTxtDBPassword() {
		return txtDBPassword;
	}

	public void setTxtDBPassword(JTextField txtDBPassword) {
		this.txtDBPassword = txtDBPassword;
	}

	public JTextField getTxtDBIp() {
		return txtDBIp;
	}

	public void setTxtDBIp(JTextField txtDBIp) {
		this.txtDBIp = txtDBIp;
	}

	public JTextField getTxtDBPort() {
		return txtDBPort;
	}

	public void setTxtDBPort(JTextField txtDBPort) {
		this.txtDBPort = txtDBPort;
	}

	private JTextField txtDBIp;
	private JTextField txtDBPort;

	/**
	 * Create the panel.
	 */
	public ConfigurationView() {
		
		JLabel lblConfiguraes = new JLabel("Configurações");
		lblConfiguraes.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfiguraes.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		
		JPanel pnDB = new JPanel();
		pnDB.setBorder(new TitledBorder(null, "Banco de Dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel pnCom = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnCom.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnCom.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Interface Com", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel pnDebug = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnDebug.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnDebug.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Debug Com", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		btnSave = new JButton("SALVAR");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(pnDB, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(pnCom, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(pnDebug, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnSave)))
						.addComponent(lblConfiguraes, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblConfiguraes)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pnDebug, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnCom, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnDB, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(9, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(415, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addContainerGap())
		);
		
		JLabel label_5 = new JLabel("Nome:");
		pnDebug.add(label_5);
		
		txtDebugName = new JTextField();
		txtDebugName.setToolTipText("Typo do banco de dados (postgres, mysql...)\n");
		txtDebugName.setColumns(15);
		pnDebug.add(txtDebugName);
		
		JLabel label_6 = new JLabel("Baud Rate:");
		pnDebug.add(label_6);
		
		txtDebugBaud = new JTextField();
		txtDebugBaud.setToolTipText("Typo do banco de dados (postgres, mysql...)\n");
		txtDebugBaud.setColumns(15);
		pnDebug.add(txtDebugBaud);
		
		JLabel label_7 = new JLabel("Paridade:");
		pnDebug.add(label_7);
		
		txtDebugParity = new JTextField();
		txtDebugParity.setToolTipText("Typo do banco de dados (postgres, mysql...)\n");
		txtDebugParity.setColumns(15);
		pnDebug.add(txtDebugParity);
		
		JLabel label_8 = new JLabel("Data Bits:");
		pnDebug.add(label_8);
		
		txtDebugDataBits = new JTextField();
		txtDebugDataBits.setToolTipText("Typo do banco de dados (postgres, mysql...)\n");
		txtDebugDataBits.setColumns(15);
		pnDebug.add(txtDebugDataBits);
		
		JLabel label_9 = new JLabel("Stop Bits:");
		pnDebug.add(label_9);
		
		txtDebugStopBits = new JTextField();
		txtDebugStopBits.setToolTipText("Typo do banco de dados (postgres, mysql...)\n");
		txtDebugStopBits.setColumns(15);
		pnDebug.add(txtDebugStopBits);
		
		JLabel label = new JLabel("Nome:");
		pnCom.add(label);
		
		txtInterfaceName = new JTextField();
		txtInterfaceName.setToolTipText("Typo do banco de dados (postgres, mysql...)\n");
		txtInterfaceName.setColumns(15);
		pnCom.add(txtInterfaceName);
		
		JLabel label_1 = new JLabel("Baud Rate:");
		pnCom.add(label_1);
		
		txtInterfaceBaud = new JTextField();
		txtInterfaceBaud.setToolTipText("Typo do banco de dados (postgres, mysql...)\n");
		txtInterfaceBaud.setColumns(15);
		pnCom.add(txtInterfaceBaud);
		
		JLabel label_2 = new JLabel("Paridade:");
		pnCom.add(label_2);
		
		txtInterfaceParity = new JTextField();
		txtInterfaceParity.setToolTipText("Typo do banco de dados (postgres, mysql...)\n");
		txtInterfaceParity.setColumns(15);
		pnCom.add(txtInterfaceParity);
		
		JLabel label_3 = new JLabel("Data Bits:");
		pnCom.add(label_3);
		
		txtInterfaceDataBits = new JTextField();
		txtInterfaceDataBits.setToolTipText("Typo do banco de dados (postgres, mysql...)\n");
		txtInterfaceDataBits.setColumns(15);
		pnCom.add(txtInterfaceDataBits);
		
		JLabel label_4 = new JLabel("Stop Bits:");
		pnCom.add(label_4);
		
		txtInterfaceStopBits = new JTextField();
		txtInterfaceStopBits.setToolTipText("Typo do banco de dados (postgres, mysql...)\n");
		txtInterfaceStopBits.setColumns(15);
		pnCom.add(txtInterfaceStopBits);
		pnDB.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Tipo do banco:");
		pnDB.add(lblNewLabel);
		
		txtDBType = new JTextField();
		pnDB.add(txtDBType);
		txtDBType.setColumns(15);
		
		JLabel lblBancoDeDados = new JLabel("Banco de Dados:");
		pnDB.add(lblBancoDeDados);
		
		txtDBDataBase = new JTextField();
		txtDBDataBase.setColumns(15);
		pnDB.add(txtDBDataBase);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário:");
		pnDB.add(lblNewLabel_1);
		
		txtDBUser = new JTextField();
		txtDBUser.setColumns(15);
		pnDB.add(txtDBUser);
		
		JLabel lblSenha = new JLabel("Senha:");
		pnDB.add(lblSenha);
		
		txtDBPassword = new JTextField();
		txtDBPassword.setColumns(15);
		pnDB.add(txtDBPassword);
		
		JLabel lblIp = new JLabel("IP:       ");
		pnDB.add(lblIp);
		
		txtDBIp = new JTextField();
		txtDBIp.setColumns(15);
		pnDB.add(txtDBIp);
		
		JLabel lblNewLabel_2 = new JLabel("Porta:");
		pnDB.add(lblNewLabel_2);
		
		txtDBPort = new JTextField();
		txtDBPort.setColumns(15);
		pnDB.add(txtDBPort);
		setLayout(groupLayout);
		
		this.setSize(800, 450);
	}
}
