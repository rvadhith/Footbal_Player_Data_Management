package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Agents;
import model.Clubs;
import model.Players;
import model.Position;
import utility.ConnectionManager;

public class AgentDAO {
	//Bookmark Players
	public void bookmarkPlayers(int agentId, int playerId) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "insert into BOOKMARKEDPLAYERS(agentid, playerid)VALUES(?,?)";
		PreparedStatement st = con.prepareStatement(sql);


		st.setInt(1, agentId);
		st.setInt(2, playerId);
		st.executeUpdate();
		con.close();
	}
	
	//Retrieving bookmarked player Ids
	List<Integer> bookmarkedPlayerIds = new ArrayList<Integer>();
	public List<Integer> getBookmarkedPlayerIds(int agentId) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sqlQuery = "SELECT playerid FROM BOOKMARKEDPLAYERS WHERE agentid = " + agentId;
		PreparedStatement st = con.prepareStatement(sqlQuery);
		ResultSet result = st.executeQuery();
		
		while(result.next()) {
			int id = result.getInt(1);
			bookmarkedPlayerIds.add(id);
		}
		con.close();
		return bookmarkedPlayerIds;
	}
	
	//Retrieving bookmarked players list
	List<Players> bookmarkedPlayerList = new ArrayList<Players>();
	public List<Players> getBookmarkedPlayer(int agentId) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sqlQuery = "SELECT DISTINCT PLAYER2.playerid, PLAYER2.name, PLAYER2.age, PLAYER2.clubid, PLAYER2.positionid, PLAYER2.overallstats, PLAYER2.height, PLAYER2.pace, PLAYER2.strength, PLAYER2.value, PLAYER2.contractTimeLeft FROM BOOKMARKEDPLAYERS INNER JOIN PLAYER2 ON BOOKMARKEDPLAYERS.playerid = PLAYER2.playerid WHERE agentid = " + agentId;
		PreparedStatement st = con.prepareStatement(sqlQuery);
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
			bookmarkedPlayerList.add(player);
		}
		con.close();
		return bookmarkedPlayerList;
	}
	
	
	
	//Agent id incrementer
	public int agentIdIncrmenter() throws Exception {
		int id = 0;
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT MAX(agentid) FROM AGENT");
		ResultSet result = st.executeQuery();
		int maxAgentId = 0;
		
		while(result.next()) {
			maxAgentId = result.getInt(1);
		}
		
		id = maxAgentId + 1;
		
		con.close();
		return id;
	}
	
	//Method to find which agent is logged in
	public int agentLoggedInId() throws Exception {
		int loggedInId = 0;
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT agentid FROM AGENT WHERE loggedinstatus = 1");
		ResultSet result = st.executeQuery();
		
		while(result.next()) {
			loggedInId = result.getInt(1);
		}
		con.close();
		return loggedInId;
	}
	
	//Method to reset login status of all agents in case if the program crashes
	public void agentLoginReset() throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE AGENT SET loggedinstatus = 0 WHERE loggedinstatus = 1";
		PreparedStatement st = con.prepareStatement(sql);
		st.executeUpdate();
								
		con.close();
	}
	
	
	public void agentLogoutStatusUpdate(int id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE AGENT SET loggedinstatus = 0 WHERE agentid = " + id;
		PreparedStatement st = con.prepareStatement(sql);
		st.executeUpdate();
								
		con.close();
	}
	
	public void agentSignUp(Agents agent) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "insert into AGENT(agentid, username, password, loggedinstatus)VALUES(?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);


		st.setInt(1, agent.getAgentId());
		st.setString(2, agent.getUsername());	
		st.setString(3, agent.getPassword());
		st.setInt(4, 1);
		st.executeUpdate();
		con.close();
	}
	
	
	List<String> usernameList = new ArrayList<String>();
	public List<String> agentSignUpValidation() throws Exception {
		String username = "";
		Connection con = ConnectionManager.getConnection();
		String sql = "SELECT username FROM AGENT";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet result = st.executeQuery();

		while(result.next()) {
			username = result.getString(1);
			usernameList.add(username);
		}
		con.close();
		return usernameList;
	}
	

	public boolean agentLoginValidation(String checkUsername, String checkPassword) throws Exception {
		boolean loginValidation = false;
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT agentid,username,password FROM AGENT");
		ResultSet result = st.executeQuery();
	
		int agentId = 0;	
		String usernameData = "";
		String passwordData = "";
		while(result.next()) {
			agentId = result.getInt(1);
			usernameData = result.getString(2);
			passwordData = result.getString(3);
			if(usernameData.equals(checkUsername) && passwordData.equals(checkPassword)) {
				loginValidation = true;
				String sql = "UPDATE AGENT SET loggedinstatus = 1 WHERE agentid = " + agentId;
				PreparedStatement st2 = con.prepareStatement(sql);
				st2.executeUpdate();
				break;
			}
			else {
				loginValidation = false;
			}
		}
		con.close();
		return loginValidation;
	}	
}
