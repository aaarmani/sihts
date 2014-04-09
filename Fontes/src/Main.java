import java.util.List;

import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.serial.SerialComm;



public class Main {

	public static void main(String[] args) {
		try {
			RoboticArm braco = new Al5b("/dev/tty.usbserial");
			braco.sendPosition(Al5b.ARTC_COTOVELO, 45, 100);
		} catch (Exception e1) {
			e1.printStackTrace();
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
