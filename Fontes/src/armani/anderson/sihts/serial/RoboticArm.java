package armani.anderson.sihts.serial;

/**
 * <p>Interface que descreve a classe de controle de um braço robótico
 * @author armani
 *
 */
public interface RoboticArm {
	
	/**
	 * <p>Inicializa a braço robótico, sua posição e sua comunicação serial
	 * @param strPort - Porta de comunicação
	 * @throws Exception
	 */
	public void initialize(String strPort) throws Exception;
	
	/**
	 * <p>Envia ao braço robótico uma nova posição
	 * @param btArticulation - articulação a ser alterada
	 * @param intPosition - posição do braço em graus
	 * @param intTime - tempo para realizar o movimento
	 */
	public void sendPosition(int intArticulation, int intPosition, int intTime);
	

}
