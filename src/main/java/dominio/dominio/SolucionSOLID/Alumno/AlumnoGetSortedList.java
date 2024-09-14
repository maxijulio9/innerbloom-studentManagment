package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AlumnoGetSortedList implements IAlumnoGetListSorted {

    @Override
    public ArrayList<Alumno> getListadoAlumnosOrdenado(Comparator<Alumno> comparatorAlumno) throws PrincipalException {
        ArrayList<Alumno> alumnos;
        alumnos = GestorInstituto.getInstancia().listaAlumnos
                .stream()
                .sorted(comparatorAlumno)
                .collect(Collectors.toCollection(ArrayList<Alumno>::new));
        System.out.println("\n\nAfter ORDENAR");
        alumnos.forEach(a->System.out.println(a.toString()));
        return alumnos;
    }
}
