package tp.pr1.logic.mundo;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import tp.pr1.control.excepciones.CasillaLlena;
import tp.pr1.control.excepciones.ErrorDeInicializacion;
import tp.pr1.control.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.control.excepciones.IndicesFueraDeRango;
import tp.pr1.control.excepciones.TipoCelulaDesconocido;
import tp.pr1.logic.Casilla;
import tp.pr1.logic.Superficie;
import tp.pr1.logic.mundo.MundoSimple;
import tp.pr1.logic.mundo.MundoComplejo;

public abstract class Mundo {
	protected Superficie superficie;
	protected int filas;
	protected int columnas;
	protected enum TiposDeCelulas {Simple, Compleja};
	
	/**
	 * Constructor de la clase mundo sin parametros
	 */
	public Mundo(){
		this.filas = 0;
		this.columnas = 0;
		this.superficie = null;
	}
	
	/**
	 * constructor de la clase Mundo con parametros fila y columna
	 * @param fil -> numero total de filas
	 * @param col -> numero total de columnas
	 */
	public Mundo(int fil, int col){
		this.filas = fil;
		this.columnas = col;
		this.superficie = new Superficie(this.filas, this.columnas);
		
	}
	
	/**
	 * Metodo abstracto que introduce el numero de celulas correspondientes al mundo segun el tipo que sean
	 * @throws TipoCelulaDesconocido 
	 * @throws ErrorDeInicializacion 
	 * @throws FormatoNumericoIncorrecto 
	 */
	public abstract void inicializaMundo() throws ErrorDeInicializacion, TipoCelulaDesconocido, CasillaLlena, IndicesFueraDeRango;
	
	/**
	 * Funci�n principal de la clase Mundo, encargada de ejecutar los movimientos de las c�lulas.
	 * 
	 * @return Devuelve un string con el resultado de los cambios en el mundo.
	 * @throws IndicesFueraDeRango 
	 * @throws CasillaLlena 
	 * @throws FormatoNumericoIncorrecto 
	 */
	public String evoluciona() throws CasillaLlena, IndicesFueraDeRango{
		Casilla[] llenas = new Casilla[this.superficie.getFil() * this.superficie.getCol()];
		StringBuilder builder = new StringBuilder();
		int nLlenas = this.casillasLlenas(llenas); 
		boolean come = false;
		
		for(int i = 0; i < nLlenas; i++){
			Casilla casN = this.superficie.casillaNueva(llenas[i]);
			if(casN != null && this.superficie.casillaLlena(casN))
				come = this.superficie.casillaComestible(casN);
			builder.append(this.superficie.ejecutaMovimiento(llenas[i], casN));
		
			if(come)
			{
				int indice = this.buscarCasilla(llenas, i, nLlenas, casN) ;
				if (indice != -1)
				{
					this.moverCasillasADerecha(llenas, indice, nLlenas);
					nLlenas--;
				}
				come = false;
			}
		}
		return builder.toString();
	}
	
	/**
	 * @return Devuelve un string con el dibujo del mundo.
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.superficie.toString());
		return builder.toString();
	}

	/**
	 * Pone todas las casillas de la superficie a null.
	 */
	public void vaciarMundo(){
		this.superficie.todasANull();
	}
	
	
	
	/////////////////////////                CODIGO NUEVO                 ////////////////////////////
	
	/*He creado una nueva forma de llenar las celulas para que sea mas generico y facil a la hora de añadir celulas*/
	
	
	/**
	 * Lo he modificado por un llenar más generico para que sea mas sencillo 
	 * añadir nuevas celulas (con añadir el tipo nuevo y su case ya vale)
	 * @param tipoDeCelula (para que añadir nuevas celulas sea mucho más facil)
	 * 		@tipo 1 -> Simple
	 * 		@tipo 2 -> Compleja
	 * @param numeroCelulas numero de celulas que queremos crear.
	 * @throws TipoCelulaDesconocido 
	 * @throws IndicesFueraDeRango 
	 * @throws CasillaLlena 
	 */
	public void llenarNCelulasAleatorias(TiposDeCelulas tipoDeCelula, int numeroCelulas) throws TipoCelulaDesconocido, CasillaLlena, IndicesFueraDeRango{
		Casilla[] casillasALlenar = this.pedirNCasillasLibres(numeroCelulas);
		switch(tipoDeCelula)
		{
			case Simple:
				for(int i = 0; i < casillasALlenar.length; i++)
					this.crearCelula(casillasALlenar[i], 1);
				break;
				
			case Compleja:
				for(int i = 0; i < casillasALlenar.length; i++)
					this.crearCelula(casillasALlenar[i], 2);
				break;
		}
	}
	
	/**
	 * Devuelve un array lleno de n casillas libres y sin repetirse entre si
	 * @param n numero de casillas a crear
	 * @return array lleno
	 */
	private Casilla[] pedirNCasillasLibres(int n)
	{
		//Hacer algun tipo de if para comprobar que no hay menos huecos que numero de casillas libres pide y lanzar excepcion;
		Casilla[] casillas = new Casilla[n];
		int i = 0;	
		while (i < n)
		{
			Casilla cas = this.generarCasillaAleatoria();
			if(!this.superficie.casillaLlena(cas) && this.buscarCasilla(casillas, 0, i, cas) == - 1)
			{
				casillas[i] = cas;
				i++;
			}
		}
		return casillas;
	}
	
	/**
	 * Genera una casilla aleatoria 
	 * @return casilla
	 */
	public Casilla generarCasillaAleatoria(){
		return new Casilla((int) (Math.random()*this.superficie.getFil()), (int) (Math.random()*this.superficie.getCol()));
	}
	
	
	/////////////////////////               FIN CODIGO NUEVO                 ////////////////////////////
	
	
	/**
	 * Crea una celula en la casilla.
	 * @param casilla -> posicion donde creamos
	 * @param tipo 
	 * @return Devuelve un bool con valor true si es posible.
	 * @throws TipoCelulaDesconocido 
	 * @throws IndicesFueraDeRango 
	 * @throws CasillaLlena 
	 * @throws FormatoNumericoIncorrecto 
	 */
	public abstract boolean crearCelula(Casilla casilla, int tipo) throws TipoCelulaDesconocido, CasillaLlena, IndicesFueraDeRango;
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Elimina una celula si es posible.
	 * @param casilla -> posicion donde eliminamos
	 * @return  Devuelve un bool con valor true si es posible.
	 */
	public boolean eliminarCelula(Casilla casilla){
		return this.superficie.eliminarCelula(casilla);
	}
	
	/**
	 * Recorre el mundo buscando las casillas ocupadas.
	 * @param llenas
	 * @return un entero con el n�mero de casillas ocupadas.
	 */
	protected int casillasLlenas(Casilla[] llenas){
		int cont = 0;
		for (int fil = 0; fil < this.superficie.getFil(); fil++) {
			for (int col = 0; col < this.superficie.getCol(); col++) {
				Casilla casilla = new Casilla(fil, col);
				if(this.superficie.casillaLlena(casilla) ){
					llenas[cont] = new Casilla(fil, col);
					cont++;
				}
			}
		}
				
				return cont;	
	}
	
	/**
	 * Busca la casilla en el array de casillas.
	 * @param llenas -> array de casillas a procesar
	 * @param desdeDonde -> desde donde buscamos
	 * @param hastaDonde -> hasta donde buscamos
	 * @param casillaABuscar -> posicion a buscar
	 * @return entero con el numero de casillas actualizado.
	 */
	private int buscarCasilla(Casilla[] llenas, int desdeDonde, int hastaDonde, Casilla casillaABuscar){
		int indice = desdeDonde ;
		
		while(indice < hastaDonde && !llenas[indice].esIgual(casillaABuscar))
			indice++;
			
		if(!(indice >= desdeDonde && indice < hastaDonde))
			indice = -1;
		
		return indice;
				
	}
	
	/**
	 * 	Mueve todas las casillas de un array a la derecha desde una posicion dada y hata otra tambien específica
	 * @param casilla -> Array de casillas
	 * @param desdeDonde -> desde donde movemos
	 * @param hastaDonde -> hasta donde movemos
	 * @return array actualizado de casillas
	 */
	private Casilla[] moverCasillasADerecha(Casilla[] casilla, int desdeDonde, int hastaDonde){
		for(int i = desdeDonde; i < hastaDonde - 1; i++)
			casilla[i] =  casilla[i + 1]; 
		
		return casilla;
	}
	
	/**
	 * Carga el mundo de fichero 
	 * @param nombFich 
	 * @return mundocargado
	 * @throws FormatoNumericoIncorrecto 
	 */
	public Mundo cargarMundo(String nombFich)throws IOException, FormatoNumericoIncorrecto{
		Mundo mundocargado = this;
		 Scanner sc = new Scanner(new File(nombFich + ".txt" ));
	     String tipo = sc.nextLine();
	     int fil, col;
		try{
		     if(tipo.equalsIgnoreCase("SIMPLE") || tipo.equalsIgnoreCase("COMPLEJO"))
		     {
		    	 try{
			    	 fil = sc.nextInt();
			    	 col= sc.nextInt();
			     
		    	 }catch (Exception e){
		    		 throw new FormatoNumericoIncorrecto("Error: el mundo no esta bien definido(Filas / Columas");
		    	 }
		    	 
		    	 if (tipo.equalsIgnoreCase("SIMPLE"))
			    	 mundocargado = new MundoSimple(fil, col, 0);
			    	 
			     else
			    	 mundocargado = new MundoComplejo(fil, col, 0, 0);
			     mundocargado.superficie.cargarSuperficie(sc);	     
		     }else
		    	 throw new FormatoNumericoIncorrecto("Error: el mundo no esta definido en el fichero(Simple/Complejo)");
		}finally{
			sc.close();
		}
		
		return mundocargado;
		
	}
		
	/**
	 * Guarda el mundo
	 * @param nombFich
	 * @throws IOException 
	 */
	public abstract void guardarMundo(String nombFich) throws IOException;

	public void iniciarMundo() {
		this.filas = 0;
		this.columnas = 0;
		this.superficie.iniciarSuperficie();
	}
}
