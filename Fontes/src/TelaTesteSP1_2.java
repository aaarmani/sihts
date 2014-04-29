import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.serial.SerialComm;



@SuppressWarnings("serial")
public class TelaTesteSP1_2 extends JPanel {
	boolean bolIsConnect = false;
	
	RoboticArm roboticArm = null;
	JComboBox<String> cbPortComm;
	JButton btnConect;
	
	JSlider	slider;
	JComboBox<String> cbArtic;
	
	
	/**
	 * Create the panel.
	 */
	public TelaTesteSP1_2() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		cbPortComm = new JComboBox<String>(getArrayPorts());
		cbPortComm.setBounds(25, 64, 175, 35);
		add(cbPortComm);
		
		JLabel lblTitle = new JLabel("Simulador IHTS SP1");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblTitle.setBounds(6, 6, 468, 16);
		add(lblTitle);
		
		JLabel lblPort = new JLabel("Porta:");
		lblPort.setBounds(25, 49, 61, 16);
		add(lblPort);
		
		btnConect = new JButton("Conectar");
		btnConect.addActionListener(new ConectcListener());
		btnConect.setBounds(210, 64, 117, 35);
		add(btnConect);
		
		JLabel lblArtic = new JLabel("Articulação:");
		lblArtic.setBounds(25, 121, 78, 16);
		add(lblArtic);
		
		cbArtic = new JComboBox<String>(getArrayArtc());
		cbArtic.setBounds(25, 137, 144, 35);
		add(cbArtic);
		
		slider = new JSlider();
		slider.setMaximum(2000);
		slider.setBounds(20, 184, 302, 35);
		add(slider);
		
		JButton btnMove = new JButton("Mover");
		btnMove.addActionListener(new MoveListener());
		btnMove.setBounds(324, 184, 117, 35);
		add(btnMove);

	}


	private String[] getArrayArtc() {
		String[] strListArtc = new String[5];
		
		strListArtc[0] = "base";
		strListArtc[1] = "ombro";
		strListArtc[2] = "cotovelo";
		strListArtc[3] = "pulso";
		strListArtc[4] = "pinça";
		
		return strListArtc;
	}


	private String[] getArrayPorts() {
		List<String> lstPorts = null;
		String[] strPortList;
		
		lstPorts = SerialComm.getListSerial();
		strPortList = new String[lstPorts.size()];
		
		//Carrega array de String com as seriais disponíveis
		for(int i = 0; i < lstPorts.size(); i++) {
			strPortList[i] = lstPorts.get(i);
		}
		
		return strPortList;
	}
	
	private class ConectcListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String strComm = cbPortComm.getItemAt(cbPortComm.getSelectedIndex());
			
			if(bolIsConnect == false) {
				try {
					roboticArm = new Al5b(strComm);
					btnConect.setText("Desconectar");
					bolIsConnect = true;
				} catch (Exception e1) {
					roboticArm.close();
					JOptionPane.showMessageDialog(null, "Erro na inicialização", e1.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				roboticArm.close();
				btnConect.setText("Conectar");
				bolIsConnect = false;
			}
		}
	}
	
	private class MoveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int intValue = slider.getValue();
			int intArtc = cbArtic.getSelectedIndex();
			
			roboticArm.sendPosition(intArtc, intValue, 0);
		}
		
	}

	
}
