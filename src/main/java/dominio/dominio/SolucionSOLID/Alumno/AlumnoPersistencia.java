package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import persistencia.PersistenciaDB;

import java.util.ArrayList;

public class AlumnoPersistencia implements IAlumnoPersistencia{

    @Override
    public ArrayList<Alumno> getListAlumnoFromDB() {
        return PersistenciaDB.getAlumnos();
    }
}
