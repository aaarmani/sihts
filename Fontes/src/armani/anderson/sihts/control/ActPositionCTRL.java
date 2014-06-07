package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import armani.anderson.sihts.view.ActPositionView;

public class ActPositionCTRL implements ActionListener {
	ActPositionView actPosView = null;
	JPanel pnActPositions = null;
	
	/**
	 * 
	 * @param actPosView
	 * @param pnActionPositions
	 */
	public ActPositionCTRL(ActPositionView actPosView, JPanel pnActionPositions) {
		this.actPosView = actPosView;
		this.pnActPositions = pnActionPositions;
		
		this.actPosView.getBtnUp().addActionListener(this);
		this.actPosView.getBtnDown().addActionListener(this);
	}

	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == this.actPosView.getBtnUp()) {
			System.out.println("BTN UP " + actPosView.getIndex());
		}
		else if(obj == this.actPosView.getBtnDown()) {
			System.out.println("BTN DOWN " + actPosView.getIndex());
		}
		else if(obj == this.actPosView.getBtnDelete()) {
			System.out.println("BTN DELETE " + actPosView.getIndex());
		}
		
	}

}
