package armani.anderson.sihts.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationVO {
	private Properties properties;
	final char SEPARATOR = File.separatorChar;
	final String DIR = System.getProperty("user.dir") + SEPARATOR;  
	
    private String propertiesName = DIR+"properties"+SEPARATOR+"config.properties";  
  
    public ConfigurationVO() throws FileNotFoundException{
    	System.out.println(propertiesName);
            properties = new Properties();
            
            File fileConfig = new File(propertiesName);
			
			if (fileConfig.exists() == false) {
				try {
					fileConfig.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
            FileInputStream file = new FileInputStream(propertiesName);

            try{  
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
  
    public String getValor(String chave){  
            return (String)properties.getProperty(chave);  
    }
    
    public String getPath() {
    	return this.propertiesName;
    }
}
