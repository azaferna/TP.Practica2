package tp.pr1.control.excepciones;

public class PalabraIncorrecta extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PalabraIncorrecta() {
		super();
	}

	public PalabraIncorrecta(String message) {
		super(message);
	}

}
