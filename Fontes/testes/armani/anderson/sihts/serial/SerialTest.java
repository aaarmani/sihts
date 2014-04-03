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
	
	@Test
	public void serialOpen() {
		Serial ser = null;
		
		try {
			ser = new Serial();
			ser.open(strSerialTest);
		} catch(Exception e) {
			fail();
		} finally {
			ser.close();
		}
		
		assertTrue(true);
	}
	
	@Test
	public void serialDontExistsOpen() {
		Serial ser = null;
		try {
			ser = new Serial();
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
	
	@Test
	public void serialOpenedOpen() {
		Serial ser1 = null;
		Serial ser2 = null;
		try {
			ser1 = new Serial();
			ser2 = new Serial();

			//abrir duas vezes para ocorrer o erro			
			ser1.open(strSerialTest);
			ser2.open(strSerialTest);

			fail();
		} catch (PortInUseException e) {
		} catch (Exception e) {
			fail();
		} finally {
			ser1.close();
			ser2.close();
		}
		assertTrue(true);
	}

	@Test
	public void serialClose() {
		Serial ser = null;
		try {
			ser = new Serial();
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
			Serial ser = new Serial();
			LinkedList<String> lstSer = ser.getListSerial();
			
			if (lstSer.size() >= 1)
				assertTrue(true);
			else
				fail();
		} catch(Exception e) {
			fail();
		}
	}
	
	
}
