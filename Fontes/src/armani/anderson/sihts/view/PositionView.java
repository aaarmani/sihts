package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;



public class PositionView extends JPanel {
	private char type;
	private JLabel lblType;
	private JTextField tfNome;
	private JSlider sldArtc1;
	private JSlider sldArtc2;
	private JSlider sldArtc3;
	private JSlider sldArtc4;
	private JSlider sldArtc5;
	private JButton btnExcluir;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JLabel lblLstPosition;
	private JList lstPosition;
	
	public JButton getBtnExcluir() {
		return btnExcluir;
	}
	public void setBtnExcluir(JButton btnExcluir) {
		this.btnExcluir = btnExcluir;
	}
	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
	public JButton getBtnSalvar() {
		return btnSalvar;
	}
	public void setBtnSalvar(JButton btnSalvar) {
		this.btnSalvar = btnSalvar;
	}
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
	public char getType() {
		return type;
	}
	public void setType(char type2) {
		this.type = type2;
		
		if(this.type == 'P') {
			lblType.setText("Posição");
		}
		else if(this.type == 'O') {
			lblType.setText("Objeto");
		}
	}
	public JList getLstPosition() {
		return lstPosition;
	}
	public void setLstPosition(JList lstPosition) {
		this.lstPosition = lstPosition;
	}
	public JLabel getLblLstPosition() {
		return lblLstPosition;
	}
	public void setLblLstPosition(JLabel lblLstPosition) {
		this.lblLstPosition = lblLstPosition;
	}
	

	/**
	 * Create the panel.
	 */
	public PositionView(char type) {		
		initComponent();
		setType(type);
	}
	
	/**
	 * Inicia os componentes gráficos
	 */
	private void initComponent() {
		
		JPanel pnRight = new JPanel();
		
		JLabel lblTitle = new JLabel("Cadastro de Posição");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		
		lblLstPosition = new JLabel("Posições Cadastradas:");
		lblLstPosition.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JPanel pnLeft = new JPanel();
		
		lstPosition = new JList();
		lstPosition.setBounds(6, 6, 311, 284);
		pnLeft.add(lstPosition);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblLstPosition, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
									.addGap(18))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(pnLeft, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addComponent(pnRight, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnRight, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLstPosition)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnLeft, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		pnLeft.setLayout(null);
		
		JLabel lblArtc5 = new JLabel("Pinça");
		lblArtc5.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblType = new JLabel("Tipo");
		lblType.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblArtc4 = new JLabel("Pulso");
		lblArtc4.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblArtc3 = new JLabel("Cotovelo");
		lblArtc3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblArtc2 = new JLabel("Ombro");
		lblArtc2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblArtc1 = new JLabel("Base");
		lblArtc1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		sldArtc5 = new JSlider();
		sldArtc5.setMaximum(2000);
		
		sldArtc4 = new JSlider();
		sldArtc4.setMaximum(2000);
		
		sldArtc3 = new JSlider();
		sldArtc3.setMaximum(2000);
		
		sldArtc2 = new JSlider();
		sldArtc2.setMaximum(2000);
		
		sldArtc1 = new JSlider();
		sldArtc1.setMaximum(2000);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		tfNome.setColumns(10);
		
		//Buttons
		btnExcluir = new JButton("Excluir");
		btnCancelar = new JButton("Cancelar");
		btnSalvar = new JButton("Salvar");
		
		GroupLayout gl_pnRight = new GroupLayout(pnRight);
		gl_pnRight.setHorizontalGroup(
			gl_pnRight.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnRight.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnRight.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnRight.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_pnRight.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblArtc5, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(lblArtc4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(lblArtc3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(lblArtc2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(lblArtc1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_pnRight.createParallelGroup(Alignment.LEADING)
								.addComponent(sldArtc1, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
								.addComponent(sldArtc2, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
								.addComponent(sldArtc3, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
								.addComponent(sldArtc4, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
								.addComponent(sldArtc5, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)))
						.addGroup(gl_pnRight.createSequentialGroup()
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalvar))
						.addGroup(gl_pnRight.createSequentialGroup()
							.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)))
					.addGap(53))
		);
		gl_pnRight.setVerticalGroup(
			gl_pnRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnRight.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_pnRight.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblType))
					.addGap(35)
					.addGroup(gl_pnRight.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblArtc1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(sldArtc1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnRight.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblArtc2, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(sldArtc2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnRight.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblArtc3, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(sldArtc3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnRight.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblArtc4, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(sldArtc4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnRight.createParallelGroup(Alignment.LEADING)
						.addComponent(lblArtc5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(sldArtc5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_pnRight.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar)
						.addComponent(btnExcluir))
					.addGap(10))
		);
		pnRight.setLayout(gl_pnRight);
		
		setLayout(groupLayout);
	}	
}
