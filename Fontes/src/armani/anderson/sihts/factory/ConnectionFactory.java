package armani.anderson.sihts.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private Connection conn = null;
	 //private String driver = "org.postgresql.Driver";  
	 private String user = "armani";  
	 private String password = "DB_SIHTS";  
	 private String url = "jdbc:postgresql://localhost:5432/DB_SIHTS";  
	
	
	public Connection getConnection() {
        try {
        	if(conn == null) {
        		return DriverManager.getConnection(url, user, password);	
        	}
        	else {
        		return conn;
        	}
        }
        catch(SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }
}
