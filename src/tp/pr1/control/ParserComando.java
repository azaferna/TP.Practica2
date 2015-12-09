package tp.pr1.control;
import tp.pr1.control.comando.Ayuda;
import tp.pr1.control.comando.Comando;
import tp.pr1.control.comando.CrearCelulaSimple;
import tp.pr1.control.comando.CrearCelulaCompleja;
import tp.pr1.control.comando.EliminarCelula;
import tp.pr1.control.comando.Iniciar;
import tp.pr1.control.comando.Paso;
import tp.pr1.control.comando.Salir;
import tp.pr1.control.comando.Vaciar;



public class ParserComando {
	private static final int MAXCOMANDOS = 8;
	private static final Comando comandos[] = { new Iniciar(), new CrearCelulaSimple(), new CrearCelulaCompleja(),  new Paso(), new Salir(), 
			new Vaciar(), new EliminarCelula(), new Ayuda()            };
	
	public Comando parseaComando(String[] cadenaString)
	{
		Comando aux = null;
		int i = 0;
		while(i < MAXCOMANDOS && aux == null ){
			aux = comandos[i].parsea(cadenaString);
			i++;
		}
		return aux;
	}
	public String AyudaComando()
	{
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < MAXCOMANDOS; i++)
		{
			builder.append(comandos[i].textoAyuda());
		}
		return builder.toString();
	}
}