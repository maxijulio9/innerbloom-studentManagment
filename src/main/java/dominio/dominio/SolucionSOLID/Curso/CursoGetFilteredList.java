package dominio.SolucionSOLID.Curso;

import dominio.Curso;
import dominio.SolucionSOLID.GenericInterface.IGetFilteredList;
import dominio.SolucionSOLID.GestorInstituto;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CursoGetFilteredList implements IGetFilteredList<Curso> {

    @Override
    public ArrayList<Curso> getFilteredList(Predicate<Curso> cursoFilter) {
        return  GestorInstituto.getInstance(null,null ).getCursosDefaultList()
                .stream()
                .filter(cursoFilter)
                .collect(Collectors.toCollection(ArrayList::new));    }
}
