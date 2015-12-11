package tp.pr1.logic.celula;

import tp.pr1.logic.Casilla;
import tp.pr1.logic.Superficie;

public class CelulaSimple extends Celula {
	/**Pasos que faltan para que una celula se reproduzca.*/
	private int pasosReproduccion;
	/**Pasos que faltan para que una celula muera*/
	private int pasosSinMover;
	/**Constante de inicio pasosSinMover.*/
	public static final int MAX_PASOS_SIN_MOVER = 2;
	/**Constante de inicio pasosReproduccion.*/
	public static final int PASOS_REPRODUCCION = 3;
	
	
	/**
	 * Constructor de la clase celula.
	 */
	public CelulaSimple()
	{
		this.pasosReproduccion = PASOS_REPRODUCCION;
		this.pasosSinMover = MAX_PASOS_SIN_MOVER;
	}

	/**
	 * Ejecuta un movimiento siguiendo las normas del juego
	 * @param casV -> Posicion de la casilla a mover
	 * @param casN -> Posicion donde se tiene que mover la casilla
	 * @param superficie -> superficie donde trabajamos
	 * @return Devuelve una cadena de texto con todo lo que ha sucedido durante el movimiento
	 */
	public String ejecutaMovimiento(Casilla casV, Superficie superficie, Casilla casN) {
		
		StringBuilder builder = new StringBuilder();

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
	
	/**
	 * @return devuelve si el tipo de celula es comestible o no
	 */
	public boolean esComestible() {
		return true;
	}
	
	/**
	 * Genera una casilla vacia acorde con las normas del juego
	 * @param casilla -> Es la posicion de la celula sobre la que queremos generar la celula libre
	 * @param superficie -> supeficie sobre la que trabajamos
	 */
	public Casilla generarCasillaVacia(Casilla casilla, Superficie superficie)
	{
		int indice, rand;
		Casilla[] casVacias = new Casilla[MAXRODEO];
		Casilla cas;
		
		indice = this.arrayVaciasAlrededor(casilla, casVacias, superficie);
		
		if(indice != 0){
			rand = (int) (Math.random()*indice);
			cas = casVacias[rand];
			//Para pruebas
			System.out.println("La posicion nueva generada para la celula " + casilla.toString() + " es " + cas.toString());
		}
		else
		{
			cas = null;
			//Para pruebas
			System.out.println("La posicion nueva generada para la celula " + casilla.toString() + " es null");
		}
		return cas;
	}
	
	/**
	 * @return Devuelve una cadena de texto con la forma en la que se pinta una celula
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		//builder.append("  X ");
		/*builder.append(" ");
		builder.append(pasosReproduccion);
		builder.append("X");
		builder.append(pasosSinMover);
		builder.append(" ");*/
		return builder.toString();
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

	
	/**
	 * Resta pasos sin mover hasta llegar a cero
	 * @return menosSinMover > 0
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
	
	
	
	private int arrayVaciasAlrededor(Casilla casilla, Casilla[] casVacias, Superficie superficie)
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
