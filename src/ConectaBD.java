import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ConectaBD {

	private String conexion;
	private String usuario;
	private String contrasena;
	
	public ConectaBD(String conexion, String usuario, String contrasena) {
		this.conexion = conexion;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	public void mostrarTablaIdioma() {
		System.out.println("Seleccione un idioma: ");
		try {
			//1- Crear conexion
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://" + this.conexion,this.usuario,this.contrasena);
			//Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tateti?serverTimezone=UTC","root","admin");
			
			//2- Crear objeto STATEMENT
			Statement miStatement =miConexion.createStatement();
			//3- Ejecutar la instruccion SQL
			ResultSet miResultSet = miStatement.executeQuery("select * from Idioma");
			
			//4- Recorrer el ResultSet
			while (miResultSet.next()) {
				System.out.println(miResultSet.getString("id") + " "+ miResultSet.getString("descripcion"));

			}
			System.out.println("");
		}
		
		catch(Exception e) {
			System.out.println("No es posible conectarse");
			e.printStackTrace();
		}
	}

}
