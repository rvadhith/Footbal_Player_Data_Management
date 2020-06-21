package model;

public class Position {
	private int positionId;
	private String positionName;
	
	//Constructors
	public Position(int positionId, String positionName) {
		this.positionId = positionId;
		this.positionName = positionName;
	}

	//Getters and Setters
	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
}
