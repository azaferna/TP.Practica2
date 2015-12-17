package tp.pr1.logic;

public class Casilla {
	private int fila;
	private int columna;

	/**
	 * Dados dos parametros fila y columna crea una casilla.
	 * @param fil -> fila
	 * @param col -> columna
	 */
	public Casilla(int fil, int col) {
		this.fila = fil;
		this.columna = col;
	}

	/**
	 * Devuelve la fila de una casilla.
	 * @return fila
	 */
	public int getFila() {
		return this.fila;
	}

	/**
	 * Devuelve la columna de una casilla.
	 * @return columna
	 */
	public int getColumna() {
		return this.columna;
	}

	/**
	 * Pinta la posicion de la casilla.
	 * @return string de la posicion pintada.
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("( " + this.fila + "," + this.columna + " )");
		return builder.toString();
	}
	
	/**
	 * Procesa si dos casillas son iguales.
	 * @param casilla -> con la que comparamos
	 * @return bool con la informacion si es cierto o no que son iguales.
	 */
	public boolean esIgual(Casilla casilla){
		return (this.fila == casilla.fila && this.columna == casilla.columna);
	}
	
	
}
