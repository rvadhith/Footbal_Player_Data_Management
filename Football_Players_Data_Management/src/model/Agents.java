package model;


public class Agents {
	private int agentId;
	private String username;
	private String password;
	
	//Constructor
	public Agents(int agentId, String username, String password) {
		super();
		this.agentId = agentId;
		this.username = username;
		this.password = password;
	}
	
	//Getters and Setters
	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
