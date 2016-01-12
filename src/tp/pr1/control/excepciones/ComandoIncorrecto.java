package tp.pr1.control.excepciones;

public class ComandoIncorrecto extends Exception {

	private static final long serialVersionUID = 1L;

	public ComandoIncorrecto() {
		super();
	}

	public ComandoIncorrecto(String message) {
		super(message);
	}

}
