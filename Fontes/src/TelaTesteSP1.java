import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.serial.SerialComm;


public class TelaTesteSP1 extends JFrame{
	RoboticArm bracoRobo = null;	
	JComboBox<String> cbComm;
	JButton btnConnect;
	JLabel lbSerialStatus;
		
	JComboBox<String> cbArtic;
	JSlider slider;
		
	public TelaTesteSP1() {
		super("Simulador_IHTS SP1");
		
		List<String> lstComm = SerialComm.getListSerial();
		String[] strLista = new String[lstComm.size()];
		
		//Carrega array de String com as seriais disponíveis
		for(int i = 0; i < lstComm.size(); i++) {
			strLista[i] = lstComm.get(i);
		}
	
		cbComm = new JComboBox<String>();
		cbComm.setModel(new DefaultComboBoxModel(strLista));
		cbComm.setSelectedIndex(0);
		
		btnConnect = new JButton("Conectar");
		btnConnect.addActionListener(new ConectarListener());
		
		lbSerialStatus = new JLabel("Desconectado");
		
		//Panel Comm - comboBox e botão
		Container contComm = new JPanel();
		contComm.setLayout(new FlowLayout());
		
		contComm.add(cbComm);
		contComm.add(btnConnect);
		contComm.add(lbSerialStatus);
		
		getContentPane().add(BorderLayout.NORTH, contComm);
		
		/*----------*/
		
		cbArtic = new JComboBox<String>();
		cbArtic.setModel(new DefaultComboBoxModel(new String[] {"Base", "Ombro", "Cotovelo", "Pulso", "Pinça"}));
		cbArtic.setSelectedIndex(0);
		
		slider = new JSlider();
		slider.setMinimum(0);
		slider.setMaximum(2000);
		
		JButton btnMover = new JButton("Mover");
		btnMover.addActionListener(new MoverListener());
		
		Container contSlider = new JPanel();
		contSlider.setLayout(new FlowLayout());
		contSlider.add(cbArtic);
		contSlider.add(slider);
		contSlider.add(btnMover);
		
		
		getContentPane().add(BorderLayout.SOUTH, contSlider);
		
		
		//FRAME PRINCIPAL
		//getContentPane().add(contSlider);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 100);
		//Posiciona no centro da tela
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class ConectarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String strComm = cbComm.getItemAt(cbComm.getSelectedIndex());
			System.out.println(strComm);
			
			try {
				bracoRobo = new Al5b(strComm);
				lbSerialStatus.setText("Conectado");
			} catch (Exception e1) {
				bracoRobo.close();
				System.out.println("Não abriu a serial");
				e1.printStackTrace();
			}
		}
	}
	
	private class MoverListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int intValue = slider.getValue();
			String strArtc = cbArtic.getItemAt(cbArtic.getSelectedIndex());
			
			int intArtc = 0;
			
			bracoRobo.sendPosition(intArtc, intValue, 0);
		}
		
	}
	

}
