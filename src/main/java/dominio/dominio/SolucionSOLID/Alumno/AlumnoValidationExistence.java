package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;

public class AlumnoValidationExistence {

    public void validar(Alumno alumno) throws PrincipalException {
        System.out.println("Validating...");
        if (existeAlumno(alumno)) {
            throw new PrincipalException("Ya se encuentra registrado.");
        }
        // Otras validaciones pueden ir aquí
    }

    private boolean existeAlumno(Alumno alumno) {
        return GestorInstituto.getInstancia().getAlumnoDefaultList()
                .stream()
                .anyMatch(a -> a.getDni().equals(alumno.getDni()));
    }
}
