package tp.pr1.control.comando;
import tp.pr1.logic.Mundo;

public class Salir extends Comando{
	
	public String ejecuta(Mundo mundo) {
		mundo.setSimulacionTerminada(false);
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
