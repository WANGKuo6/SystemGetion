package mypackage.model;

public class Login {
	private int id;
	private String login;
	private String password;
	public Login() {
		
	}
	public Login(String paramLogin, String parampwd) {
		super();
		this.login = paramLogin;
		this.password = parampwd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
}
