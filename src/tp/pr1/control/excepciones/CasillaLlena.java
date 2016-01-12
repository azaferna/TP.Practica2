package tp.pr1.control.excepciones;

public class CasillaLlena extends Exception{

	private static final long serialVersionUID = 1L;

	public CasillaLlena() {
		super();
	}
	
	public CasillaLlena(String message) {
		super(message);
	}

}
