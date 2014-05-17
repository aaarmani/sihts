package armani.anderson.sihts.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe que contém as regras de negócio para CRUD de Posição
 * @author armani
 * @version V00.01
 */
public class PositionBO {
	/**
	 * Método de validação e inserção de Objeto Posição no Banco de Dados
	 * @param position - PositionVO a ser inserida
	 * @return boolean - true = inserido / false = não inserido
	 * @throws IllegalArgumentException
	 */
	public boolean insert (PositionVO position) throws IllegalArgumentException {
		boolean ret;
		String expMsg = "";
		
		expMsg = validatePosition(position);
		
		if(!expMsg.isEmpty()) {
			throw new IllegalArgumentException(expMsg);
		}
		
		PositionDAO posDAO = new PositionDAO();
		ret = posDAO.insert(position);
		
		return ret;
	}

	/**
	 * Seleciona linhas da tabela de Posição
	 * @param position - PositionVO com os dados para a seleção
	 * @return List<PositionVO> - Lista de Posições selecionadas do banco de dados
	 * @throws IllegalArgumentException
	 * @throws SQLException
	 */
	public List<PositionVO> select (PositionVO position) throws IllegalArgumentException {
		boolean exception = false;
		String expMsg = "";
		List<PositionVO> lstPosVO = null;
		
		/*if(position == null) {
			exception = true;
			expMsg = "Não aceita objeto posição NULO";
		}*/
		if(position != null) {
			if ((position.getName().isEmpty()) && 
					((position.getType() != 'P') || (position.getType() != 'O')) &&
					(position.getId() < 0)) {
				exception = true;
				expMsg = "Faltam dados para realizar a seleção";
			}
		
			if(exception == true) {
				throw new IllegalArgumentException(expMsg);
			}			
		}

		PositionDAO posDAO = new PositionDAO();
		lstPosVO = posDAO.select(position);

		return lstPosVO;
	}
	
	/**
	 * Atualiza um registro da tabela de Posição
	 * @param position - PositionVO com os dados a serem atualizados
	 * @return boolean - true = Atualizou uma ou mais linhas da tabela / false = nenhuma linha atualizada
	 * @throws IllegalArgumentException
	 */
	public boolean update (PositionVO position) throws IllegalArgumentException {
		String expMsg = "";
		
		expMsg = validatePosition(position);
		
		if(expMsg.isEmpty()) {
			if(position.getId() < 0) {
				expMsg = "Impossível atualizar Posição sem o valor do ID";
			}
		}
		
		if(!expMsg.isEmpty()) {
			throw new IllegalArgumentException(expMsg);
		}
		
		PositionDAO posDAO = new PositionDAO();
		int ret = posDAO.update(position);
		
		return (ret >= 1);
	}
	
	/**
	 * Deleta uma linha da tabela de Posição
	 * @param position - PositionVO com o ID a ser deletado
	 * @return boolean - true = ID deletado / false = não deletado
	 * @throws IllegalArgumentException
	 */
	public boolean delete (PositionVO position) throws IllegalArgumentException {
		
		if(position.getId() < 0) {
			throw new IllegalArgumentException("Necessário ID para deletar um resitro");
		}
		
		PositionDAO posDAO = new PositionDAO();
		int ret = posDAO.delete(position);
		
		return (ret > 0);
	}
	
//############################ Métodos Complementares ############################
	private String validatePosition(PositionVO position) {
		String expMsg = "";
		if(position == null) {
			expMsg = "Não aceita objeto Posição NULO";
		}
		else if(position.getName().isEmpty()) {
			expMsg = "O nome da Posição não pode ser NULO ou Vazio";
		}
		else if((position.getType() != 'P') && (position.getType() != 'O')) {
			expMsg = "O tipo da Posição deve ser P ou O";
		}
		return expMsg;
	}
}
