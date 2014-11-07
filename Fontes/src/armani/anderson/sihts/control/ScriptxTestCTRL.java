package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JPanel;

import armani.anderson.sihts.model.TestVO;
import armani.anderson.sihts.view.ActPositionView;

public class ScriptxTestCTRL implements ActionListener {
	private ActPositionView actPosView = null;
	private JPanel pnTests = null;
	private Map<Integer, TestVO> mapTstxAct = null;
	private Map<Integer, ActPositionView> mapTestPns = null;
	
	public ScriptxTestCTRL(ActPositionView actPosView, JPanel pnTests, Map<Integer, TestVO> mapScptxTst, Map<Integer, ActPositionView> mapTestPns) {
		this.actPosView = actPosView;
		this.pnTests = pnTests;
		this.mapTstxAct = mapScptxTst;
		this.mapTestPns = mapTestPns;
		
		this.actPosView.getBtnUp().addActionListener(this);
		this.actPosView.getBtnDown().addActionListener(this);
		this.actPosView.getBtnDelete().addActionListener(this);
		
		addPositionInPanel();
		refreshPanel();
	}

	private void refreshPanel() {
		pnTests.removeAll();
		
		for(int i = 0; i < mapTestPns.size(); i++) {
			ActPositionView actPosView = mapTestPns.get(i);
		
			if(actPosView == null)
				System.out.println("ACT POS VIEW  = NULL size = " + mapTestPns.size());
			else
				pnTests.add(actPosView);
		}
		pnTests.updateUI();
	}

	private void addPositionInPanel() {
		pnTests.add(actPosView);
		pnTests.updateUI();
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
			TestVO actAnt = mapTstxAct.get(index -1);
			mapTstxAct.put(index - 1, mapTstxAct.get(index));
			mapTstxAct.put(index, actAnt);
			
			ActPositionView actPosView1 = mapTestPns.get(index -1);
			ActPositionView actPosView2 = mapTestPns.get(index);
			
			actPosView1.setIndex(index);
			actPosView2.setIndex(index -1);
			
			mapTestPns.put(index, actPosView1);
			mapTestPns.put(index -1, actPosView2);
		}
		refreshPanel();
	}

	private void btnDownClick() {
		int index = actPosView.getIndex();
		System.out.println("BTN DWN " + index);
		
		if(index < mapTestPns.size() -1) {
			TestVO actPost = mapTstxAct.get(index +1);
			mapTstxAct.put(index + 1, mapTstxAct.get(index));
			mapTstxAct.put(index, actPost);
			
			ActPositionView actPosView1 = mapTestPns.get(index +1);
			ActPositionView actPosView2 = mapTestPns.get(index);
			
			actPosView1.setIndex(index);
			actPosView2.setIndex(index +1);
			
			mapTestPns.put(index, actPosView1);
			mapTestPns.put(index +1, actPosView2);
		}
		refreshPanel();
	}

	private void btnDelClick(int index) {
		if(mapTstxAct.size() <= 1) {
			mapTstxAct.clear();
			mapTestPns.clear();
		}
		else {
			ActPositionView actPosView;
			
			for(int i = index; i < mapTstxAct.size() - 1; i++) {
				mapTstxAct.put(i, mapTstxAct.get(i + 1));
				
				actPosView = mapTestPns.get(i + 1);
				actPosView.setIndex(i);
				mapTestPns.put(i, actPosView);
			}
			
			mapTstxAct.remove(mapTstxAct.size() -1);
			mapTestPns.remove(mapTestPns.size() -1);
		}
		refreshPanel();
	}
}
