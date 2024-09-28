package dominio.SolucionSOLID.GenericInterface;

import java.util.ArrayList;
import java.util.Comparator;

public interface IGetSortedList<T> {
    ArrayList<T> getSortedList(Comparator<T> comparator);
}
