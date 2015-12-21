package tp.pr1.logic.mundo;

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


}
