package dominio.SolucionSOLID.Curso;

import dominio.Curso;
import dominio.SolucionSOLID.Alumno.AlumnoGetDefaultList;
import dominio.SolucionSOLID.Alumno.IAlumnoGetDefaultList;
import dominio.SolucionSOLID.GenericInterface.IDeletion;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;
import persistencia.PersistenciaDBCurso;

import java.util.ArrayList;

public class CursoDelete implements IDeletion<Curso> {
    private CursoGetDefaultList cursoList;
    private AlumnoGetDefaultList alumnoList;
    private CursoValidateExistenceAlumno cursoHasAlumno;


    /*
    public CursoDelete(ICursoGetDefaultList cursos, IAlumnoGetDefaultList alumnos, CursoValidateExistenceAlumno cursoHasAlumno){
        this.cursoList = cursos;
        this.alumnoList = alumnos;
        this.cursoHasAlumno = cursoHasAlumno;
    }

     */

    @Override
    public boolean delete(String nameCurso) throws PrincipalException {

        ArrayList<Curso> cursoLista =  cursoList.getDefaultList();
        for (Curso curso : cursoLista) {
            if (curso.getNombre().trim().equals(nameCurso.trim())
                    && cursoHasAlumno.cursoWithoutAlumno(alumnoList.getDefaultList(), nameCurso)) {
//				System.out.println("elimino");
                PersistenciaDBCurso.delete(nameCurso);

                return true;
            }
        }
        /*
        for (Curso curso : cursoList.getListCursos()) {
            if (curso.getNombre().trim().equals(nameCurso.trim())
                    && cursoHasAlumno.cursoWithoutAlumno(alumnoList.getListAlumnos(), nameCurso)) {
//				System.out.println("elimino");
                PersistenciaDBCurso.delete(nameCurso);

                return true;
            }
        }

         */
        return false;
        //throw new PrincipalException("El curso que intentas eliminar tiene alumnos registrados");
    }
}
