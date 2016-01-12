package tp.pr1.control;

import java.io.IOException;
import java.util.Scanner;

import tp.pr1.control.comando.Comando;
import tp.pr1.control.excepciones.CasillaLlena;
import tp.pr1.control.excepciones.ComandoIncorrecto;
import tp.pr1.control.excepciones.ErrorDeInicializacion;
import tp.pr1.control.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.control.excepciones.IndicesFueraDeRango;
import tp.pr1.control.excepciones.TipoCelulaDesconocido;
import tp.pr1.logic.Casilla;
import tp.pr1.logic.mundo.Mundo;
import tp.pr1.logic.mundo.MundoSimple;


public class Controlador {
	private Mundo mundo;
	private ParserComando parser;
	private boolean simulacionTerminada;
	private Scanner entrada;
	
	/**
	 * Constructor de la clase controlador, incluye un nuevo mundo, una entrada y parser.
	 */
	public Controlador(){
		this.entrada = new Scanner(System.in);
		this.parser = new ParserComando();
		this.simulacionTerminada = false;
		this.mundo = new MundoSimple(0,0,0);
	}
	
	/**
	 * Recibe un bool simulacionterminada y cambiar el par�metro simulacionterminada de mundo.
	 * @param simulacionTerminada
	 */
	public void setSimulacionTerminada(boolean simulacionTerminada) {
		this.simulacionTerminada = simulacionTerminada;
	}

	/**
	 * Una vez obtenida la cadena de datos a procesar este metodo busca el comando a procesar y lo ejecuta.	
	 * @param cadena
	 * @throws IOException 
	 * @throws ComandoIncorrecto 
	 * @throws FormatoNumericoIncorrecto 
	 */
	public void procesarComando(String cadena) throws ComandoIncorrecto, FormatoNumericoIncorrecto{
		String[] array = cadena.split(" ");
		Comando comando = this.parser.parseaComando(array);
		//if(comando != null)
			System.out.println(comando.ejecuta(this));
		
	}
	
	/**
	 * Pide un comando por teclado y lo devuelve como un string.
	 * @return
	 */
	private String leerComando(){	
		System.out.print("Comando >");
		return this.entrada.nextLine();
	}
	
	//CAMBIOS 
	/**
	 * Es el que pinta mundo(necesario que este en el controlador para que se pueda usar en los comandos)
	 * @return un String con la impresion del mundo por pantalla
	 */
	public String stringMundo()	{
		return this.mundo.toString();
	}
	
	
	//CAMBIOS
	/**
	 * Ejecuta la aplicacion hasta su fin. 
	 * @throws FormatoNumericoIncorrecto 
	 */
	public void lanzarAplicacion() {/////////////OJO////////////////////////////////////
		
		String comando = null; 
		
		while(!this.simulacionTerminada){
			try{
				/*Arreglo para limpiar buffer*/
					//this.entrada.skip("");
				/*---------------------------*/
				comando = this.leerComando();
				this.procesarComando(comando);
			}catch(ComandoIncorrecto e){
				System.out.println(e.getMessage());
			}catch(FormatoNumericoIncorrecto e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*////////////////////////////// CODIGO NUEVO (FUNCIONES DE PASO ENTRE COMANDO Y MUNDO) //////////////////////////////////*/
	public boolean crearCelula(Casilla casilla){
		int tipo = 0;
		boolean  ok = false;
		
		System.out.println("De que tipo: Simple(1), Compleja(2):");
		tipo = this.entrada.nextInt();
		
		try {
			this.mundo.crearCelula(casilla, tipo);
			ok = true;
			
		} catch (TipoCelulaDesconocido e) {// Mas 1 porque el usuario empieza en 1 y el enumerado en 0
			System.out.println(e.getMessage() );
		} catch (CasillaLlena e) {
			System.out.println(e.getMessage() );
		}catch ( IndicesFueraDeRango e) {
			System.out.println(e.getMessage() );
		}
		return ok;
	}

	public boolean eliminarCelula(Casilla casilla) {
		boolean ok = false;
		try{
			ok = this.mundo.eliminarCelula(casilla);
		}catch(Exception e){
			System.out.println("Error: Casilla fuera de rango." );
		}
		return ok;
	}

	public void vaciarMundo() {
		this.mundo.vaciarMundo();
	}
	public void iniciarMundo()
	{
		this.mundo.iniciarMundo();
	}

	public String evoluciona() {
		String aux = "";
		try {
			aux = this.mundo.evoluciona();
		} catch (CasillaLlena e) {
			System.out.println(e.getMessage() );
		} catch (IndicesFueraDeRango e) {
			System.out.println(e.getMessage() );
		}
		return aux;
	}
	public void guardaControlador(String nombFich){
		try{
			this.mundo.guardarMundo(nombFich);	 
		}catch(IOException e){
			System.out.println(e.getMessage() );
		}
	}
	public void cargarControlador(String nombFich) {
	//Practicamente todo lo que habia aquí esta ahora en mundo (las cosas de mundo las hace mundo)
		try{
			this.mundo = this.mundo.cargarMundo(nombFich);		
		}catch(IOException e){
			System.out.println("Error: El fichero no existe" );
		} catch (FormatoNumericoIncorrecto e) {
			System.out.println(e.getMessage() );
		}
	
	}
	public void iniMundo(Mundo mundo){
		this.mundo = mundo;
		try {
			this.mundo.inicializaMundo();
		} catch (ErrorDeInicializacion e) {
			System.out.println(e.getMessage() );
		} catch (TipoCelulaDesconocido e) {
			System.out.println(e.getMessage() );
		} catch (CasillaLlena e){
			System.out.println(e.getMessage() );
		} catch (IndicesFueraDeRango e) {
			System.out.println(e.getMessage() );
		}
		
	}
}

