package dominio.SolucionSOLID.Curso;

import dominio.Curso;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CursoGetFilteredList implements ICursoGetFilteredList{

    @Override
    public ArrayList<Curso> getListadoFiltrado(Predicate<Curso> cursoFilter, ArrayList<Curso> cursosList) {
        return cursosList.stream()
                .filter(cursoFilter)
                .collect(Collectors.toCollection(ArrayList::new));    }
}
