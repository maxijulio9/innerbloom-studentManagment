package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;

import java.util.ArrayList;

public interface IAlumnoPersistencia {
    ArrayList<Alumno> getListAlumnoFromDB();

}
