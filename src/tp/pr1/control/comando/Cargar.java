package tp.pr1.control.comando;
import tp.pr1.control.Controlador;

public class Cargar implements Comando {

	private String nombFich;
	
	@Override
	public String ejecuta(Controlador control) {

	//Problema cuando carga mal. No lo indica
		control.cargarControlador(nombFich);
		return "Mundo cargado: " + '\n' + control.stringMundo();
	}

	@Override 
	public Comando parsea(String[] cadena) {
		Comando aux = null;
		if(cadena.length ==  2 && cadena[0].equalsIgnoreCase("CARGAR")){
			this.nombFich = cadena[1];
			aux = this;
			
		}
		return aux;
	}

	@Override
	public String textoAyuda() {
		return "Carga una partida desde fichero";
	}

}
