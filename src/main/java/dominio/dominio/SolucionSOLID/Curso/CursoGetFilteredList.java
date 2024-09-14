package dominio.SolucionSOLID.Curso;

import dominio.Curso;
import dominio.SolucionSOLID.GestorInstituto;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CursoGetFilteredList implements ICursoGetFilteredList{

    @Override
    public ArrayList<Curso> getListadoFiltrado(Predicate<Curso> cursoFilter) {
        return GestorInstituto.getInstancia().listaCurso
                .stream()
                .filter(cursoFilter)
                .collect(Collectors.toCollection(ArrayList::new));    }
}
