package tp.pr1.control.comando;

import tp.pr1.control.Controlador;
import tp.pr1.control.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.logic.mundo.MundoSimple;
import tp.pr1.logic.mundo.MundoComplejo;
import tp.pr1.logic.mundo.Mundo;

public class Jugar implements Comando {
	private Mundo mundo;
	
	@Override
	public String ejecuta(Controlador control){
		control.iniMundo(this.mundo);
		return control.stringMundo();
	}

	@Override
	public Comando parsea(String[] cadena) throws FormatoNumericoIncorrecto { 
		Comando aux = null;
		int f, c, ns;
		if(cadena.length > 3 && cadena[0].equalsIgnoreCase("JUGAR")){
			try{
				f = Integer.parseInt(cadena[2]);
				c = Integer.parseInt(cadena[3]);
				ns = Integer.parseInt(cadena[4]);
			}catch(Exception e){
				throw new FormatoNumericoIncorrecto("Error: Paraetros no validos");
			}	
			if ( cadena.length == 5 && cadena[1].equalsIgnoreCase("SIMPLE") ){
				this.mundo = new MundoSimple( f, c, ns );
				aux = this;
			}
			else if(cadena.length == 6 && cadena[1].equalsIgnoreCase("COMPLEJO") ){
				int nc = Integer.parseInt(cadena[5]);
				this.mundo = new MundoComplejo(f, c, ns, nc);
				aux = this;
			}else
				throw new FormatoNumericoIncorrecto("Error: Tipo de mundo no valido");
			
		}
		
		return aux;
	}
	@Override
	public String textoAyuda() {
		return "	JUGAR (Tipo) (filas) (columnas) (celulas tipo1) (tipo2) (tipo3)...: inicializa un juego con el mundo y sus parametros a elegir "+ '\n';
	}

}
