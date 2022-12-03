import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class ConectaBD {

	private String conexion;
	private String usuario;
	private String contrasena;
	private Connection miConexion;
	
	public ConectaBD(String conexion, String usuario, String contrasena) {
		this.conexion = conexion;
		this.usuario = usuario;
		this.contrasena = contrasena;
		try {
			this.miConexion = DriverManager.getConnection("jdbc:mysql://" + this.conexion,this.usuario,this.contrasena);
		}
		catch(Exception e) {
			System.out.println("No es posible conectarse");
			e.printStackTrace();
		}
	}
	
	public void imprimirTablaResultados(int idioma) {
		int mensajeTitulo = 11;
		System.out.println("\n\t\t"+ imprimirMensaje(idioma, mensajeTitulo) + " \n");
		try {
			//1- Crear objeto STATEMENT
			Statement miStatement =this.miConexion.createStatement();
			
			//2- Ejecutar la instruccion SQL
			ResultSet miResultSet = miStatement.executeQuery("select * from historial");
			
			//3- Recorrer el ResultSet
			int mensaje = 12;
			System.out.println(imprimirMensaje(idioma, mensaje)+"\n");
			while (miResultSet.next()) {
				System.out.println(miResultSet.getString("id_resultado") + "\t\t\t"+ miResultSet.getString("Jugador_nombre") + "\t "+ miResultSet.getString("fecha_comienzo") + "\t "+ miResultSet.getString("fecha_fin") + "\t "+ miResultSet.getString("ganador"));
			}
			
			miResultSet.close();
		}
		
		catch(Exception e) {
			System.out.println("No conecta imprimir tabla de resultados");
			e.printStackTrace();
		}
	}
	
	public void imprimirTablaResultadosJugador(int idioma, String jugador) {
		int mensajeTitulo = 11;
		System.out.println("\n\t\t"+ imprimirMensaje(idioma, mensajeTitulo) + " \n");
		try {
			//1- Crear objeto STATEMENT
			PreparedStatement miStatement = this.miConexion.prepareStatement("select * from historial where Jugador_nombre =?");
			
			//2- Establecer los parametros
			miStatement.setString(1, jugador);
			
			//3- Ejecutar la instruccion SQL
			ResultSet miResultSet = miStatement.executeQuery();
			
			
			//3- Recorrer el ResultSet
			int mensaje = 12;
			System.out.println(imprimirMensaje(idioma, mensaje)+"\n");
			while (miResultSet.next()) {
				System.out.println(miResultSet.getString("id_resultado") + "\t\t\t"+ miResultSet.getString("Jugador_nombre") + "\t "+ miResultSet.getString("fecha_comienzo") + "\t "+ miResultSet.getString("fecha_fin") + "\t "+ miResultSet.getString("ganador"));
			}
			
			miResultSet.close();
		}
		
		catch(Exception e) {
			System.out.println("No conecta imprimir tabla de resultados de jugador");
			e.printStackTrace();
		}
	}
	
	public void mostrarTablaIdioma() {
		System.out.println("Seleccione un idioma: ");
		try {
			//1- Crear conexion
			//Connection miConexion = DriverManager.getConnection("jdbc:mysql://" + this.conexion,this.usuario,this.contrasena);
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
			System.out.println("No conecta");
			e.printStackTrace();
		}
	}
	
	public void cargarResultados( LocalDateTime inicioPartida,  LocalDateTime finPartida, String nombre, String ganador) {
		try {
			
			//1- Crear objeto STATEMENT
			Statement miStatement = this.miConexion.createStatement();
			
			//2- instruccion SQL
			String sql = "insert into historial (fecha_fin, fecha_comienzo, jugador_nombre, ganador) values ('" + finPartida + "','" + inicioPartida + "','" + nombre + "','"+ ganador +"')";
			
			
			//3- Ejecutar la instruccion SQL
			miStatement.executeUpdate(sql);
			
		}
		
		catch(Exception e) {
			System.out.println("No conecta, al cargar resultados");
			e.printStackTrace();
		}
	}

	
	public String imprimirMensaje(int idioma, int mensaje) {
		String mensajeAImprimir ="";
		try {
			
			//1- Crear objeto STATEMENT
			PreparedStatement miStatement = this.miConexion.prepareStatement("select * from mensajeidioma where id_idioma =? and id_mensaje =?");
			
			//2- Establecer los parametros
			miStatement.setInt(1, idioma);
			miStatement.setInt(2, mensaje);
			
			
			//3- Ejecutar la instruccion SQL
			ResultSet miResultSet = miStatement.executeQuery();
			
			//4- Imprimir mensaje
			while (miResultSet.next()) {
				 mensajeAImprimir += miResultSet.getString("descripcion");
			}
			
			miResultSet.close();
		}
		
		catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}
		
		return mensajeAImprimir;
	}

}
