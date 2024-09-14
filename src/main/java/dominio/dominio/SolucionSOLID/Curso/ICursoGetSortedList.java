package dominio.SolucionSOLID.Curso;

import dominio.Curso;

import java.util.ArrayList;
import java.util.Comparator;

public interface ICursoGetSortedList {
    ArrayList<Curso> getCursosSortedByComparator(Comparator<Curso> compa, ArrayList<Curso> cursos);
}
