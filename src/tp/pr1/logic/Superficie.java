package tp.pr1.logic;

import tp.pr1.logic.celula.CelulaSimple;
import tp.pr1.logic.celula.Celula;
import tp.pr1.logic.celula.CelulaCompleja;
/**
 * 
 * @author AzaharaFernandez
 * @version 
 *
 */
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
	
	
	
	public Casilla casillaNueva(Casilla casV)
	{
		return this.superficie[casV.getFila()][casV.getColumna()].generarCasillaVacia(casV, this);
	}
	public String ejecutaMovimiento(Casilla casV, Casilla casN)
	{
		return this.superficie[casV.getFila()][casV.getColumna()].ejecutaMovimiento(casV, this, casN);
	}
	
	/**
	 * Crea una célulaSimple en la casilla.
	 * @param casilla -> Posicion donde queremos crear la celula
	 * @return celulaCreada -> true si celula creada
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
	 * @param casilla -> Posicion donde queremos crear la celula
	 * @return celulaCreada -> true si celula creada
	 */
	public boolean crearCelulaCompleja(Casilla casilla)//Transformar en añadirX(i, j, X);
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
	 * Dada dos casillas una nueva y vieja, introduce en la nueva la vieja.
	 * @param casV -> Casilla que queremos mover
	 * @param casN -> Posicion donde la hemos movido
	 */
	public void modificarCasilla(Casilla casV, Casilla casN )
	{
		this.superficie[casN.getFila()][casN.getColumna()] = this.superficie[casV.getFila()][casV.getColumna()];
	}
	
	/**

	 *  @return Devuelve un String con el dibujo del tablero.
	 */
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

	/**
	 * @param casilla -> casilla a comprobar
	 * @return  Devuelve true si la casilla esta ocupada por una celula.
	 */
	public boolean casillaLlena(Casilla casilla){
		return (this.superficie[casilla.getFila()][casilla.getColumna()] != null);
	}
		
	/**
	 * @param cas -> casilla a comprobar
	 * @return  Dada unca casilla devuelve true si la casilla es comestible.
	 */
	public boolean casillaComestible(Casilla cas)
	{
		return this.superficie[cas.getFila()][cas.getColumna()].esComestible();	
	}
	
	}










