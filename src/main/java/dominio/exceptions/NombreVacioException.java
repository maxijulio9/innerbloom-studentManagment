package exceptions;

public class NombreVacioException extends PrincipalException{
	public NombreVacioException() {
		super("Por favor completa el campo Nombre.");
	}
	public NombreVacioException(String msg) {
		super(msg);
	}
	
}