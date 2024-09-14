package exceptions;

public class FechaNoValida extends PrincipalException{
	public FechaNoValida() {
		super("La fecha no es válida. Debe ser mayor o igual a la actual.");
	}

}
