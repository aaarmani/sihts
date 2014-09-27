package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JPanel;

import armani.anderson.sihts.model.PositionVO;
import armani.anderson.sihts.view.ActPositionView;

public class ActPositionCTRL implements ActionListener {
	private ActPositionView actPosView = null;
	private JPanel pnActPositions = null;
	private Map<Integer, PositionVO> mapActionPositions = null;
	private Map<Integer, ActPositionView> mapActPosViews = null;
	
	/**
	 * 
	 * @param actPosView
	 * @param pnActionPositions
	 * @param mapActionPositions 
	 * @param mapActPosViews 
	 */
	public ActPositionCTRL(ActPositionView actPosView, JPanel pnActionPositions, Map<Integer, PositionVO> mapActionPositions, Map<Integer, ActPositionView> mapActPosViews) {
		this.actPosView = actPosView;
		this.pnActPositions = pnActionPositions;
		this.mapActionPositions = mapActionPositions;
		this.mapActPosViews  = mapActPosViews;
		
		this.actPosView.getBtnUp().addActionListener(this);
		this.actPosView.getBtnDown().addActionListener(this);
		this.actPosView.getBtnDelete().addActionListener(this);
		addPositionInPanel();
		refreshPanel();
	}

	/**
	 * 
	 */
	private void addPositionInPanel() {
		pnActPositions.add(actPosView);
		pnActPositions.updateUI();
	}
	
	/**
	 * 
	 */
	public void refreshPanel() {
		pnActPositions.removeAll();
		
		for(int i = 0; i < mapActPosViews.size(); i++) {
			ActPositionView actPosView = mapActPosViews.get(i);
		
			if(actPosView == null)
				System.out.println("ACT POS VIEW  = NULL size = " + mapActPosViews.size());
			else
				pnActPositions.add(actPosView);
		}
		pnActPositions.updateUI();
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == this.actPosView.getBtnUp()) {
			int index = actPosView.getIndex();
			System.out.println("BTN UP " + index);
			
			if(index > 0) {
				PositionVO posAnt = mapActionPositions.get(index -1);
				mapActionPositions.put(index - 1, mapActionPositions.get(index));
				mapActionPositions.put(index, posAnt);
				
				ActPositionView actPosView1 = mapActPosViews.get(index -1);
				ActPositionView actPosView2 = mapActPosViews.get(index);
				
				actPosView1.setIndex(index);
				actPosView2.setIndex(index -1);
				
				mapActPosViews.put(index, actPosView1);
				mapActPosViews.put(index -1, actPosView2);
			}
			refreshPanel();
		}
		else if(obj == this.actPosView.getBtnDown()) {		
			int index = actPosView.getIndex();
			System.out.println("BTN DOWN " + index);
			
			if(index < mapActionPositions.size() -1) {
				PositionVO posAnt = mapActionPositions.get(index + 1);
				mapActionPositions.put(index + 1, mapActionPositions.get(index));
				mapActionPositions.put(index, posAnt);
				
				
				ActPositionView actPosView1 = mapActPosViews.get(index);
				ActPositionView actPosView2 = mapActPosViews.get(index + 1);
				
				//inverte os índices
				actPosView1.setIndex(index + 1);
				actPosView2.setIndex(index);
				
				mapActPosViews.put(index + 1, actPosView1);
				mapActPosViews.put(index, actPosView2);
			}
			refreshPanel();
		}
		else if(obj == this.actPosView.getBtnDelete()) {
			posDelete(actPosView.getIndex());
			refreshPanel();
		}	
	}

	private void posDelete(int index) {
		
		if(mapActionPositions.size() <= 1) {
			mapActionPositions.clear();
			mapActPosViews.clear();
		}
		else {
			ActPositionView actPosView;
			
			for(int i = index; i < mapActionPositions.size() - 1; i++) {
				mapActionPositions.put(i, mapActionPositions.get(i + 1));
				
				actPosView = mapActPosViews.get(i + 1);
				actPosView.setIndex(i);
				mapActPosViews.put(i, actPosView);
			}
			
			mapActionPositions.remove(mapActionPositions.size() -1);
			mapActPosViews.remove(mapActPosViews.size() -1);
		}
	}

}
