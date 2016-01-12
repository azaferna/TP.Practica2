package tp.pr1.logic.mundo;

import java.io.FileWriter;
import java.io.IOException;

import tp.pr1.control.excepciones.CasillaLlena;
import tp.pr1.control.excepciones.ErrorDeInicializacion;
import tp.pr1.control.excepciones.IndicesFueraDeRango;
import tp.pr1.control.excepciones.TipoCelulaDesconocido;
import tp.pr1.logic.Casilla;
import tp.pr1.logic.celula.CelulaSimple;


public class MundoSimple extends Mundo {
	private int simples;
	/**
	 * Constructor MundoSimple
	 * @param filas
	 * @param columnas
	 * @param numero de simples
	 */
	public MundoSimple(int filas, int columnas, int simples)
	{
		super(filas, columnas);
		this.simples = simples;
	}
	
	@Override
	public void inicializaMundo() throws ErrorDeInicializacion, TipoCelulaDesconocido, CasillaLlena, IndicesFueraDeRango{
		if((this.columnas * this.filas ) >= this.simples)
			this.llenarNCelulasAleatorias(TiposDeCelulas.Simple, this.simples);
		else
			throw new ErrorDeInicializacion("Error: No se puede iniciar el mundo con tantas células. El mundo se queda vacio");
		
	}
	
	public void guardarMundo(String nombFich)throws IOException{
		FileWriter fw = new FileWriter (nombFich + ".txt");
		try{		
			 fw.write("simple");
		 	 fw.write(System.getProperty("line.separator"));
		 	 fw.write(Integer.toString (this.filas));
		 	 fw.write(System.getProperty("line.separator"));
		 	 fw.write(Integer.toString(this.columnas));
		 	 fw.write(System.getProperty("line.separator"));
		 	 this.superficie.guardarSuperficie(fw);
		 }
		catch (IOException e){
            throw new IOException("Error al guardar el juego");
		}
		finally{
			fw.close();
		}
	}

	@Override
	public boolean crearCelula(Casilla casilla, int tipo) throws TipoCelulaDesconocido, CasillaLlena, IndicesFueraDeRango{
		TiposDeCelulas t1 = TiposDeCelulas.Simple;
		boolean ok = true;
		if( tipo == t1.ordinal() + 1 )
			this.superficie.crearCelula(casilla, new CelulaSimple());
		else{
			ok = false;
			throw new TipoCelulaDesconocido("Tipo de celula desconocido para el mundo actual");
		}
		return ok;
	}
	
}


