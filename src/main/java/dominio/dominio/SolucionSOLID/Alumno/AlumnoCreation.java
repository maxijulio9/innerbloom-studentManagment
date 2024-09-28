package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GenericInterface.ICreation;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.*;

import java.util.ArrayList;

public class AlumnoCreation implements ICreation<Alumno> {
   private AlumnoValidationExistence alumnoValidador;
  // private Alumno alumnoLocal;

    public AlumnoCreation(){
        this.alumnoValidador = new AlumnoValidationExistence();

    }
    /*
    //Aca se inyect dependencia con l
    public AlumnoCreation(Alumno alumno) {
        this.alumnoValidador = new AlumnoValidationExistence();
        addAlumno(alumno);
        //this.alumnoLocal = alumno;
    }
     */

    /*
    public AlumnoCreation(String nombre, String apellido, String dni, String telefono) {
        this.alumnoValidador = new AlumnoValidationExistence();
        addAlumno(nombre, apellido, dni,telefono);
    }
     */

    //check s
    /*@Override
    public boolean addAlumno(String nombre, String apellido, String dni, String telefono) {

        try {
            Alumno alumno = new Alumno(nombre, apellido, dni, telefono);

            alumnoValidador.validar(alumno);
            return GestorInstituto.getInstancia().listaAlumnos.add(alumno);

        } catch (PrincipalException e) {
            return false;
        }

    }

     */

    @Override
    public boolean add(Alumno alumno, ArrayList<Alumno> alumnosList) {

        try {
            System.out.println("Ingreséeeee");
            alumnoValidador.validar(alumno, alumnosList);  // Uso de AlumnoValidador para validar

            /*
            return GestorInstituto.getInstance(
                    null, // No estamos utilizando IGestor para este ejemplo simple
                    null,
                    null,
                    null, // No estamos utilizando IDeletion en este ejemplo
                    null,
                    null,
                    null,
                    null, // No estamos utilizando ICreation para cursos
                    null,
                    null,
                    null,
                    null
            ).addAlumnoToList(alumno);

             */
            return alumnosList.add(alumno);
           // return GestorInstituto.getInstance(null,null ).addAlumnoToList(alumno);
        } catch (PrincipalException e) {
            return false;
        }

    }
}
