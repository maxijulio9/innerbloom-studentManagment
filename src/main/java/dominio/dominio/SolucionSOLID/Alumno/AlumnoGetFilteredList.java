package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GenericInterface.IGetFilteredList;
import dominio.SolucionSOLID.GestorInstituto;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AlumnoGetFilteredList implements IGetFilteredList <Alumno> {

    //validar si es utilizado.--
    @Override
    public ArrayList<Alumno> getFilteredList(Predicate<Alumno> filtroAlumno) {
        return GestorInstituto.getInstance(null,null)
                .getAlumnoDefaultList()//getInstancia().listaAlumnos
                .stream()
                .filter(filtroAlumno)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
