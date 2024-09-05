package dominio.Solucion;

import dominio.Alumno;

public interface IAlumnoCreation {
    boolean addAlumno(String nombre, String apellido, String dni, String telefono, GestorInstituto gestor);
    boolean addAlumno(Alumno alumno, GestorInstituto gestor);

}

