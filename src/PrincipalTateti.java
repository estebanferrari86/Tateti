import java.util.Scanner;
import java.time.LocalDateTime;




public class PrincipalTateti 
{

	public static void main(String[] args) 
	{

		Tateti t1=new Tateti();
		Scanner lector = new Scanner(System.in);
		ConectaBD bd = new ConectaBD("localhost:3306/tateti?serverTimezone=UTC","root","admin");
		int idioma = seleccionarIdioma(bd, lector);
		imprimirBienvenida(bd,idioma);
		t1.mostrarTableroMuestra();
		String nombre = solicitarNombre(bd, idioma, lector);
		int opcion = menu(bd, idioma, lector);
		while (opcion != 5) {
			switch (opcion) {
				case 1:
					idioma = seleccionarIdioma(bd, lector);
					break;
				case 2:
					t1.limpiar();
					Jugador j1=new Jugador("X",nombre,t1,idioma,bd);
					Jugador j2=new Jugador("O","CPU",t1);;
					LocalDateTime inicioPartida = LocalDateTime.now();
					LocalDateTime finPartida = null;
					mientrasJuego(j1, j2 , t1, lector, bd, idioma, inicioPartida);
					cargarResultadoPartida(j1, t1, inicioPartida, finPartida, nombre, bd, idioma);
					break;
				case 3:
					bd.imprimirTablaResultados(idioma);
					break;
				case 4:
					bd.imprimirTablaResultadosJugador(idioma, nombre);
					break;
				case 5:
					break;
			}
			opcion = menu(bd, idioma, lector);
		}
		lector.close();
	}
				
	private static void mientrasJuego(Jugador j1,Jugador j2, Tateti t1, Scanner lector, ConectaBD bd, int idioma, LocalDateTime inicioPartida) {
		boolean gan=false;
		LocalDateTime finPartida = null;
		t1.generarTablero();
		t1.mostrarTablero();
		while (gan == false && j2.getJugada() < 9) {
			j1.jugar();
			//t1.mostrarTablero();
			if(!(gan=t1.hayTateti())) 
			{
				j2.jugarMaquina();
				j2.aumentarJugada();
				t1.mostrarTablero();
				gan=t1.hayTateti();
			}

		}
		
		if(gan==true)
		{
			System.out.println(bd.imprimirMensaje(idioma, 9) + " " +t1.fichaGan);	
			cargarResultadoPartida(j1, t1, inicioPartida, finPartida, j1.nombre, bd, idioma);	
		}

	}
	
	private static void imprimirBienvenida(ConectaBD bd, int idioma) {
		int mensaje1 = 1;
		int mensaje2 = 2;
		int mensaje3 = 3;
		System.out.println(bd.imprimirMensaje(idioma, mensaje1));
		System.out.println(bd.imprimirMensaje(idioma, mensaje2));
		System.out.println(bd.imprimirMensaje(idioma, mensaje3));
	}
	
	private static String solicitarNombre(ConectaBD bd, int idioma, Scanner lector) {
		int mensaje = 7;
		System.out.println(bd.imprimirMensaje(idioma, mensaje));
		String nombre = lector.nextLine();
		return nombre;
	}
	
	private static void cargarResultadoPartida(Jugador jugador , Tateti t1,  LocalDateTime inicioPartida,  LocalDateTime finPartida, String nombre, ConectaBD bd, int idioma ) {
		finPartida = LocalDateTime.now();
		if (t1.hayTateti()) {
			int mensaje = 6;
			String ganador = "";
			if (t1.fichaGan == "X") {
				ganador = nombre;
			} else {
				ganador = "CPU";
			}
			System.out.println(bd.imprimirMensaje(idioma, mensaje) + " " + ganador);
			bd.cargarResultados(inicioPartida, finPartida, nombre, ganador);
		} else {
			int mensaje = 8;
			System.out.println(bd.imprimirMensaje(idioma, mensaje));
			bd.cargarResultados(inicioPartida, finPartida, nombre, "Empate");
		}
	}
	
	private static int seleccionarIdioma(ConectaBD bd, Scanner lector) {
		bd.mostrarTablaIdioma();
		int valor = 0;
		int mensaje = 0;
		do {
			System.out.println();
			for(int i = 1; i <=4; i++) {
				System.out.println(i+ "- " +bd.imprimirMensaje(i, mensaje));
			}
			valor = Integer.parseInt(lector.nextLine());
		} while (!(valor >= 1 && valor <= 4));
		return valor;
	}
	
	private static int menu(ConectaBD bd, int idioma, Scanner lector) {
		int opcion = 0;
		boolean opcionErronea = false;
		do {
			if (opcionErronea) {
				int mensaje10 = 10;
				System.out.println(bd.imprimirMensaje(idioma, mensaje10));
			} else {
				imprimirMenu(bd, idioma);
			}
			opcion = Integer.parseInt(lector.nextLine());
			opcionErronea = true;
		} while (!(opcion >= 1 && opcion <= 5));
		return opcion;
	}
	
	private static void imprimirMenu(ConectaBD bd, int idioma) {
		int mensaje = 12;
		System.out.println();
		for (int i =1; i <=5; i++) {
			System.out.println(i + "- " + bd.imprimirMensaje(idioma, mensaje +i));
		}
	}

}
