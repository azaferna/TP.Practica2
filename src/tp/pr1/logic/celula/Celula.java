package tp.pr1.logic.celula;

import tp.pr1.logic.Casilla;
import tp.pr1.logic.Superficie;

public abstract class Celula {

	protected static final int MAXRODEO = 9;//Maximo numero de celdas que pueden rodear una celula(incluida ella misma)
	/**
	 * Ejecuta un movimiento siguiendo las normas del juego
	 * @param casV -> Posicion de la casilla a mover
	 * @param casN -> Posicion donde se tiene que mover la casilla
	 * @param superficie -> superficie donde trabajamos
	 * @return Devuelve una cadena de texto con todo lo que ha sucedido durante el movimiento
	 */
	public abstract String ejecutaMovimiento(Casilla casV, Superficie superficie, Casilla casN);
	
	/** 
	 * @return Devuelve true si la casilla es comestible.
	 */
	public abstract boolean esComestible();
	/**
	 * @return Devuelve un string con la celula pintada.
	 */
	public abstract String toString();
	/**
	 * Genera una casilla vacia acorde con las normas del juego
	 * @param casilla -> Es la posicion de la celula sobre la que queremos generar la celula libre
	 * @param superficie -> supeficie sobre la que trabajamos
	 */
	public abstract Casilla generarCasillaVacia(Casilla casilla, Superficie superficie);
	
	/**
	 * Mueve una celula de una casilla a otra y elimina de la posicon antigua
	 * @return devuelve si se ha movido correctamente
	 */
	protected boolean moverCelula(Casilla casV, Casilla casN, Superficie superficie){
		boolean ok = false;
		
		if(!superficie.casillaLlena(casN) || superficie.casillaComestible(casN) )
		{ 	
			superficie.modificarCasilla(casV, casN);				
			superficie.eliminarCelula(casV);	
			
			ok = true;
		}	
		return ok;
	}
}
