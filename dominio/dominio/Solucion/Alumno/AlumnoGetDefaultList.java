package dominio.Solucion.Alumno;

import dominio.Alumno;
import persistencia.PersistenciaDB;

import java.util.ArrayList;

public class AlumnoGetDefaultList implements IAlumnoGetDefaultList {

    @Override
    public ArrayList<Alumno> getListAlumnos() {

        return PersistenciaDB.getAlumnos();
    }
}
