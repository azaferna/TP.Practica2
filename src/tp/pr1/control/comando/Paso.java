package tp.pr1.control.comando;
import tp.pr1.control.Controlador;

public class Paso extends Comando {

	
	public String ejecuta(Controlador control) {
		return control.evoluciona() + control.stringMundo();
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
