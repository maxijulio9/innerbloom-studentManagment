package dominio.SolucionSOLID.Curso;

import dominio.Curso;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface ICursoGetFilteredList {
    ArrayList<Curso> getListadoFiltrado(Predicate<Curso> cursoFilter);
}
