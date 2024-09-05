package dominio.Solucion;

import dominio.Alumno;
import exceptions.PrincipalException;
import persistencia.PersistenciaDB;

import javax.swing.*;

public class AlumnoEliminar implements IAlumnoEliminacion {
    @Override
    public boolean eliminarAlumno(String dni, GestorInstituto gestor) throws PrincipalException {

        for (Alumno alumno : gestor.listaAlumnos) {
            if (alumno.getDni().equalsIgnoreCase(dni)) {

                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Deseas eliminar " + alumno.getNombre() + " " + alumno.getApellido() + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    gestor.listaAlumnos.remove(alumno.getDni());
                    //PersistenciaDB.delete(alumno.getDni());
                    return true;
                }

            }
        }
        throw new PrincipalException("No se encontró al alumno con el DNI ingresado");
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