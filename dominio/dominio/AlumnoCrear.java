package dominio;
import Alumno;

public class AlumnoCrear {

    //this is from windows
    private Alumno alumno;
    public AlumnoCrear(Gestor gestor){
        if (!existeAlumno(gestor)) {
            alumno = new Alumno();
        }
    }

    private boolean existeAlumno(Gestor gestor){
        return gestor.listaAlumnos.stream()
                .filter(a->a.getDni().equals(alumno.getDni()))
                .findAny().isPresent();
    }
}
