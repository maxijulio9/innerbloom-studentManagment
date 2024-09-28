package dominio.SolucionSOLID.GenericInterface;

import dominio.Alumno;

import java.util.ArrayList;

public interface ICreation<T> {
    boolean add(T entity, ArrayList<T> list);

}
