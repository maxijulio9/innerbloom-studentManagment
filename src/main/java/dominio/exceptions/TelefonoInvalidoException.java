package exceptions;

public class TelefonoInvalidoException extends PrincipalException{
	public TelefonoInvalidoException() {
		super("Telefono inválido. Por favor ingresá nuevamente.");
	}

}
