package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Clubs;
import model.Players;
import model.Position;
import utility.ConnectionManager;

public class PlayerDAO {
	//Auto id incrementer 
	public int getNextId() throws Exception {
		int id = 0;
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st1 = con.prepareStatement("SELECT MAX(playerid) FROM PLAYER2");
		PreparedStatement st2 = con.prepareStatement("SELECT MAX(obsoleteplayerid) FROM OBSOLETEPLAYERS");
		ResultSet result1 = st1.executeQuery();
		ResultSet result2 = st2.executeQuery();
		int maxPlayerId = 0;
		int maxObsoletePlayerId = 0;
		
		while(result1.next()) {
			maxPlayerId = result1.getInt(1);
		}
		
		while(result2.next()) {
			maxObsoletePlayerId = result2.getInt(1);
		}
		
		if(maxPlayerId > maxObsoletePlayerId) {
			id = maxPlayerId + 1;
		}
		else {
			id = maxObsoletePlayerId + 1;
		}
		
		con.close();
		return id;
	}
	
	//Addition of player
	public void addPlayer(Players player) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "insert into PLAYER2(playerid, name, age, clubid, positionid, overallstats, height, pace, strength, value, contractTimeLeft)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);


		st.setInt(1, player.getId());
		st.setString(2, player.getName());	
		st.setInt(3, player.getAge());
		st.setInt(4, player.getClubs().getId());
		st.setInt(5, player.getPosition().getPositionId());
		st.setInt(6, player.getOverallStats());
		st.setInt(7, player.getHeight());
		st.setInt(8, player.getPace());
		st.setInt(9, player.getStrength());
		st.setInt(10, player.getBasePrice());
		st.setInt(11, player.getContractTimeLeft());
		st.executeUpdate();
		
		con.close();
	}
	
	
	//Addition of player to obsolete player database
	public void addObsoletePlayer(int idToBeDeleted) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sqlQuery = "SELECT * FROM PLAYER2 WHERE playerid = " + idToBeDeleted;
		
		//Retrieving detail of individual id
		PreparedStatement individualIdData = con.prepareStatement(sqlQuery);
		ResultSet result = individualIdData.executeQuery();
		
		int id = 0, age = 0, clubId = 0, positionID = 0, overallStats = 0, height = 0, pace = 0, strength = 0, basePrice = 0, contractTimeLeft  = 0;
		String name = "";
		
		while(result.next()) {
			id = result.getInt(1);
			name = result.getString(2);
			age = result.getInt(3);
			clubId = result.getInt(4);
			positionID = result.getInt(5);
			overallStats = result.getInt(6);
			height = result.getInt(7);
			pace = result.getInt(8);
			strength = result.getInt(9);
			basePrice = result.getInt(10);
			contractTimeLeft = result.getInt(11);
		}
		
		//Addition part
		String sql = "insert into OBSOLETEPLAYERS(obsoleteplayerid, name, age, clubid, positionid, overallstats, height, pace, strength, value, contractTimeLeft)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);

		
		st.setInt(1, id);
		st.setString(2, name);	
		st.setInt(3, age);
		st.setInt(4, clubId);
		st.setInt(5, positionID);
		st.setInt(6, overallStats);
		st.setInt(7, height);
		st.setInt(8, pace);
		st.setInt(9, strength);
		st.setInt(10, basePrice);
		st.setInt(11, contractTimeLeft);
		st.executeUpdate();
		
		con.close();
	}
	
	//Delete Player from Players table
	public void deletePlayer(int idToBeDeleted) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql1 = "DELETE FROM BOOKMARKEDPLAYERS WHERE playerid = " + idToBeDeleted;
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.executeUpdate();
		
		String sql2 = "DELETE FROM PLAYER2 WHERE playerid = " + idToBeDeleted;
		PreparedStatement st2 = con.prepareStatement(sql2);
		st2.executeUpdate();
		
		con.close();
	}
	
	//To retrieve the list of all players
	List<Players> playerList = new ArrayList<Players>();
	public List<Players> listAllPlayers() throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM PLAYER2 ORDER BY playerid");
		ResultSet result = st.executeQuery();
		
		Clubs club = null;
		Position position = null;
		while(result.next()) {
			int id = result.getInt(1);
			String name = result.getString(2);
			int age = result.getInt(3);
			int clubId = result.getInt(4);
			int positionID = result.getInt(5);
			ClubDAO clubdao = new ClubDAO();
			PositionDAO positiondao = new PositionDAO();
			club = clubdao.getClubBylD(clubId);
			position = positiondao.getPositionBylD(positionID);
			int overallStats = result.getInt(6);
			int height = result.getInt(7);
			int pace = result.getInt(8);
			int strength = result.getInt(9);
			int basePrice = result.getInt(10);
			int contractTimeLeft = result.getInt(11);
			Players player = new Players(id, name, age, club, position, overallStats, height, pace, strength, basePrice, contractTimeLeft);
			playerList.add(player);
		}
		con.close();
		return playerList;
	}
	
	//To retrieve all player Ids
	List<Integer> playerIds = new ArrayList<Integer>();
	public List<Integer> listAllPlayerIds() throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT playerid FROM PLAYER2");
		ResultSet result = st.executeQuery();
		
		while(result.next()) {
			int id = result.getInt(1);
			playerIds.add(id);
		}
		con.close();
		return playerIds;
	}

	
	//View Obsolete Players
	List<Players> obsoletePlayerList = new ArrayList<Players>();
	public List<Players> listAllObsoletePlayers() throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM OBSOLETEPLAYERS");
		ResultSet result = st.executeQuery();
		
		Clubs club = null;
		Position position = null;
		while(result.next()) {
			int id = result.getInt(1);
			String name = result.getString(2);
			int age = result.getInt(3);
			int clubId = result.getInt(4);
			int positionID = result.getInt(5);
			ClubDAO clubdao = new ClubDAO();
			PositionDAO positiondao = new PositionDAO();
			club = clubdao.getClubBylD(clubId);
			position = positiondao.getPositionBylD(positionID);
			int overallStats = result.getInt(6);
			int height = result.getInt(7);
			int pace = result.getInt(8);
			int strength = result.getInt(9);
			int basePrice = result.getInt(10);
			int contractTimeLeft = result.getInt(11);
			Players player = new Players(id, name, age, club, position, overallStats, height, pace, strength, basePrice, contractTimeLeft);
			obsoletePlayerList.add(player);
		}
		con.close();
		return obsoletePlayerList;
	}

	
	//Filtration of players based on agent input parameters
	List<Players> filteredPlayerList = new ArrayList<Players>();
	public List<Players> filterPlayers(List<Integer> searchTerm) throws Exception {
		int minimumAge = searchTerm.get(0);
		int maximumAge = searchTerm.get(1);
		int positionIdChoice = searchTerm.get(2);
		int minimumOverallStats = searchTerm.get(3);
		int maximumOverallStats = searchTerm.get(4);
		int minimumHeight = searchTerm.get(5);
		int maximumHeight = searchTerm.get(6);
		int minimumPace = searchTerm.get(7);
		int maximumPace = searchTerm.get(8);
		int minimumStrength = searchTerm.get(9);
		int maximumStrength = searchTerm.get(10);
		int minimumPrice = searchTerm.get(11);
		int maximumPrice = searchTerm.get(12);
		int minimumContractTimeLeft = searchTerm.get(13);
		int maximumContractTimeLeft = searchTerm.get(14);
		
		
		String sqlSelection = "SELECT * FROM PLAYER2";
		String sqlAgeSelection = " WHERE age >= " + minimumAge + " AND age <= " + maximumAge; 
		String sqlPositionId = " AND positionid = " + positionIdChoice;
		String sqlOverallStatsSelection = " AND overallStats >= " + minimumOverallStats + " AND overallStats <= " + maximumOverallStats;
		String sqlHeightSelection = " AND height >= " + minimumHeight + " AND height <= " + maximumHeight;
		String sqlPaceSelection = " AND pace >= " + minimumPace + " AND pace <= " + maximumPace;
		String sqlStrengthSelection = " AND strength >= " + minimumStrength + " AND strength <= " + maximumStrength;
		String sqlValueSelection = " AND value >= " + minimumPrice + " AND value <= " + maximumPrice;
		String sqlContractTimeLeftSelection = " AND contractTimeLeft >= " + minimumContractTimeLeft + " AND contractTimeLeft <= " + maximumContractTimeLeft;
		String sqlOrder = " ORDER by playerid";
		String sqlQueryStatement = sqlSelection + sqlAgeSelection + sqlPositionId + sqlOverallStatsSelection + sqlHeightSelection + sqlPaceSelection + sqlStrengthSelection + sqlValueSelection + sqlContractTimeLeftSelection + sqlOrder;
		
		//System.out.println(sqlQueryStatement);
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement(sqlQueryStatement);
		//PreparedStatement st = con.prepareStatement(sqlSelection + sqlAgeSelection + sqlPositionId);
		ResultSet result = st.executeQuery();
		
		Clubs club = null;
		Position position = null;
		while(result.next()) {
			int id = result.getInt(1);
			String name = result.getString(2);
			int age = result.getInt(3);
			int clubId = result.getInt(4);
			int positionID = result.getInt(5);
			ClubDAO clubdao = new ClubDAO();
			PositionDAO positiondao = new PositionDAO();
			club = clubdao.getClubBylD(clubId);
			position = positiondao.getPositionBylD(positionID);
			int overallStats = result.getInt(6);
			int height = result.getInt(7);
			int pace = result.getInt(8);
			int strength = result.getInt(9);
			int basePrice = result.getInt(10);
			int contractTimeLeft = result.getInt(11);
			Players player = new Players(id, name, age, club, position, overallStats, height, pace, strength, basePrice, contractTimeLeft);
			filteredPlayerList.add(player);
		}
		con.close();
		return filteredPlayerList;
	}
	
	
	//Updation of player data by admin
	//Update Player Age
	public void updatePlayerAge(int id, int updateValue) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE PLAYER2 SET age = " + updateValue + " WHERE playerid = " + id;
		PreparedStatement st = con.prepareStatement(sql);
		st.executeUpdate();
		
		con.close();
	}
	
	//Update Player Club
	public void updatePlayerClub(int id, int updateValue) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE PLAYER2 SET clubid = " + updateValue + " WHERE playerid = " + id;
		PreparedStatement st = con.prepareStatement(sql);
		st.executeUpdate();
		
		con.close();
	}
	
	//Update Player Position
	public void updatePlayerPosition(int id, int updateValue) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE PLAYER2 SET positionid = " + updateValue + " WHERE playerid = " + id;
		PreparedStatement st = con.prepareStatement(sql);
		st.executeUpdate();
		
		con.close();
	}
	
	
	//Update Player Overall Stats score
	public void updatePlayerOverallStatsScore(int id, int updateValue) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE PLAYER2 SET overallStats = " + updateValue + " WHERE playerid = " + id;
		PreparedStatement st = con.prepareStatement(sql);
		st.executeUpdate();
			
		con.close();
	}
	
	//Update Player Height
	public void updatePlayerHeight(int id, int updateValue) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE PLAYER2 SET height = " + updateValue + " WHERE playerid = " + id;
		PreparedStatement st = con.prepareStatement(sql);
		st.executeUpdate();
				
		con.close();
	}
	
	//Update Player Pace
	public void updatePlayerPace(int id, int updateValue) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE PLAYER2 SET pace = " + updateValue + " WHERE playerid = " + id;
		PreparedStatement st = con.prepareStatement(sql);
		st.executeUpdate();
					
		con.close();
	}
	
	
	//Update Player Strength
	public void updatePlayerStrength(int id, int updateValue) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE PLAYER2 SET strength = " + updateValue + " WHERE playerid = " + id;
		PreparedStatement st = con.prepareStatement(sql);
		st.executeUpdate();
						
		con.close();
	}
	
	//Update Player Value
	public void updatePlayerValue(int id, int updateValue) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE PLAYER2 SET value = " + updateValue + " WHERE playerid = " + id;
		PreparedStatement st = con.prepareStatement(sql);
		st.executeUpdate();
							
		con.close();
	}
	
	//Update Player Value
	public void updatePlayerContractTimeLeft(int id, int updateValue) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE PLAYER2 SET contractTimeLeft = " + updateValue + " WHERE playerid = " + id;
		PreparedStatement st = con.prepareStatement(sql);
		st.executeUpdate();
								
		con.close();
	}
}
