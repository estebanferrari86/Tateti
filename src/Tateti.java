
public class Tateti
{
	public String Tablero[][] = new String[3][3];
	public String fichaGan;
	
	public boolean hayTateti() 
	{
		
		
		if((Tablero[0][0]!="_")&&(Tablero[0][0]==Tablero[0][1]) && (Tablero[0][1]==Tablero[0][2]))
		{
			fichaGan=Tablero[0][0];
			return(true);
		}
		
		if((Tablero[1][0]!="_")&&(Tablero[1][0]==Tablero[1][1]) && (Tablero[1][1]==Tablero[1][2]))
		{
			fichaGan=Tablero[1][0];
			return(true);
		}
		
		if((Tablero[2][0]!="_")&&(Tablero[2][0]==Tablero[2][1]) && (Tablero[2][1]==Tablero[2][2]))
		{
			fichaGan=Tablero[2][0];
			return(true);
		}
		
		if((Tablero[0][0]!="_")&&(Tablero[0][0]==Tablero[1][0]) && (Tablero[1][0]==Tablero[2][0]))
		{
			fichaGan=Tablero[0][0];
			return(true);
		}
		
		if((Tablero[0][1]!="_")&&(Tablero[0][1]==Tablero[1][1]) && (Tablero[1][1]==Tablero[2][1]))
		{
			fichaGan=Tablero[0][1];
			return(true);
		}
		
		if((Tablero[0][2]!="_")&&(Tablero[0][2]==Tablero[1][2]) && (Tablero[1][2]==Tablero[2][2]))
		{
			fichaGan=Tablero[0][2];
			return(true);
		}
		
		if((Tablero[0][0]!="_")&&(Tablero[0][0]==Tablero[1][1]) && (Tablero[1][1]==Tablero[2][2]))
		{
			fichaGan=Tablero[0][0];
			return(true);
		}
		
		if((Tablero[2][0]!="_")&&(Tablero[2][0]==Tablero[1][1]) && (Tablero[1][1]==Tablero[0][2]))
		{
			fichaGan=Tablero[2][0];
			return(true);
		}
		
		
		return(false);
		
	}
	
	public boolean ponerFicha(int posF, int posC,String ficha) 
	{
		if(Tablero[posF][posC]!=null)
		{
			if(Tablero[posF][posC]=="_")
			{
				Tablero[posF][posC]=ficha;
				return(true);
			}
			else
			{
				return(false);
			}
		}
		else
		{
			return(false);
		}
		
			
			
	} 
	
	//Tablero de Muestra para instrucciones
	public void mostrarTableroMuestra() 
	{
		int casillero = 1;
		for(int f=0;f<Tablero.length;f++) 
		{
			for(int c=0;c<Tablero[0].length;c++) 
			{				
				Tablero[f][c]=String.valueOf(casillero);
				System.out.print(Tablero[f][c]+" ");
				casillero++;
			}
			System.out.println(" ");
		}
	}
 
	public void generarTablero() 
	{
		for(int f=0;f<Tablero.length;f++) 
		{
			for(int c=0;c<Tablero[0].length;c++) 
			{
				Tablero[f][c]="_";
			}
		}
	}
	
	public void mostrarTablero() 
	{
		System.out.println(" ");
		for(int f=0;f<Tablero.length;f++) 
		{
			for(int c=0;c<Tablero[0].length;c++) 
			{
				System.out.print(Tablero[f][c]+" ");
			}
			System.out.println(" ");
		}
	}
}
