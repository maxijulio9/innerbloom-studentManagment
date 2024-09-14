package exceptions;

public class ApellidoVacioException extends PrincipalException {
	public ApellidoVacioException() {
		super("Por favor completa el campo Apellido.");
	}
}
