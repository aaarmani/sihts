package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import armani.anderson.sihts.factory.ConnectionFactory;

public class PositionDAO {
	/**
	 * Insere um novo registro na tabela position
	 * @param position
	 * @return true - inserido / false - não inserido
	 */
	public boolean insert(PositionVO position) {
		boolean ret = false;
		Connection connection = null;
		PreparedStatement stmt = null;
	    String sql = "INSERT INTO POSITION(name, pos_type, positionArtc1, positionArtc2, positionArtc3, positionArtc4, positionArtc5)"
	    		   + " VALUES(?,?,?,?,?,?,?)";
		
		try {
		   connection = new ConnectionFactory().getConnection();
			
		   stmt = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
		   stmt.setString(1, position.getName());
		   stmt.setString(2, String.valueOf(position.getType()));
		   stmt.setInt(3, position.getPositionArtc1());
		   stmt.setInt(4, position.getPositionArtc2());
		   stmt.setInt(5, position.getPositionArtc3());
		   stmt.setInt(6, position.getPositionArtc4());
		   stmt.setInt(7, position.getPositionArtc5());
		   
		   stmt.executeUpdate();

		   ResultSet keys = stmt.getGeneratedKeys();
		   if(keys.next()) {
			   position.setId(keys.getInt(1));   
		   }
		   
		   keys.close();
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

	/**
	 * Deleta uma linha da tabela pelo ID
	 * @param position
	 * @return
	 */
	public boolean delete(PositionVO position) {
		int ret = 0;
		boolean bolRet = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM position WHERE id = ?";
		
		try {
			connection = new ConnectionFactory().getConnection();
			
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

	/**
	 * Atualiza um registro da tabela position
	 * @param position
	 * @return
	 */
	public boolean update(PositionVO position) {
		boolean ret = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "UPDATE position SET name=?, pos_type=?, positionArtc1=?, positionArtc2=?,"
				+ " positionArtc3=?, positionArtc4=?, positionArtc5=? WHERE id=?";
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, position.getName());
			stmt.setString(2, String.valueOf(position.getType()));
		    stmt.setInt(3, position.getPositionArtc1());
		    stmt.setInt(4, position.getPositionArtc2());
		    stmt.setInt(5, position.getPositionArtc3());
		    stmt.setInt(6, position.getPositionArtc4());
		    stmt.setInt(7, position.getPositionArtc5());			
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

	/**
	 * Seleciona um registro da tabela position por id ou por nome
	 * @param position
	 * @return
	 */
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
