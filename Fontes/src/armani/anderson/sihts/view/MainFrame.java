package armani.anderson.sihts.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
	private int frmWidth;
	private int frmHeight;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		
		setFrameSize();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private void setFrameSize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension scrnsize = toolkit.getScreenSize();  
		
		this.setMinimumSize(new Dimension(800,600));  
		
		this.frmHeight = scrnsize.height;
		this.frmWidth = scrnsize.width;
		
		setBounds(0, 0, frmWidth, frmHeight);
	}

}
