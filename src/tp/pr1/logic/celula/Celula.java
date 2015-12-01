package tp.pr1.logic.celula;

import tp.pr1.logic.Casilla;
import tp.pr1.logic.Superficie;

public abstract class Celula {

	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);
	public abstract boolean esComestible();

}
