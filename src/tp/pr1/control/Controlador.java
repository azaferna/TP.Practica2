package tp.pr1.control;

import java.util.Scanner;
import tp.pr1.logic.Mundo;
import tp.pr1.logic.Casilla;


public class Controlador 
{
	private Mundo mundo;
	private Scanner entrada;

	//Crea el juego
	public Controlador(){
		this.mundo = new Mundo();
		this.entrada = new Scanner(System.in);
	}
	
	//Muestra la ayuda
	private String mostrarAyuda()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("POSIBLES COMANDOS:" );
		builder.append("	PASO: realiza un paso en la simulacion." + '\n');
		builder.append("	AYUDA: muestra esta ayuda." + '\n');
		builder.append("	SALIR: cierra la aplicación" + '\n');
		builder.append("	INICIAR: inicia una nueva simulación." + '\n');
		builder.append("	VACIAR : Elimina todas las células del mundo." + '\n');
		//He cambiado el texto porque "tecnicamente" no crea un nuevo mundo sino que vacia uno ya existente.
		builder.append("	CREARCELULA F C :crea una nueva celula en la posición (f,c) si es posible." + '\n');
		builder.append("	ELIMINARCELULA F C :elimina una celula de la posición (f,c) si es posible.)" + '\n');
		
		
		return builder.toString();
	}
	
	//Lee que comando es y devuelve un indice con la opcion, -1 si no existe
	/**
	 * 
	 * @param comando
	 * @return
	 */
	private int procesarComando(String comando){
		int opcion = -1;
		
		
		if(comando.equalsIgnoreCase("PASO"))
			opcion = 0;
		else if(comando.equalsIgnoreCase("AYUDA"))
			opcion = 1;
		else if(comando.equalsIgnoreCase("SALIR"))
			opcion = 2;
		else if(comando.equalsIgnoreCase("INICIAR"))
			opcion = 3;
		else if(comando.equalsIgnoreCase("VACIAR"))
			opcion = 4;
		else if(comando.equalsIgnoreCase("CREARCELULA"))
			opcion = 5;
		else if(comando.equalsIgnoreCase("ELIMINARCELULA"))
			opcion = 6;
		
		return opcion;
	}
	
	//pide un comando por teclado y lo devuelve como un string
	private String leerComando(){	
		System.out.print("Comando >");
		return this.entrada.next();
	}
	
	//Lee una posicion y la devuelve en forma de casilla
	private Casilla leerCasilla(){	
		return new Casilla( this.entrada.nextInt(), this.entrada.nextInt() ); 
	}
	
	//limpia el buffer de entrada para que no queden espacios ni saltos de linea al leer las posiciones
	private void limpiarBuffer(){
		this.entrada.nextLine();
	}
	
	//Devuelve false mientras la opcion no sea salir y ejecuta las distintas opciones del juego
	private boolean menu(int opcion){
		boolean salir = false;
		switch (opcion)
		{
			case 0://PASO
				System.out.println(this.mundo.evoluciona());
				this.pintarMundo();
				break;
				
			case 1://AYUDA
				System.out.println(this.mostrarAyuda());
				break;
				
			case 2://SALIR
				System.out.println("Fin de la simulacion...");
				salir = true;
				break;
				
			case 3://INICIAR
				System.out.println("Reiniciando mundo...");
				this.mundo.vaciarMundo();
				if (!this.mundo.llenarNCelulasAleatorias())
					System.out.println("Error al inicializar");
				this.pintarMundo();
				break;
				
			case 4://VACIAR
				System.out.println("Vaciando mundo...");
				this.mundo.vaciarMundo();
				this.pintarMundo();
				break;
				
			case 5://CREAR
				Casilla casillaC = this.leerCasilla();
				this.limpiarBuffer();
				
				if (this.mundo.crearCelula(casillaC))
				{
					System.out.println("Creamos una nueva celula en la posicion " + casillaC.toString());
					this.pintarMundo();
				}else
					System.out.println("Error a crear una celula");
				break;
				
			case 6://ELIMINAR
				Casilla casillaE = this.leerCasilla();
				this.limpiarBuffer();
				
				if (this.mundo.eliminarCelula(casillaE))
				{
					System.out.println("Eliminamos la celula de la posicion " + casillaE.toString());
					this.pintarMundo();
				}else
					System.out.println("Error al eliminar una celula");
				break;	
				
		}
		return salir;
		
	}
	
	//Lanza la aplicacion
	public void lanzarAplicacion(){
		this.pintarMundo();
		String comando = this.leerComando();
		while(!menu(this.procesarComando(comando))){
			comando = this.leerComando();
		}
	}
	
	//Pinta el mundo
	private void pintarMundo(){
		System.out.println(this.mundo.toString());
	}
	
}

