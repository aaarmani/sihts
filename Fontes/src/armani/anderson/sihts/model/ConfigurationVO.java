package armani.anderson.sihts.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class ConfigurationVO {
	private Properties properties;
	final char SEPARATOR = File.separatorChar;
	final String DIR = System.getProperty("user.dir") + SEPARATOR;  
	
    private String propertiesName = DIR+"properties"+SEPARATOR+"config.properties";  
  
    public ConfigurationVO() {
    	FileInputStream file = null;
    	
            properties = new Properties();
            
            File fileConfig = new File(propertiesName);
			
			if (fileConfig.exists() == false) {
				try {
					fileConfig.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
            try{
            	file = new FileInputStream(propertiesName);
            	properties.load(file);    
            } catch(IOException e)
            {
            	e.printStackTrace();
            } finally {
            	try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
    }  
  
    public String getValue(String key){  
            return (String)properties.getProperty(key);
    }
    
    public void setValue(String key, String value) {
    	properties.setProperty(key, value);
    }
    
    public String getPath() {
    	return this.propertiesName;
    }
    
    public void save() {
    	File fileConfig = new File(propertiesName);
    	
    	FileOutputStream file;
		try {
			file = new FileOutputStream(propertiesName);
			properties.store(file, null);
			
			JOptionPane.showMessageDialog(null, "Arquivo de configurações salvo!", null, JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}
