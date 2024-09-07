package dominio.Solucion.Curso;

import dominio.Curso;
import dominio.Solucion.GestorInstituto;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface ICursoGetFilteredList {
    ArrayList<Curso> getListadoFiltrado(Predicate<Curso> cursoFilter, ArrayList<Curso> cursosList);
}
