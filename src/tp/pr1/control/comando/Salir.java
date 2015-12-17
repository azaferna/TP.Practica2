package tp.pr1.control.comando;
import tp.pr1.control.Controlador;

public class Salir extends Comando{
	
	public String ejecuta(Controlador control) {
		control.setSimulacionTerminada(true);
		return "Fin de la simulacion...";
	}

	
	public Comando parsea(String[] cadena) {
		Comando aux = null;
		if(cadena.length ==  1 && cadena[0].equalsIgnoreCase("SALIR"))
			aux = this;

		return aux;
	}

	public String textoAyuda() {
		return "	SALIR: cierra la aplicación" + '\n';
	}

}
