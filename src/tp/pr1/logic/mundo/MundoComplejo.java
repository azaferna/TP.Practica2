package tp.pr1.logic.mundo;

import java.io.FileWriter;
import java.io.IOException;


public class MundoComplejo extends Mundo {	
	
	
	private int simples;
	private int complejas;
	/**
	 * Inicializa los atributos del Mundo, cuenta con una superficie, inicializa un cierto n�mero de c�lulas y el par�metro de simulaci�n terminada a true;
	 */
	public MundoComplejo(int fil, int col, int simples, int complejas)
	{
		super(fil, col);
		this.simples = simples;
		this.complejas = complejas;
	}
	
	@Override
	public void inicializaMundo()
	{
		this.llenarNCelulasAleatorias(1, this.simples);
		this.llenarNCelulasAleatorias(2, this.complejas);
	}
	public void guardarMundo(String nombFich)
	{
		try{
		 FileWriter fw = new FileWriter (nombFich + ".txt");
		
			 fw.write("complejo");
		 	 fw.write(System.getProperty("line.separator"));
		 	 
		 	//Guardar mundo
		 	 fw.write(Integer.toString (this.filas));
		 	 fw.write(System.getProperty("line.separator"));
		 	 fw.write(Integer.toString(this.columnas));
		 	 fw.write(System.getProperty("line.separator"));
		 	 this.superficie.guardarSuperficie(fw);
		 fw.close();
		 }
		catch (IOException e){
            System.out.println("Error E/S: "+e);
		}
	}


		
		

}
