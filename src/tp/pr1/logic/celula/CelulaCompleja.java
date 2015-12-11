package tp.pr1.logic.celula;

import tp.pr1.logic.Casilla;
import tp.pr1.logic.Superficie;

public class CelulaCompleja extends Celula{

	/** Pasos que faltan para que una celula explote*/
	private int explota;
	/** MAximo numero de celulas que puede comer*/
	public static final int MAX_COMER = 3;
	
	/**
	 * Constructor de la clase celula
	 */
	public CelulaCompleja()
	{
		this.explota = MAX_COMER;
	}

	/**
	 * Ejecuta un movimiento siguiendo las normas del juego
	 * @param casV -> Posicion de la casilla a mover
	 * @param casN -> Posicion donde se tiene que mover la casilla
	 * @param superficie -> superficie donde trabajamos
	 * @return Devuelve una cadena de texto con todo lo que ha sucedido durante el movimiento
	 */
	public String ejecutaMovimiento(Casilla casV, Superficie superficie, Casilla casN ) {
		
		StringBuilder builder = new StringBuilder();
		boolean come = false;
		if(superficie.casillaLlena(casN))
			come = superficie.casillaComestible(casN);

		if(come) 
		{	 
			if(this.menosComer() && this.moverCelula(casV, casN, superficie))
					builder.append("Celula Compleja en" + casV.toString() + "se mueve a" + casN.toString() + "--COME--" + '\n');
			else
			{
				builder.append("Explota la celula " + casV.toString() + '\n');
				superficie.eliminarCelula(casV);
			}
			
		}
		else if(!come && this.moverCelula(casV, casN, superficie))
			builder.append("Celula Compleja en" + casV.toString() + "se mueve a" + casN.toString() + "--NO COME--"+ '\n');
		else
			builder.append("Celula Compleja en"+ casV.toString() +" no se ha movido"+ '\n');
		
		return builder.toString();
	}
	
	/**
	 * @return devuelve si el tipo de celula es comestible o no
	 */
	public boolean esComestible() {
		return false;
	}
	
	/**
	 * Genera una casilla vacia acorde con las normas del juego
	 * @param casilla -> Es la posicion de la celula sobre la que queremos generar la celula libre
	 * @param superficie -> supeficie sobre la que trabajamos
	 */
	public Casilla generarCasillaVacia(Casilla casilla, Superficie superficie)
	{
		Casilla cas;
		int f = -1, c = -1;
		do
		{
			f = (int) (Math.random()*superficie.getFil());
			c = (int) (Math.random()*superficie.getCol());
		}while(f == casilla.getFila() || c == casilla.getColumna());
		
		cas = new Casilla(f, c);
		
		
		//Para pruebas
		System.out.println("La posicion nueva generada para la celula " + casilla.toString() + " es " + cas.toString());
		return cas;
	}
	
	/**
	 * @return Devuelve una cadena de texto con la forma en la que se pinta una celula
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		//builder.append("  * ");
		builder.append(" ");
		builder.append(" *" + this.explota + " ");
		 
		return builder.toString();
	}

	/**
	 * Resta pasos para explotar
	 * @return explota > 0
	 */
	private boolean menosComer()
	{
		boolean ok = false;
		if (this.explota > 0)
		{
			this.explota --;
			ok = true;
		}
		return ok;
	}
	

}
