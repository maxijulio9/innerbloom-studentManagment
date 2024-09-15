package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;
import persistencia.PersistenciaDB;

import java.util.ArrayList;

public class AlumnoGetDefaultList implements IAlumnoGetDefaultList {

    @Override
    public ArrayList<Alumno> getListAlumnos() {

        return GestorInstituto.getInstancia().listaAlumnos;
        //return PersistenciaDB.getAlumnos();
    }
}
