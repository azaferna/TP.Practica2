package tp.pr1.control.comando;

import tp.pr1.control.Controlador;
import tp.pr1.logic.mundo.MundoSimple;
import tp.pr1.logic.mundo.MundoComplejo;
import tp.pr1.logic.mundo.Mundo;

public class Jugar implements Comando {
	private Mundo mundo;
	
	@Override
	public String ejecuta(Controlador control) {
		control.iniMundo(this.mundo);
		return control.stringMundo();
	}

	@Override
	public Comando parsea(String[] cadena) { 
		Comando aux = null;
		if(cadena.length > 3 && cadena[0].equalsIgnoreCase("JUGAR"))
		{
			int f = Integer.parseInt(cadena[2]);
			int c = Integer.parseInt(cadena[3]);
			int ns = Integer.parseInt(cadena[4]);
			
			if ( cadena.length == 5 && cadena[1].equalsIgnoreCase("SIMPLE") ){
				this.mundo = new MundoSimple( f, c, ns );
				aux = this;
			}
			if(cadena.length == 6 && cadena[1].equalsIgnoreCase("COMPLEJO") ){
				int nc = Integer.parseInt(cadena[5]);
				this.mundo = new MundoComplejo(f, c, ns, nc);
				aux = this;
			}
		}
		
		return aux;
	}
	@Override
	public String textoAyuda() {
		// TODO Auto-generated method stub
		return null;
	}

}
