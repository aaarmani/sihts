package armani.anderson.sihts.control;

import javax.swing.JPanel;

import armani.anderson.sihts.serial.RoboticArm;
import armani.anderson.sihts.view.ActionView;

public class ActionCTRL {
	private ActionView actView = null;
	RoboticArm roboticArm = null;
	
	public ActionCTRL(ActionView actView, RoboticArm roboticArm) {
		this.actView = actView;
		this.roboticArm = roboticArm;
	}

}
