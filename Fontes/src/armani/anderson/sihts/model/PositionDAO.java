package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import armani.anderson.sihts.factory.ConnectionFactory;

/**
 * Classe que contém os métodos de CRUD de Posição no Banco de Dados
 * @author armani
 * @version V00.01
 */
public class PositionDAO {
	public final static String POS_COL_ID = "id";
	public final static String POS_COL_NAME = "name";
	public final static String POS_COL_TYPE = "pos_type";
	public final static String POS_COL_ARTC1 = "positionartc1";
	public final static String POS_COL_ARTC2 = "positionartc2";
	public final static String POS_COL_ARTC3 = "positionartc3";
	public final static String POS_COL_ARTC4 = "positionartc4";
	public final static String POS_COL_ARTC5 = "positionartc5";
	
	/**
	 * Insere um novo registro na tabela position
	 * @param position - Objeto PositionVO a ser inserido
	 * @return Boolean -  true - inserido / false - não inserido
	 * @throws RuntimeException
	 */
	public boolean insert(PositionVO position) throws RuntimeException {
		int ret = 0;
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
		   
		   ret = stmt.executeUpdate();

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
		return (ret > 0);
	}
	
	/**
	 * Seleciona um registro da tabela position por id ou por nome
	 * @param position - Objeto PositionVO com os dados do filtro de seleção ou * se parâmetro position == null
	 * @return List<PositionVO> - Lista com os dados retornados ou null 
	 * @throws RuntimeException
	 */
	public List<PositionVO> select(PositionVO position) throws RuntimeException {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		List<PositionVO> lstPosVO = null;
		
		String sql = "SELECT * FROM POSITION ";
		
		if(position != null) {
			String where = "WHERE ";
			
			if(position.getId() >= 0) {
				where += "id = "+ position.getId();
			}
			
			if(position.getName() != "") {
				where += POS_COL_NAME + " = '" + position.getName() + "'";
			}
			
			if(position.getType() == 'P' || position.getType() == 'O') {
				where += POS_COL_TYPE + " = '" + position.getType() + "";
			}
			
			sql += where;
			System.out.println("SQL = " + sql);
		}
		
		sql += " ORDER BY name";
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {
					lstPosVO = new LinkedList<PositionVO>();

					while(resSet.next()) {
						PositionVO posVO = new PositionVO();
			
						posVO.setId(Long.valueOf(resSet.getString(PositionDAO.POS_COL_ID)));
						posVO.setName(resSet.getString(PositionDAO.POS_COL_NAME));
						posVO.setType(resSet.getString(PositionDAO.POS_COL_TYPE).charAt(0));
						posVO.setPositionArtc1(Integer.valueOf(resSet.getString(PositionDAO.POS_COL_ARTC1)));
						posVO.setPositionArtc2(Integer.valueOf(resSet.getString(PositionDAO.POS_COL_ARTC2)));
						posVO.setPositionArtc3(Integer.valueOf(resSet.getString(PositionDAO.POS_COL_ARTC3)));
						posVO.setPositionArtc4(Integer.valueOf(resSet.getString(PositionDAO.POS_COL_ARTC4)));
						posVO.setPositionArtc5(Integer.valueOf(resSet.getString(PositionDAO.POS_COL_ARTC5)));
						
						lstPosVO.add(posVO);
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
		return lstPosVO;
	}
	
	/**
	 * Atualiza um registro da tabela position
	 * @param position - Objeto PositionVO a ser atualizado
	 * @return int - Número de linhas alteradas
	 * @throws RuntimeException
	 */
	public int update(PositionVO position) throws RuntimeException {
		int ret = 0;
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
		    stmt.setLong(8, position.getId());
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
	
	/**
	 * Deleta uma linha da tabela pelo ID
	 * @param position - Objeto PositionVO com o id a ser deletado
	 * @return int - Número de linhas afetadas
	 * @throws RuntimeException
	 */
	public int delete(PositionVO position) throws RuntimeException {
		int ret = 0;
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
