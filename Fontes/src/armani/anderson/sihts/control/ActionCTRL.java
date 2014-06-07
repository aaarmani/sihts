package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import armani.anderson.sihts.model.ActionBO;
import armani.anderson.sihts.model.ActionListBO;
import armani.anderson.sihts.model.ActionListVO;
import armani.anderson.sihts.model.ActionVO;
import armani.anderson.sihts.model.PositionBO;
import armani.anderson.sihts.model.PositionVO;
import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.ActPositionView;
import armani.anderson.sihts.view.ActionView;

/**
 * Classe que controla a tela de Ações 
 * @author armani
 * @version V00.01
 */
public class ActionCTRL implements ActionListener, ListSelectionListener{
	private ActionView actView = null;
	RoboticArm roboticArm = null;
	
	JList<String> jlstAction = null;
	JList<String> jlstPosition = null;
	
	Vector<String> vctPosition = null;
	Vector<String> vctAction = null;
	
	Map<Integer, ActPositionView> mapViewPositions= null;
	Map<String, PositionVO> mapPosition = null;
	Map<Integer, PositionVO> mapActionPositions = null;
	ActionVO currentActionVO = null;
	
	/**
	 * <p>Método construtor<br>
	 * Inicializa os listners dos botões
	 * Inicializa as listas de Ações e Posições
	 * Inicializa os botões Excluir e Simular invisíveis
	 * @param actView - Tela de Ações
	 * @param roboticArm - Interface com o braço robótico já inicializada
	 */
	public ActionCTRL(ActionView actView, RoboticArm roboticArm) {
		this.actView = actView;
		this.roboticArm = roboticArm;
		
		mapPosition = new HashMap<String, PositionVO>();
		mapActionPositions = new HashMap<Integer, PositionVO>();
		
		jlstAction = this.actView.getLstAction();
		jlstPosition = this.actView.getLstPosition();
		
		lstPositionInitialize();
		lstActionInitialize();
		
		//Initialize buttons
		this.actView.getBtnDelete().setVisible(false);
		this.actView.getBtnPlay().setVisible(false);
		
		//Listners buttons
		this.actView.getBtnAddPosition().addActionListener(this);
		this.actView.getBtnCancel().addActionListener(this);
		this.actView.getBtnDelete().addActionListener(this);
		this.actView.getBtnPlay().addActionListener(this);
		this.actView.getBtnSave().addActionListener(this);
		
		//Listners lists
		this.actView.getLstAction().addListSelectionListener(this);
		//this.actView.getLstPosition().addListSelectionListener(this);
	}
	
	/**
	 * Método que inicializa a lista de posições já cadastradas
	 */
	private void lstPositionInitialize() {
		PositionBO posBO = new PositionBO();
		vctPosition = new Vector<String>();
		
		List<PositionVO> lstPos = posBO.select(null);
		if(lstPos != null) {
			for(int i = 0; i < lstPos.size(); i++) {
				vctPosition.add(lstPos.get(i).getName());
				mapPosition.put(lstPos.get(i).getName(), lstPos.get(i));
			}
			jlstPosition.setListData(vctPosition);
		}
	}

	/**
	 * Método que inicializa as Ações já cadastradas
	 */
	private void lstActionInitialize() {
		ActionBO actBO = new ActionBO();
		vctAction = new Vector<String>();
		
		List<ActionVO> lstAct = actBO.select(null);
		if(lstAct != null) {
			for(int i = 0; i < lstAct.size(); i++) {
				vctAction.add(lstAct.get(i).getName());
			}
			System.out.println("SIZE VECT = " + vctAction.size());
			jlstAction.setListData(vctAction);
		}
	}

	/**
	 * Método que trata os eventos de botões
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object objBtn = e.getSource();
		
		//ADD - Adiciona posição na lista
		if(objBtn == this.actView.getBtnAddPosition()) {
			String posName = this.actView.getLstPosition().getSelectedValue();
			
			if(posName != null) {
				System.out.println("Position Selected = " + posName);
				
				PositionVO posVO = mapPosition.get(posName);
				
				Integer index = mapActionPositions.size();
				mapActionPositions.put(index, posVO);
				
				System.out.println("INDEX = " + index + " pos =  " + posVO.getName());
				
				ActPositionView actPosView = new ActPositionView(index);
				//ActPositionView actPosView = new ActPositionView();
				actPosView.getLblPositionName().setText(posVO.getName());
				
				ActPositionCTRL actPosCTRL = new ActPositionCTRL(actPosView, this.actView.getPnActionPositions());
				
				//adicionar no map e mandar printar o map pois pode alterar posição
				this.actView.getPnActionPositions().add(actPosView);
				this.actView.updateUI();
				
				for(int i = 0; i < mapActionPositions.size(); i++) {
					System.out.println("POS ACT = " + mapActionPositions.get(i).getName());
				}
				
				//insertIntoActionPositionsView();
				
				
				//ativa os botões delete e play
				this.actView.getBtnPlay().setVisible(true);
				//this.actView.getBtnDelete().setVisible(true);
			}
		}
		//Limpa o cadastro de Ação
		else if(objBtn == this.actView.getBtnCancel()) {
			clearActionView();
		}
		//Deleta Ação selecionada
		else if(objBtn == this.actView.getBtnDelete()) {
			//deletar realmente??
			
			
			
			
			//se sim 
			clearActionView();
			this.actView.getBtnPlay().setVisible(false);
			this.actView.getBtnDelete().setVisible(false);
			//mapActionPositions
		}
		//Executa a Ação selecionada
		else if(objBtn == this.actView.getBtnPlay()) {
			actionExecute();
		}
		//Salva a Ação em cadastro
		else if(objBtn == this.actView.getBtnSave()) {
			ActionVO actVO = new ActionVO();
			actVO.setName(this.actView.getTxtName().getText());
			actVO.setDescription(this.actView.getTxtaDescription().getText());
			
			if(!actVO.getName().isEmpty()) {
				ActionBO actBo = new ActionBO();
				
				if(actBo.insert(actVO) == true) {
					
					ActionListBO actLstBO = new ActionListBO();
					for(int i = 0; i < mapActionPositions.size(); i++) {
						ActionListVO actLstVO = new ActionListVO(); 
						
						actLstVO.setIndex(i);
						actLstVO.setActionId((int) actVO.getId());
						actLstVO.setPositionId((int) mapActionPositions.get(i).getId());
						
						actLstBO.insert(actLstVO);
					}
					
					lstActionInitialize();
					clearActionView();
				}
			}
		}
	}

	/**
	 * Método que simula a posição selecionada/em cadastro
	 */
	private void actionExecute() {
		for(int i = 0; i < mapActionPositions.size(); i++) {
			System.out.println("POS ACT = " + mapActionPositions.get(i).getName());
			
			PositionVO posVo = mapActionPositions.get(i);
			
			roboticArm.sendPosition(Al5b.ARTC_BASE, posVo.getPositionArtc1(), 500);
			delay(300);
			roboticArm.sendPosition(Al5b.ARTC_OMBRO, posVo.getPositionArtc1(), 500);
			delay(300);
			roboticArm.sendPosition(Al5b.ARTC_COTOVELO, posVo.getPositionArtc1(), 500);
			delay(300);
			roboticArm.sendPosition(Al5b.ARTC_PULSO, posVo.getPositionArtc1(), 500);
			delay(300);
			roboticArm.sendPosition(Al5b.ARTC_PINCA, posVo.getPositionArtc1(), 500);
			delay(200);
		}
	}

	/**
	 * Método de tempo de espera
	 * @param time
	 */
	private void delay(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que limpa a área de visualização de ações
	 * 
	 *  Limpa os campos:
	 * 		- Nome
	 * 		- Descrição
	 * 		- Painel de Posições
	 * 		- Esconde botões de simulação e de 
	 * Limpa também o mapa de posições para a ação selecionada
	 */
	private void clearActionView() {
		mapActionPositions.clear();
		
		this.actView.getTxtName().setText(null);
		this.actView.getTxtaDescription().setText(null);
		this.actView.getPnActionPositions().removeAll();
		this.actView.updateUI();
		
		this.actView.getBtnPlay().setVisible(false);
		this.actView.getBtnDelete().setVisible(false);
	}

	/**
	 * Método que trata eventos das Listas
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object objLst = e.getSource();
		
		if(objLst == this.actView.getLstAction()) {
			if(e.getValueIsAdjusting() == false) {
				clearActionView();
				
				//select na ação e na lista de posições

				//seta dados na tela
				
				//atualiza dados na tela
			}
		}
		else if(objLst == this.actView.getLstPosition()) {
			System.out.println("POSITION!!!");
		}
	}

}
