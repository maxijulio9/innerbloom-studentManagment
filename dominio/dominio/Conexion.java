package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	private Connection conect = null;
	private String user="root";
	private String password="895623";
	private String bd= "InstitutoInnerbloom";
	private String ip="localhost";
	private String puerto="3306";
	private static final String controlador= "com.mysql.cj.jdbc.Driver";
	private String cadena="jdbc:mysql://"+ip+":"+puerto+"/"+bd;
	
	
	
	static {
		try {
			Class.forName(controlador);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	public Connection Conectar() {
		
		Connection conexion = null;
		try {
 			conexion = DriverManager.getConnection(cadena,user, password); 
//			System.out.println("Conexión OK");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("error de conexion");
			e.printStackTrace();
		}
		return conexion; 
	}
	
	
}
