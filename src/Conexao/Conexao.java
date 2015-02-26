package Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
	
	
	public static Connection getConnection(){
		Connection con = null;
		
			try {
				Class.forName("org.postgresql.Driver");//Essa linha reforça o reconhecimento do driver
				
				
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/deposito","postgres","00974403377");
			} catch (SQLException e) {
				
				System.out.println("Erro no banco");
			} catch (ClassNotFoundException e) {
				System.out.println("Driver não encontrado!");
			}
		return con;
	}

}
