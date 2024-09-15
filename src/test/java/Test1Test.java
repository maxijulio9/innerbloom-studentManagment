import dominio.Alumno;
import dominio.SolucionSOLID.Alumno.*;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class GestorInstitutoTest {


    private ArrayList<Alumno> listaAlumnos;
    private Alumno alumno1;
    private Alumno alumno2;

    /*
    @BeforeEach
    void setingUP() throws PrincipalException {
        listaAlumnos = GestorInstituto.getInstancia().getAlumnoDefaultList();
        alumno1 = new Alumno("Juan", "Pérez", "12345678", "55234");
        alumno2 = new Alumno("Ana", "Garcia", "87654321", "78433");

        GestorInstituto.getInstancia().addAlumnoToList(alumno1);
        GestorInstituto.getInstancia().addAlumnoToList(alumno2);

    }
    */

    @Test
    void testAlumnoExists() throws PrincipalException {

        alumno1 = new Alumno("Juan", "Pérez", "12345678", "55234");
        alumno2 = new Alumno("Ana", "Garcia", "87654321", "78433");

        //System.out.println("NombreA1 "+ alumno1.getNombre());
        //Creo las instancias
        IAlumnoGetDefaultList alumnosDefaultList = new AlumnoGetDefaultList();
        IAlumnoCreation alumnoCreating = new AlumnoCreation(alumno1);
        IAlumnoDelete alumnoDeleting = new AlumnoDelete();

        GestorInstituto gestorcito =  GestorInstituto.getInstancia(alumnosDefaultList, alumnoCreating, alumnoDeleting);
        listaAlumnos = alumnosDefaultList.getListAlumnos();


        gestorcito.addAlumnoToList(alumno1);
        gestorcito.addAlumnoToList(alumno2);

        assertFalse( gestorcito.addAlumnoToList(alumno1), "Ya se encuentra registrado.");
        assertFalse( gestorcito.addAlumnoToList(alumno2), "Ya se encuentra registrado.");

        Alumno alumnoNoRegistrado = new Alumno("María", "López", "11223344", "59999");
        assertTrue(gestorcito.addAlumnoToList(alumnoNoRegistrado), "Alumno registrado.,");
    }
}
