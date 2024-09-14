package dominio.SolucionSOLID.Curso;

import dominio.Alumno;

import java.util.ArrayList;

public class CursoValidateExistenceAlumno {

    public boolean cursoWithoutAlumno(ArrayList<Alumno> alumnos, String nameCurso) {
        System.out.println("\n - actual >"+ nameCurso+"<");

        for (Alumno alumno : alumnos) {
            String[] cursosAlumno= alumno.getCursosNombre().split(", ");

            for (String curso : cursosAlumno) {
                if (curso.trim().equals(nameCurso.trim())) {
                    return false;
                }
            }
        }
        return true;

    }
}
