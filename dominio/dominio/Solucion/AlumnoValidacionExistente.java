package dominio.Solucion;

import dominio.Alumno;
import exceptions.PrincipalException;

public class AlumnoValidacionExistente {

    public void validar(Alumno alumno, GestorInstituto gestor) throws PrincipalException {
        if (existeAlumno(alumno, gestor)) {
            throw new PrincipalException("Ya se encuentra registrado.");
        }
        // Otras validaciones pueden ir aquí
    }

    private boolean existeAlumno(Alumno alumno, GestorInstituto gestor) {
        return gestor.listaAlumnos.stream()
                .anyMatch(a -> a.getDni().equals(alumno.getDni()));
    }
}