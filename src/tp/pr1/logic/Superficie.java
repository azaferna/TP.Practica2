package tp.pr1.logic;

public class Superficie {
	private Celula[][] superficie;
	private int filas;
	private int columnas;
	
	/* 						FUNCIONES DE INICIALIZACION					*/
	
	/**
	 * Constructor de una superficie
	 */
	public Superficie(int filas, int columnas)
	{	
		this.filas = filas;
		this.columnas = columnas;
		
		this.superficie = new Celula[this.filas][this.columnas];
		this.todasANull();
	}		
	
	/**
	 * Pone todas las celdas a null
	 */
	public void todasANull()
	{
		for(int f = 0; f < this.filas; f++)
		{
			for(int c = 0; c < this.columnas; c++)
			{
				this.superficie[f][c] = null;
			}
		}
	}
	
	/**
	 * @return superficie.filas
	 */
	public int getFil()
	{
		return this.filas;
	}
	
	/**
	 * @return superficie.columnas
	 */
	public int getCol()
	{
		return this.columnas;
	}
	
	/**
	 * @return true si la casilla esta dentro de la superficie
	 */
	private boolean dentroDeSuperficie(Casilla casilla){
		return ((casilla.getFila() < this.filas && casilla.getFila() >= 0) && (casilla.getColumna() < this.columnas && casilla.getColumna() >=0));
	}
	
	/*---------------------------------------------------------------------*/
	
	
	/*							FUNCIONES DE PROCESAMIENTO				   */
	
	/**
	 * Crea una célula en la casilla.
	 * @param casilla
	 * @return celulaCreada;
	 */
	public boolean crearCelula(Casilla casilla)
	{
		boolean ok = false;
		
		if(this.dentroDeSuperficie(casilla) && !this.casillaLlena(casilla))
		{
			this.superficie[casilla.getFila()][casilla.getColumna()] = new Celula();
			ok = true;
		}
	
		return ok;
	}
	
	/**
	 * Elimina la celula de la casilla. 
	 * @param casilla 			-> Posicion de la celula a eliminar.
	 * @return celulaEliminada  -> true si la celula se ha eliminado correctamente
	 */
	public boolean eliminarCelula(Casilla casilla)
	{
		boolean ok = false;
		if( this.dentroDeSuperficie(casilla) && this.casillaLlena(casilla) )
		{
			this.superficie[casilla.getFila()][casilla.getColumna()] = null;
			ok = true;
		}
		
		return ok;
	}
	
	/**
	 * Crea n celulas en la superficie
	 * @param     n -> Numero de celulas a crear
	 * @return true -> Si se han podido crear todas.
	 */
	public boolean llenarNCeluslasLibres(int n)
	{
		boolean ok = false;
		int col = 0, fil = 0;
		int cont = 0;
		for (int i = 0; i < n; i++)
		{	
			while(!ok)
			{
				//x =(int)Math.random() * n //genera un numero entero entre 0 y n

				fil = (int) (Math.random()*this.filas);
				col = (int) (Math.random()*this.columnas);
				
				Casilla casilla = new Casilla(fil, col);
				if(this.crearCelula(casilla))
				{
					ok = true;
					cont++;
				}
			}
			ok = false;
		}
		return (cont == n);
	}
	

	/**
	 * Mueve la celula de casV a casN (una posicion adyacente a ella vacia)
	 * @param  casV -> casilla vieja
	 * @param  casN -> casilla nueva
	 * @return true -> si lo ha podido mover correctamente
	 */
	public boolean moverCelula(Casilla casV, Casilla casN)
	{
		int filV = casV.getFila(), colV = casV.getColumna();
		boolean ok = false;
		
		if(casN != null)
		{ 	
			int filN = casN.getFila(), colN = casN.getColumna();
			
			this.superficie[filN][colN] = this.superficie[filV][colV];
			this.eliminarCelula(casV);
			
			
			ok = true;
		}
			
		return ok;
	}
	
	//Devuelve un String con el dibujo del tablero.
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("");
		for(int fil = 0; fil < this.filas; fil++)
		{
			for(int col = 0; col < this.columnas; col++)
			{
				if(this.superficie[fil][col] == null)
					builder.append("  - ");
				else
					builder.append(this.superficie[fil][col].toString());
			}
			
			builder.append('\n');
		}

		
		return builder.toString();
	}

	//Devuelve true si la casilla esta ocupada por una celula;
	public boolean casillaLlena(Casilla casilla){
		return (this.superficie[casilla.getFila()][casilla.getColumna()] != null);
	}
		
	//Crea un array con todas las casillas vacias que hay alrededor de una celula
	public int arrayVaciasAlrededor(Casilla casilla, Casilla[] casVacias)
		{	
		int indice = 0;
		int fil = casilla.getFila(), col = casilla.getColumna();
			//Crea un array de casillas vacias alrededor de la celula
			for(int f = fil - 1;  f <= (fil + 1) ; f++){
				for(int c = col - 1; c <= (col + 1); c++){
					if(( f >= 0 && f < this.filas ) && ( c >= 0 && c < this.columnas) ){
						Casilla cas = new Casilla(f, c);
						if ( !this.casillaLlena(cas) ){
							casVacias[indice] = new Casilla(cas.getFila(), cas.getColumna());
							indice++;
						}
					}
				}
				
			}
			return indice;
		}
	
	//Si puede resta pasos de reproduccion
	public boolean menosPasosRep(Casilla casilla){
		return this.superficie[casilla.getFila()][casilla.getColumna()].menosPasosRep();
	}
	
	//Si puede resta pasos de muerte
	public boolean menosPasosMover(Casilla casilla){
		return this.superficie[casilla.getFila()][casilla.getColumna()].menosSinMover();
	}
	
	//Reincia los pasos de reproduccion
	public void iniPasosReproduccion(Casilla casilla){
		this.superficie[casilla.getFila()][casilla.getColumna()].iniReproduccion();
	}
	
}









