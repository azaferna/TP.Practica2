package tp.pr1.control.comando;
import tp.pr1.control.Controlador;

public class Iniciar implements Comando {

	public Iniciar(){}
	
	public String ejecuta(Controlador control) {
		
		control.iniciarMundo();
		/*if ()//Cambiar por iniciar cuando este hecho
			return "Error al inicializar" + '\n'+ control.stringMundo().toString();
		else
			return "Reiniciando mundo..."+ '\n' + control.stringMundo().toString();
		*/
		return control.stringMundo();
		 
	}

	public Comando parsea(String[] cadena) { 
		Comando aux = null;
		if(cadena.length ==  1 && cadena[0].equalsIgnoreCase("INICIAR"))
			aux = this;
		
		return aux;
	}

	public String textoAyuda() {
		return "	INICIAR: inicia una nueva simulación." + '\n';
	}

}
