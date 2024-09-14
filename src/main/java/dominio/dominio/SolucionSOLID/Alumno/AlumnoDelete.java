package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;

import javax.swing.*;

public class AlumnoDelete implements IAlumnoDelete {
    @Override
    public boolean deleteAlumno(String dni, GestorInstituto gestor) throws PrincipalException {

        for (Alumno alumno : gestor.listaAlumnos) {
            if (alumno.getDni().equalsIgnoreCase(dni)) {

                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Deseas eliminar " + alumno.getNombre() + " " + alumno.getApellido() + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    gestor.listaAlumnos.remove(alumno);
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