package model;

public class Clubs {
	private int Clubid;
	private String Clubname;
	
	//Constructors
	public Clubs(int id, String name) {
		this.Clubid = id;
		this.Clubname = name;
	}

	
	//Getters and Setters
	public int getId() {
		return Clubid;
	}

	public void setId(int id) {
		this.Clubid = id;
	}

	public String getName() {
		return Clubname;
	}

	public void setName(String name) {
		this.Clubname = name;
	}
	
	
}
