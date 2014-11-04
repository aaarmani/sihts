package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import armani.anderson.sihts.factory.ConnectionFactory;

public class UserDAO {
	public final static String USR_COL_ID = "id";
	public final static String USR_COL_NAME = "name";
	public final static String USR_COL_LOGIN = "login";
	public final static String USR_COL_PASS = "password";
	public final static String USR_COL_TYPE = "user_type";
	public final static String USR_COL_TYPE_ID = "type_name";
	public final static String USR_COL_TYPE_LEVEL = "level";
	
	/**
	 * Insere um novo registro na tabela user
	 * @param userVO - Objeto UserVO a ser inserido
	 * @return Boolean -  true - inserido / false - não inserido
	 * @throws RuntimeException
	 */
	public boolean insert(UserVO userVO) throws RuntimeException {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
	    String sql = "INSERT INTO \"user\" (name, login, password, user_type)"
	    		   + " VALUES(?,?,md5(?),?)";
		
		try {
		   connection = new ConnectionFactory().getConnection();
			
		   stmt = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
		   stmt.setString(1, userVO.getName());
		   stmt.setString(2, userVO.getLogin());
		   stmt.setString(3, userVO.getPassword());
		   stmt.setInt(4, userVO.getType_level());
		   
		   ret = stmt.executeUpdate();

		   ResultSet keys = stmt.getGeneratedKeys();
		   if(keys.next()) {
			   userVO.setId(keys.getInt(1));   
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
	 * Seleciona um registro da tabela user por id, por nome ou por login e senha
	 * @param userVO - Objeto UserVO com os dados do filtro de seleção ou * se parâmetro position == null
	 * @return List<UserVO> - Lista com os dados retornados ou null 
	 * @throws RuntimeException
	 */
	public List<UserVO> select(UserVO userVO) throws RuntimeException {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		List<UserVO> lstUserVO = null;
		
		String sql = "SELECT u.id, u.name, u.login, u.password, u.user_type, ut.name as type_name, ut.level FROM \"user\" u, \"userType\" ut "
				+ "WHERE u.user_type = ut.id";

		
		if(userVO != null) {
			String where = " AND ";
			
			if(userVO.getId() >= 0) {
				where += "USR_COL_ID = "+ userVO.getId() + " ";
			}
			
			if(userVO.getName() != "") {
				where += USR_COL_NAME + " = '" + userVO.getName() + "' ";
			}
			
			if(userVO.getLogin() != "" && userVO.getPassword() != "") {
				where += USR_COL_LOGIN + " = '" + userVO.getLogin() + "' " +  USR_COL_PASS + " = '" + userVO.getPassword() + "' ";
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
					lstUserVO = new LinkedList<UserVO>();

					while(resSet.next()) {
						UserVO retUser = new UserVO();
			
						retUser.setId(Integer.valueOf(resSet.getString(USR_COL_ID)));
						retUser.setName(resSet.getString(USR_COL_NAME));
						retUser.setLogin(resSet.getString(USR_COL_LOGIN));
						retUser.setPassword(resSet.getString(USR_COL_PASS));
						retUser.setType(resSet.getString(USR_COL_TYPE));
						retUser.setType_id(Integer.valueOf(resSet.getString(USR_COL_TYPE)));
						retUser.setType_level(Integer.valueOf(resSet.getString(USR_COL_TYPE)));
						
						lstUserVO.add(retUser);
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
		return lstUserVO;
	}
	
	/**
	 * Atualiza um registro da tabela position
	 * @param userVO - Objeto PositionVO a ser atualizado
	 * @return int - Número de linhas alteradas
	 * @throws RuntimeException
	 */
	public int update(UserVO userVO) throws RuntimeException {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "UPDATE \"user\" SET name=?, login=?, password=?, user_type=?"
				+ " WHERE id=?";
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, userVO.getName());
			stmt.setString(2, userVO.getLogin());
			stmt.setString(3, userVO.getPassword());
			stmt.setInt(4, userVO.getType_level());
		    
		    stmt.setLong(5, userVO.getId());
		    
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
	 * @param userVO - Objeto PositionVO com o id a ser deletado
	 * @return int - Número de linhas afetadas
	 * @throws RuntimeException
	 */
	public int delete(UserVO userVO) throws RuntimeException {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM \"user\" WHERE id = ?";
		
		try {
			connection = new ConnectionFactory().getConnection();
			
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, userVO.getId());
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
