package tp.pr1.logic.celula;

import tp.pr1.logic.Casilla;
import tp.pr1.logic.Superficie;

public abstract class Celula {

	protected static final int MAXRODEO = 9;//Maximo numero de celdas que pueden rodear una celula(incluida ella misma)
	
	public abstract String ejecutaMovimiento(Casilla casV, Superficie superficie);
	public abstract boolean esComestible();
	public abstract String toString();
	protected abstract Casilla generarCasillaVacia(Casilla casilla, Superficie superficie);
	protected abstract boolean moverCelula(Casilla casV, Casilla casN, Superficie superficie);
}
