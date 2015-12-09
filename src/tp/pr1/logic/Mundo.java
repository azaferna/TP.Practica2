package tp.pr1.logic;

public class Mundo {
	private Superficie superficie;
	private boolean simulacionTerminada;
	

	public static final int INICELLSIMPLES = 12; // Número inicial de células en la superficie
	public static final int INICELLCOMPLEJAS = 0;
	public static final int INIFIL = 3;//Numero inicial de filas
	public static final int INICOL = 4;// Número inicial de columnas
	
	
	//Inicializa los atributos del Mundo
	public Mundo()
	{
		this.superficie = new Superficie(INIFIL, INICOL);
		this.llenarNCelulasAleatorias();
		this.simulacionTerminada = true;
	}
	
	public boolean isSimulacionTerminada() {
		return simulacionTerminada;
	}
	
	public void setSimulacionTerminada(boolean simulacionTerminada) {
		this.simulacionTerminada = simulacionTerminada;
	}
	
	//Da un paso en el mundo
	public String evoluciona(){
		Casilla[] llenas = new Casilla[this.superficie.getFil() * this.superficie.getCol()];
		int indice = casillasLlenas(llenas);
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < indice; i++){
				if(this.superficie.casillaLlena(llenas[i]) ){
					builder.append(this.superficie.ejecutaMovimiento(llenas[i]));
			}
		}
		return builder.toString();
	}
	
	//Devuelve un string con el dibujo del mundo
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.superficie.toString());
		return builder.toString();
	}
	
	//Genera una casilla vacia contigua a la introducida
	
	
	

	//Devuelve el indice de un array con todas las casillas llenas del tablero
	public int casillasLlenas(Casilla[] llenas){
		int cont = 0;
	
		for (int fil = 0; fil < this.superficie.getFil(); fil++) {
			for (int col = 0; col < this.superficie.getCol(); col++) {
				Casilla casilla = new Casilla(fil, col);
				if(this.superficie.casillaLlena(casilla) ){
					llenas[cont] = new Casilla(fil, col);
					cont++;
				}
			}
		}
				return cont;	
	}

	//Pone todas las casillas de la superficie a null
	public void vaciarMundo(){
		this.superficie.todasANull();
	}
	
	//Llena n celulas si es capaz, si no devuelve false
	public boolean llenarNCelulasAleatorias(){
		boolean ok = false;
		if( ((INICELLSIMPLES + INICELLCOMPLEJAS) <= INIFIL*INICOL) && this.superficie.llenarNCeluslasLibres(INICELLSIMPLES,INICELLCOMPLEJAS ))
			ok = true;
		return ok;
		
	}
	
	//Crea una celula en la casilla devuelve true si es posible
	public boolean crearCelulaSimple(Casilla casilla){
		return this.superficie.crearCelulaSimple(casilla);
	}
	public boolean crearCelulaCompleja(Casilla casilla){
		return this.superficie.crearCelulaCompleja(casilla);
	}
	
	//Elimina una celula si es posible
	public boolean eliminarCelula(Casilla casilla){
		return this.superficie.eliminarCelula(casilla);
	}

}
