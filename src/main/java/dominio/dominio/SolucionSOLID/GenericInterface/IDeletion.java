package dominio.SolucionSOLID.GenericInterface;

import exceptions.PrincipalException;

//interfaz geneérica para los alumnos y cursios
public interface IDeletion<T> {
    boolean delete(String identifier) throws PrincipalException;
}
