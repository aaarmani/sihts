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
import armani.anderson.sihts.model.PositionDAO;
import armani.anderson.sihts.model.PositionVO;
import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.panelCrudPosition;
/**
 * Controller para o painel CRUD de Posição
 * @author armani
 * @version V00.01
 */
public class PositionCTRL implements ActionListener, ChangeListener, ListSelectionListener {
	private panelCrudPosition pnCrudPosition = null;
	private RoboticArm roboticArm = null;

	/**
	 * Método Construtor do Controller
	 *  - Inicializa o painel CRU Position
	 *  - Inicializa os listners para tratar os eventos do painel
	 * @param pnCrudPosition2 - Painel que será controlado
	 * @param roboticArm2 - Controle do braço mecânico
	 */
	public PositionCTRL(panelCrudPosition pnCrudPosition2, RoboticArm roboticArm2) {
		this.pnCrudPosition = pnCrudPosition2;
		this.roboticArm = roboticArm2;
		
		initializeListI();
		
		this.pnCrudPosition.getLstPosition().addListSelectionListener(this);
		this.pnCrudPosition.getSldArtc1().addChangeListener(this);
		this.pnCrudPosition.getSldArtc2().addChangeListener(this);
		this.pnCrudPosition.getSldArtc3().addChangeListener(this);
		this.pnCrudPosition.getSldArtc4().addChangeListener(this);
		this.pnCrudPosition.getSldArtc5().addChangeListener(this);
		
		this.pnCrudPosition.getBtnSalvar().addActionListener(this);
		this.pnCrudPosition.getBtnCancelar().addActionListener(this);
		this.pnCrudPosition.getBtnExcluir().addActionListener(this);
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
			if(posBO.insert(posVO) == true) {
				JOptionPane.showMessageDialog(null, "Posição inserida com sucesso!", "Insert", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				JOptionPane.showMessageDialog(null, "Erro ao inserir posição", "Insert", JOptionPane.ERROR_MESSAGE);
			}
		}
		//CANCELAR
		else if(objEvent == this.pnCrudPosition.getBtnCancelar()) {
			this.pnCrudPosition.getTfNome().setText("");
			this.pnCrudPosition.getSldArtc1().setValue(0);
			this.pnCrudPosition.getSldArtc2().setValue(0);
			this.pnCrudPosition.getSldArtc3().setValue(0);
			this.pnCrudPosition.getSldArtc4().setValue(0);
			this.pnCrudPosition.getSldArtc5().setValue(0);
		}
		//DELETAR
		else if(objEvent == this.pnCrudPosition.getBtnExcluir()) {
			
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
		System.out.println("ATUALIZANDO...");
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
		
		roboticArm.sendPosition(intArtc, intPos, 10);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		//List lstPos = e.getSource();
		
		//PositionBO posBO = new PositionBO();
		
		

	}
	
//########################### Métodos Auxiliares ###########################
	
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
	
	private void initializeListI() {
		PositionBO posBO = new PositionBO();
		Vector<String> vctPosition = new Vector<String>();
		
		List<PositionVO> lstPosVO = posBO.select(null);
		
		for(int i = 0; i < lstPosVO.size(); i++) {
			vctPosition.add(lstPosVO.get(i).getName());
		}
		
		System.out.println("TAMANHO DO VETOR = " + vctPosition.size());
		
		this.pnCrudPosition.getLstPosition().setListData(vctPosition);
		this.pnCrudPosition.updateUI();
		System.out.println("Tamanho da lista = " + lstPosVO.size());
		
	}
}
