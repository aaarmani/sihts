package armani.anderson.sihts.model;

/**
 * Position - Classe que descreve uma posição do braço robótico
 * @author armani
 * @version V00.01
 */
public class PositionVO {
	private long id;
	private char type;
	private String name;
	private int positionArtc1;
	private int positionArtc2;
	private int positionArtc3;
	private int positionArtc4;
	private int positionArtc5;
	
	/**
	 * Método construtor - Inicializa as variáveis zeradas
	 */
	public PositionVO() {
		this.id = -1;
		this.type = 0;
		this.name = "";
		this.positionArtc1 = 0;
		this.positionArtc2 = 0;
		this.positionArtc3 = 0;
		this.positionArtc4 = 0;
		this.positionArtc5 = 0;
	}
	
	/**
	 * Retorna o Id da Posição no banco de dados
	 * @return long ID
	 */
	public long getId() {
		return id;
	}
	/**
	 * Seta o valor do ID da Posição
	 * @param long id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Retorna o tipo da Posição
	 * @return char - Tipo P = Posição / O = Objeto
	 */
	public char getType() {
		return type;
	}
	/**
	 * Seta o tipo da Posição
	 * @param type - P = Posição / O = Objeto
	 */
	public void setType(char type) {
		this.type = type;
	}
	/**
	 * Retorna o nome da Posição
	 * @return String - Nome
	 */
	public String getName() {
		return name;
	}
	/**
	 * Seta o nome da Posição
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Pega Posição da articulação 1
	 * @return int - Valor [0 a 2000]
	 */
	public int getPositionArtc1() {
		return positionArtc1;
	}
	/**
	 * Seta o valor da Posição da articulação 1
	 * @param int - positionArtc1 [0 a 2000] 
	 */
	public void setPositionArtc1(int positionArtc1) {
		this.positionArtc1 = positionArtc1;
	}
	/**
	 * Pega Posição da articulação 2
	 * @return int - Valor [0 a 2000]
	 */
	public int getPositionArtc2() {
		return positionArtc2;
	}
	/**
	 * Seta o valor da Posição da articulação 2
	 * @param int - positionArtc2 [0 a 2000] 
	 */
	public void setPositionArtc2(int positionArtc2) {
		this.positionArtc2 = positionArtc2;
	}
	/**
	 * Pega Posição da articulação 3
	 * @return int - Valor [0 a 2000]
	 */
	public int getPositionArtc3() {
		return positionArtc3;
	}
	/**
	 * Seta o valor da Posição da articulação 3
	 * @param int - positionArtc3 [0 a 2000] 
	 */
	public void setPositionArtc3(int positionArtc3) {
		this.positionArtc3 = positionArtc3;
	}
	/**
	 * Pega Posição da articulação 4
	 * @return int - Valor [0 a 2000]
	 */
	public int getPositionArtc4() {
		return positionArtc4;
	}
	/**
	 * Seta o valor da Posição da articulação 4
	 * @param int - positionArtc4 [0 a 2000] 
	 */
	public void setPositionArtc4(int positionArtc4) {
		this.positionArtc4 = positionArtc4;
	}
	/**
	 * Pega Posição da articulação 5
	 * @return int - Valor [0 a 2000]
	 */
	public int getPositionArtc5() {
		return positionArtc5;
	}
	/**
	 * Seta o valor da Posição da articulação 5
	 * @param int - positionArtc5 [0 a 2000] 
	 */
	public void setPositionArtc5(int positionArtc5) {
		this.positionArtc5 = positionArtc5;
	}
}
