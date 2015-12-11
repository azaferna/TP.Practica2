package tp.pr1.logic;

public class Mundo {
	private Superficie superficie;
	private boolean simulacionTerminada;
	

	public static final int INICELLSIMPLES = 3; // Número inicial de células en la superficie
	public static final int INICELLCOMPLEJAS = 3;
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
	
	//Da un paso en el mundo OPCION A
	public String evoluciona(){
		Casilla[] llenas = new Casilla[this.superficie.getFil() * this.superficie.getCol()];
		int nLlenas = this.casillasLlenas(llenas); 
		StringBuilder builder = new StringBuilder();
		boolean come = false;
		for(int i = 0; i < nLlenas; i++){
	
			Casilla casN = this.superficie.casillaNueva(llenas[i]);
			if(casN != null && this.superficie.casillaLlena(casN))
				come = this.superficie.casillaComestible(casN); // Esto puede estar ahí?? 
				//Si la celula a moverse es compleja y genera una posicion donde ya hay una compleja(no se commen entre ellas no  comerá)
				//por lo cual necesito saberlo y es logica del mundo. 
				//Si se eliminase la celula compleja simplemente nunca comeria y ese paso no lo haria
			builder.append(this.superficie.ejecutaMovimiento(llenas[i], casN));
		
			if(come)
			{
				int indice = this.buscarCasilla(llenas, i, nLlenas, casN) ;
				if (indice != -1)
				{
					this.moverCasillasADerecha(llenas, indice, nLlenas);
					nLlenas--;
				}
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

	//Pone todas las casillas de la superficie a null
	public void vaciarMundo(){
		this.superficie.todasANull();
	}
	
	//Llena n celulas si es capaz, si no devuelve false
	public boolean llenarNCelulasAleatorias()
	{
		/*
		 * Aunque parezca una comparacion un poco absurda puede que al modificar las constantes 
		 * para pruebas no nos demos cuenta de que son demasiadas celulas.
		 */
		if(INICELLSIMPLES + INICELLCOMPLEJAS <= INIFIL * INICOL)
		{
			llenarNSimples(INICELLSIMPLES);
			llenarNComplejas(INICELLCOMPLEJAS);
		}
		return (INICELLSIMPLES + INICELLCOMPLEJAS <= INIFIL * INICOL);
	}
	
	private void llenarNSimples(int nSimples)
	{
		int col = 0, fil = 0;
		boolean ok = false;
		
		for (int i = 0; i < nSimples; i++)
		{	
			while(!ok)
			{	
				fil = (int) (Math.random()*this.superficie.getFil());
				col = (int) (Math.random()*this.superficie.getCol());
				
				Casilla casilla = new Casilla(fil, col);
				
				if(this.crearCelulaSimple(casilla))
					ok = true;
			}
			ok = false;
		}
	}
	
	private void llenarNComplejas(int nComplejas)
	{
		int col = 0, fil = 0;
		boolean ok = false;
		
		for (int i = 0; i < nComplejas; i++)
		{	
			while(!ok)
			{	
				fil = (int) (Math.random()*this.superficie.getFil());
				col = (int) (Math.random()*this.superficie.getCol());
				
				Casilla casilla = new Casilla(fil, col);
				
				if(this.crearCelulaCompleja(casilla))
					ok = true;
			}
			ok = false;
		}
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
	
	private int casillasLlenas(Casilla[] llenas){
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
	
	private int buscarCasilla(Casilla[] llenas, int desdeDonde, int hastaDonde, Casilla casillaABuscar)
	{
		int indice = desdeDonde ;
		
		while(indice < hastaDonde && !llenas[indice].esIgual(casillaABuscar))
			indice++;
			
		if(!(indice >= desdeDonde && indice < hastaDonde))
			indice = -1;
		
		return indice;
				
	}
			
	private Casilla[] moverCasillasADerecha(Casilla[] casilla, int desdeDonde, int hastaDonde)
	{
		for(int i = desdeDonde; i < hastaDonde - 1; i++)
			casilla[i] =  casilla[i + 1]; 
		
		return casilla;
	}


}
