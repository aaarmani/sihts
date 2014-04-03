import java.util.List;

import armani.anderson.sihts.serial.Serial;



public class Main {

	public static void main(String[] args) {
		Serial ser = new Serial();
		
		List lstPorts = ser.getListSerial();
		
		for(int i = 0; i < lstPorts.size(); i++) {
			System.out.println("Porta: " + lstPorts.get(i));
		}
	}

}
