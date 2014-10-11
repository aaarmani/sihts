package armani.anderson.sihts.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class ScriptExecView extends JPanel {
	private JComboBox<String> comboBox;
	private JButton btnExecute;
	private JButton btnReport;
	private JTextArea txtrTextarea;
	
	
	
	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	public JButton getBtnExecute() {
		return btnExecute;
	}

	public void setBtnExecute(JButton btnExecute) {
		this.btnExecute = btnExecute;
	}

	public JButton getBtnReport() {
		return btnReport;
	}

	public void setBtnReport(JButton btnReport) {
		this.btnReport = btnReport;
	}

	public JTextArea getTxtrTextarea() {
		return txtrTextarea;
	}

	public void setTxtrTextarea(JTextArea txtrTextarea) {
		this.txtrTextarea = txtrTextarea;
	}



	/**
	 * Create the panel.
	 */
	public ScriptExecView() {
		
		JLabel lblNewLabel = new JLabel("Executa Script");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		
		JLabel lblTest = new JLabel("Script");
		
		comboBox = new JComboBox<String>();
		
		btnExecute = new JButton("Executar");
		
		btnReport = new JButton("Relatório");
		
		txtrTextarea = new JTextArea();
		txtrTextarea.setEditable(false);
		
		JScrollPane scpTextArea = new JScrollPane();
		scpTextArea.setBackground(Color.WHITE);
		scpTextArea.setViewportView(txtrTextarea);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(100)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scpTextArea, GroupLayout.PREFERRED_SIZE, 591, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTest, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnExecute)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnReport)))
							.addGap(103)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel)
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTest)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExecute)
						.addComponent(btnReport))
					.addGap(18)
					.addComponent(scpTextArea, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
