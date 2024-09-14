package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;

public interface IAlumnoCreation {
    boolean addAlumno(String nombre, String apellido, String dni, String telefono, GestorInstituto gestor);
    boolean addAlumno(Alumno alumno, GestorInstituto gestor);

}

