package dominio.SolucionSOLID.Curso;

import dominio.Alumno;
import dominio.Curso;
import dominio.SolucionSOLID.Alumno.AlumnoGetDefaultList;
import dominio.SolucionSOLID.Alumno.IAlumnoGetDefaultList;
import dominio.SolucionSOLID.GenericInterface.IDeletion;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;
import persistencia.PersistenciaDBCurso;

import java.util.ArrayList;

public class CursoDelete implements IDeletion<Curso> {
    //private CursoGetDefaultList cursoList;
    //private AlumnoGetDefaultList alumnoList;
    private CursoValidateExistenceAlumno cursoHasAlumno;


    /*
    public CursoDelete(ICursoGetDefaultList cursos, IAlumnoGetDefaultList alumnos, CursoValidateExistenceAlumno cursoHasAlumno){
        this.cursoList = cursos;
        this.alumnoList = alumnos;
        this.cursoHasAlumno = cursoHasAlumno;
    }

     */

    @Override
    public boolean delete(String nameCurso, ArrayList<Curso> cursosList) throws PrincipalException {

        //ArrayList<Curso> cursoLista =  cursoList.getDefaultList();

        ArrayList<Alumno> alumnosList = GestorInstituto.getInstance(null, null).getAlumnoDefaultList();
        for (Curso curso : cursosList) {
            if (curso.getNombre().trim().equals(nameCurso.trim())
                    && cursoHasAlumno.cursoWithoutAlumno(alumnosList, nameCurso)) {
//				System.out.println("elimino");
                //PersistenciaDBCurso.delete(nameCurso);

                return cursosList.remove(curso);
            }
        }
        return false;
        //return  throw new PrincipalException("El curso que intentas eliminar tiene alumnos registrados");
    }
}
