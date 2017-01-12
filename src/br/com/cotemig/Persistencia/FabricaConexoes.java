
package br.com.cotemig.Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexoes {

	private static Connection con = null;
	
	
	
	public static Connection Conexao(){
	

		try {
			if(con == null || con.isClosed()){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@ENDERECO_BD", "USER_BD", "SENHA_USER_BD");
			}
	} catch (SQLException | ClassNotFoundException e) {

		e.printStackTrace();
		
}
		return con;
}



}
