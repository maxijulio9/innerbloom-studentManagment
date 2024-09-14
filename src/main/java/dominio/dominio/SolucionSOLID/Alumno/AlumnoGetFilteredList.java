package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AlumnoGetFilteredList implements IAlumnoGetFiltered {

    //validar si es utilizado.--
    @Override
    public ArrayList<Alumno> getListadoFiltradoAlumno(Predicate<Alumno> filtroAlumno) {
        return GestorInstituto.getInstancia().listaAlumnos
                .stream()
                .filter(filtroAlumno)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
