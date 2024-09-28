package dominio.SolucionSOLID.GenericInterface;

import exceptions.PrincipalException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public interface IGestor<T> {
    boolean addToList(T item) throws PrincipalException;
    boolean deleteFromList(String id) throws PrincipalException;
    ArrayList<T> getSortedList(Comparator<T> comparator) throws PrincipalException ;
    ArrayList<T> getFilteredList(Predicate<T> filter) throws PrincipalException ;
    ArrayList<T> getDefaultList();
}
