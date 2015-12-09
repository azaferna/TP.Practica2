package tp.pr1.logic;

import tp.pr1.logic.celula.CelulaSimple;
import tp.pr1.logic.celula.Celula;
import tp.pr1.logic.celula.CelulaCompleja;

public class Superficie {
	private Celula[][] superficie;
	private int filas;
	private int columnas;
	
	
	/* 						FUNCIONES DE INICIALIZACION					*/
	
	/**
	 * Constructor de una superficie
	 */
	public Superficie(int filas, int columnas)
	{	
		this.filas = filas;
		this.columnas = columnas;
		
		this.superficie = new Celula[this.filas][this.columnas];
		this.todasANull();
	}		
	
	/**
	 * Pone todas las celdas a null
	 */
	public void todasANull()
	{
		for(int f = 0; f < this.filas; f++)
		{
			for(int c = 0; c < this.columnas; c++)
			{
				this.superficie[f][c] = null;
			}
		}
	}
	
	/**
	 * @return superficie.filas
	 */
	public int getFil()
	{
		return this.filas;
	}
	
	/**
	 * @return superficie.columnas
	 */
	public int getCol()
	{
		return this.columnas;
	}
	
	/**
	 * @return true si la casilla esta dentro de la superficie
	 */
	private boolean dentroDeSuperficie(Casilla casilla){
		return ((casilla.getFila() < this.filas && casilla.getFila() >= 0) && (casilla.getColumna() < this.columnas && casilla.getColumna() >=0));
	}
	
	/*---------------------------------------------------------------------*/
	
	
	/*							FUNCIONES DE PROCESAMIENTO				   */
	
	public String ejecutaMovimiento(Casilla cas)
	{
		return this.superficie[cas.getFila()][cas.getColumna()].ejecutaMovimiento(cas, this);
	}
	
	/**
	 * Crea una célulaSimple en la casilla.
	 * @param casilla
	 * @return celulaCreada;
	 */
	public boolean crearCelulaSimple(Casilla casilla)
	{
		boolean ok = false;
		
		if(this.dentroDeSuperficie(casilla) && !this.casillaLlena(casilla))
		{
			this.superficie[casilla.getFila()][casilla.getColumna()] = new CelulaSimple();
			ok = true;
		}
	
		return ok;
	}
	
	/**
	 * Crea una célulaCompleja en la casilla.
	 * @param casilla
	 * @return celulaCreada;
	 */
	public boolean crearCelulaCompleja(Casilla casilla)
	{
		boolean ok = false;
		
		if(this.dentroDeSuperficie(casilla) && !this.casillaLlena(casilla))
		{
			this.superficie[casilla.getFila()][casilla.getColumna()] = new CelulaCompleja();
			ok = true;
		}
	
		return ok;
	}
	
	/**
	 * Elimina la celula de la casilla. 
	 * @param casilla 			-> Posicion de la celula a eliminar.
	 * @return celulaEliminada  -> true si la celula se ha eliminado correctamente
	 */
	public boolean eliminarCelula(Casilla casilla)
	{
		boolean ok = false;
		if( this.dentroDeSuperficie(casilla) && this.casillaLlena(casilla) )
		{
			this.superficie[casilla.getFila()][casilla.getColumna()] = null;
			ok = true;
		}
		
		return ok;
	}
	
	/**
	 * Crea n celulas en la superficie
	 * @param     nSimples -> Numero de celulas simples a crear 
	 * @param     nCmplejas -> Numero de celulas complejas a crear 
	 * @return true -> Si se han podido crear todas.
	 */
	public boolean llenarNCeluslasLibres(int nSimples, int nComplejas)
	{
		boolean ok = false;
		int col = 0, fil = 0;
		int cont = 0;
		for (int i = 0; i < nSimples; i++)
		{	
			while(!ok)
			{
				//x =(int)Math.random() * n //genera un numero entero entre 0 y n

				fil = (int) (Math.random()*this.filas);
				col = (int) (Math.random()*this.columnas);
				
				Casilla casilla = new Casilla(fil, col);
				if(this.crearCelulaSimple(casilla))
				{
					ok = true;
					cont++;
				}
			}
			ok = false;
		}
		for (int i = 0; i < nComplejas; i++)
		{	
			while(!ok)
			{
				//x =(int)Math.random() * n //genera un numero entero entre 0 y n

				fil = (int) (Math.random()*this.filas);
				col = (int) (Math.random()*this.columnas);
				
				Casilla casilla = new Casilla(fil, col);
				if(this.crearCelulaCompleja(casilla))
				{
					ok = true;
					cont++;
				}
			}
			ok = false;
		}
		return (cont == nSimples + nComplejas);
	}
	
	public void modificarCasilla(Casilla casV, Casilla casN )
	{
		this.superficie[casN.getFila()][casN.getColumna()] = this.superficie[casV.getFila()][casV.getColumna()];
	}
	
	//Devuelve un String con el dibujo del tablero.
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("");
		for(int fil = 0; fil < this.filas; fil++)
		{
			for(int col = 0; col < this.columnas; col++)
			{
				if(this.superficie[fil][col] == null)
					builder.append("  - ");
				else
					builder.append(this.superficie[fil][col].toString());
			}
			
			builder.append('\n');
		}

		
		return builder.toString();
	}

	//Devuelve true si la casilla esta ocupada por una celula;
	public boolean casillaLlena(Casilla casilla){
		return (this.superficie[casilla.getFila()][casilla.getColumna()] != null);
	}
		
	public boolean casillaComastible(Casilla cas)
	{
		return this.superficie[cas.getFila()][cas.getColumna()].esComestible();	
	}
}









