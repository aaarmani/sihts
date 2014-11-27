package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JTextField;

public class VersionView extends JPanel {
	private JTextField txtVersoVBeta;

	/**
	 * Create the panel.
	 */
	public VersionView() {
		
		JLabel lblNewLabel = new JLabel("Versão do Sistema");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JTextArea txtrTxt = new JTextArea();
		txtrTxt.setEditable(false);
		txtrTxt.setLineWrap(true);
		txtrTxt.setColumns(5);
		txtrTxt.setText("Simulador de Iterações Humanas para Teste de Software é um projeto de TCC da Faculdades Senac desenvolvido por Anderson Augusto Armani e orientado por Luciano Zanuz.");
		
		txtVersoVBeta = new JTextField();
		txtVersoVBeta.setEditable(false);
		txtVersoVBeta.setText("Versão: V0.05-Beta    26/11/2014");
		txtVersoVBeta.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtVersoVBeta, Alignment.LEADING)
								.addComponent(txtrTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(lblNewLabel)
					.addGap(27)
					.addComponent(txtrTxt, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtVersoVBeta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(104, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
