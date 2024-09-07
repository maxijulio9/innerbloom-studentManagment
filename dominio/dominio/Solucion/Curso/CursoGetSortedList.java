package dominio.Solucion.Curso;

import dominio.Curso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CursoGetSortedList implements ICursoGetSortedList{


    @Override
    public ArrayList<Curso> getCursosSortedByComparator(Comparator<Curso> comparatorSort, ArrayList<Curso> cursosList) {
        return cursosList
                .stream()
                .sorted(comparatorSort)
                .collect(Collectors.toCollection(ArrayList<Curso>::new));
    }
}
