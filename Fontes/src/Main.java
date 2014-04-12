
import javax.swing.JOptionPane;

import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;

public class Main {

	public static void main(String[] args) {
		try {
			Al5b braco = new Al5b("/dev/tty.usbserial");
			braco.getVersion();
			while(true) {
				String posicao = JOptionPane.showInputDialog("Texto para enviar pela serial: ");
				
				braco.sendPosition(Al5b.ARTC_BASE, Integer.parseInt(posicao), 500);
				braco.sendPosition(Al5b.ARTC_OMBRO, Integer.parseInt(posicao), 500);
				/*braco.sendPosition(Al5b.ARTC_COTOVELO, 0, 0);
				braco.sendPosition(Al5b.ARTC_PULSO, 0, 0);
				braco.sendPosition(Al5b.ARTC_PINCA, 0, 0);*/
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("ERRO ARMANI: "+ e1.getMessage());
		}
		
		/*
		SerialComm ser = new SerialComm();

		try {
			ser.open("/dev/tty.usbserial");
			ser.setParameters(SerialComm.BAUD_115200, SerialComm.DATABITS_8, SerialComm.STOPBITS_1, SerialComm.PARITY_NONE);
			
			while(true) {
				String texto = JOptionPane.showInputDialog("Texto para enviar pela serial: ");
				
				if(texto.length() > 0) {
					String ret = new String(ser.txRx(texto.getBytes(), 1000, texto.length()));
					
					String retorno = new String("RECEBIDO: " + ret);
					System.out.println(retorno);	
				}
			}
			
			
			//System.out.println("POS RETORNO");
		} catch (UnsupportedCommOperationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("MAIN EXCEPTION!!!");
		} finally {
			ser.close();
		}*/
	}
}
