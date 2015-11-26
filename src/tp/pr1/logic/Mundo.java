package tp.pr1.logic;

public class Mundo {
	private Superficie superficie;
	public static final int INICELLS = 9; // Número inicial de células en la superficie
	public static final int INIFIL = 3;//Numero inicial de filas
	public static final int INICOL = 4;// Número inicial de columnas
	private static final int MAXRODEO = 9;//Maximo numero de celdas que pueden rodear una celula(incluida ella misma)
	
	//Inicializa los atributos del Mundo
	public Mundo()
	{
		this.superficie = new Superficie(INIFIL, INICOL);
		this.llenarNCelulasAleatorias();
	}

	
	//Da un paso en el mundo
	public String evoluciona(){
		Casilla[] llenas = new Casilla[this.superficie.getFil() * this.superficie.getCol()];
		int indice = casillasLlenas(llenas);
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < indice; i++){
				if(this.superficie.casillaLlena(llenas[i]) ){
					builder.append(this.procesar(llenas[i]));
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

	//Procesa una celula con las normas del juego
	public String procesar(Casilla casV)
	{
		Casilla casN = new Casilla(-1, -1);
		casN = generarCasillaVaciaContigua(casV);
		StringBuilder builder = new StringBuilder();
		
		if(this.superficie.moverCelula(casV, casN)){
			//Imprime el movimiento
			builder.append("Movimiento de" + casV.toString() + " a " + casN.toString() + '\n');
			if(!this.superficie.menosPasosRep(casN)){
				if(this.superficie.crearCelula(casV)){
					this.superficie.iniPasosReproduccion(casN);
					
					builder.append("Nace una celula en " + casN.toString() );
					builder.append( " cuyo padre ha sido "+ casV.toString() + '\n' );
				}
				else
					builder.append("Ha habido un fallo en la reproducción." + '\n');
			}	
		}
		else{
			if(!this.superficie.menosPasosMover(casV)){
				if(this.superficie.eliminarCelula(casV))
					builder.append("Muere la celula de la casilla "+ casV.toString() + "por inactividad." + '\n');
				else
					builder.append("La celula no ha podido morir por algun fallo" + '\n');
			
			}
			else if(!this.superficie.menosPasosRep(casV)){
				if(this.superficie.eliminarCelula(casV))
					builder.append("Muere la celula de la casilla"+ casV.toString() + "por no poder reproducirse." + '\n');
				else
					builder.append("La celula no ha podido morir por algun fallo" + '\n');
			}	
		
		}
		//Solo para pruebas;
		//System.out.println(this.toString());
		return builder.toString();
	}
	
	//Genera una casilla vacia contigua a la introducida
	public Casilla generarCasillaVaciaContigua(Casilla casilla)
	{
		int indice, rand;
		Casilla[] casVacias = new Casilla[MAXRODEO];
		Casilla cas;
		
		indice = this.superficie.arrayVaciasAlrededor(casilla, casVacias);
		
		if(indice != 0){
			rand = (int) (Math.random()*indice);
			cas = casVacias[rand];
		}
		else
			cas = null;
		
		return cas;
	}

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
		if(INICELLS <= INIFIL*INICOL && this.superficie.llenarNCeluslasLibres(INICELLS))
			ok = true;
		return ok;
		
	}
	
	//Crea una celula en la casilla devuelve true si es posible
	public boolean crearCelula(Casilla casilla){
		return this.superficie.crearCelula(casilla);
	}
	
	//Elimina una celula si es posible
	public boolean eliminarCelula(Casilla casilla){
		return this.superficie.eliminarCelula(casilla);
	}

}
