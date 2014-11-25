package armani.anderson.sihts.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import armani.anderson.sihts.model.ConfigurationVO;
import armani.anderson.sihts.serial.SerialComm;
import armani.anderson.sihts.view.ConfigurationView;

/**
 * Classe que implementa o controle para a tela de configurações
 * @author armani
 * @version V00.01
 */
public class ConfigurationCTRL implements ActionListener {
	ConfigurationView configView = null;
	ConfigurationVO confVO = null;
	JComboBox<String> cbSerial = null;
	
	/**
	 * Método contrutor do ConfigurationCTRL, responsável pela inicialização dos componentes e tratamento de ações da tela de configuração.
	 * @param ConfigView - Tela de configurações com getters e setters dos objetos
	 */
	public ConfigurationCTRL(ConfigurationView ConfigView) {
		this.configView = ConfigView;
		
		confVO = new ConfigurationVO();

		this.configView.getBtnSave().addActionListener(this);
		this.cbSerial = this.configView.getCbSerial();
		
		getConfig();
		initializeCBSerial();
	}

	/**
	 * Método que pega le o arquivo de configuração e seta os campos da tela
	 */
	private void getConfig() {
		//Banco de Dados
		this.configView.getTxtDBType().setText(confVO.getValue("prop.db.type"));
		this.configView.getTxtDBDataBase().setText(confVO.getValue("prop.db.database"));
		this.configView.getTxtDBUser().setText(confVO.getValue("prop.db.user"));
		this.configView.getTxtDBPassword().setText(confVO.getValue("prop.db.password"));
		this.configView.getTxtDBIp().setText(confVO.getValue("prop.db.ip"));
		this.configView.getTxtDBPort().setText(confVO.getValue("prop.db.port"));
		
		//Interface Com
		this.configView.getTxtInterfaceName().setText(confVO.getValue("prop.serial.interface.name"));
		this.configView.getTxtInterfaceBaud().setText(confVO.getValue("prop.serial.interface.baudrate"));
		this.configView.getTxtInterfaceParity().setText(confVO.getValue("prop.serial.interface.parity"));
		this.configView.getTxtInterfaceDataBits().setText(confVO.getValue("prop.serial.interface.databits"));
		this.configView.getTxtInterfaceStopBits().setText(confVO.getValue("prop.serial.interface.stopbits"));
		
		//Debug Com
		this.configView.getTxtDebugName().setText(confVO.getValue("prop.serial.debug.name"));
		this.configView.getTxtDebugBaud().setText(confVO.getValue("prop.serial.debug.baudrate"));
		this.configView.getTxtDebugParity().setText(confVO.getValue("prop.serial.debug.parity"));
		this.configView.getTxtDebugDataBits().setText(confVO.getValue("prop.serial.debug.databits"));
		this.configView.getTxtDebugStopBits().setText(confVO.getValue("prop.serial.debug.stopbits"));
	}

	/**
	 * Método que salva os campos da tela de configuração no arquivo de configuração
	 */
	private void saveConfig() {
		//Banco de Dados
		confVO.setValue("prop.db.type", this.configView.getTxtDBType().getText());
		confVO.setValue("prop.db.database", this.configView.getTxtDBDataBase().getText());
		confVO.setValue("prop.db.user", this.configView.getTxtDBUser().getText());
		confVO.setValue("prop.db.password", this.configView.getTxtDBPassword().getText());
		confVO.setValue("prop.db.ip", this.configView.getTxtDBIp().getText());
		confVO.setValue("prop.db.port", this.configView.getTxtDBPort().getText());
		
		//Interface Com
		confVO.setValue("prop.serial.interface.name", this.configView.getTxtInterfaceName().getText());
		confVO.setValue("prop.serial.interface.baudrate", this.configView.getTxtInterfaceBaud().getText());
		confVO.setValue("prop.serial.interface.parity", this.configView.getTxtInterfaceParity().getText());
		confVO.setValue("prop.serial.interface.databits", this.configView.getTxtInterfaceDataBits().getText());
		confVO.setValue("prop.serial.interface.stopbits", this.configView.getTxtInterfaceStopBits().getText());
		
		//Debug Com
		confVO.setValue("prop.serial.debug.name", this.configView.getTxtDebugName().getText());
		confVO.setValue("prop.serial.debug.baudrate", this.configView.getTxtDebugBaud().getText());
		confVO.setValue("prop.serial.debug.parity", this.configView.getTxtDebugParity().getText());
		confVO.setValue("prop.serial.debug.databits", this.configView.getTxtDebugDataBits().getText());
		confVO.setValue("prop.serial.debug.stopbits", this.configView.getTxtDebugStopBits().getText());
		
		confVO.save();
	}
	
	/**
	 * Método de tratamento do botão Salvar, responsável por guardar as configurações setadas
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.configView.getBtnSave()) {
			
			System.out.println("SAVE CONFIG");
			saveConfig();
		}
	}

	/**
	 * Método de Inicialização do ComboBox que contém as seriais disponíveis no PC
	 */
	private void initializeCBSerial() {
		List<String> lstSeriais = SerialComm.getListSerial();
		
		for(int i = 0; i < lstSeriais.size(); i++) {
			cbSerial.addItem(lstSeriais.get(i));
		}
	}
}
