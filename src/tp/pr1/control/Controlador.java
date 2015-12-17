package tp.pr1.control;

import java.util.Scanner;

import tp.pr1.control.comando.Comando;
import tp.pr1.logic.Casilla;
import tp.pr1.logic.mundo.Mundo;
import tp.pr1.logic.mundo.MundoComplejo;
import tp.pr1.logic.mundo.MundoSimple;

public class Controlador 
{
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
		this.mundo = new MundoSimple(3, 3, 2);
	}
	
	/*
	/**
	 * Encargado de comprobar el valor de la variable simulaci�n terminada.
	 * @return bool simulacionterminada.
	 *
	public boolean isSimulacionTerminada() {
		return simulacionTerminada;
	}
	*/
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
 */
	public void procesarComando(String cadena)
	{
		String[] array = cadena.split(" ");
		Comando comando = this.parser.parseaComando(array);
		if(comando != null)
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
	
	/**
	 * Pinta el mundo.
	 */
	private void pintarMundo(){
		System.out.println(this.mundo.toString());
	}
	public String stringMundo()
	{
		return this.mundo.toString();
	}
	
	/**
	 * Ejecuta la aplicacion hasta su fin.
	 */
	public void lanzarAplicacion(){
		
		this.mundo.inicializaMundo();
		this.pintarMundo();
		
		String comando;
		
		while(!this.simulacionTerminada){
			comando = this.leerComando();
			procesarComando(comando);
		}
	}
	
	public boolean crearCelulaCompleja(Casilla casilla)
	{
		return this.mundo.crearCelulaCompleja(casilla);
	}

	public boolean crearCelulaSimple(Casilla casilla) {
		return this.mundo.crearCelulaSimple(casilla);
	}

	public boolean eliminarCelula(Casilla casilla) {
		return this.mundo.eliminarCelula(casilla);
	}

	public void vaciarMundo() {
		this.mundo.vaciarMundo();
	}

	public String evoluciona() {
		return this.mundo.evoluciona();
	}
	
	
	
	//Crear funciones de paso para todos los comandos con mundo.(vacia, inicia, elimina...)
}

