import dominio.Alumno;
import dominio.SolucionSOLID.Alumno.*;
import dominio.SolucionSOLID.GenericInterface.ICreation;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestorInstitutoTest {

    private ArrayList<Alumno> listaAlumnos;
    private Alumno alumno1;
    private Alumno alumno2;

    @BeforeEach
    void setUp() throws PrincipalException {
        // Inicializa la lista de alumnos
        AlumnoGetDefaultList alumnosDefaultList = new AlumnoGetDefaultList();
        listaAlumnos = alumnosDefaultList.getDefaultList();

        // Crea las instancias de alumnos
        alumno1 = new Alumno("Juan", "Pérez", "12345678", "55234");
        alumno2 = new Alumno("Ana", "Garcia", "87654321", "78433");

        // Crea instancias para la creación y eliminación de alumnos
        ICreation alumnoCreating = new AlumnoCreation();
        //IAlumnoDelete alumnoDeleting = new AlumnoDelete();

        alumnoCreating.add(alumno1,null);


        // Crea la instancia del gestor
        GestorInstituto gestorcito = GestorInstituto.getInstance(null,null);//getInstancia(alumnosDefaultList, alumnoCreating, alumnoDeleting);

        // Agrega los alumnos a la lista
        gestorcito.addAlumnoToList(alumno1);
        gestorcito.addAlumnoToList(alumno2);
    }

    @Test
    void testAlumnoExists() throws PrincipalException {
        // Crea una nueva instancia de GestorInstituto para realizar el test
        AlumnoGetDefaultList alumnosDefaultList = new AlumnoGetDefaultList();
        ICreation alumnoCreating = new AlumnoCreation();
     //   IAlumnoDelete alumnoDeleting = new AlumnoDelete();
        alumnoCreating.add(alumno1,null);
        GestorInstituto gestorcito = GestorInstituto.getInstance(null,null);

        // Verifica que el alumno ya registrado no se pueda agregar nuevamente
        assertFalse(gestorcito.addAlumnoToList(alumno1), "Ya se encuentra registrado.");
        assertFalse(gestorcito.addAlumnoToList(alumno2), "Ya se encuentra registrado.");

        // Crea un nuevo alumno que no está registrado
        Alumno alumnoNoRegistrado = new Alumno("María", "López", "11223344", "59999");
        assertTrue(gestorcito.addAlumnoToList(alumnoNoRegistrado), "Alumno registrado correctamente.");
    }
}

/*
import dominio.Alumno;
import dominio.SolucionSOLID.Alumno.*;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class GestorInstitutoTestCurso {

 */

    /*
    private ArrayList<Alumno> listaAlumnos;
    private Alumno alumno1;
    private Alumno alumno2;

     */
/*
    @BeforeEach
    void settingUP() throws PrincipalException {
        listaAlumnos = new ArrayList<>(); // Inicializar la lista
        alumno1 = new Alumno("Juan", "Pérez", "12345678", "55234");
        alumno2 = new Alumno("Ana", "Garcia", "87654321", "78433");

        IAlumnoGetDefaultList alumnosDefaultList = new AlumnoGetDefaultList();
        IAlumnoCreation alumnoCreating = new AlumnoCreation(alumno1);
        IAlumnoDelete alumnoDeleting = new AlumnoDelete();

        GestorInstituto gestorcito =  GestorInstituto.getInstancia(alumnosDefaultList, alumnoCreating, alumnoDeleting);
        // Asignar la instancia al campo de clase
        this.gestorInstituto = gestorcito;

        // Asegurarte de agregar los alumnos a la lista predeterminada o a la lista de gestión
        gestorcito.addAlumnoToList(alumno1);
        gestorcito.addAlumnoToList(alumno2);
    }

 */
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
/*

    @Test
    void testAlumnoExists() throws PrincipalException {

 */

        /*
        alumno1 = new Alumno("Juan", "Pérez", "12345678", "55234");
        alumno2 = new Alumno("Ana", "Garcia", "87654321", "78433");

        //System.out.println("NombreA1 "+ alumno1.getNombre());
        //Creo las instancias
        IAlumnoGetDefaultList alumnosDefaultList = new AlumnoGetDefaultList();
        //Si usa el método add() de la clase AlumnoCreation
        IAlumnoCreation alumnoCreating = new AlumnoCreation(alumno1);
        IAlumnoDelete alumnoDeleting = new AlumnoDelete();

        GestorInstituto gestorcito =  GestorInstituto.getInstancia(alumnosDefaultList, alumnoCreating, alumnoDeleting);
        listaAlumnos = alumnosDefaultList.getListAlumnos();


        gestorcito.addAlumnoToList(alumno1);
        gestorcito.addAlumnoToList(alumno2);

         */
/*
        assertFalse( gestorcito.addAlumnoToList(alumno1), "Ya se encuentra registrado.");
        assertFalse( gestorcito.addAlumnoToList(alumno2), "Ya se encuentra registrado.");

        Alumno alumnoNoRegistrado = new Alumno("María", "López", "11223344", "59999");
        assertTrue(gestorcito.addAlumnoToList(alumnoNoRegistrado), "Alumno registrado.,");
    }
}

*/

