package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.*;

public class AlumnoCreation implements IAlumnoCreation {
    private AlumnoValidationExisting alumnoValidador;
    //private Alumno alumnoLocal;

    //Aca se inyect dependencia con la Class ALumnovalidador
    public AlumnoCreation(AlumnoValidationExisting alumnoValidador) {
        this.alumnoValidador = alumnoValidador;
    }

    //check si realmente es necesario this method
    @Override
    public boolean addAlumno(String nombre, String apellido, String dni, String telefono) {

        try {
            Alumno alumno = new Alumno(nombre, apellido, dni, telefono);

            alumnoValidador.validar(alumno, GestorInstituto.getInstancia().listaAlumnos);
            return GestorInstituto.getInstancia().listaAlumnos.add(alumno);

        } catch (PrincipalException e) {
            return false;
        }

    }
    @Override
    public boolean addAlumno(Alumno alumno) {

        try {
            alumnoValidador.validar(alumno, GestorInstituto.getInstancia().listaAlumnos);  // Uso de AlumnoValidador para validar
            return GestorInstituto.getInstancia().listaAlumnos.add(alumno);
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
