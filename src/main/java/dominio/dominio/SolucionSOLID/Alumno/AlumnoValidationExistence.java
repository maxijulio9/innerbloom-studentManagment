package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;

import java.util.ArrayList;

public class AlumnoValidationExistence {

    public void validar(Alumno alumno, ArrayList<Alumno> alumnosList) throws PrincipalException {
        System.out.println("Validating...");
        if (existeAlumno(alumno, alumnosList)) {
            throw new PrincipalException("Ya se encuentra registrado.");
        }
        // Otras validaciones pueden ir
    }

    private boolean existeAlumno(Alumno alumno, ArrayList<Alumno> alumnosList) {

        /*
        boolean existeONo = GestorInstituto.getInstance(null, null).getAlumnoDefaultList()
                .stream()
                .anyMatch(a -> a.getDni().equals(alumno.getDni()));

         */
        //System.out.println("EXiste alumno? "+ existeONo);

        return alumnosList
                .stream()
                .anyMatch(a -> a.getDni().equals(alumno.getDni()));
    }
}
