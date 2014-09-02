package armani.anderson.sihts.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.UIManager;

public class MainView extends JFrame {
	private int frmWidth;
	private int frmHeight;
	private JPanel contentPane;
	
	private JPanel pnCenter;
	private JPanel pnTop;
	private JPanel pnDown;
	
	private JMenuItem mntmAction;
	private JMenuItem mntmPosition;
	private JMenuItem mntmObjeto;
	private JMenuItem mntmConfig;
	private JMenuItem mntmSair;
	
	private JMenuItem mntmNewTst;
	private JMenuItem mntmExecutarTst;
	private JMenuItem mntmEditarTst;
	
	private JMenuItem mntmNewScpt;
	private JMenuItem mntmEditarScpt;
	private JMenuItem mntmExecutarScpt;
	
	
	public JMenuItem getMntmNewTst() {
		return mntmNewTst;
	}

	public void setMntmNewTst(JMenuItem mntmNewTst) {
		this.mntmNewTst = mntmNewTst;
	}

	public JMenuItem getMntmExecutarTst() {
		return mntmExecutarTst;
	}

	public void setMntmExecutarTst(JMenuItem mntmExecutarTst) {
		this.mntmExecutarTst = mntmExecutarTst;
	}

	public JMenuItem getMntmEditarTst() {
		return mntmEditarTst;
	}

	public void setMntmEditarTst(JMenuItem mntmEditarTst) {
		this.mntmEditarTst = mntmEditarTst;
	}

	public JMenuItem getMntmNewScpt() {
		return mntmNewScpt;
	}

	public void setMntmNewScpt(JMenuItem mntmNewScpt) {
		this.mntmNewScpt = mntmNewScpt;
	}

	public JMenuItem getMntmEditarScpt() {
		return mntmEditarScpt;
	}

	public void setMntmEditarScpt(JMenuItem mntmEditarScpt) {
		this.mntmEditarScpt = mntmEditarScpt;
	}

	public JMenuItem getMntmExecutarScpt() {
		return mntmExecutarScpt;
	}

	public void setMntmExecutarScpt(JMenuItem mntmExecutarScpt) {
		this.mntmExecutarScpt = mntmExecutarScpt;
	}

	public JMenuItem getMntmSair() {
		return mntmSair;
	}

	public void setMntmSair(JMenuItem mntmSair) {
		this.mntmSair = mntmSair;
	}

	public JMenuItem getMntmConfig() {
		return mntmConfig;
	}

	public void setMntmConfig(JMenuItem mntmConfig) {
		this.mntmConfig = mntmConfig;
	}

	public JPanel getPnCenter() {
		return pnCenter;
	}

	public void setPnCenter(JPanel pnCenter) {
		this.pnCenter = pnCenter;
	}

	public JPanel getPnTop() {
		return pnTop;
	}

	public void setPnTop(JPanel pnTop) {
		this.pnTop = pnTop;
	}

	public JPanel getPnDown() {
		return pnDown;
	}

	public void setPnDown(JPanel pnDown) {
		this.pnDown = pnDown;
	}

	public JMenuItem getMntmObjeto() {
		return mntmObjeto;
	}

	public void setMntmObjeto(JMenuItem mntmObjeto) {
		this.mntmObjeto = mntmObjeto;
	}

	public JMenuItem getMntmPosition() {
		return mntmPosition;
	}

	public void setMntmPosition(JMenuItem mntmPosition) {
		this.mntmPosition = mntmPosition;
	}
	
	public JMenuItem getMntmAction() {
		return mntmAction;
	}

	public void setMntmAction(JMenuItem mntmAction) {
		this.mntmAction = mntmAction;
	}

	/**
	 * Create the frame.
	 */
	public MainView(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFrameSize();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		mntmConfig = new JMenuItem("Configuração");
		mnArquivo.add(mntmConfig);
		
		mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
//TEST		
		JMenu mnTeste = new JMenu("Teste");
		menuBar.add(mnTeste);
		
		mntmNewTst = new JMenuItem("Novo");
		mnTeste.add(mntmNewTst);

		mntmEditarTst = new JMenuItem("Editar");
		mnTeste.add(mntmEditarTst);
		
		mntmExecutarTst = new JMenuItem("Executar");
		mnTeste.add(mntmExecutarTst);
//SCRIPT		
		JMenu mnScript = new JMenu("Script");
		menuBar.add(mnScript);
		
		mntmNewScpt = new JMenuItem("Novo");
		mnScript.add(mntmNewScpt);
		
		mntmEditarScpt = new JMenuItem("Editar");
		mnScript.add(mntmEditarScpt);
		
		mntmExecutarScpt = new JMenuItem("Executar");
		mnScript.add(mntmExecutarScpt);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		mntmAction = new JMenuItem("Ação");
		mnCadastro.add(mntmAction);
		
		mntmPosition = new JMenuItem("Posição");
		mnCadastro.add(mntmPosition);
		
		mntmObjeto = new JMenuItem("Objeto");
		mnCadastro.add(mntmObjeto);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		pnTop = new JPanel();
		pnTop.setBackground(UIManager.getColor("Menu.background"));
		//FlowLayout flowLayout = (FlowLayout) pnTop.getLayout();
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
		pnTop.setLayout(flowLayout);
		
		pnCenter = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnCenter.getLayout();
		flowLayout_1.setAlignOnBaseline(true);
		
		pnDown = new JPanel();
		pnDown.setBackground(UIManager.getColor("Menu.background"));

		
		JLabel lblIcon1 = new JLabel("");
		lblIcon1.setIcon(new ImageIcon("/Users/armani/Documents/Senac/TCC/TCC/GitHub/Fontes/images/icon/home.png"));
		pnTop.add(lblIcon1);
		
		JLabel lblIcon2 = new JLabel("");
		lblIcon2.setIcon(new ImageIcon("/Users/armani/Documents/Senac/TCC/TCC/GitHub/Fontes/images/icon/arm.png"));
		pnTop.add(lblIcon2);
		
		JLabel lblIcon3 = new JLabel("");
		lblIcon3.setIcon(new ImageIcon("/Users/armani/Documents/Senac/TCC/TCC/GitHub/Fontes/images/icon/report.png"));
		pnTop.add(lblIcon3);
		
		JLabel lblIcon4 = new JLabel("");
		lblIcon4.setIcon(new ImageIcon("/Users/armani/Documents/Senac/TCC/TCC/GitHub/Fontes/images/icon/calendar.png"));
		pnTop.add(lblIcon4);
		
		JLabel lblIcon5 = new JLabel("");
		lblIcon5.setIcon(new ImageIcon("/Users/armani/Documents/Senac/TCC/TCC/GitHub/Fontes/images/icon/settings.png"));
		pnTop.add(lblIcon5);		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(pnDown, GroupLayout.DEFAULT_SIZE, 1270, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(pnCenter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(6)
								.addComponent(pnTop, GroupLayout.DEFAULT_SIZE, 1254, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(pnTop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(pnCenter, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnDown, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		contentPane.setLayout(gl_contentPane);
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
