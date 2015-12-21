package tp.pr1.control.comando;

import tp.pr1.control.Controlador;

public class Vaciar implements Comando{

	@Override
	public String ejecuta(Controlador control) {
		
		control.vaciarMundo();
		return "Vaciando mundo..."+ '\n' + control.stringMundo();
		
				
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
