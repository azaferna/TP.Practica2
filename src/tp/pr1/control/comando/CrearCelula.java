package tp.pr1.control.comando;

import tp.pr1.control.Controlador;
import tp.pr1.control.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.logic.Casilla;

public class CrearCelula implements Comando{

	private Casilla casilla;
	
	public String ejecuta(Controlador control){
		if (control.crearCelula(casilla))
			return"Creamos una nueva celula en la posicion " + casilla.toString() + '\n' + control.stringMundo();
		else
			return"La celula no ha sido creada. " + '\n' + control.stringMundo();
	}

	@Override
	public Comando parsea(String[] cadena) throws FormatoNumericoIncorrecto { 
		Comando aux = null;
		if(cadena.length ==  3 && cadena[0].equalsIgnoreCase("CREARCELULA"))
		{
			try{
				int i = Integer.parseInt(cadena[1]);
				int j = Integer.parseInt(cadena[2]);
				
				this.casilla = new Casilla(i, j);
	
				aux = this;
			}catch(Exception e){
				throw new FormatoNumericoIncorrecto("La casilla introducida no es valida");
			}
		}
		
		return aux;
	}

	@Override
	public String textoAyuda() {
		
		return "	CREARCELULACOMPLEJA F C :crea una nueva celula en la posición (f,c) si es posible." + '\n';
	}

}
