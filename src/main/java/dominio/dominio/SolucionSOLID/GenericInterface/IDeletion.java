package dominio.SolucionSOLID.GenericInterface;

import exceptions.PrincipalException;

import java.util.ArrayList;

//interfaz geneérica para los alumnos y cursios
public interface IDeletion<T> {
    boolean delete(String identifier, ArrayList<T> list) throws PrincipalException;
}
