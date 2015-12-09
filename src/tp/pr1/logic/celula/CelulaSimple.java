package tp.pr1.logic.celula;

import tp.pr1.logic.Casilla;
import tp.pr1.logic.Superficie;

public class CelulaSimple extends Celula {
	private int pasosReproduccion; //Pasos que faltan para que una celula se reproduzca.
	private int pasosSinMover;//Pasos que falta para que una celula muera.
	public static final int MAX_PASOS_SIN_MOVER = 0;//Constante de inicio pasosSinMover.
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
	private void iniReproduccion()
	{
		this.pasosReproduccion = PASOS_REPRODUCCION;
	}
	
	/**
	 * Resta pasos de reproduccion hasta llegar a cero
	 * @return true si pasosReproduccion > 0;
	 */
	private boolean menosPasosRep()
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
	private boolean menosSinMover()
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
		//builder.append("  X ");
		builder.append(" ");
		builder.append(pasosReproduccion);
		builder.append("X");
		builder.append(pasosSinMover);
		return builder.toString();
	}

	public String ejecutaMovimiento(Casilla casV, Superficie superficie) {
		
		StringBuilder builder = new StringBuilder();
		Casilla casN = this.generarCasillaVacia(casV, superficie);
		
		if(casN != null)
		{
			if(this.moverCelula(casV, casN, superficie)){
			builder.append("Movimiento de" + casV.toString() + " a " + casN.toString() + '\n');
			
				if(!this.menosPasosRep()){
					if(superficie.crearCelulaSimple(casV)){
						this.iniReproduccion();
						
						builder.append("Nace una celula en " + casN.toString() );
						builder.append( " cuyo padre ha sido "+ casV.toString() + '\n' );
					}
					else
						builder.append("Ha habido un fallo en la reproducción." + '\n');
				}
			}
			
		}
		else{
			if(!this.menosSinMover()){
				if(superficie.eliminarCelula(casV))
					builder.append("Muere la celula de la casilla "+ casV.toString() + "por inactividad." + '\n');
				else
					builder.append("La celula no ha podido morir por algun fallo" + '\n');
			
			}
			else if(!this.menosPasosRep()){
				if(superficie.eliminarCelula(casV))
					builder.append("Muere la celula de la casilla"+ casV.toString() + "por no poder reproducirse." + '\n');
				else
					builder.append("La celula no ha podido morir por algun fallo" + '\n');
			}	
		
		}
		return builder.toString();
	}

	
	public boolean esComestible() {
		return true;
	}
	
	protected Casilla generarCasillaVacia(Casilla casilla, Superficie superficie)
	{
		int indice, rand;
		Casilla[] casVacias = new Casilla[MAXRODEO];
		Casilla cas;
		
		indice = this.arrayVaciasAlrededor(casilla, casVacias, superficie);
		
		if(indice != 0){
			rand = (int) (Math.random()*indice);
			cas = casVacias[rand];
		}
		else
			cas = null;
		
		return cas;
	}
	
	protected boolean moverCelula(Casilla casV, Casilla casN, Superficie superficie)
	{
		boolean ok = false;
		
		if(!superficie.casillaLlena(casN))
		{ 	
			superficie.modificarCasilla(casV, casN);				
			superficie.eliminarCelula(casV);	
			
			ok = true;
		}	
		return ok;
	}
	
	public int arrayVaciasAlrededor(Casilla casilla, Casilla[] casVacias, Superficie superficie)
	{	
	int indice = 0;
	int fil = casilla.getFila(), col = casilla.getColumna();
		//Crea un array de casillas vacias alrededor de la celula
		for(int f = fil - 1;  f <= (fil + 1) ; f++){
			for(int c = col - 1; c <= (col + 1); c++){
				if(( f >= 0 && f < superficie.getFil() ) && ( c >= 0 && c < superficie.getCol()) ){
					Casilla cas = new Casilla(f, c);
					if ( !superficie.casillaLlena(cas) ){
						casVacias[indice] = new Casilla(cas.getFila(), cas.getColumna());
						indice++;
					}
				}
			}
			
		}
		return indice;
	}

}
