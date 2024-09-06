package dominio.Solucion;

import dominio.Alumno;

import java.util.ArrayList;

public class CursoValidateExistenceAlumno {

    public boolean cursoWithoutAlumno(ArrayList<Alumno> alumnos, String nameCurso) {
        System.out.println("\n - actual >"+ nameCurso+"<");

        for (Alumno alumno : alumnos) {
            String[] cursos= alumno.getCursosNombre().split(", ");

            for (String curso : cursos) {
                if (curso.trim().equals(nameCurso.trim())) {
                    return false;
                }
            }
        }
        return true;

    }
}
