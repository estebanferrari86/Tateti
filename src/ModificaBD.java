import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModificaBD {

	private String conexion;
	private String usuario;
	private String contrasena;

	public ModificaBD(String conexion, String usuario, String contrasena) {
		this.conexion = conexion;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	public void updateBD() {
		try {

			// 1- Crear conexión
			// Connection miConexion =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root",
			// "root");
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://" + this.conexion, this.usuario,
					this.contrasena);

			// 2- Crear objeto STATEMENT
			Statement miStatement = miConexion.createStatement();

			// 3 - Ejecutar instrucción SQL

			Date date = new Date();
			DateFormat hourdateFormat = new SimpleDateFormat("2022-08-11 22:00:00");

			String id_usuario = "4";
			String nombreUsuario = "Jugador" + id_usuario + "- Micaela";

			String instruccionSQL = "INSERT Usuario (id_usuario, nombre) values('"
					+ id_usuario + "','" + nombreUsuario +"')";

			// String instruccionSQL = "insert into clientes (CodCli, NomYApe, TipoCliente)
			// values (555, 'Ana López', 1)";
			// String instruccionSQL = "update clientes set NomYApe = 'Julieta Martínez'
			// where CodCli = 555";
			// String instruccionSQL = "delete from clientes where CodCli = 555";

			miStatement.executeUpdate(instruccionSQL);

			System.out.println("Registro insertado correctamente");
			
			//Consulto usuario ingresado y le doy la bienvenida
			ResultSet miResultSet = miStatement.executeQuery("select nombre FROM Usuario Where (id_usuario =" + id_usuario + ")");
	        
			//4- Recorrer el ResultSet
			while (miResultSet.next()) {
				System.out.println("Bienvenido " + miResultSet.getString("nombre"));

			}
			System.out.println("");
		

			// System.out.println("Registro modificado correctamente");
			// System.out.println("Registro eliminado correctamente");

		} catch (Exception e) {

			System.out.println("No conecta");
			e.printStackTrace();

		}

	}

}
