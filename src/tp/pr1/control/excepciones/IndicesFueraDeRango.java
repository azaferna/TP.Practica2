package tp.pr1.control.excepciones;

public class IndicesFueraDeRango extends Exception {

	private static final long serialVersionUID = 1L;

	public IndicesFueraDeRango() {
		super();
	}

	public IndicesFueraDeRango(String message) {
		super(message);
	}

}
