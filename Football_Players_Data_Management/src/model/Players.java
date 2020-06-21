package model;

public class Players {
	private int id;
	private String name;
	private int age;
	private Clubs clubs;
	private Position position;
	private int overallStats;
	private int height;
	private int pace;
	private int strength;
	private int basePrice;
	private int contractTimeLeft;
	
	//Players class Constructor
	public Players(int id, String name, int age, Clubs clubs, Position position, int overallStats, int height, int pace,
			int strength, int basePrice, int contractTimeLeft) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.clubs = clubs;
		this.position = position;
		this.overallStats = overallStats;
		this.height = height;
		this.pace = pace;
		this.strength = strength;
		this.basePrice = basePrice;
		this.contractTimeLeft = contractTimeLeft;
	}

	//Players default constructor
	public Players() {
		// TODO Auto-generated constructor stub
	}


	//Players class Getters and Setters
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Clubs getClubs() {
		return clubs;
	}

	public void setClubs(Clubs clubs) {
		this.clubs = clubs;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getOverallStats() {
		return overallStats;
	}

	public void setOverallStats(int overallStats) {
		this.overallStats = overallStats;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPace() {
		return pace;
	}

	public void setPace(int pace) {
		this.pace = pace;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public int getContractTimeLeft() {
		return contractTimeLeft;
	}

	public void setContractTimeLeft(int contractTimeLeft) {
		this.contractTimeLeft = contractTimeLeft;
	}
}






