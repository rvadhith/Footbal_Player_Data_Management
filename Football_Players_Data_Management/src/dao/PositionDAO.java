package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import model.Position;
import utility.ConnectionManager;

public class PositionDAO {
	//Auto id incrementer of positions
	public int PositionIdIncrmenter() throws Exception {
		int id = 0;
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT MAX(positionid) FROM POSITION");
		ResultSet result = st.executeQuery();
		int maxPositionId = 0;
		
		while(result.next()) {
			maxPositionId = result.getInt(1);
		}
		
		id = maxPositionId + 1;
		
		con.close();
		return id;
	}
	
	//Addition of positions
	public void addPosition(Position position) throws Exception {
	Connection con = ConnectionManager.getConnection();
	String sql = "insert into POSITION(positionid, name)VALUES(?,?)";
	PreparedStatement st = con.prepareStatement(sql);

	
	st.setInt(1, position.getPositionId());
	st.setString(2, position.getPositionName());	
	st.executeUpdate();
	
	con.close();
	}
	
	//To retrieve the minimum and maximum position ids
	List<Integer> minMaxPositionIdList = new ArrayList<Integer>();
	public List<Integer> getMinMaxPositionId() throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st1 = con.prepareStatement("SELECT MIN(positionid) FROM POSITION");
		PreparedStatement st2 = con.prepareStatement("SELECT MAX(positionid) FROM POSITION");
		ResultSet result1 = st1.executeQuery();
		ResultSet result2 = st2.executeQuery();
		int minPositionId = 0;
		int maxPositionId = 0;
		
		while(result1.next()) {
			minPositionId = result1.getInt(1);
		}
		
		while(result2.next()) {
			maxPositionId = result2.getInt(1);
		}
		
		minMaxPositionIdList.add(minPositionId);
		minMaxPositionIdList.add(maxPositionId);
		
		con.close();
		return minMaxPositionIdList;
	}
	
	
	//To get a list of all the positions
	List<Position> positionList = new ArrayList<Position>();
	public List<Position> listAllPosition() throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM POSITION");
		ResultSet result = st.executeQuery();
		
		while(result.next()) {
			int id = result.getInt(1);
			String name = result.getString(2);
			Position position = new Position(id,name);
			positionList.add(position);
		}
		con.close();
		return positionList;
	}
	
	
	//To retrieve the position based on id
	public Position getPositionBylD(int id) throws Exception{
		Connection con = ConnectionManager.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM POSITION");
		ResultSet rs = st.executeQuery();
		 
		while(rs.next()){
			 int checkid = rs.getInt(1);
			 if(checkid == id){
				 String name = rs.getString(2);
				 Position position = new Position(checkid, name);
				 return position;
			 }
		}
		con.close();
		return null;
	}
	
}
