package tp.pr1.control.comando;
import java.io.IOException;

import tp.pr1.control.Controlador;
import tp.pr1.control.excepciones.ErrorDeInicializacion;
import tp.pr1.control.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.control.excepciones.TipoCelulaDesconocido;

//TRANSFORMADO EN INTERFACE TODOS LOS EXTENDS DE LAS CLASES HIJAS LOS HE CAMBIADO POR IMPLEMENTS
public interface Comando {
	
	/**
	 * Dado un mundo, ejecuta el comando correspondiente sobre el.
	 * @param mundo
	 * @return String del estado del mundo despues de ejecutar el movimiento.
	 * @throws IOException 
	 * @throws TipoCelulaDesconocido 
	 * @throws ErrorDeInicializacion 
	 */
	public abstract String ejecuta(Controlador control);
	
	/**
	 * Dada una cadena de string identifica si corresponde al comando de la clase que lo procesa.
	 * @param cadena
	 * @return Comando, null si no corresponde a esa clase. 
	 * @throws FormatoNumericoIncorrecto 
	 */
	public abstract Comando parsea(String[] cadena) throws FormatoNumericoIncorrecto;
	/**
	 * Se genera un String con la informacion del uso del comando.
	 * @return String.
	 */
	public abstract String textoAyuda();
	
}
