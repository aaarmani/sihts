package armani.anderson.sihts.model;

/**
 * Position - Classe que descreve uma posição do braço robótico
 * @author armani
 *
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
	 * Retorna o Id da posição no banco de dados
	 * @return
	 */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPositionArtc1() {
		return positionArtc1;
	}
	public void setPositionArtc1(int positionArtc1) {
		this.positionArtc1 = positionArtc1;
	}
	public int getPositionArtc2() {
		return positionArtc2;
	}
	public void setPositionArtc2(int positionArtc2) {
		this.positionArtc2 = positionArtc2;
	}
	public int getPositionArtc3() {
		return positionArtc3;
	}
	public void setPositionArtc3(int positionArtc3) {
		this.positionArtc3 = positionArtc3;
	}
	public int getPositionArtc4() {
		return positionArtc4;
	}
	public void setPositionArtc4(int positionArtc4) {
		this.positionArtc4 = positionArtc4;
	}
	public int getPositionArtc5() {
		return positionArtc5;
	}
	public void setPositionArtc5(int positionArtc5) {
		this.positionArtc5 = positionArtc5;
	}
}
