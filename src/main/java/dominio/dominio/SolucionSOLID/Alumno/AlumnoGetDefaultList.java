package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GenericInterface.IGetDefaultList;
import dominio.SolucionSOLID.GestorInstituto;
import persistencia.PersistenciaDB;

import java.util.ArrayList;

public class AlumnoGetDefaultList implements IGetDefaultList <Alumno>{

    @Override
    public ArrayList<Alumno> getDefaultList(ArrayList<Alumno> lists) {

        return lists;
        //return GestorInstituto.getInstance(null,null).getAlumnoDefaultList();
        //return PersistenciaDB.getAlumnos();
    }
}
