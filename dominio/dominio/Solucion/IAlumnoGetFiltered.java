package dominio.Solucion;

import dominio.Alumno;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface IAlumnoGetFiltered {
    ArrayList<Alumno> getListadoFiltradoAlumno(Predicate<Alumno> filtroAlumno, GestorInstituto gestor);
}
