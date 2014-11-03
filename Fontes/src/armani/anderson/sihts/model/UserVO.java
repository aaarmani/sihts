package armani.anderson.sihts.model;

public class UserVO {
	private int id;
	private String name;
	private String login;
	private String password;
	private String type;
	private int type_id;
	private int type_level;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getType_level() {
		return type_level;
	}
	public void setType_level(int type_level) {
		this.type_level = type_level;
	}
}
