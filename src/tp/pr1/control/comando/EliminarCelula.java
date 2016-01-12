package tp.pr1.control.comando;
import tp.pr1.control.Controlador;
import tp.pr1.control.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.logic.Casilla;

public class EliminarCelula implements Comando{

	private Casilla casilla;
	
	public String ejecuta(Controlador control) {
		
		if (control.eliminarCelula(casilla))
			return "Eliminamos la celula de la posicion " + casilla.toString() + '\n' + control.stringMundo();
		else return  control.stringMundo();
	}

	@Override
	public Comando parsea(String[] cadena) throws FormatoNumericoIncorrecto {
		Comando aux = null;
		if(cadena.length ==  3 && cadena[0].equalsIgnoreCase("ELIMINARCELULA")){
			try{
				int i = Integer.parseInt(cadena[1]);
				int j = Integer.parseInt(cadena[2]);
				
				this.casilla = new Casilla(i, j);
				aux = this;
			}catch(Exception e){
				throw new FormatoNumericoIncorrecto("La casilla introducida no tiene el formato correcto");
			}
			
		}
		
		return aux;
		
	}

	@Override
	public String textoAyuda() {
	
		return "	ELIMINARCELULA F C :elimina una celula de la posición (f,c) si es posible.)" + '\n';
	}

}
