package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import java.awt.Color;

public class ActPositionView extends JPanel {
	private JLabel lblPositionName = null;
	private JButton btnUp = null;
	private JButton btnDown = null;
	private JButton btnDelete = null;
	private int index = -1;
	
	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}
	
	public JLabel getLblPositionName() {
		return lblPositionName;
	}

	public void setLblPositionName(JLabel lblPositionName) {
		this.lblPositionName = lblPositionName;
	}

	public JButton getBtnUp() {
		return btnUp;
	}

	public void setBtnUp(JButton btnUp) {
		this.btnUp = btnUp;
	}

	public JButton getBtnDown() {
		return btnDown;
	}

	public void setBtnDown(JButton btnDown) {
		this.btnDown = btnDown;
	}

	/**
	 * Create the panel.
	 * @param posName 
	 */
	public ActPositionView(int index, String posName) {
		this.index = index;
		
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		
		lblPositionName = new JLabel(posName);
		lblPositionName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		btnUp = new JButton("");
		btnUp.setIcon(new ImageIcon("/Users/armani/Documents/Senac/TCC/Imagens/arrow-up.png"));
		
		btnDown = new JButton("");
		btnDown.setIcon(new ImageIcon("/Users/armani/Documents/Senac/TCC/Imagens/arrow-down.png"));
		
		btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon("/Users/armani/Documents/Senac/TCC/Imagens/1401169701_f-cross_256.png"));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPositionName, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDown)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUp)
					.addGap(18)
					.addComponent(btnDelete)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDown)
						.addComponent(btnUp)
						.addComponent(btnDelete))
					.addContainerGap(8, Short.MAX_VALUE))
				.addComponent(lblPositionName, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
		);
		setLayout(groupLayout);

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
