package tp.pr1.control.comando;

import tp.pr1.control.ParserComando;
import tp.pr1.control.Controlador;

public class Ayuda extends Comando{

	@Override
	public String ejecuta(Controlador control) {
		
		ParserComando parser = new ParserComando();
		return"POSIBLES COMANDOS:" + '\n' + parser.AyudaComando();
	}

	@Override
	public Comando parsea(String[] cadena) {
		Comando aux = null;
		if(cadena.length ==  1 && cadena[0].equalsIgnoreCase("AYUDA"))
			aux = this;
		
		return aux;
	}

	@Override
	public String textoAyuda() {
		
		return "	AYUDA: muestra esta ayuda." + '\n';
	}

	
}
