package exceptions;

public class MatriculaExistenteException extends PrincipalException {
	public MatriculaExistenteException() {
		super("El alumno ya se encuentra registrado en el curso seleccionado.");
	}
}
