import java.util.Scanner;

public class Jugador 
{
	public String ficha;
	public String nombre;
	public Tateti t;
	public int idioma;
	public ConectaBD bd1;
	private int jugada;
	
	public void jugar() 
	{

		int f=0;int c=0;
		Scanner s= new Scanner(System.in);
		bd1.imprimirMensaje(idioma, 3);
		int j = s.nextInt();
		switch(j) 
		{
		default:  bd1.imprimirMensaje(idioma, 4);break;
		
		case 1: f=0;c=0;break;
		case 2: f=0;c=1;break;
		case 3: f=0;c=2;break;
		
		case 4: f=1;c=0;break;
		case 5: f=1;c=1;break;
		case 6: f=1;c=2;break;
		
		case 7: f=2;c=0;break;
		case 8: f=2;c=1;break;
		case 9: f=2;c=2;break;
	
		}

		if(t.ponerFicha(f,c,ficha)==false)
		{
			 bd1.imprimirMensaje(idioma, 3);
			 jugar();
		}
	
	}
	
	public void jugarMaquina() 
	{
		int f=0;int c=0;
		int j = (int) (Math.random() * 9);
		switch(j) 
		{
		
		case 7: f=0;c=0;break;
		case 8: f=0;c=1;break;
		case 9: f=0;c=2;break;
		
		case 4: f=1;c=0;break;
		case 5: f=1;c=1;break;
		case 6: f=1;c=2;break;
		
		case 1: f=2;c=0;break;
		case 2: f=2;c=1;break;
		case 3: f=2;c=2;break;
	
		}

		if(t.ponerFicha(f,c,ficha)==false)
		{
			jugarMaquina();
		}
	
		
	}
	
	public int getJugada() {
		return this.jugada;
	}
	
	public void aumentarJugada() {
		this.jugada++;
	}
	
	Jugador(String ficha,String nombre,Tateti t)
	{
		this.ficha=ficha;
		this.nombre=nombre;
		this.t =t;
	}

	Jugador(String ficha, String nombre, Tateti t, int idioma, ConectaBD bd1) {
		
		this.ficha=ficha;
		this.nombre=nombre;
		this.t=t;
		this.idioma=idioma;
		this.bd1=bd1;
	}
}
