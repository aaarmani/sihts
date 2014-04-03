package armani.anderson.sihts.serial;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.util.Enumeration;
import java.util.LinkedList;


public class Serial {
	static final int OPEN_TIMEOUT = 2000;
	SerialPort serialPort = null;
	
	public void open(String portName) throws Exception {
		System.out.println("PORTA: " + portName);
		CommPortIdentifier cpiPort = getPortIdentifier(portName);
		
		if(cpiPort == null) {
			throw new IllegalArgumentException();
		}
		
		serialPort = cpiPort.open(this.getClass().getName(), OPEN_TIMEOUT);
	}

	public void close() {
		if(serialPort != null) {
			serialPort.close();
		}
	}
	
	public void setParameters() {
		
	}
	
	public void read() {
		
	}
	
	public void write() {
		
	}

	
	public LinkedList<String> getListSerial() {
		String strPortName = null;
		LinkedList<String> lstPortNames = null;
		LinkedList<String> lstPorts = new LinkedList<String>();
		
		lstPortNames = getPortNameSO();
		
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> enumPorts = CommPortIdentifier.getPortIdentifiers();
	    
        while (enumPorts.hasMoreElements()) {
            CommPortIdentifier portId = enumPorts.nextElement();
            
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
            	
            	for(int i = 0; i < lstPortNames.size() ; i++) {
            		strPortName = lstPortNames.get(i);	
            		
            		if (portId.getName().startsWith(strPortName)) {
                        lstPorts.add(strPortName);
            		}
            	}
            }
        }
        return lstPorts;
	}
	
	private LinkedList<String> getPortNameSO() {
		LinkedList<String> lstNames = new LinkedList<String>();
		String osname = System.getProperty("os.name","").toLowerCase();
		
        if ( osname.startsWith("windows") ) {
        	lstNames.add("COM");
        } else if (osname.startsWith("linux")) {
        	lstNames.add("/dev/ttyS");
        	lstNames.add("/dev/ttyUSB");
        } else if ( osname.startsWith("mac") ) {
        	lstNames.add("/dev/tty.usbserial");
        } else {
            System.out.println("Sistema Operacional não suportado!");
        }
        
        return lstNames;
	}
	
	private CommPortIdentifier getPortIdentifier(String portName) {
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> enumPortList = CommPortIdentifier.getPortIdentifiers();
	    CommPortIdentifier cpiReturn = null;
	    
        while (enumPortList.hasMoreElements()) {
            CommPortIdentifier cpiPortId = enumPortList.nextElement();
            String strCpiName = cpiPortId.getName();
            
            if ((cpiPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) && (strCpiName.equals(portName))) {
            	cpiReturn = cpiPortId;
            	break;
            } 
        } 
        
        return cpiReturn;
	}

}
