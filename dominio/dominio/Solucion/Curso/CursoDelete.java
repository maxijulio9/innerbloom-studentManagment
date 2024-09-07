package dominio.Solucion.Curso;

import dominio.Curso;
import dominio.Solucion.Alumno.IAlumnoGetDefaultList;
import exceptions.PrincipalException;
import persistencia.PersistenciaDBCurso;

public class CursoDelete implements ICursoDelete {
    private ICursoGetDefaultList cursoList;
    private IAlumnoGetDefaultList alumnoList;
    private CursoValidateExistenceAlumno cursoHasAlumno;


    public CursoDelete(ICursoGetDefaultList cursos, IAlumnoGetDefaultList alumnos, CursoValidateExistenceAlumno cursoHasAlumno){
        this.cursoList = cursos;
        this.alumnoList = alumnos;
        this.cursoHasAlumno = cursoHasAlumno;
    }

    @Override
    public boolean deleteCurso(String nameCurso) throws PrincipalException {

        for (Curso curso : cursoList.getListCursos()) {
            if (curso.getNombre().trim().equals(nameCurso.trim())
                    && cursoHasAlumno.cursoWithoutAlumno(alumnoList.getListAlumnos(), nameCurso)) {
//				System.out.println("elimino");
                PersistenciaDBCurso.delete(nameCurso);

                return true;
            }
        }
        throw new PrincipalException("El curso que intentas eliminar tiene alumnos registrados");
    }
}
