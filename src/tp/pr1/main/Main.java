/*
 * PRACTICA REALIZADA POR JONH BYRON SANCHEZ JIMENEZ Y AZAHARA FERNANDEZ NOTARIO
 * CURSO 2ºC
 *
 * */

package tp.pr1.main;

import tp.pr1.control.Controlador;
import tp.pr1.control.excepciones.FormatoNumericoIncorrecto;

public class Main {

	/**
	 * Main, crea un controlador y lanza la aplicaci�n.
	 * @param args
	 * @throws FormatoNumericoIncorrecto 
	 */
	public static void main(String[] args) 
	{
		Controlador controlador = new Controlador();
		controlador.lanzarAplicacion();
	}

}

