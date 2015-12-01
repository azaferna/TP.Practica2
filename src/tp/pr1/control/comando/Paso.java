package tp.pr1.control.comando;

import tp.pr1.control.Controlador;
import tp.pr1.logic.Mundo;

public class Paso extends Comando {

	
	public String ejecuta(Mundo mundo) {
		return mundo.evoluciona() + mundo.toString();
	}

	public Comando parsea(String[] cadena) {
		Comando aux = null;
		if(cadena.length ==  1 && cadena[0].equalsIgnoreCase("PASO"))
			aux = this;
		
		return aux;
	}

	@Override
	public String textoAyuda() {
		return "	PASO: realiza un paso en la simulacion." + '\n';
	}

}
