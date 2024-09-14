package exceptions;

public class DniInvalidoException extends PrincipalException {

	public DniInvalidoException() {
		super("DNI Inválido. Ingresá correctamente los datos.");
	}
}
