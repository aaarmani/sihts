package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JPanel;

import armani.anderson.sihts.model.ActionVO;
import armani.anderson.sihts.view.ActPositionView;

public class TestxActionCTRL implements ActionListener {
	private ActPositionView actPosView = null;
	private JPanel pnActions = null;
	private Map<Integer, ActionVO> mapTstxAct = null;
	private Map<Integer, ActPositionView> mapActPns = null;
	
	public TestxActionCTRL(ActPositionView actPosView, JPanel pnActions, Map<Integer, ActionVO> mapTstxAct, Map<Integer, ActPositionView> mapActPns) {
		this.actPosView = actPosView;
		this.pnActions = pnActions;
		this.mapTstxAct = mapTstxAct;
		this.mapActPns = mapActPns;
		
		this.actPosView.getBtnUp().addActionListener(this);
		this.actPosView.getBtnDown().addActionListener(this);
		this.actPosView.getBtnDelete().addActionListener(this);
		
		addPositionInPanel();
		refreshPanel();
	}

	private void refreshPanel() {
		pnActions.removeAll();
		
		for(int i = 0; i < mapActPns.size(); i++) {
			ActPositionView actPosView = mapActPns.get(i);
		
			if(actPosView == null)
				System.out.println("ACT POS VIEW  = NULL size = " + mapActPns.size());
			else
				pnActions.add(actPosView);
		}
		pnActions.updateUI();
	}

	private void addPositionInPanel() {
		pnActions.add(actPosView);
		pnActions.updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == this.actPosView.getBtnUp()) {
			btnUpClick();
		}
		else if(obj == this.actPosView.getBtnDown()) {
			btnDownClick();
		}
		else if(obj == this.actPosView.getBtnDelete()) {
			btnDelClick(this.actPosView.getIndex());
		}
	}

	private void btnUpClick() {
		int index = actPosView.getIndex();
		System.out.println("BTN UP " + index);
		
		if(index > 0) {
			ActionVO actAnt = mapTstxAct.get(index -1);
			mapTstxAct.put(index - 1, mapTstxAct.get(index));
			mapTstxAct.put(index, actAnt);
			
			ActPositionView actPosView1 = mapActPns.get(index -1);
			ActPositionView actPosView2 = mapActPns.get(index);
			
			actPosView1.setIndex(index);
			actPosView2.setIndex(index -1);
			
			mapActPns.put(index, actPosView1);
			mapActPns.put(index -1, actPosView2);
		}
		refreshPanel();
	}

	private void btnDownClick() {
		int index = actPosView.getIndex();
		System.out.println("BTN DWN " + index);
		
		if(index < mapActPns.size() -1) {
			ActionVO actPost = mapTstxAct.get(index +1);
			mapTstxAct.put(index + 1, mapTstxAct.get(index));
			mapTstxAct.put(index, actPost);
			
			ActPositionView actPosView1 = mapActPns.get(index +1);
			ActPositionView actPosView2 = mapActPns.get(index);
			
			actPosView1.setIndex(index);
			actPosView2.setIndex(index +1);
			
			mapActPns.put(index, actPosView1);
			mapActPns.put(index +1, actPosView2);
		}
		refreshPanel();
	}

	private void btnDelClick(int index) {
		if(mapTstxAct.size() <= 1) {
			mapTstxAct.clear();
			mapActPns.clear();
		}
		else {
			ActPositionView actPosView;
			
			for(int i = index; i < mapTstxAct.size() - 1; i++) {
				mapTstxAct.put(i, mapTstxAct.get(i + 1));
				
				actPosView = mapActPns.get(i + 1);
				actPosView.setIndex(i);
				mapActPns.put(i, actPosView);
			}
			
			mapTstxAct.remove(mapTstxAct.size() -1);
			mapActPns.remove(mapActPns.size() -1);
		}
		refreshPanel();
	}
}
