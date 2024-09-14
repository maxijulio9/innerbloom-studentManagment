package dominio.SolucionSOLID.Curso;

import dominio.Curso;
import dominio.SolucionSOLID.GestorInstituto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CursoGetSortedList implements ICursoGetSortedList{


    @Override
    public ArrayList<Curso> getCursosSortedByComparator(Comparator<Curso> comparatorSort) {
        return GestorInstituto.getInstancia().listaCurso
                .stream()
                .sorted(comparatorSort)
                .collect(Collectors.toCollection(ArrayList<Curso>::new));
    }
}
