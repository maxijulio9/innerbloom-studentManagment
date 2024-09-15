package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.*;

public class AlumnoCreation implements IAlumnoCreation {
   private AlumnoValidationExistence alumnoValidador;
  // private Alumno alumnoLocal;

    public AlumnoCreation(){

    }
    //Aca se inyect dependencia con l
    public AlumnoCreation(Alumno alumno) {
        this.alumnoValidador = new AlumnoValidationExistence();
        addAlumno(alumno);
        //this.alumnoLocal = alumno;
    }

    public AlumnoCreation(String nombre, String apellido, String dni, String telefono) {
        this.alumnoValidador = new AlumnoValidationExistence();
        addAlumno(nombre, apellido, dni,telefono);
    }

    //check s
    @Override
    public boolean addAlumno(String nombre, String apellido, String dni, String telefono) {

        try {
            Alumno alumno = new Alumno(nombre, apellido, dni, telefono);

            alumnoValidador.validar(alumno);
            return GestorInstituto.getInstancia().listaAlumnos.add(alumno);

        } catch (PrincipalException e) {
            return false;
        }

    }
    @Override
    public boolean addAlumno(Alumno alumno) {

        try {
            System.out.println("Ingreséeeee");
            alumnoValidador.validar(alumno);  // Uso de AlumnoValidador para validar
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
