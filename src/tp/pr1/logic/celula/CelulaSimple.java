package tp.pr1.logic.celula;

import tp.pr1.logic.Casilla;
import tp.pr1.logic.Superficie;

public class CelulaSimple extends Celula {
	private int pasosReproduccion; //Pasos que faltan para que una celula se reproduzca.
	private int pasosSinMover;//Pasos que falta para que una celula muera.
	public static final int MAX_PASOS_SIN_MOVER = 2;//Constante de inicio pasosSinMover.
	public static final int PASOS_REPRODUCCION = 3;//Constante de inicio pasosReproduccion.
	
	
	/**
	 * Constructor de la clase celula.
	 */
	public CelulaSimple()
	{
		this.pasosReproduccion = PASOS_REPRODUCCION;
		this.pasosSinMover = MAX_PASOS_SIN_MOVER;
	}
	
	/**
	 * Reinicia los pasos de reproduccion de una celula.
	 */
	public void iniReproduccion()
	{
		this.pasosReproduccion = PASOS_REPRODUCCION;
	}
	
	/**
	 * Resta pasos de reproduccion hasta llegar a cero
	 * @return true si pasosReproduccion > 0;
	 */
	public boolean menosPasosRep()
	{
		boolean ok = false;
		if(this.pasosReproduccion > 0)
		{
			this.pasosReproduccion--;
			ok = true;
		}
		
		return ok;
		
	}

	/**		Resta pasos para su muerte si es posible.
	 * 		@return: Devuelve 'true' si pasosSinMover > 0;
     */
	public boolean menosSinMover()
	{
		boolean ok = false;
		if(this.pasosSinMover > 0)
		{
			this.pasosSinMover--;
			ok = true;
		}
		return ok;
	}


	/**
	 * 		@return: Devuelve un string "( nPasosREp - nPasosSinMover)" 
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" ");
		builder.append(pasosReproduccion);
		builder.append("-");
		builder.append(pasosSinMover);
		return builder.toString();
	}

	@Override
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esComestible() {
		return true;
	}
	
}
