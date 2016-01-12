package tp.pr1.logic;

import tp.pr1.logic.celula.CelulaSimple;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tp.pr1.control.excepciones.CasillaLlena;
import tp.pr1.control.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.control.excepciones.IndicesFueraDeRango;
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
	 * @param filas
	 * @param columnas
	 */
	public Superficie(int filas, int columnas){	
		this.filas = filas;
		this.columnas = columnas;
		
		this.superficie = new Celula[this.filas][this.columnas];
		this.todasANull();
	}		
	
	/**
	 * Pone todas las celdas a null
	 */
	public void todasANull(){
		for(int f = 0; f < this.filas; f++){
			for(int c = 0; c < this.columnas; c++){
				this.superficie[f][c] = null;
			}
		}
	}
	
	/**
	 * @return superficie.filas
	 */
	public int getFil(){
		return this.filas;
	}
	
	/**
	 * @return superficie.columnas
	 */
	public int getCol(){
		return this.columnas;
	}
	
	public void iniciarSuperficie()
	{
		this.filas = 0;
		this.columnas = 0;
		this.todasANull();
	}
	/**
	 * @return true si la casilla esta dentro de la superficie
	 */
	public Casilla casillaNueva(Casilla casV){
		return this.superficie[casV.getFila()][casV.getColumna()].generarCasillaVacia(casV, this);
	}
	
	/**
	 * Llama al ejecuta movimiento de cada celula
	 * @param casV Casilla vieja
	 * @param casN Casilla nueva
	 * @return String con los datos del movimiento
	 * @throws IndicesFueraDeRango 
	 * @throws CasillaLlena 
	 */
	public String ejecutaMovimiento(Casilla casV, Casilla casN) throws CasillaLlena, IndicesFueraDeRango{
		return this.superficie[casV.getFila()][casV.getColumna()].ejecutaMovimiento(casV, this, casN);
	}
	
	/**
	 * Crea una célulaSimple en la casilla.
	 * @param casilla -> Posicion donde queremos crear la celula
	 * @return celulaCreada -> true si celula creada
	 * @throws CasillaLlena 
	 * @throws IndicesFueraDeRango 
	 */
	public boolean crearCelula(Casilla casilla, Celula cell) throws CasillaLlena, IndicesFueraDeRango{
		boolean ok = true;
		try{
			if(!this.casillaLlena(casilla)){
					this.superficie[casilla.getFila()][casilla.getColumna()] = cell;
			}else
			{
				ok = false;
				throw new CasillaLlena("Error: La casilla ya esta ocupada");
			}
			return ok;
		}catch(Exception e){
			throw new IndicesFueraDeRango("La casilla esta fuera del tablero");
		}
	}
	
	/**
	 * Elimina la celula de la casilla. 
	 * @param casilla 			-> Posicion de la celula a eliminar.
	 * @return celulaEliminada  -> true si la celula se ha eliminado correctamente
	 */
	public boolean eliminarCelula(Casilla casilla){
		boolean ok = false;
		
		if( this.casillaLlena(casilla) ){
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
	public void modificarCasilla(Casilla casV, Casilla casN ){
		this.superficie[casN.getFila()][casN.getColumna()] = this.superficie[casV.getFila()][casV.getColumna()];
	}
	
	/**
	 *  @return Devuelve un String con el dibujo del tablero.
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("");
		for(int fil = 0; fil < this.filas; fil++){
			for(int col = 0; col < this.columnas; col++){ //Cambio en el if this.superficie[][] = null por casillallena
				Casilla cas = new Casilla(fil, col);
				if(!this.casillaLlena(cas))
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
	public boolean casillaComestible(Casilla cas){
		return this.superficie[cas.getFila()][cas.getColumna()].esComestible();	
	}
		
	
	public void guardarSuperficie(FileWriter fw)throws IOException{	
		try{
			
			for(int f = 0; f < this.filas; f++){
				for(int c = 0; c < this.columnas; c++){
					Casilla aux = new Casilla(f, c);
					if(this.casillaLlena(aux)){
						fw.write(Integer.toString (f));
						fw.write(" ");
					 	fw.write(Integer.toString(c));
					 	fw.write(" ");
						this.superficie[f][c].guardaCelula(fw);	
						fw.write(System.getProperty("line.separator"));
					}
				}
			}
		}catch(IOException e){
			throw new IOException("Error al guardar superficie");
		}
	}
	
	public void cargarSuperficie(Scanner sc) throws FormatoNumericoIncorrecto{	
		boolean ok = true;
		try{
			while( sc.hasNext()){	
				
				int fila = sc.nextInt();
				int columna = sc.nextInt();
				String tipoCell = sc.next();
				
				if(tipoCell.equalsIgnoreCase("SIMPLE"))
					this.superficie[fila][columna] = new CelulaSimple();
				else if(tipoCell.equalsIgnoreCase("COMPLEJA"))
					this.superficie[fila][columna] = new CelulaCompleja();
				else 
					throw new FormatoNumericoIncorrecto("Error: La superficie no esta bien definida en el fichero.");
				
				if(ok)
					this.superficie[fila][columna].cargaCelula(sc);
			}
		}catch(Exception e){
			throw new FormatoNumericoIncorrecto("Error: La superficie no esta bien definida en el fichero.");
		}		
		
	}

}










