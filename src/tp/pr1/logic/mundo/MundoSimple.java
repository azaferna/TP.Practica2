package tp.pr1.logic.mundo;

import java.io.FileWriter;
import java.io.IOException;


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
	public void inicializaMundo()
	{
		this.llenarNCelulasAleatorias(1, this.simples);
	}
	public void guardarMundo(String nombFich)
	{
		try{
		 FileWriter fw = new FileWriter (nombFich + ".txt");
		
			 fw.write("simple");
		 	 fw.write(System.getProperty("line.separator"));
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


