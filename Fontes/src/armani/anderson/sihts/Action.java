package armani.anderson.sihts;

import armani.anderson.sihts.model.PositionVO;
import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.serial.SerialComm;

/**
 * Classe que define ações para o braço robótico
 * <br> Seus métodos recebem como parâmetro posições pré-cadastradas e executam o posicionamento do braço robótico
 * e demais ações pertinentes a cada movimento.
 * @author armani
 * @version V00.01
 */
public class Action {
	RoboticArm roboticArm = null;
	SerialComm serialDebug = null;
	
	final static int OPEN_GRIP = 2000;
	final static int CLOSE_GRIP = 0;
	
	final static int TIME_MOVEMENT_SHOT 	= 0;
	final static int TIME_MOVEMENT_FAST 	= 10;
	final static int TIME_MOVEMENT_AVERAGE 	= 70;
	final static int TIME_MOVEMENT_SLOW 	= 200;
	
	/**
	 * Método Construtor: Recebe a interface com o braço robótico inicializada
	 * @param roboticArm
	 */
	public Action(RoboticArm roboticArm, SerialComm serialDebug) {
		this.roboticArm = roboticArm;
		this.serialDebug = serialDebug;
	}
	
	
	/**
	 * Pega Objeto posicionando o braço robótico através dos passos:
	 * 	<br>- Posiciona na posição inicial (se diferente de null)
	 *  <br>- Abre a garra
	 *  <br>- Posiciona na posição média OBS - Está posiçõa não pode ser nula
	 *  <br>- Fecha a garra
	 *  <br>- Posiciona na posição final (se diferente de null)
	 * @param posIni - Posição inicial
	 * @param posMed - Posição média
	 * @param posEnd - Posição final
	 * @throws IllegalArgumentException
	 */
	public void pickUpObject(PositionVO posIni, PositionVO posMed, PositionVO posEnd) throws IllegalArgumentException {
		
		if(posMed == null) {
			throw new IllegalArgumentException("pickUpObject: Posição Média não pode ser nula");
		}
		
		//Posição inicial
		if(posIni != null) {
			roboticArm.sendPosition(Al5b.ARTC_BASE, posIni.getPositionArtc1(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_OMBRO, posIni.getPositionArtc2(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_COTOVELO, posIni.getPositionArtc3(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_PULSO, posIni.getPositionArtc4(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_PINCA, posIni.getPositionArtc5(), TIME_MOVEMENT_FAST);
		}
		
		//Força Garra Aberta
		roboticArm.sendPosition(Al5b.ARTC_PINCA, OPEN_GRIP, TIME_MOVEMENT_SHOT);
		
		//Posição média
		roboticArm.sendPosition(Al5b.ARTC_BASE, posMed.getPositionArtc1(), TIME_MOVEMENT_FAST);
		roboticArm.sendPosition(Al5b.ARTC_OMBRO, posMed.getPositionArtc2(), TIME_MOVEMENT_FAST);
		roboticArm.sendPosition(Al5b.ARTC_COTOVELO, posMed.getPositionArtc3(), TIME_MOVEMENT_FAST);
		roboticArm.sendPosition(Al5b.ARTC_PULSO, posMed.getPositionArtc4(), TIME_MOVEMENT_FAST);
		roboticArm.sendPosition(Al5b.ARTC_PINCA, posMed.getPositionArtc5(), TIME_MOVEMENT_FAST);
		
		//Força Garra Fechada
		roboticArm.sendPosition(Al5b.ARTC_PINCA, CLOSE_GRIP, TIME_MOVEMENT_SHOT);
		
		//Posição Final
		if(posEnd != null) {
			roboticArm.sendPosition(Al5b.ARTC_BASE, posEnd.getPositionArtc1(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_OMBRO, posEnd.getPositionArtc2(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_COTOVELO, posEnd.getPositionArtc3(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_PULSO, posEnd.getPositionArtc4(), TIME_MOVEMENT_FAST);
			//roboticArm.sendPosition(Al5b.ARTC_PINCA, posEnd.getPositionArtc5(), TIME_MOVEMENT_FAST);
		}
	}
	
	/**
	 * Solta um objeto posicionando o braço robótico através dos passos:
	 * <br> - Posiciona na posição inicial (se diferente de null)
	 * <br> - Posiciona na posição média
	 * <br> - Força a abertura da garra
	 * <br> - Posiciona na posição final (se diferente de null)
	 * @param posIni - Posição inicial ou null
	 * @param posMed - Posição média, ação efetiva
	 * @param posEnd - Posição final ou null
	 * @throws IllegalArgumentException
	 */
	public void dropObject (PositionVO posIni, PositionVO posMed, PositionVO posEnd) throws IllegalArgumentException {
		
		if(posMed == null) {
			throw new IllegalArgumentException("dropObject: Posição Média não pode ser nula");
		}
		
		//Posição inicial
		if(posIni != null) {
			roboticArm.sendPosition(Al5b.ARTC_BASE, posIni.getPositionArtc1(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_OMBRO, posIni.getPositionArtc2(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_COTOVELO, posIni.getPositionArtc3(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_PULSO, posIni.getPositionArtc4(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_PINCA, posIni.getPositionArtc5(), TIME_MOVEMENT_FAST);
		}
		
		//Posição média
		roboticArm.sendPosition(Al5b.ARTC_BASE, posMed.getPositionArtc1(), TIME_MOVEMENT_FAST);
		roboticArm.sendPosition(Al5b.ARTC_OMBRO, posMed.getPositionArtc2(), TIME_MOVEMENT_FAST);
		roboticArm.sendPosition(Al5b.ARTC_COTOVELO, posMed.getPositionArtc3(), TIME_MOVEMENT_FAST);
		roboticArm.sendPosition(Al5b.ARTC_PULSO, posMed.getPositionArtc4(), TIME_MOVEMENT_FAST);
		roboticArm.sendPosition(Al5b.ARTC_PINCA, posMed.getPositionArtc5(), TIME_MOVEMENT_FAST);
		
		//Força Garra Aberta
		roboticArm.sendPosition(Al5b.ARTC_PINCA, OPEN_GRIP, TIME_MOVEMENT_SHOT);
				
		//DELAY PARA O OBJ SAIR DA PINCA
		
		//Posição Final
		if(posEnd != null) {
			roboticArm.sendPosition(Al5b.ARTC_BASE, posEnd.getPositionArtc1(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_OMBRO, posEnd.getPositionArtc2(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_COTOVELO, posEnd.getPositionArtc3(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_PULSO, posEnd.getPositionArtc4(), TIME_MOVEMENT_FAST);
			roboticArm.sendPosition(Al5b.ARTC_PINCA, posEnd.getPositionArtc5(), TIME_MOVEMENT_FAST);
		}
	}
	
	/**
	 * Aguarda tempo sem ação
	 * @param mlsTime - Tempo em millesegundos
	 */
	public void waitTime(int mlsTime) throws IllegalArgumentException{
		if(mlsTime < 1) {
			throw new IllegalArgumentException("Tempo de espera deve ser superior a 1 millesegundos!");
		}
		
		try {
			Thread.sleep(mlsTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Aguarda o retorno de strValue via serial ou timeout
	 * 
	 * @param strValue - String contendo o retorno esperado para que o teste sejá válido
	 * @param timeOut - Tempo máximo para aguardar o retorno da strValue
	 * @return boolean - true = retorno OK / false = retorno não contém string esperada 
	 */
	public boolean waitSerialValue(String strValue, int timeOut) {
		boolean bolRet = false;
		
		return bolRet;
	}
	
	/**
	 * Posiciona o braço robótico sem alterar estado da pinça
	 * @param position
	 * @throws IllegalArgumentException
	 */
	public void positioning(PositionVO position) throws IllegalArgumentException {
		
		if(position == null) {
			throw new IllegalArgumentException("Não aceita posição nula!");
		}
		
		roboticArm.sendPosition(Al5b.ARTC_BASE, position.getPositionArtc1(), TIME_MOVEMENT_FAST);
		roboticArm.sendPosition(Al5b.ARTC_OMBRO, position.getPositionArtc2(), TIME_MOVEMENT_FAST);
		roboticArm.sendPosition(Al5b.ARTC_COTOVELO, position.getPositionArtc3(), TIME_MOVEMENT_FAST);
		roboticArm.sendPosition(Al5b.ARTC_PULSO, position.getPositionArtc4(), TIME_MOVEMENT_FAST);
		//roboticArm.sendPosition(Al5b.ARTC_PINCA, position.getPositionArtc5(), TIME_MOVEMENT_FAST);	
	}
	
	/**
	 * Método para abertura da garra do braço robótico
	 */
	public void openGrip() {
		roboticArm.sendPosition(Al5b.ARTC_PINCA, OPEN_GRIP, TIME_MOVEMENT_SHOT);
	}
	
	/**
	 * Método para fechamento da garra do braço robótico
	 */
	public void closeGrip() {
		roboticArm.sendPosition(Al5b.ARTC_PINCA, OPEN_GRIP, TIME_MOVEMENT_SHOT);
	}
	
}
