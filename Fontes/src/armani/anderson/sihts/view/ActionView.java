package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class ActionView extends JPanel {
	private JTextField txtName = null;
	private JList lstAction = null;
	private JList lstPosition = null;
	private JTextArea txtaDescription = null;
	private JButton btnSave = null;
	private JButton btnCancel = null;
	private JButton btnDelete = null;
	private JButton btnPlay = null;
	private JButton btnAddPosition = null; 
	
	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JList getLstAction() {
		return lstAction;
	}

	public void setLstAction(JList lstAction) {
		this.lstAction = lstAction;
	}

	public JList getLstPosition() {
		return lstPosition;
	}

	public void setLstPosition(JList lstPosition) {
		this.lstPosition = lstPosition;
	}

	public JTextArea getTxtaDescription() {
		return txtaDescription;
	}

	public void setTxtaDescription(JTextArea txtaDescription) {
		this.txtaDescription = txtaDescription;
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

	public JButton getBtnPlay() {
		return btnPlay;
	}

	public void setBtnPlay(JButton btnPlay) {
		this.btnPlay = btnPlay;
	}

	public JButton getBtnAddPosition() {
		return btnAddPosition;
	}

	public void setBtnAddPosition(JButton btnAddPosition) {
		this.btnAddPosition = btnAddPosition;
	}

	/**
	 * Create the panel.
	 */
	public ActionView() {
		
		JLabel lblTitle = new JLabel("Cadastro de Ação");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		lstAction = new JList();
		
		lstPosition = new JList();
		
		JLabel lblPosition = new JLabel("Posições:");
		lblPosition.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblAction = new JLabel("Ações:");
		lblAction.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		txtName = new JTextField();
		txtName.setColumns(20);
		
		JLabel lblName = new JLabel("Nome:");
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblDescription = new JLabel("Descrição:");
		lblDescription.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		txtaDescription = new JTextArea();
		txtaDescription.setLineWrap(true);
		txtaDescription.setColumns(25);
		txtaDescription.setTabSize(0);
		txtaDescription.setRows(2);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		
		btnSave = new JButton("Salvar");
		
		btnCancel = new JButton("Cancelar");
		
		btnDelete = new JButton("Excluir");
		
		btnPlay = new JButton("Simular");
		
		btnAddPosition = new JButton(">>");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblAction, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lstPosition, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lstAction, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
								.addComponent(lblPosition))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAddPosition, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
											.addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(txtName)
											.addComponent(txtaDescription, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnPlay)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnDelete)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnCancel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnSave)))
								.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE))
							.addGap(72))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPosition)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtaDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescription))
							.addGap(12)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(67)
									.addComponent(btnAddPosition))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lstPosition, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblAction)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lstAction, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPlay)
						.addComponent(btnSave)
						.addComponent(btnCancel)
						.addComponent(btnDelete))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
