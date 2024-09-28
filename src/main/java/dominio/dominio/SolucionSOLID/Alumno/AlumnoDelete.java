package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.Persona;
import dominio.SolucionSOLID.GenericInterface.IDeletion;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;

import javax.swing.*;
import java.util.ArrayList;

public class AlumnoDelete implements IDeletion<Alumno> {


    @Override
    public boolean delete(String dni, ArrayList<Alumno> alumnosList) throws PrincipalException {
/*
        for (Alumno alumno : GestorInstituto
                .getInstance(null, null)
                .getAlumnoDefaultList()) {//getInstancia().listaAlumnos) {
        */

        for (Alumno alumno : alumnosList) {
            if (alumno.getDni().equalsIgnoreCase(dni)) {

                //verificar si se elimina desde  el metodo deleteAlumnoFromList o de otro lugar
                //return GestorInstituto.getInstance(null, null).deleteAlumnoFromList(alumno.getDni());//getInstancia().listaAlumnos.remove(alumno);

                return alumnosList.remove(alumno);
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