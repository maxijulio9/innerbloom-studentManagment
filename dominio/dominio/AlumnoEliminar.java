package dominio;
import java.util.Objects;

import Alumno;
import GestorInstituto;
public class AlumnoEliminar {


    public AlumnoEliminar(dominio.GestorInstituto gestor, String dni) {
        if (existeAlumno(gestor)) {
            gestor.listaAlumnos.delete(alumno.getDni());
        }

        private boolean existeAlumno (dominio.GestorInstituto gestor, String dni){
            return gestor.listaAlumnos.stream()
                    .filter(a -> a.getDni().equals(dni))
                    .findAny().isPresent();
        }
    }

}