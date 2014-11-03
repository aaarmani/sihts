package armani.anderson.sihts.model;

import java.sql.SQLException;
import java.util.List;

public class UserBO {
	/**
	 * Método de validação e inserção de Objeto User no Banco de Dados
	 * @param userVO - PositionVO a ser inserida
	 * @return boolean - true = inserido / false = não inserido
	 * @throws IllegalArgumentException
	 */
	public boolean insert (UserVO userVO) throws IllegalArgumentException {
		boolean ret;
		String expMsg = "";
		
		expMsg = validateUser(userVO);
		
		if(!expMsg.isEmpty()) {
			throw new IllegalArgumentException(expMsg);
		}
		
		UserDAO userDAO = new UserDAO();
		ret = userDAO.insert(userVO);
		
		return ret;
	}

	/**
	 * Seleciona linhas da tabela de User
	 * @param userVO - UserVO com os dados para a seleção
	 * @return List<UserVO> - Lista de Users selecionadas do banco de dados
	 * @throws IllegalArgumentException
	 * @throws SQLException
	 */
	public List<UserVO> select (UserVO userVO) throws IllegalArgumentException {
		boolean exception = false;
		String expMsg = "";
		List<UserVO> lstUserVO = null;
		
		/*if(position == null) {
			exception = true;
			expMsg = "Não aceita objeto posição NULO";
		}*/
		if(userVO != null) {
			if ((userVO.getName().isEmpty()) &&
					(userVO.getId() < 0)) {
				exception = true;
				expMsg = "Faltam dados para realizar a seleção";
			}
		
			if(exception == true) {
				throw new IllegalArgumentException(expMsg);
			}			
		}

		UserDAO userDAO = new UserDAO();
		lstUserVO = userDAO.select(userVO);

		return lstUserVO;
	}
	
	/**
	 * Atualiza um registro da tabela de User
	 * @param userVO - UserVO com os dados a serem atualizados
	 * @return boolean - true = Atualizou uma ou mais linhas da tabela / false = nenhuma linha atualizada
	 * @throws IllegalArgumentException
	 */
	public boolean update (UserVO userVO) throws IllegalArgumentException {
		String expMsg = "";
		
		expMsg = validateUser(userVO);
		
		if(expMsg.isEmpty()) {
			if(userVO.getId() < 0) {
				expMsg = "Impossível atualizar Posição sem o valor do ID";
			}
		}
		
		if(!expMsg.isEmpty()) {
			throw new IllegalArgumentException(expMsg);
		}
		
		UserDAO posDAO = new UserDAO();
		int ret = posDAO.update(userVO);
		
		return (ret >= 1);
	}
	
	/**
	 * Deleta uma linha da tabela de User
	 * @param userVO - UserVO com o ID a ser deletado
	 * @return boolean - true = ID deletado / false = não deletado
	 * @throws IllegalArgumentException
	 */
	public boolean delete (UserVO userVO) throws IllegalArgumentException {
		
		if(userVO.getId() < 0) {
			throw new IllegalArgumentException("Necessário ID para deletar um resitro");
		}
		
		UserDAO posDAO = new UserDAO();
		int ret = posDAO.delete(userVO);
		
		return (ret > 0);
	}
//############################ Métodos Complementares ############################
	private String validateUser(UserVO userVO) {
		String expMsg = "";
		if(userVO == null) {
			expMsg = "Não aceita objeto User NULO";
		}
		else if(userVO.getName().isEmpty()) {
			expMsg = "O nome do Usuário não pode ser NULO ou Vazio";
		}
		else if(userVO.getLogin().isEmpty() || userVO.getPassword().isEmpty()) {
			expMsg = "Login ou Senha inválido!";
		}
		return expMsg;
	}

}
