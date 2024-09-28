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
        // Otras validaciones pueden ir
    }

    private boolean existeAlumno(Alumno alumno) {
        /*
        boolean existeONo = GestorInstituto.getInstance(
                        null, // No estamos utilizando IGestor para este ejemplo simple
                        null,
                        null,
                        null, // No estamos utilizando IDeletion en este ejemplo
                        null,
                        null,
                        null,
                        null, // No estamos utilizando ICreation para cursos
                        null,
                        null,
                        null,
                        null
                ).getAlumnoDefaultList()
                .stream()
                .anyMatch(a -> a.getDni().equals(alumno.getDni()));

         */
        boolean existeONo = GestorInstituto.getInstance(null, null).getAlumnoDefaultList()
                .stream()
                .anyMatch(a -> a.getDni().equals(alumno.getDni()));

        System.out.println("EXiste alumno? "+ existeONo);

        return existeONo;
    }
}
