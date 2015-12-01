package tp.pr1.control.comando;

import tp.pr1.logic.Mundo;

public class Vaciar extends Comando{

	@Override
	public String ejecuta(Mundo mundo) {
		
		mundo.vaciarMundo();
		return "Vaciando mundo..."+ '\n' + mundo.toString();
		
				
	}

	@Override
	public Comando parsea(String[] cadena) {
		Comando aux = null;
		if(cadena.length ==  1 && cadena[0].equalsIgnoreCase("VACIAR"))
			aux = this;
		
		return aux;
	}

	@Override
	public String textoAyuda() {
		return "	VACIAR : Elimina todas las células del mundo." + '\n';
	}

}
