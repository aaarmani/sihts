
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame{

	public Main(){
		initUI();
	}
	
	private void initUI() {
		
		JPanel tela = new TelaTesteSP1_2();
		tela.setVisible(true);
		
		add(tela);
		
		setTitle("Interface Serial AL5B");
		setSize(480, 250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Main mainFrame = new Main();
				mainFrame.setVisible(true);	
			}
		});
			

	}
}
