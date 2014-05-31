package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import armani.anderson.sihts.factory.ConnectionFactory;

/**
 * <p>Classe que contém os métodos de acesso a tabela Action do banco no padrão DAO
 * @author armani
 * V00.01
 */
public class ActionDAO {
	final static String COL_ID = "id";
	final static String COL_NAME = "name";
	final static String COL_DESCRIPTION = "description";
	
	public boolean insert(ActionVO actionVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
	    String sql = "INSERT INTO ACTION(" + COL_NAME + ", " + COL_DESCRIPTION + ")"
	    		   + " VALUES(?,?)";
		
		try {
		   connection = new ConnectionFactory().getConnection();
			
		   stmt = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
		   stmt.setString(1, actionVO.getName());
		   stmt.setString(2, actionVO.getDescription());
		   
		   ret = stmt.executeUpdate();

		   ResultSet keys = stmt.getGeneratedKeys();
		   if(keys.next()) {
			   actionVO.setId(keys.getLong(1));   
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
		return (ret > 0);
	}
	
	public List<ActionVO> select(ActionVO actionVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		List<ActionVO> lstActVO = null;
		
		String sql = "SELECT * FROM action";
		
		if(actionVO != null) {
			String where = " WHERE ";
			
			if(actionVO.getId() >= 0) {
				where += COL_ID + " = " + actionVO.getId();
			}
			
			if(actionVO.getName() != "") {
				where += COL_NAME + " = '" + actionVO.getName() + "'";
			}
			
			sql += where;
			System.out.println("SQL = " + sql);
		}
		
		sql += " ORDER BY " + COL_NAME;
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {
					lstActVO = new LinkedList<ActionVO>();

					while(resSet.next()) {
						ActionVO actVO = new ActionVO();
			
						actVO.setId(Long.valueOf(resSet.getString(COL_ID)));
						actVO.setName(resSet.getString(COL_NAME));
						actVO.setDescription(resSet.getString(COL_DESCRIPTION));
						lstActVO.add(actVO);
					}
				}
			}
			
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
		return lstActVO;
	}
	
	public int update(ActionVO actionVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "UPDATE action SET "+ COL_NAME + "=?, " + COL_DESCRIPTION + "=? WHERE id=?";
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, actionVO.getName());
			stmt.setString(2, actionVO.getDescription());
		    stmt.setLong(3, actionVO.getId());
		    ret = stmt.executeUpdate();
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
	
	public int delete(ActionVO actionVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM action WHERE id = ?";
		
		try {
			connection = new ConnectionFactory().getConnection();
			
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, actionVO.getId());
			ret = stmt.executeUpdate();
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
