package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GenericInterface.IGetSortedList;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AlumnoGetSortedList implements IGetSortedList<Alumno> {

    @Override
    public ArrayList<Alumno> getSortedList(Comparator<Alumno> comparatorAlumno) {
        ArrayList<Alumno> alumnos;
        alumnos = GestorInstituto.getInstance(null,null)
                .getAlumnoDefaultList()//getInstancia().listaAlumnos
                .stream()
                .sorted(comparatorAlumno)
                .collect(Collectors.toCollection(ArrayList<Alumno>::new));
        System.out.println("\n\nAfter ORDENAR");
        alumnos.forEach(a->System.out.println(a.toString()));
        return alumnos;
    }
}
