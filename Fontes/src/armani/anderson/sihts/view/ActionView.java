package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class ActionView extends JPanel {
	private JTextField txtName = null;
	private JList<String> lstAction = null;
	private JList<String> lstPosition = null;
	private JTextArea txtaDescription = null;
	private JButton btnSave = null;
	private JButton btnCancel = null;
	private JButton btnDelete = null;
	private JButton btnPlay = null;
	private JButton btnAddPosition = null; 
	private JPanel pnActionPositions2 = null;
	private JScrollPane pnActionPositions = null;
	
	public JPanel getPnActionPositions() {
		return pnActionPositions2;
	}

	public void setPnActionPositions(JPanel pnActionPositions) {
		this.pnActionPositions2 = pnActionPositions;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JList<String> getLstAction() {
		return lstAction;
	}

	public void setLstAction(JList<String> lstAction) {
		this.lstAction = lstAction;
	}

	public JList<String> getLstPosition() {
		return lstPosition;
	}

	public void setLstPosition(JList<String> lstPosition) {
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
		
		txtName = new JTextField();
		txtName.setColumns(20);
		
		JLabel lblName = new JLabel("Nome:");
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblDescription = new JLabel("Descrição:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		txtaDescription = new JTextArea();
		txtaDescription.setDragEnabled(false);
		txtaDescription.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtaDescription.setLineWrap(true);
		txtaDescription.setTabSize(0);
		txtaDescription.setRows(2);
		
		pnActionPositions2 = new JPanel();
		pnActionPositions2.setBackground(Color.WHITE);
		pnActionPositions = new JScrollPane();
		pnActionPositions.setBackground(Color.WHITE);
		
		btnSave = new JButton("Salvar");
		
		btnCancel = new JButton("Cancelar");
		
		btnDelete = new JButton("Excluir");
		
		btnPlay = new JButton("Simular");
		
		btnAddPosition = new JButton(">>");
		btnAddPosition.setToolTipText("Adicionar Posição");
		
		JPanel pnPos = new JPanel();
		
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
							.addComponent(pnPos, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddPosition, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
											.addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(txtName)
											.addComponent(txtaDescription, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnPlay)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnDelete)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnCancel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnSave)))
								.addComponent(pnActionPositions, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE))
							.addGap(40))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblTitle)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(118)
							.addComponent(btnAddPosition))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(pnPos, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblName)
										.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtaDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDescription))
									.addGap(12)
									.addComponent(pnActionPositions, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnPlay)
										.addComponent(btnSave)
										.addComponent(btnCancel)
										.addComponent(btnDelete))))))
					.addContainerGap())
		);
		pnPos.setLayout(null);
		
		JLabel lblPosition = new JLabel("Posições:");
		lblPosition.setBounds(0, 0, 72, 20);
		pnPos.add(lblPosition);
		lblPosition.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JScrollPane scpPosition = new JScrollPane();
		pnPos.add(scpPosition);
		scpPosition.setBounds(new Rectangle(0, 25, 166, 150));
		lstPosition = new JList<String>();
		scpPosition.setViewportView(lstPosition);
		
		JLabel lblAction = new JLabel("Ações:");
		lblAction.setBounds(0, 195, 143, 20);
		pnPos.add(lblAction);
		lblAction.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JScrollPane scpAction = new JScrollPane();
		pnPos.add(scpAction);
		scpAction.setBounds(new Rectangle(0, 215, 166, 150));
		lstAction = new JList<String>();
		scpAction.setViewportView(lstAction);
		
		//pnActionPositions2.setBounds(0, 0, 410, 310);
		pnActionPositions2.setPreferredSize(new Dimension(330, 1500));
		pnActionPositions.setViewportView(pnActionPositions2);
		
		setLayout(groupLayout);

	}
}
