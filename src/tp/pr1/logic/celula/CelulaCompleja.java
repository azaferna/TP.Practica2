package tp.pr1.logic.celula;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tp.pr1.control.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.logic.Casilla;
import tp.pr1.logic.Superficie;

public class CelulaCompleja implements Celula{

	/** Pasos que faltan para que una celula explote*/
	private int explota;
	/** MAximo numero de celulas que puede comer*/
	public static final int MAX_COMER = 3;
	
	/**
	 * Constructor de la clase celula
	 */
	public CelulaCompleja(){
		this.explota = MAX_COMER;
	}

	@Override
	public String ejecutaMovimiento(Casilla casV, Superficie superficie, Casilla casN ) {
		
		StringBuilder builder = new StringBuilder();
		boolean come = false;
		if(superficie.casillaLlena(casN))
			come = superficie.casillaComestible(casN);

		if(come) {	 
			if(this.menosComer() && this.moverCelula(superficie, casV, casN))
					builder.append("Celula Compleja en" + casV.toString() + "se mueve a" + casN.toString() + "--COME--" + '\n');
			else{
				builder.append("Explota la celula " + casV.toString() + "despues de comer en " + casN.toString() + '\n');
				superficie.eliminarCelula(casV);
				superficie.eliminarCelula(casN);
			}
			
		}
		else if(!come && this.moverCelula(superficie, casV, casN))
			builder.append("Celula Compleja en" + casV.toString() + "se mueve a" + casN.toString() + "--NO COME--"+ '\n');
		else
			builder.append("Celula Compleja en"+ casV.toString() +" no se ha movido"+ '\n');
		
		return builder.toString();
	}
	
	@Override
	public boolean esComestible() {
		return false;
	}
	
	@Override
	public Casilla generarCasillaVacia(Casilla casilla, Superficie superficie)
	{
		Casilla cas;
		int f = -1, c = -1;
		do{
			f = (int) (Math.random()*superficie.getFil());
			c = (int) (Math.random()*superficie.getCol());
		}while(f == casilla.getFila() || c == casilla.getColumna());
		
		cas = new Casilla(f, c);
		
		
		//Para pruebas
		//System.out.println("La posicion nueva generada para la celula " + casilla.toString() + " es " + cas.toString());
		return cas;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		//Para entregar:
		//builder.append("  * ");
		//Para pruebas:
		builder.append(" ");
		builder.append(" *" + this.explota + " ");
		 
		return builder.toString();
	}

	/**
	 * Resta pasos para explotar
	 * @return explota > 0
	 */
	private boolean menosComer(){
		boolean ok = false;
		if (this.explota > 0){
			this.explota --;
			ok = true;
		}
		return ok;
	}
	
	
	
	//ESTA FUNCION ANTES ESTABAN EN CELULA AL CONVERTIRLO EN INTERFACE LO HE COMPIADO Y PEGADO EN CADA UNA DE LAS CELULAS
	/**
	 * Mueve una celula de una casilla a otra y elimina de la posicon antigua
	 * @return devuelve si se ha movido correctamente
	 */
	private boolean moverCelula(Superficie superficie, Casilla casV, Casilla casN){
		boolean ok = false;
		
		if(!superficie.casillaLlena(casN) || superficie.casillaComestible(casN) ){ 	
			superficie.modificarCasilla(casV, casN);				
			superficie.eliminarCelula(casV);	
			
			ok = true;
		}	
		return ok;
	}

	
	public  void guardaCelula( FileWriter fw)throws IOException{
		try{
	 		 fw.write("Compleja ");
			 fw.write(Integer.toString(this.explota));
			
		}catch (IOException e){
				throw new IOException("Error al guardar los parametros de la celula");
			}
	
		
	}

	@Override
	public void cargaCelula(Scanner sc) throws FormatoNumericoIncorrecto{
		
		try{
			this.explota = sc.nextInt();
		}catch(Exception e){//Capturará cualquier cosa rara del fichero		
			throw new FormatoNumericoIncorrecto("Error: Los parametros de la celula a cargar no estan bien definidos en el fichero");
		}
	}
}
