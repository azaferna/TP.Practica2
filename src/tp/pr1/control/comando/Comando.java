package tp.pr1.control.comando;
import tp.pr1.logic.Mundo;

public abstract class Comando {
	
	public abstract String ejecuta(Mundo mundo);
	public abstract Comando parsea(String[] cadena);
	public abstract String textoAyuda();
	
}
