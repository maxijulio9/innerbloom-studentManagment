package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface IAlumnoGetFiltered {
    ArrayList<Alumno> getListadoFiltradoAlumno(Predicate<Alumno> filtroAlumno);
}
