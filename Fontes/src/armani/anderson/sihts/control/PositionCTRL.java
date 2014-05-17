package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import armani.anderson.sihts.model.PositionDAO;
import armani.anderson.sihts.model.PositionVO;
import armani.anderson.sihts.serial.Al5b;
import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.panelCrudPosition;

public class PositionCTRL implements ActionListener, ChangeListener {
	private panelCrudPosition pnCrudPosition = null;
	private RoboticArm roboticArm = null;

	public PositionCTRL(panelCrudPosition pnCrudPosition2, RoboticArm roboticArm2) {
		this.pnCrudPosition = pnCrudPosition2;
		this.roboticArm = roboticArm2;
		
		this.pnCrudPosition.getSldArtc1().addChangeListener(this);
		this.pnCrudPosition.getSldArtc2().addChangeListener(this);
		this.pnCrudPosition.getSldArtc3().addChangeListener(this);
		this.pnCrudPosition.getSldArtc4().addChangeListener(this);
		this.pnCrudPosition.getSldArtc5().addChangeListener(this);
		
		this.pnCrudPosition.getBtnSalvar().addActionListener(this);
		this.pnCrudPosition.getBtnCancelar().addActionListener(this);
		this.pnCrudPosition.getBtnExcluir().addActionListener(this);
	}
	
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object objEvent = e.getSource();
		if(objEvent == this.pnCrudPosition.getBtnSalvar()) {
			PositionVO posVO = getPosition();
			PositionDAO posDAO = new PositionDAO();
			
			posDAO.insert(posVO);
		}
		else if(objEvent == this.pnCrudPosition.getBtnCancelar()) {
			this.pnCrudPosition.getTfNome().setText("");
			this.pnCrudPosition.getSldArtc1().setValue(0);
			this.pnCrudPosition.getSldArtc2().setValue(0);
			this.pnCrudPosition.getSldArtc3().setValue(0);
			this.pnCrudPosition.getSldArtc4().setValue(0);
			this.pnCrudPosition.getSldArtc5().setValue(0);
		}
		else if(objEvent == this.pnCrudPosition.getBtnExcluir()) {
			
		}
	}

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
}
