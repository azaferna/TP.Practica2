package tp.pr1.control.comando;
import tp.pr1.control.Controlador;

public class Guardar implements Comando {

	private String nombFich;
	@Override
	public String ejecuta(Controlador control){
		control.guardaControlador( nombFich);
		return "Mundo guardado: " + '\n' + control.stringMundo();
	}

	@Override
	public Comando parsea(String[] cadena) {
		Comando aux = null;
		if(cadena.length ==  2 && cadena[0].equalsIgnoreCase("GUARDAR")){
			this.nombFich = cadena[1];
			aux = this;
		}
		return aux;
	}

	@Override
	public String textoAyuda() {
		return "Guarda la partida";
	}

}
