package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;

import java.util.ArrayList;

public class AlumnoValidationExisting {

    public void validar(Alumno alumno, ArrayList<Alumno> listaAlumnos) throws PrincipalException {
        if (existeAlumno(alumno, listaAlumnos)) {
            throw new PrincipalException("Ya se encuentra registrado.");
        }
        // Otras validaciones pueden ir aquí
    }

    private boolean existeAlumno(Alumno alumno, ArrayList<Alumno> listaAlumnos) {
        return listaAlumnos.stream()
                .anyMatch(a -> a.getDni().equals(alumno.getDni()));
    }
}
