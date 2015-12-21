package tp.pr1.logic.mundo;

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
}


