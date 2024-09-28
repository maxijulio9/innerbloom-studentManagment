package dominio.SolucionSOLID.Gestor;

import dominio.Curso;
import dominio.SolucionSOLID.Curso.*;
import dominio.SolucionSOLID.GenericInterface.*;
import exceptions.PrincipalException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class GestorCursos implements IGestor<Curso> {
    private ArrayList<Curso> cursosList;
    private ICreation cursoCreation;
    private IDeletion cursoDelete;
    private IGetSortedList cursoGetSortedList;
    private IGetFilteredList cursoGetFilteredList;
    private IGetDefaultList cursoGetDefaultList;

    public GestorCursos(ArrayList<Curso>cursosList, ICreation cursoCreation, IDeletion cursoDelete,
                        IGetSortedList cursoGetSortedList, IGetFilteredList cursoGetFilteredList,
                        IGetDefaultList cursoGetDefaultList) {
       // this.listaCursos = cursoGetDefaultList.getDefaultList();
        this.cursoCreation = cursoCreation;
        this.cursoDelete = cursoDelete;
        this.cursoGetSortedList = cursoGetSortedList;
        this.cursoGetFilteredList = cursoGetFilteredList;
        this.cursoGetDefaultList = cursoGetDefaultList;
    }

    @Override
    public boolean addToList(Curso curso) throws PrincipalException, PrincipalException {
        return cursoCreation.add(curso, cursosList);
    }

    @Override
    public boolean deleteFromList(String nameCurso) throws PrincipalException {
        return cursoDelete.delete(nameCurso);
    }

    @Override
    public ArrayList<Curso> getSortedList(Comparator<Curso> comparator) {
        return cursoGetSortedList.getSortedList(comparator);
    }

    @Override
    public ArrayList<Curso> getFilteredList(Predicate<Curso> filter) {
        return cursoGetFilteredList.getFilteredList(filter);
    }

    @Override
    public ArrayList<Curso> getDefaultList() {
        return cursoGetDefaultList.getDefaultList();
    }
}
