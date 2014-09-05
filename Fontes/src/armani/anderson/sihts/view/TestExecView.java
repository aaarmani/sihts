package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class TestExecView extends JPanel {

	/**
	 * Create the panel.
	 */
	public TestExecView() {
		
		JLabel lblNewLabel = new JLabel("Executa Teste");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		
		JLabel lblTest = new JLabel("Teste");
		
		JComboBox comboBox = new JComboBox();
		
		JButton btnExecute = new JButton("Executar");
		
		JButton btnReport = new JButton("Relatório");
		
		JTextArea txtrTextarea = new JTextArea();
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
								.addComponent(txtrTextarea, GroupLayout.PREFERRED_SIZE, 591, GroupLayout.PREFERRED_SIZE)
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
					.addComponent(txtrTextarea, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
