import java.util.Scanner;

public class PrincipalTateti 
{

	public static void main(String[] args) 
	{

		System.out.println("Bienvenido al TATETI! Jugador X! ");
		System.out.println("Instrucciones vea el tablero para saber donde ingresar su ficha!");
		ConectaBD bd = new ConectaBD("localhost:3306/tateti?serverTimezone=UTC","root","admin");
		bd.mostrarTablaIdioma();
		ModificaBD ModBd = new ModificaBD("localhost:3306/tateti?serverTimezone=UTC","root","admin");
		ModBd.updateBD();
		Tateti t1=new Tateti();
		t1.mostrarTableroMuestra();
		t1.generarTablero();
		t1.mostrarTablero();
		Jugador j1=new Jugador("X","Jugador",t1);
		Jugador j2=new Jugador("O","CPU",t1);
		
		boolean gan=false;
		
		while(gan==false) 
		{
			j1.jugar();
			t1.mostrarTablero();
			if(!(gan=t1.hayTateti())) 
			{
				j2.jugarMaquina();
				t1.mostrarTablero();
				gan=t1.hayTateti();
			}

		}
		
		if(gan==true)
		{
			System.out.print("Hay un ganador y es " +t1.fichaGan);				
		}
				
	}

}
