package dominio.SolucionSOLID.Gestor;

import dominio.Alumno;
import dominio.SolucionSOLID.Alumno.*;
import dominio.SolucionSOLID.GenericInterface.*;
import exceptions.PrincipalException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class GestorAlumnos implements IGestor<Alumno> {
    private ArrayList<Alumno> alumnosList;
    private ICreation alumnoCreation;
    private IDeletion alumnoDelete;
    private IGetSortedList alumnoGetListSorted;
    private IGetFilteredList alumnoGetFiltered;
    private IGetDefaultList alumnoGetDefaultList;

    public GestorAlumnos(ArrayList<Alumno> listaAlumnos, ICreation<Alumno> alumnoCreation,
                         IDeletion<Alumno> alumnoDelete, IGetSortedList<Alumno> alumnoGetListSorted,
                         IGetFilteredList<Alumno> alumnoGetFiltered, IGetDefaultList<Alumno> alumnoGetDefaultList) {
        this.alumnosList = listaAlumnos; // Se asigna la lista pasada por referencia
        this.alumnoCreation = alumnoCreation;
        this.alumnoDelete = alumnoDelete;
        this.alumnoGetListSorted = alumnoGetListSorted;
        this.alumnoGetFiltered = alumnoGetFiltered;
        this.alumnoGetDefaultList = alumnoGetDefaultList;
    }


    @Override
    public boolean addToList(Alumno alumno) throws PrincipalException {
        return alumnoCreation.add(alumno, alumnosList);
    }

    @Override
    public boolean deleteFromList(String dni) throws PrincipalException {
        return alumnoDelete.delete(dni);
    }

    @Override
    public ArrayList<Alumno> getSortedList(Comparator<Alumno> comparator)  throws PrincipalException {
        return alumnoGetListSorted.getSortedList(comparator);
    }

    @Override
    public ArrayList<Alumno> getFilteredList(Predicate<Alumno> filter) {
        return alumnoGetFiltered.getFilteredList(filter);
    }

    @Override
    public ArrayList<Alumno> getDefaultList() {
        return alumnoGetDefaultList.getDefaultList();
    }
}
