package armani.anderson.sihts.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class pnCrudObject extends JPanel {
	panelCrudPosition pnCrudPos = null;
	JPanel pnListObj = null;
	
	/**
	 * Create the panel.
	 */
	public pnCrudObject() {
		this.setBounds(0, 0, 800, 400);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		pnListObj = new JPanel();
		pnListObj.setBounds(0, 0, 200, 400);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pnListObj.add(list);
		
		this.add(pnListObj);
		
		
		pnCrudPos = new panelCrudPosition('O');
		this.add(pnCrudPos);

	}

}
