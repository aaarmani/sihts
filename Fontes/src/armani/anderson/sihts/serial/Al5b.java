package armani.anderson.sihts.serial;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import armani.anderson.sihts.serial.SerialComm;

/**
 * <p>Classe de interface com o braço robótico AL5B
 * @author armani
 *
 */
public class Al5b implements RoboticArm {
	public static final int ARTC_BASE = 0;
	public static final int ARTC_OMBRO = 1;
	public static final int ARTC_COTOVELO = 2;
	public static final int ARTC_PULSO = 3;
	public static final int ARTC_PINCA = 4;
	
	public static final int MAX_ARTC_VAL 		= ARTC_PINCA;
	public static final int MAX_TIME_VAL		= 65535;
	public static final int MAX_POSITION_VAL	= 2000;
	private static final int POSITION_OFFSET 	= 500;
	
	/*Tempo para envio e recepção de apenas uma posição*/
	private static final int AL5B_SEND_POS_TIME = 10;
	//private static final int AL5B_ONEPOS_TIME = 300;
	
	
	private int intPositionBase;
	private int intPositionOmbro;
	private int intPositionCotovelo;
	private int intPositionPulso;
	private int intPositionPinca;
	private SerialComm scSerial;

	/**
	 * <p>Método construtor da classe al5b. Cria uma nova interface com o kit AL5B contendo uma serial de controle
	 * e a posição atual de cada servomotor
	 * @param strSerial - Nome da Serial de interface com o kit AL5B
	 * @throws Exception
	 */
	public Al5b(String strSerial) throws Exception {
		clearPositions();
		initialize(strSerial);
	}

	private int getIntPositionBase() {
		return intPositionBase;
	}
	private void setIntPositionBase(int intPositionBase) {
		this.intPositionBase = intPositionBase;
	}
	private int getIntPositionOmbro() {
		return intPositionOmbro;
	}
	private void setIntPositionOmbro(int intPositionOmbro) {
		this.intPositionOmbro = intPositionOmbro;
	}
	private int getIntPositionCotovelo() {
		return intPositionCotovelo;
	}
	private void setIntPositionCotovelo(int intPositionCotovelo) {
		this.intPositionCotovelo = intPositionCotovelo;
	}
	private int getIntPositionPulso() {
		return intPositionPulso;
	}
	private void setIntPositionPulso(int intPositionPulso) {
		this.intPositionPulso = intPositionPulso;
	}
	private int getIntPositionPinca() {
		return intPositionPinca;
	}
	private void setIntPositionPinca(int intPositionPinca) {
		this.intPositionPinca = intPositionPinca;
	}
	
	/**
	 * <p>Método de inicialização do braço robótico
	 */
	@Override
	public void initialize(String strPort) throws Exception {
		scSerial = new SerialComm();
		
		List<String> lstSeriais = SerialComm.getListSerial();
		
		if( !lstSeriais.contains(strPort) ) {
			throw new IllegalArgumentException();
		}
		
		scSerial.open(strPort);
		scSerial.setParameters(SerialComm.BAUD_115200, SerialComm.DATABITS_8, SerialComm.STOPBITS_1, SerialComm.PARITY_NONE);
	}

	/**
	 * <p>Método que altera a posição de uma articulação.
	 */
	@Override
	public void sendPosition(int intArticulation, int intPosition, int intTime) throws IllegalArgumentException{
		String strSend;
		
		//verifica consistência dos dados
		if((intArticulation < 0) || (intArticulation > MAX_ARTC_VAL)) {
			throw new IllegalArgumentException("Valor não corresponde a uma articulação.");
		}
		
		if((intPosition < 0) || (intPosition > MAX_POSITION_VAL)) {
			throw new IllegalArgumentException("Valor não permitido para posição.");
		}
		
		if((intTime < 0) || (intTime > MAX_TIME_VAL)) {
			throw new IllegalArgumentException("Valor não permitido para tempo.");
		}
		
		//ajusta posição de 500 - 2500
		intPosition += POSITION_OFFSET;
		
		strSend = '#' + Integer.toString(intArticulation);
		strSend += 'P' + Integer.toString(intPosition);
		strSend += 'T' + Integer.toString(intTime);
		strSend += '\r';
		
		//RETIRAR
		System.out.println("SEND: " + strSend);
		
		byte[] bt = scSerial.txRx(strSend.getBytes(), strSend.length(), AL5B_SEND_POS_TIME);
		
		
		//RETIRAR
		System.out.println("RECEBIDO : " + bt.length);
		for(int i = 0; i < bt.length; i++) {
			System.out.println("Byte ["+i+"] = " + bt[i]);
		}
		
		setPosition(intArticulation, intPosition);
	}
	
	/**
	 * <p>Método que retorna a versão da placa de controle do kit Al5b
	 * @return
	 */
	public String getVersion() {
		String strSend;
		String strReturn = null;
		
		//comando para retornar versão da placa de controle do kit
		strSend = "VER";
		strSend += '\r';
		
		byte[] bt = scSerial.txRx(strSend.getBytes(), strSend.length(), AL5B_SEND_POS_TIME);
		
		strReturn = new String(bt);
		System.out.println("strRETURN = " + strReturn);
		return strReturn;
	}
	
	/**
	 * <p>Retorna a posição da articulação especificada
	 * @param btArticulation
	 * @return
	 */
	private int getPisition(int intArticulation) {
		int btReturn = 0;
	
		switch(intArticulation) {
		case ARTC_BASE:
			btReturn = getIntPositionBase();
			break;
		case ARTC_OMBRO:
			btReturn = getIntPositionOmbro();
			break;
		case ARTC_COTOVELO:
			btReturn = getIntPositionCotovelo();
			break;
		case ARTC_PULSO:
			btReturn = getIntPositionPulso();
			break;
		case ARTC_PINCA:
			btReturn = getIntPositionPinca();
			break;
		}
		
		return btReturn;
	}
	
	
	/**
	 * <p>Seta a posição de uma única articulação
	 * @param btArticulation
	 * @param btPosition
	 */
	private void setPosition(int intArticulation, int intPosition) {
		switch(intArticulation) {
		case ARTC_BASE:
			setIntPositionBase(intPosition);
			break;
		case ARTC_OMBRO:
			setIntPositionOmbro(intPosition);
			break;
		case ARTC_COTOVELO:
			setIntPositionCotovelo(intPosition);
			break;
		case ARTC_PULSO:
			setIntPositionPulso(intPosition);
			break;
		case ARTC_PINCA:
			setIntPositionPinca(intPosition);
			break;
		}	
	}
	
	/**
	 * Seta posição de todos os servomotores
	 * @param posBase
	 * @param posOmbro
	 * @param posCotovelo
	 * @param posPulso
	 * @param posPinca
	 */
	private void setPositions(int posBase, int posOmbro, int posCotovelo, int posPulso, int posPinca) {
		setIntPositionBase(posBase);
		setIntPositionCotovelo(posCotovelo);
		setIntPositionOmbro(posOmbro);
		setIntPositionPulso(posPulso);
		setIntPositionPinca(posPinca);
	}
	

	/**
	 * <p>Zera a posição de todos os servos 
	 */
	private void clearPositions() {
		setPositions(0, 0, 0, 0, 0);
	}
}
