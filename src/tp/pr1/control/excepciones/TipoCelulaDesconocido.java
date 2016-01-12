package tp.pr1.control.excepciones;

public class TipoCelulaDesconocido extends Exception {

	private static final long serialVersionUID = 1L;

	public TipoCelulaDesconocido() {
		super();
	}

	public TipoCelulaDesconocido(String message) {
		super(message);
	}

}
