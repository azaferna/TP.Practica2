package tp.pr1.control.excepciones;

public class ErrorDeInicializacion extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorDeInicializacion() {
		super();
	}

	public ErrorDeInicializacion(String message) {
		super(message);
		
	}

}
