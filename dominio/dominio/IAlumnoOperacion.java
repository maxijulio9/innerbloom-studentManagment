package dominio;

import exceptions.PrincipalException;

import java.util.ArrayList;


public interface IAlumnoOperacion {


    boolean agregarAlumno(Alumno alumno);
    boolean agregarAlumno(String nombre, String apellido, String dni, String telefono) throws PrincipalException;
    boolean existeAlumno1(Alumno alumno);
    public boolean eliminarAlumno(String dni) throws PrincipalException;
    ArrayList<Alumno> getListaAlumnos();


}
