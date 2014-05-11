package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import armani.anderson.sihts.factory.ConnectionFactory;

public class PositionDAO {
	
	public boolean insert(PositionVO position) {
		boolean ret = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
		   connection = new ConnectionFactory().getConnection();
		   
		   String sql = "INSERT INTO POSITION(name, pos_type, positionArtc1, positionArtc2, positionArtc3, positionArtc4, positionArtc5)"
					+ " VALUES(?,?,?,?,?,?,?)";
			
		   stmt = connection.prepareStatement(sql);
		   
		   stmt.setString(1, position.getName());
		   stmt.setString(2, String.valueOf(position.getType()));
		   stmt.setInt(3, position.getPositionArtc1());
		   stmt.setInt(4, position.getPositionArtc2());
		   stmt.setInt(5, position.getPositionArtc3());
		   stmt.setInt(6, position.getPositionArtc4());
		   stmt.setInt(7, position.getPositionArtc5());
		   
		   ret = stmt.execute();
		   stmt.close();
		} catch (SQLException e) {
		    throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public boolean delete(PositionVO position) {
		int ret = 0;
		boolean bolRet = false;
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = new ConnectionFactory().getConnection();
			
			String sql = "DELETE FROM position WHERE id = ?";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, position.getId());
			ret = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//Ret > 0 =  delete sucess
		bolRet = (ret > 0);
		return bolRet;
	}

	public boolean update(PositionVO position) {
		boolean ret = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "SELECT * FROM POSITION ";
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public ResultSet select(PositionVO position) {
		ResultSet ret = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "SELECT * FROM POSITION ";
		String where = "WHERE ";
		
		if(position.getId() != 0) {
			where += "id = "+ position.getId();
		}
		
		if(position.getName() != "") {
			where += "name = " + position.getName();
		}
		
		if(position.getType() == 'P' || position.getType() == 'O') {
			where += position.getType();
		}
		
		sql += where;
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			ret = stmt.getResultSet();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
}
