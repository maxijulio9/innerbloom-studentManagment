import dominio.Alumno;
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

    @BeforeEach
    void setingUP() throws PrincipalException {
        listaAlumnos = GestorInstituto.getInstancia().getAlumnoDefaultList();
        alumno1 = new Alumno("Juan", "Pérez", "12345678", "55234");
        alumno2 = new Alumno("Ana", "Garcia", "87654321", "78433");

        GestorInstituto.getInstancia().addAlumnoToList(alumno1);
        GestorInstituto.getInstancia().addAlumnoToList(alumno2);

    }

    @Test0
    void testAlumnoExists() throws PrincipalException {
        assertFalse(GestorInstituto.getInstancia().addAlumnoToList(alumno1), "El alumno ya se encuentra registrado");
        assertFalse(GestorInstituto.getInstancia().addAlumnoToList(alumno2), "El alumno ya se encuentra registrado");

        Alumno alumnoNoRegistrado = new Alumno("María", "López", "11223344", "59999");
        assertTrue(GestorInstituto.getInstancia().addAlumnoToList(alumnoNoRegistrado), "Alumno registrado.,");
    }
}
