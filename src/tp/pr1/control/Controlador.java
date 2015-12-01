package tp.pr1.control;

import java.util.Scanner;
import tp.pr1.logic.Mundo;
import tp.pr1.logic.Casilla;
import tp.pr1.control.comando.Comando;
import tp.pr1.control.comando.Salir;

public class Controlador 
{
	private Mundo mundo;
	private ParserComando parser;
	private Scanner entrada;
	
	
	//Crea el juego
	public Controlador(){
		this.mundo = new Mundo();
		this.entrada = new Scanner(System.in);
		this.parser = new ParserComando();
	}

	
	public void procesarComando(String cadena)
	{
		String[] array = cadena.split(" ");
		Comando comando = this.parser.parseaComando(array);
		if(comando != null)
			System.out.println(comando.ejecuta(this.mundo));
		
	}
	
	//pide un comando por teclado y lo devuelve como un string
	private String leerComando(){	
		System.out.print("Comando >");
		return this.entrada.nextLine();
	}
	

	
	//Devuelve false mientras la opcion no sea salir y ejecuta las distintas opciones del juego
	/*private boolean menu(int opcion){
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
		
	}*/
	
	//Lanza la aplicacion
	public void lanzarAplicacion(){
		
		this.pintarMundo();
		
		String comando;
		
		while(this.mundo.isSimulacionTerminada()){
			comando = this.leerComando();
			procesarComando(comando);
		}
	}
	
	//Pinta el mundo
	private void pintarMundo(){
		System.out.println(this.mundo.toString());
	}
}

