package tp.pr1.logic.mundo;

import java.io.FileWriter;
import java.io.IOException;

import tp.pr1.control.excepciones.CasillaLlena;
import tp.pr1.control.excepciones.ErrorDeInicializacion;
import tp.pr1.control.excepciones.IndicesFueraDeRango;
import tp.pr1.control.excepciones.TipoCelulaDesconocido;
import tp.pr1.logic.Casilla;
import tp.pr1.logic.celula.CelulaCompleja;
import tp.pr1.logic.celula.CelulaSimple;


public class MundoComplejo extends Mundo {	
	
	
	private int simples;
	private int complejas;
	/**
	 * Inicializa los atributos del Mundo, cuenta con una superficie, inicializa un cierto n�mero de c�lulas y el par�metro de simulaci�n terminada a true;
	 */
	public MundoComplejo(int fil, int col, int simples, int complejas){
		super(fil, col);
		this.simples = simples;
		this.complejas = complejas;
	}
	
	@Override
	public void inicializaMundo() throws TipoCelulaDesconocido, ErrorDeInicializacion, CasillaLlena, IndicesFueraDeRango{
		if((this.columnas * this.filas ) >= this.simples){
			this.llenarNCelulasAleatorias(TiposDeCelulas.Simple, this.simples);
			this.llenarNCelulasAleatorias(TiposDeCelulas.Compleja, this.complejas);
		}else
			throw new ErrorDeInicializacion("Error: No se puede iniciar el mundo con tantas células. El mundo se queda vacio.");
	}
	public void guardarMundo(String nombFich)throws IOException{
		FileWriter fw = new FileWriter (nombFich + ".txt");
		try{
			 fw.write("complejo");
		 	 fw.write(System.getProperty("line.separator"));
		 	 
		 	//Guardar mundo
		 	 fw.write(Integer.toString (this.filas));
		 	 fw.write(System.getProperty("line.separator"));
		 	 fw.write(Integer.toString(this.columnas));
		 	 fw.write(System.getProperty("line.separator"));
		 	 this.superficie.guardarSuperficie(fw);
		 }
		catch (IOException e){
            throw new IOException("Error al guardar el juego.");
		}
		finally{
			fw.close();
		}
	}

	//El tipo mejor cambiarlo por un enumerado
	public boolean crearCelula(Casilla casilla, int tipo) throws TipoCelulaDesconocido, CasillaLlena, IndicesFueraDeRango{
		TiposDeCelulas t1 = TiposDeCelulas.Simple, t2 = TiposDeCelulas.Compleja;
		boolean ok = true;
		if(tipo == t1.ordinal() + 1)
			this.superficie.crearCelula(casilla, new CelulaSimple());
		else if(tipo == t2.ordinal() + 1)
			this.superficie.crearCelula(casilla, new CelulaCompleja());
		else{
			ok = false;
			throw new TipoCelulaDesconocido("Error: Tipo de celula desconocido.");
		}
		return ok;
	}
		
		

}
