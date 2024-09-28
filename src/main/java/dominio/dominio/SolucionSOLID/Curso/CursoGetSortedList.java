package dominio.SolucionSOLID.Curso;

import dominio.Curso;
import dominio.SolucionSOLID.GenericInterface.IGetSortedList;
import dominio.SolucionSOLID.GestorInstituto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CursoGetSortedList implements IGetSortedList<Curso> {


    @Override
    public ArrayList<Curso> getSortedList(Comparator<Curso> comparatorSort) {
        return GestorInstituto.getInstance(null,null)
                .getCursosDefaultList()
                .stream()
                .sorted(comparatorSort)
                .collect(Collectors.toCollection(ArrayList<Curso>::new));
    }
}
