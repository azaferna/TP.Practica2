package tp.pr1.control.comando;

import tp.pr1.control.Controlador;
import tp.pr1.logic.Casilla;

public class CrearCelulaCompleja implements Comando{

	private Casilla casilla;
	
	public String ejecuta(Controlador control)
	{
		
		if (control.crearCelulaCompleja(casilla))
			return"Creamos una nueva celula en la posicion " + casilla.toString() + '\n' + control.stringMundo();
		else
			return "Error a crear una celula";
		
		
	}

	@Override
	public Comando parsea(String[] cadena) { 
		Comando aux = null;
		if(cadena.length ==  3 && cadena[0].equalsIgnoreCase("CREARCELULACOMPLEJA"))
		{
			int i = Integer.parseInt(cadena[1]);
			int j = Integer.parseInt(cadena[2]);
			
			this.casilla = new Casilla(i, j);

			aux = this;
		}
		
		return aux;
	}

	@Override
	public String textoAyuda() {
		
		return "	CREARCELULACOMPLEJA F C :crea una nueva celula en la posición (f,c) si es posible." + '\n';
	}

}
