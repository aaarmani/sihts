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

import armani.anderson.sihts.model.PositionBO;
import armani.anderson.sihts.model.PositionVO;
import armani.anderson.sihts.model.ReturnBO;
import armani.anderson.sihts.model.ReturnVO;
import armani.anderson.sihts.view.ReturnView;

public class ReturnCTRL implements ActionListener, ListSelectionListener {
	private ReturnView returnView = null;
	private JList<String> lstReturn = null;
	
	private Vector<String> vctReturn = null;
	
	private Map<String, ReturnVO> mapReturn = null;
	
	private ReturnVO curRetVO = null;
	
	public ReturnCTRL(ReturnView returnView) {
		this.returnView = returnView;
		
		this.returnView.getBtnDelete().setVisible(false);
		
		lstReturn = this.returnView.getLstReturn();
		mapReturn = new HashMap<String, ReturnVO>();
		
		IinitializeReturnList();
		
		this.returnView.getBtnSave().addActionListener(this);
		this.returnView.getBtnCancel().addActionListener(this);
		this.returnView.getBtnDelete().addActionListener(this);
		this.returnView.getLstReturn().addListSelectionListener(this);
	}

	//############### Actions ###############
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if (obj == this.returnView.getBtnSave()) {
			saveClick();
		}
		else if (obj == this.returnView.getBtnCancel()) {
			cancelClick();
		} 
		else if (obj == this.returnView.getBtnDelete()) {
			deleteClick();
		}
		else {
			return;
		}
	}

	private void saveClick() {
		ReturnVO retVo = new ReturnVO();
		
		retVo.setName(this.returnView.getTxtName().getText());
		retVo.setDescription(this.returnView.getTxtDescription().getText());
		retVo.setTimeout(Integer.valueOf(this.returnView.getTxtTimeout().getText()));
		retVo.setText(this.returnView.getTxtReturn().getText());
		
		if(retVo.getName().length() >= 0) {
			ReturnBO retBO = new ReturnBO();
			
			if( retBO.insert(retVo) == true)
			{
				clearFields();
				IinitializeReturnList();
			}
		}
	}

	private void cancelClick() {
		clearFields();
	}

	private void deleteClick() {
		ReturnBO retBO = new ReturnBO();
		
		if(retBO.delete(curRetVO) > 0) {
			clearFields();
			IinitializeReturnList();	
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting() == false) {
			int lstIdx = lstReturn.getSelectedIndex();
			if(lstIdx >= 0) {
				ReturnVO retVO = mapReturn.get(vctReturn.get(lstIdx));
				curRetVO = retVO;
				this.returnView.getBtnDelete().setVisible(true);
				
				this.returnView.getTxtName().setText(retVO.getName());
				this.returnView.getTxtDescription().setText(retVO.getDescription());
				this.returnView.getTxtTimeout().setText(String.valueOf(retVO.getTimeout()));
				this.returnView.getTxtReturn().setText(retVO.getText());
			}
		}
	}
	
	//############### Metodos ###############
	private void clearFields() {
		this.returnView.getTxtName().setText(null);
		this.returnView.getTxtDescription().setText(null);
		this.returnView.getTxtTimeout().setText(null);
		this.returnView.getTxtReturn().setText(null);
		
		curRetVO = null;
		this.returnView.getBtnDelete().setVisible(false);
		lstReturn.clearSelection();
	}
	
	private void IinitializeReturnList() {
		ReturnBO retBO = new ReturnBO();
		vctReturn = new Vector<String>();
		
		List<ReturnVO> lstPos = retBO.select(null);
		if (lstPos != null) {
			for(int i = 0; i < lstPos.size(); i++) {
				vctReturn.add(lstPos.get(i).getName());
				mapReturn.put(lstPos.get(i).getName(), lstPos.get(i));
			}
			lstReturn.setListData(vctReturn);
		}
	}
	
}