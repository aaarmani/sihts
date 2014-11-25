package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import armani.anderson.sihts.model.PositionBO;
import armani.anderson.sihts.model.PositionVO;
import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.PositionView;
/**
 * Controller para o painel CRUD de Posição
 * @author armani
 * @version V00.01
 */
public class PositionCTRL implements ActionListener, ChangeListener, ListSelectionListener {
	private PositionView pnCrudPosition = null;
	private RoboticArm roboticArm = null;
	
	private Vector<String> vctPosition;
	private boolean isEdition = false;
	private long idForEdition = -1;

	/**
	 * Método Construtor do Controller
	 *  - Inicializa o painel CRU Position
	 *  - Inicializa os listners para tratar os eventos do painel
	 * @param pnCrudPosition2 - Painel que será controlado
	 * @param roboticArm2 - Controle do braço mecânico
	 */
	public PositionCTRL(PositionView pnCrudPosition2, RoboticArm roboticArm2) {
		this.pnCrudPosition = pnCrudPosition2;
		this.roboticArm = roboticArm2;
		
		initializeListPositions();
		
		this.pnCrudPosition.getBtnExcluir().setVisible(false);
		
		this.pnCrudPosition.getLstPosition().addListSelectionListener(this);
		this.pnCrudPosition.getSldArtc1().addChangeListener(this);
		this.pnCrudPosition.getSldArtc2().addChangeListener(this);
		this.pnCrudPosition.getSldArtc3().addChangeListener(this);
		this.pnCrudPosition.getSldArtc4().addChangeListener(this);
		this.pnCrudPosition.getSldArtc5().addChangeListener(this);
		
		this.pnCrudPosition.getBtnSalvar().addActionListener(this);
		this.pnCrudPosition.getBtnCancelar().addActionListener(this);
		this.pnCrudPosition.getBtnExcluir().addActionListener(this);
		
		clearPositionPanel();
	}
	
	/**
	 * Tratamento dos eventos de click
	 * - Botões Salvar/Cancelar/Excluir
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object objEvent = e.getSource();
		//SALVAR
		if(objEvent == this.pnCrudPosition.getBtnSalvar()) {
			PositionVO posVO = getPosition();
			
			PositionBO posBO = new PositionBO();
			if(isEdition == false) {
				//INSERT
				if(posBO.insert(posVO) == true) {
					JOptionPane.showMessageDialog(null, "Posição inserida com sucesso!", "Insert", JOptionPane.ERROR_MESSAGE);	
				}
				else {
					JOptionPane.showMessageDialog(null, "Erro ao inserir posição", "Insert", JOptionPane.ERROR_MESSAGE);
				}	
			}
			else {
				//UPDATE
				posVO.setId(idForEdition);
				if(posBO.update(posVO) == true) {
					JOptionPane.showMessageDialog(null, "Posição atualizada com sucesso!", "Insert", JOptionPane.ERROR_MESSAGE);	
				}
				else {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar posição", "Insert", JOptionPane.ERROR_MESSAGE);
				}
			}
			initializeListPositions();
		}
		//CANCELAR
		else if(objEvent == this.pnCrudPosition.getBtnCancelar()) {
			clearPositionPanel();
		}
		//DELETAR
		else if(objEvent == this.pnCrudPosition.getBtnExcluir()) {
			PositionVO posVO = getPosition();
			posVO.setId(idForEdition);
			
			clearPositionPanel();
			
			PositionBO posBO = new PositionBO();
			posBO.delete(posVO);
			
			initializeListPositions();
			
		}
	}

	/**
	 * Tratamento dos eventos de atualização de Slider
	 * - Ao soltar o slider atualiza automaticamente a articulação do braço robótico
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		int intArtc = Al5b.ARTC_BASE;
		int	intPos = 0;
		JSlider sldBkp = null;
		
		Object objEvent = e.getSource();
		
		sldBkp = (JSlider) objEvent;
		if(sldBkp.getValueIsAdjusting() == true) {
			//while change
			return;
		}

		if(objEvent == this.pnCrudPosition.getSldArtc1()) {
			intArtc = Al5b.ARTC_BASE; 
			intPos = this.pnCrudPosition.getSldArtc1().getValue();
		}
		else if(objEvent == this.pnCrudPosition.getSldArtc2()) {
			intArtc = Al5b.ARTC_OMBRO;
			intPos = this.pnCrudPosition.getSldArtc2().getValue();
		}
		else if(objEvent == this.pnCrudPosition.getSldArtc3()) {
			intArtc = Al5b.ARTC_COTOVELO;
			intPos = this.pnCrudPosition.getSldArtc3().getValue();
		}
		else if(objEvent == this.pnCrudPosition.getSldArtc4()) {
			intArtc = Al5b.ARTC_PULSO;
			intPos = this.pnCrudPosition.getSldArtc4().getValue();
		}
		else if(objEvent == this.pnCrudPosition.getSldArtc5()) {
			intArtc = Al5b.ARTC_PINCA;
			intPos = this.pnCrudPosition.getSldArtc5().getValue();
		}
		else {
			return;
		}
		
		if(roboticArm != null)
			roboticArm.sendPosition(intArtc, intPos, 1000);
	}

	/**
	 * Método que trata o selecionar de um ítem do listview
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//List lstPos = e.getSource();
		
		//PositionBO posBO = new PositionBO();
		
		if(e.getValueIsAdjusting() == false) {
			System.out.println("ValueChanged listener");
			
			int idx = e.getLastIndex();
			String name = vctPosition.get(idx);
			
			PositionVO posVO = new PositionVO();
			posVO.setName(name);
			
			PositionBO posBO = new PositionBO();
			List<PositionVO> lstVO = posBO.select(posVO);
			
			if(lstVO.size() == 1) {
				posVO = lstVO.get(0);
				setPanelForEdit(posVO);
			}
		}
	}
	
//########################### Métodos Auxiliares ###########################
	
	/**
	 * Seta os valores da posição selecionada no painel de edição de posição
	 * @param posVO - Posição a ser editada
	 */
	private void setPanelForEdit(PositionVO posVO) {
		isEdition = true;
		idForEdition = posVO.getId();
		this.pnCrudPosition.getBtnExcluir().setVisible(true);
		
		this.pnCrudPosition.getTfNome().setText(posVO.getName());
		this.pnCrudPosition.setType(posVO.getType());
		this.pnCrudPosition.getSldArtc1().setValue(posVO.getPositionArtc1());
		this.pnCrudPosition.getSldArtc2().setValue(posVO.getPositionArtc2());
		this.pnCrudPosition.getSldArtc3().setValue(posVO.getPositionArtc3());
		this.pnCrudPosition.getSldArtc4().setValue(posVO.getPositionArtc4());
		this.pnCrudPosition.getSldArtc5().setValue(posVO.getPositionArtc5());
	}

	/**
	 * Carrega os dados do Painel CRUD Position e um Objeto PositionVO
	 * @return {@link PositionVO}
	 */
	private PositionVO getPosition() {
		PositionVO posVo = new PositionVO();
		posVo.setName(this.pnCrudPosition.getTfNome().getText());
		posVo.setType(this.pnCrudPosition.getType());
		posVo.setPositionArtc1(this.pnCrudPosition.getSldArtc1().getValue());
		posVo.setPositionArtc2(this.pnCrudPosition.getSldArtc2().getValue());
		posVo.setPositionArtc3(this.pnCrudPosition.getSldArtc3().getValue());
		posVo.setPositionArtc4(this.pnCrudPosition.getSldArtc4().getValue());
		posVo.setPositionArtc5(this.pnCrudPosition.getSldArtc5().getValue());
		return posVo;
	}
	
	/**
	 * Inicializa o painel de posições com todas as posições cadastradas no sistema
	 */
	private void initializeListPositions() {
		PositionBO posBO = new PositionBO();
		vctPosition = new Vector<String>();
			
		List<PositionVO> lstPosVO = posBO.select(null);
		
		for(int i = 0; i < lstPosVO.size(); i++) {
			vctPosition.add(lstPosVO.get(i).getName());
		}
		
		System.out.println("TAMANHO DO VETOR = " + vctPosition.size());
		
		this.pnCrudPosition.getLstPosition().setListData(vctPosition);
		this.pnCrudPosition.updateUI();
		System.out.println("Tamanho da lista = " + lstPosVO.size());
		
	}
	
	/**
	 * Limpa a área de edição de posição e seta sliders para posição central
	 */
	private void clearPositionPanel() {
		this.pnCrudPosition.getLstPosition().clearSelection();			
		this.pnCrudPosition.getBtnExcluir().setVisible(false);
		
		isEdition = false;
		idForEdition = -1;

		this.pnCrudPosition.getTfNome().setText("");
		this.pnCrudPosition.getSldArtc1().setValue(1000);
		this.pnCrudPosition.getSldArtc2().setValue(1000);
		this.pnCrudPosition.getSldArtc3().setValue(1000);
		this.pnCrudPosition.getSldArtc4().setValue(1000);
		this.pnCrudPosition.getSldArtc5().setValue(1000);		
	}
}
