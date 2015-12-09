package tp.pr1.logic;

public class Casilla {
	private int fila;
	private int columna;
	private boolean movida;

	//Crea una variable casilla
	public Casilla(int fil, int col) {
		this.fila = fil;
		this.columna = col;
		this.movida = false;

	}

	//Devuelve la fila de una casilla
	public int getFila() {
		return this.fila;
	}

	//Devuelve la columna de una casilla
	public int getColumna() {
		return this.columna;
	}
	
	public void setMovida(boolean mov)
	{
		this.movida = mov;
	}
	public boolean getMovida()
	{
		return this.movida;
	}
	//Devuelve un string que pinta la posicion de la casilla
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("( " + this.fila + "," + this.columna + " )");
		return builder.toString();
	}

	
}
