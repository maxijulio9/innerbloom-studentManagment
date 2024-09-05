package dominio.Solucion;

import dominio.Alumno;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AlumnoGetListFiltered implements IAlumnoGetFiltered{

    //validar si es utilizado.--
    @Override
    public ArrayList<Alumno> getListadoFiltradoAlumno(Predicate<Alumno> filtroAlumno, GestorInstituto gestor) {
        return gestor.listaAlumnos.stream()
                .filter(filtroAlumno)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
