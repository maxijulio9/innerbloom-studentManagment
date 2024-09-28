package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.Persona;
import dominio.SolucionSOLID.GenericInterface.IDeletion;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;

import javax.swing.*;

public class AlumnoDelete implements IDeletion {


    @Override
    public boolean delete(String dni) throws PrincipalException {

        for (Alumno alumno : GestorInstituto
                .getInstance(null, null)
                .getAlumnoDefaultList()) {//getInstancia().listaAlumnos) {
            if (alumno.getDni().equalsIgnoreCase(dni)) {

                //verificar si se elimina desde  el metodo deleteAlumnoFromList o de otro lugar
                return GestorInstituto.getInstance(null, null).deleteAlumnoFromList(alumno.getDni());//getInstancia().listaAlumnos.remove(alumno);
                //PersistenciaDB.delete(alumno.getDni());
            }
        }
        return false;
        //throw new PrincipalException("No se encontró al alumno con el DNI ingresado");
    }



    /*
    public AlumnoEliminar(dominio.Problema.GestorInstituto gestor, String dni) {
        if (existeAlumno(gestor)) {
            gestor.listaAlumnos.delete(alumno.getDni());
        }

        private boolean existeAlumno (dominio.Problema.GestorInstituto gestor, String dni){
            return gestor.listaAlumnos.stream()
                    .filter(a -> a.getDni().equals(dni))
                    .findAny().isPresent();
        }
    }
    */

}