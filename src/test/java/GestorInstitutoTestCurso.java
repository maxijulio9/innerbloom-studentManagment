import dominio.Alumno;
import dominio.Curso;
import dominio.SolucionSOLID.Alumno.*;
import dominio.SolucionSOLID.Curso.*;
import dominio.SolucionSOLID.GenericInterface.IGestor;
import dominio.SolucionSOLID.Gestor.GestorAlumnos;
import dominio.SolucionSOLID.Gestor.GestorCursos;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GestorInstitutoTestCurso {

    private GestorInstituto gestorInstituto;

    @BeforeEach
    public void setUp() {
        // Inicializamos GestorInstituto con sus dependencias necesarias
        // Aquí no usamos mocks, sino instancias reales de las clases.
        gestorInstituto = new GestorInstituto(new GestorAlumnos(new ArrayList<Alumno>(),new AlumnoCreation(),new AlumnoDelete(),
                                                                new AlumnoGetSortedList(),
                                                                new AlumnoGetFilteredList(),
                                                                new AlumnoGetDefaultList()),
                                            new GestorCursos(new ArrayList<Curso>(),new CursoCreation(),
                                                            new CursoDelete(),
                                                            new CursoGetSortedList(),
                                                            new CursoGetFilteredList(),
                                                            new CursoGetDefaultList()));
    }

    @Test
    public void testAddCursoToListSuccess() throws PrincipalException {
        // Crear un curso de prueba
        Curso curso = new Curso("Portugués", "A1", "300", "20", "Maximiliano");

        //String nombre, String nivel, String totalHoras, String cantidadAlumnos,
        //			String profesorAsignado

        boolean resultado = gestorInstituto.addCursoToList(curso);

        System.out.println("ERESULTADO test 3 "+ resultado);
        assertTrue(resultado);//, "¡Nuevo curso registrado, que alegría crecer!");
    }

    @Test
    public void testAddCursoToListNullCursoShouldThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gestorInstituto.addCursoToList(null);
        });

        assertEquals("El curso no puede ser nulo.", exception.getMessage());
    }

    @Test
    public void testAddCursoToListShouldFailure() throws PrincipalException {
        Curso curso = new Curso("Inglés", "A1", "200", "20", "Elon Musk");


        gestorInstituto.addCursoToList(curso); // agrego el curo
        boolean resultado = gestorInstituto.addCursoToList(curso); // lo agrego otraveza

        // Verificar que la segunda vez falle
        assertFalse(resultado, "El curso no debería haberse añadido de nuevo");
    }
}
