package tp.pr1.logic;

public class Casilla {
	private int fila;
	private int columna;

	//Crea una variable casilla
	public Casilla(int fil, int col) {
		this.fila = fil;
		this.columna = col;
	}

	//Devuelve la fila de una casilla
	public int getFila() {
		return this.fila;
	}

	//Devuelve la columna de una casilla
	public int getColumna() {
		return this.columna;
	}

	//Devuelve un string que pinta la posicion de la casilla
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("( " + this.fila + "," + this.columna + " )");
		return builder.toString();
	}

	public boolean esIgual(Casilla casilla){
		return (this.fila == casilla.fila && this.columna == casilla.columna);
	}
	
}
