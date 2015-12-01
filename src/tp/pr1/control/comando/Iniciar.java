package tp.pr1.control.comando;

import tp.pr1.control.Controlador;
import tp.pr1.logic.Mundo;

public class Iniciar extends Comando {

	public Iniciar(){}
	
	public String ejecuta(Mundo mundo) {
		
		mundo.vaciarMundo();
		if (!mundo.llenarNCelulasAleatorias())
			return "Error al inicializar" + '\n'+ mundo.toString();
		else
			return "Reiniciando mundo..."+ '\n' + mundo.toString();
			
		 
	}

	@Override
	public Comando parsea(String[] cadena) { 
		Comando aux = null;
		if(cadena.length ==  1 && cadena[0].equalsIgnoreCase("INICIAR"))
			aux = this;
		
		return aux;
	}

	@Override
	public String textoAyuda() {
		
		return "	INICIAR: inicia una nueva simulación." + '\n';
	}

}
