package armani.anderson.sihts.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class ScriptView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5678637540356422048L;
	private JTextField txtName;
	private JTextField txtDesc;
	
	private JButton btnExecute;
	private JButton btnDelete;
	private JButton btnCancel;
	private JButton btnSave;
	
	private JButton btnTestAdd;
	
	private JList<String> lstTest;
	private JList<String> lstScript;
	private JPanel pnTestActive;

	public JButton getBtnTestAdd() {
		return btnTestAdd;
	}

	public void setBtnTestAdd(JButton btnTestAdd) {
		this.btnTestAdd = btnTestAdd;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtDesc() {
		return txtDesc;
	}

	public void setTxtDesc(JTextField txtDesc) {
		this.txtDesc = txtDesc;
	}

	public JButton getBtnExecute() {
		return btnExecute;
	}

	public void setBtnExecute(JButton btnExecute) {
		this.btnExecute = btnExecute;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JList<String> getLstTest() {
		return lstTest;
	}

	public void setLstTest(JList<String> lstTest) {
		this.lstTest = lstTest;
	}

	public JList<String> getLstScript() {
		return lstScript;
	}

	public void setLstTests(JList<String> lstScript) {
		this.lstScript = lstScript;
	}
	
	public JPanel getPnTestActive() {
		return pnTestActive;
	}

	public void setPnTestActive(JPanel pnTestActive) {
		this.pnTestActive = pnTestActive;
	}

	/**
	 * Create the panel.
	 */
	public ScriptView() {
		
		JScrollPane spnTest = new JScrollPane();
		
		JLabel lblNome = new JLabel("Nome");
		
		JLabel lblDescrio = new JLabel("Descrição");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(30);
		
		JScrollPane scpAction = new JScrollPane();
		
		JScrollPane scpTests = new JScrollPane();
		
		JLabel lblAo = new JLabel("Teste");
		
		JLabel lblTeste = new JLabel("Script");
		
		btnSave = new JButton("Salvar");
		
		btnCancel = new JButton("Cancelar");
		
		btnDelete = new JButton("Excluir");
		
		btnExecute = new JButton("Simular");
		
		JLabel lblTeste_1 = new JLabel("Script");
		lblTeste_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeste_1.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		
		btnTestAdd = new JButton(">>");
		btnTestAdd.setToolTipText("Adicionar Teste");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAo)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTeste)
								.addComponent(scpAction, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(scpTests, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnTestAdd, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTeste_1, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(spnTest, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNome)
										.addComponent(lblDescrio))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
										.addComponent(txtDesc)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnExecute)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnDelete)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCancel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSave)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTeste_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDesc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescrio))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(scpAction, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblTeste)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scpTests, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
								.addComponent(spnTest, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSave)
								.addComponent(btnCancel)
								.addComponent(btnDelete)
								.addComponent(btnExecute)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(185)
							.addComponent(btnTestAdd)))
					.addContainerGap())
		);
		
		lstScript = new JList<String>();
		lstScript.setBackground(Color.WHITE);
		scpTests.setViewportView(lstScript);
		
		lstTest = new JList<String>();
		lstTest.setBackground(Color.WHITE);
		scpAction.setViewportView(lstTest);
		
		pnTestActive = new JPanel();
		pnTestActive.setPreferredSize(new Dimension(330, 1500));
		pnTestActive.setBackground(Color.WHITE);
		spnTest.setViewportView(pnTestActive);
		setLayout(groupLayout);

	}
}
