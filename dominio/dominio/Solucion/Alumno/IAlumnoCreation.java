package dominio.Solucion.Alumno;

import dominio.Alumno;
import dominio.Solucion.GestorInstituto;

public interface IAlumnoCreation {
    boolean addAlumno(String nombre, String apellido, String dni, String telefono, GestorInstituto gestor);
    boolean addAlumno(Alumno alumno, GestorInstituto gestor);

}

