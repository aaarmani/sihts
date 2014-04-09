package armani.anderson.sihts.serial;

import java.util.LinkedList;

import gnu.io.PortInUseException;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class SerialTest {
	static final String SERIAL_DONT_EXISTS = "serialTtyCom"; 
	static String strSistema = null;
	static String strSerialTest = null;
	static LinkedList<String> lstSerialNames = null;
	
	/**
	 * <b>Set Up Before Class
	 * <p>Este método inicia os testes pegando as variáveis específicas para cada sistema operacional
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		lstSerialNames = new LinkedList<String>();
		String strSistema = System.getProperty("os.name");
		
		if(strSistema.equalsIgnoreCase("Windows")) {
			lstSerialNames.add("COM");
			strSerialTest = "COM1";
		}
		else if(strSistema.equalsIgnoreCase("Linux")) {
			lstSerialNames.add("ttyS");
			lstSerialNames.add("ttyUSB");
			strSerialTest = "/dev/ttyS0";
		}
		else if(strSistema.equalsIgnoreCase("Mac OS X")) {
			lstSerialNames.add("tty.usbserial");
			strSerialTest = "/dev/tty.usbserial";
		}
		else {
			System.out.println("Sistema não suportado!");
		}
	}
	
	/**
	 * <b>Teste SerialOpen
	 * <p>Este teste simula a abertura de uma porta serial para o sistema alvo.
	 */
	@Test
	public void serialOpen() {
		SerialComm ser = null;
		
		try {
			ser = new SerialComm();
			ser.open(strSerialTest);
		} catch(Exception e) {
			fail();
		} finally {
			ser.close();
		}
		
		assertTrue(true);
	}
	
	/**
	 * <b>Teste Serial dont Exists Open
	 * <p>Este teste é responsável por testar a tentativa de abertura de uma porta não existente no sistema alvo.
	 */
	@Test
	public void serialDontExistsOpen() {
		SerialComm ser = null;
		try {
			ser = new SerialComm();
			ser.open(SERIAL_DONT_EXISTS);
			fail();
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail();
		} finally {
			ser.close();
		}
		
		assertTrue(true);
	}
	
	/**
	 * <b>Teste Serial Opende Open
	 * <p>Este teste simula a tentativa de abertura de uma porta serial já aberta
	 */
	@Test
	public void serialOpenedOpen() {
		SerialComm ser1 = null;
		SerialComm ser2 = null;
		boolean bolReturn = false;
		try {
			ser1 = new SerialComm();
			ser2 = new SerialComm();

			//abrir duas vezes para ocorrer o erro			
			ser1.open(strSerialTest);
			ser2.open(strSerialTest);

			bolReturn = false;
		} catch (PortInUseException e) {
			bolReturn = true;
		} catch (Exception e) {
			bolReturn = false;
		} finally {
			ser1.close();
			ser2.close();
		}
		assertTrue(bolReturn);
	}

	@Test
	public void serialClose() {
		SerialComm ser = null;
		try {
			ser = new SerialComm();
			ser.open(strSerialTest);
			ser.close();
		} catch(Exception e) {
			fail();
		}
		
		assertTrue(true);
	}
	
	@Test
	public void serialGetListSerial() {
		try {
			LinkedList<String> lstSer = SerialComm.getListSerial();
			
			if (lstSer.size() >= 1)
				assertTrue(true);
			else
				fail();
		} catch(Exception e) {
			fail();
		}
	}
	
	
}
