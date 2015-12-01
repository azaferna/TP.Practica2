package tp.pr1.control.comando;

import tp.pr1.logic.Mundo;
import tp.pr1.control.ParserComando;
public class Ayuda extends Comando{

	
	public String ejecuta(Mundo mundo) {
		
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
