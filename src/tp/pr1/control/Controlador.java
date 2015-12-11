package tp.pr1.control;

import java.util.Scanner;
import tp.pr1.logic.Mundo;
import tp.pr1.control.comando.Comando;

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

