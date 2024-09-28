package dominio.SolucionSOLID.GenericInterface;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface IGetFilteredList<T> {
    ArrayList<T> getFilteredList(Predicate<T> filter, ArrayList<T> lists);
}
