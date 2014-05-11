package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


public class panelCrudPosition extends JPanel {
	private char type;
	private JLabel lblType;
	private JTextField tfNome;
	private JSlider sldArtc1;
	private JSlider sldArtc2;
	private JSlider sldArtc3;
	private JSlider sldArtc4;
	private JSlider sldArtc5;
	private JTextField txtStatus;
	
	public JLabel getLblType() {
		return lblType;
	}
	public void setLblType(JLabel lblType) {
		this.lblType = lblType;
	}
	public JTextField getTfNome() {
		return tfNome;
	}
	public void setTfNome(JTextField tfNome) {
		this.tfNome = tfNome;
	}
	public JSlider getSldArtc1() {
		return sldArtc1;
	}
	public void setSldArtc1(JSlider sldArtc1) {
		this.sldArtc1 = sldArtc1;
	}
	public JSlider getSldArtc2() {
		return sldArtc2;
	}
	public void setSldArtc2(JSlider sldArtc2) {
		this.sldArtc2 = sldArtc2;
	}
	public JSlider getSldArtc3() {
		return sldArtc3;
	}
	public void setSldArtc3(JSlider sldArtc3) {
		this.sldArtc3 = sldArtc3;
	}
	public JSlider getSldArtc4() {
		return sldArtc4;
	}
	public void setSldArtc4(JSlider sldArtc4) {
		this.sldArtc4 = sldArtc4;
	}
	public JSlider getSldArtc5() {
		return sldArtc5;
	}
	public void setSldArtc5(JSlider sldArtc5) {
		this.sldArtc5 = sldArtc5;
	}
	public JTextField getTxtStatus() {
		return txtStatus;
	}
	public void setTxtStatus(JTextField txtStatus) {
		this.txtStatus = txtStatus;
	}
	public char getType() {
		return type;
	}
	private void setType(char type2) {
		this.type = type2;
		
		if(this.type == 'P') {
			lblType.setText("Posição");
		}
		else if(this.type == 'O') {
			lblType.setText("Objeto");
		}
	}
	
	/**
	 * Create the panel.
	 */
	public panelCrudPosition(char type) {		
		initComponent();
		setType(type);
	}

	/**
	 * Inicia os componentes gráficos
	 */
	private void initComponent() {
		
		lblType = new JLabel("Tipo");
		lblType.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		tfNome.setColumns(10);
		
		JLabel lblArtc1 = new JLabel("Artc1");
		
		sldArtc1 = new JSlider();
		sldArtc1.setMaximum(2000);
		
		JLabel lblArtc2 = new JLabel("Artc2");
		
		sldArtc2 = new JSlider();
		sldArtc2.setMaximum(2000);
		
		JLabel lblArtc3 = new JLabel("Artc3");
		
		sldArtc3 = new JSlider();
		sldArtc3.setMaximum(2000);
		
		JLabel lblArtc4 = new JLabel("Artc4");
		
		sldArtc4 = new JSlider();
		sldArtc4.setMaximum(2000);
		
		JLabel lblArtc5 = new JLabel("Artc5");
		
		sldArtc5 = new JSlider();
		sldArtc5.setMaximum(2000);
		
		//Buttons
		JButton btnExcluir = new JButton("Excluir");
		JButton btnCancelar = new JButton("Cancelar");
		JButton btnSalvar = new JButton("Salvar");
		
		txtStatus = new JTextField();
		txtStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatus.setBackground(UIManager.getColor("Panel.background"));
		txtStatus.setEnabled(false);
		txtStatus.setEditable(false);
		txtStatus.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblArtc5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblArtc4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblArtc3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblArtc2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblArtc1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(sldArtc5, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addComponent(sldArtc4, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addComponent(sldArtc3, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addComponent(sldArtc2, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addComponent(sldArtc1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addComponent(tfNome, 300, 300, 300)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(btnExcluir)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnCancelar)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSalvar))))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblArtc1)
						.addComponent(sldArtc1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblArtc2)
						.addComponent(sldArtc2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblArtc3)
						.addComponent(sldArtc3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblArtc4)
						.addComponent(sldArtc4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblArtc5)
						.addComponent(sldArtc5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(18)
					.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
		);
		
		setLayout(groupLayout);
		this.setBounds(0, 0, 450, 430);
	}	
}
