package dominio.Solucion;

import dominio.Alumno;
import exceptions.PrincipalException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AlumnoGetListSorted implements IAlumnoGetListSorted {

    @Override
    public ArrayList<Alumno> getListadoAlumnosOrdenado(Comparator<Alumno> comparatorAlumno, GestorInstituto gestor) throws PrincipalException {
        ArrayList<Alumno> alumnos;
        alumnos = gestor.listaAlumnos
                .stream()
                .sorted(comparatorAlumno)
                .collect(Collectors.toCollection(ArrayList<Alumno>::new));
        System.out.println("\n\nAfter ORDENAR");
        alumnos.forEach(a->System.out.println(a.toString()));
        return alumnos;
    }
}
