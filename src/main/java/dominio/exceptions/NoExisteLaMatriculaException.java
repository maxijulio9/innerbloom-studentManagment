package exceptions;

public class NoExisteLaMatriculaException extends PrincipalException {

	public NoExisteLaMatriculaException() {
		super("La matricula que intentas eliminar no se encuentra registrada.");
	}
}
