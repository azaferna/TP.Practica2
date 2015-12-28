package tp.pr1.control;


import java.util.Scanner;

import tp.pr1.control.comando.Comando;
import tp.pr1.logic.Casilla;
import tp.pr1.logic.mundo.Mundo;


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
	 */
	public void procesarComando(String cadena){
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
	 */
	public void lanzarAplicacion(){
		
		String comando = "JUGAR COMPLEJO 3 3 8 1"; 
		/*Para inicializar en la primera vuelta
		 * (si cambias ese comando por otro que no sea del tipo jugar dara fallo)
		 * es una forma de inicializar el mundo por defecto
		 */
		
		this.procesarComando(comando);
		
		while(!this.simulacionTerminada){
			comando = this.leerComando();
			this.procesarComando(comando);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*////////////////////////////// CODIGO NUEVO (FUNCIONES DE PASO ENTRE COMANDO Y MUNDO) //////////////////////////////////*/
	public boolean crearCelulaCompleja(Casilla casilla)	{
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
	public void guardaControlador(String nombFich)
	{
		 this.mundo.guardarMundo(nombFich);
	}
	public void cargarControlador(String nombFich) 
	//Practicamente todo lo que habia aquí esta ahora en mundo (las cosas de mundo las hace mundo)
	{
		this.mundo = this.mundo.cargarMundo(nombFich);		
	
	}
	public void iniMundo(Mundo mundo)
	{
		this.mundo = mundo;
		this.mundo.inicializaMundo();
	}
}

