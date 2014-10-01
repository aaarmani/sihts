package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ReturnView extends JPanel {
	private JTextField txtName;
	private JTextField txtTimeout;
	private JTextArea txtDescription;
	private JTextArea txtReturn;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnDelete;
	private JList lstReturn;
	
	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtTimeout() {
		return txtTimeout;
	}

	public void setTxtTimeout(JTextField txtTimeout) {
		this.txtTimeout = txtTimeout;
	}

	public JTextArea getTxtDescription() {
		return txtDescription;
	}

	public void setTxtDescription(JTextArea txtDescription) {
		this.txtDescription = txtDescription;
	}

	public JTextArea getTxtReturn() {
		return txtReturn;
	}

	public void setTxtReturn(JTextArea txtReturn) {
		this.txtReturn = txtReturn;
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

	public JList getLstReturn() {
		return lstReturn;
	}

	public void setLstReturn(JList lstReturn) {
		this.lstReturn = lstReturn;
	}

	/**
	 * Create the panel.
	 */
	public ReturnView() {
		
		JLabel lblNewLabel = new JLabel("Cadastro de Retorno de Teste");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		
		JLabel lblName = new JLabel("Nome");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtDescription = new JTextArea();
		txtDescription.setDragEnabled(false);
		txtDescription.setRows(2);
		txtDescription.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDescription.setTabSize(0);
		txtDescription.setLineWrap(true);
		
		JLabel lblTimeout = new JLabel("Timeout");
		lblTimeout.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtTimeout = new JTextField();
		txtTimeout.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Return");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtReturn = new JTextArea();
		txtReturn.setRows(2);
		txtReturn.setDragEnabled(false);
		txtReturn.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtReturn.setTabSize(0);
		txtReturn.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_2 = new JLabel("Retornos");
		
		btnSave = new JButton("Salvar");
		
		btnCancel = new JButton("Cancelar");
		
		btnDelete = new JButton("Deletar");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(txtDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnDelete)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnCancel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnSave)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
												.addComponent(lblTimeout, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtReturn, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
												.addComponent(txtTimeout, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))))
									.addGap(21))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addComponent(lblNewLabel)
					.addGap(48)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblName))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(17))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDescription)
									.addGap(18)))
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTimeout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTimeout))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(txtReturn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnCancel)
										.addComponent(btnSave)
										.addComponent(btnDelete)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(28)
									.addComponent(lblNewLabel_1))))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
					.addGap(35))
		);
		
		lstReturn = new JList();
		scrollPane.setViewportView(lstReturn);
		setLayout(groupLayout);

	}
}
