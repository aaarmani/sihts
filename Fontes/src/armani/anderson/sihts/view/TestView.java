package armani.anderson.sihts.view;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TestView extends JPanel {
	private JTextField txtName;
	private JTextField txtDesc;
	
	private JButton btnExecute;
	private JButton btnDelete;
	private JButton btnCancel;
	private JButton btnSave;
	
	private JButton btnActionAdd;
	private JButton btnEspecialAdd;
	
	private JList<String> lstAction;
	private JList<String> lstReturn;
	private JList<String> lstTests;
	private JPanel pnTestActive;
	
	public JButton getBtnActionAdd() {
		return btnActionAdd;
	}

	public void setBtnActionAdd(JButton btnActionAdd) {
		this.btnActionAdd = btnActionAdd;
	}

	public JButton getBtnEspecialAdd() {
		return btnEspecialAdd;
	}

	public void setBtnEspecialAdd(JButton btnEspecialAdd) {
		this.btnEspecialAdd = btnEspecialAdd;
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

	public JList<String> getLstAction() {
		return lstAction;
	}

	public void setLstAction(JList<String> lstAction) {
		this.lstAction = lstAction;
	}
	
	public JList<String> getLstReturn() {
		return lstReturn;
	}

	public void setLstEspecial(JList<String> lstEspecial) {
		this.lstReturn = lstEspecial;
	}

	public JList<String> getLstTests() {
		return lstTests;
	}

	public void setLstTests(JList<String> lstTests) {
		this.lstTests = lstTests;
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
	public TestView() {
		
		JScrollPane spnTest = new JScrollPane();
		
		JLabel lblNome = new JLabel("Nome");
		
		JLabel lblDescrio = new JLabel("Descrição");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(30);
		
		JScrollPane scpEspecial = new JScrollPane();
		
		JScrollPane scpAction = new JScrollPane();
		
		JScrollPane scpTests = new JScrollPane();
		
		JLabel lblAoEspecial = new JLabel("Retorno");
		
		JLabel lblAo = new JLabel("Ação");
		
		JLabel lblTeste = new JLabel("Teste");
		
		btnSave = new JButton("Salvar");
		
		btnCancel = new JButton("Cancelar");
		
		btnDelete = new JButton("Excluir");
		
		btnExecute = new JButton("Simular");
		
		JLabel lblTeste_1 = new JLabel("Teste");
		lblTeste_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeste_1.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		
		btnActionAdd = new JButton(">>");
		
		btnEspecialAdd = new JButton(">>");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scpEspecial, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAo)
								.addComponent(scpAction, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTeste)
								.addComponent(scpTests, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnActionAdd, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEspecialAdd, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblAoEspecial))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTeste_1, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(spnTest, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblDescrio)
									.addComponent(lblNome))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtDesc)
									.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnExecute)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDelete)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnCancel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSave))))
					.addContainerGap(13, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAoEspecial)
								.addComponent(lblTeste_1))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(scpEspecial, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scpAction, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTeste)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scpTests, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNome))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtDesc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDescrio)
										.addComponent(btnEspecialAdd))
									.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
									.addComponent(spnTest, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnSave)
										.addComponent(btnCancel)
										.addComponent(btnDelete)
										.addComponent(btnExecute)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(241)
							.addComponent(btnActionAdd)))
					.addContainerGap())
		);
		
		lstTests = new JList<String>();
		lstTests.setBackground(Color.WHITE);
		scpTests.setViewportView(lstTests);
		
		lstAction = new JList<String>();
		lstAction.setBackground(Color.WHITE);
		scpAction.setViewportView(lstAction);
		
		lstReturn = new JList<String>();
		lstReturn.setBackground(Color.WHITE);
		scpEspecial.setViewportView(lstReturn);
		
		pnTestActive = new JPanel();
		pnTestActive.setBackground(Color.WHITE);
		spnTest.setViewportView(pnTestActive);
		setLayout(groupLayout);

	}
}
