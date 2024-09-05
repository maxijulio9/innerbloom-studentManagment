package dominio.Solucion;

import dominio.Alumno;
import dominio.Solucion.GestorInstituto;
import exceptions.*;

public class AlumnoCreacion implements IAlumnoCreacion{
    private AlumnoValidacionExistente alumnoValidador;

    //Aca se inyect dependencia con la Class ALumnovalidador
    public AlumnoCreacion(AlumnoValidacionExistente alumnoValidador) {
        this.alumnoValidador = alumnoValidador;
    }

    //check si realmente es necesario this method
    @Override
    public boolean agregarAlumno(String nombre, String apellido, String dni, String telefono, GestorInstituto gestor) {

        try {
            Alumno alumno = new Alumno(nombre, apellido, dni, telefono);

            alumnoValidador.validar(alumno, gestor);
            return gestor.listaAlumnos.add(alumno);

        }catch (DniInvalidoException e2) {
            return false;
        }catch (NombreVacioException e2) {
            return false;
        }catch (ApellidoVacioException e2) {
            return false;
        }catch (TelefonoInvalidoException e2) {
            return false;
        }catch (PrincipalException e) {
            return false;
        }

    }
    @Override
    public boolean agregarAlumno(Alumno alumno, GestorInstituto gestor) {

        try {
            alumnoValidador.validar(alumno, gestor);  // Uso de AlumnoValidador para validar
            return gestor.listaAlumnos.add(alumno);
        } catch (PrincipalException e) {
            return false;
        }

    }


    /*
    private boolean existeAlumno1(Alumno alumno, GestorInstituto gestor) {
        //ArrayList<Alumno> listaAlumnosDB = PersistenciaDB.getAlumnos();

        return gestor.listaAlumnos.stream()
                .anyMatch(a->a.getDni().equals(alumno.getDni()));
    }

     */

}
