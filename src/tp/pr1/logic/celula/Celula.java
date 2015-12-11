package tp.pr1.logic.celula;

import tp.pr1.logic.Casilla;
import tp.pr1.logic.Superficie;

public abstract class Celula {

	protected static final int MAXRODEO = 9;//Maximo numero de celdas que pueden rodear una celula(incluida ella misma)
	
	public abstract String ejecutaMovimiento(Casilla casV, Superficie superficie, Casilla casN);
	public abstract boolean esComestible();
	public abstract String toString();
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
