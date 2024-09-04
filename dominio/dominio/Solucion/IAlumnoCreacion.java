package dominio.Solucion;

import dominio.Alumno;
import exceptions.PrincipalException;

public interface IAlumnoCreacion {
    boolean agregarAlumno(String nombre, String apellido, String dni, String telefono, GestorInstituto gestor);
    boolean agregarAlumno(Alumno alumno, GestorInstituto gestor);

}

