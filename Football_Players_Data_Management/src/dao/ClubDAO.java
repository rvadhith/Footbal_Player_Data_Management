package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Clubs;
import utility.ConnectionManager;

public class ClubDAO {
	//Club id auto incrementer
	public int ClubIdIncrmenter() throws Exception {
		int id = 0;
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT MAX(clubid) FROM CLUB");
		ResultSet result = st.executeQuery();
		int maxClubId = 0;
		
		while(result.next()) {
			maxClubId = result.getInt(1);
		}
		
		id = maxClubId + 1;
		con.close();
		return id;
	}
	
	//Addition of new club
	public void addClub(Clubs club) throws Exception {
		Connection con = ConnectionManager.getConnection();
		String sql = "insert into CLUB(clubid, name)VALUES(?,?)";
		PreparedStatement st = con.prepareStatement(sql);

		
		st.setInt(1, club.getId());
		st.setString(2, club.getName());	
		st.executeUpdate();
		
		con.close();
	}
	
	//To retrieve a list of all clubs
	List<Clubs> clubList = new ArrayList<Clubs>();
	public List<Clubs> listAllClubs() throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM CLUB ORDER BY clubid");
		ResultSet result = st.executeQuery();
		
		while(result.next()) {
			int id = result.getInt(1);
			String name = result.getString(2);
			Clubs club = new Clubs(id,name);
			clubList.add(club);
		}
		con.close();
		return clubList;
	}
	
	//To retrieve the minimum and maximum club ids
	List<Integer> minMaxClubIdList = new ArrayList<Integer>();
	public List<Integer> getMinMaxClubId() throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st1 = con.prepareStatement("SELECT MIN(clubid) FROM CLUB");
		PreparedStatement st2 = con.prepareStatement("SELECT MAX(clubid) FROM CLUB");
		ResultSet result1 = st1.executeQuery();
		ResultSet result2 = st2.executeQuery();
		int minClubId = 0;
		int maxClubId = 0;
		
		while(result1.next()) {
			minClubId = result1.getInt(1);
		}
		
		while(result2.next()) {
			maxClubId = result2.getInt(1);
		}
		
		minMaxClubIdList.add(minClubId);
		minMaxClubIdList.add(maxClubId);
		con.close();
		return minMaxClubIdList;
	}
	
	//To retrieve the name of the club based on club id
	public Clubs getClubBylD(int id) throws Exception{
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM CLUB");
		ResultSet rs = st.executeQuery();
		
		while(rs.next()){
			 int checkid = rs.getInt(1);
			 if(checkid == id){
				 String name = rs.getString(2);
				 Clubs club = new Clubs(checkid, name);
				 return club;
			 }
			}
		con.close();
		return null;
	}
	
}
