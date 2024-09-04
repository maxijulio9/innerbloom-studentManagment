package dominio.Solucion;

import dominio.Alumno;
import dominio.Solucion.GestorInstituto;
import exceptions.*;

public class AlumnoCreacion implements IAlumnoCreacion{

    //check si realmente es necesario this method
    @Override
    public boolean agregarAlumno(String nombre, String apellido, String dni, String telefono, GestorInstituto gestor) {

        try {
            Alumno alumno = new Alumno(nombre, apellido, dni, telefono);

            if (existeAlumno1(alumno, gestor)) {
                throw new PrincipalException("Ya se encuentra registrado.");
            }
            return gestor.listaAlumnos.add(alumno);

        }catch (DniInvalidoException e2) {
            return false;
        }catch (NombreVacioException e2) {
            return false;
        }catch (ApellidoVacioException e2) {
            return false;
        }catch (TelefonoInvalidoException e2) {
            return false;
        }catch (PrincipalException e) {
            return false;
        }

    }
    @Override
    public boolean agregarAlumno(Alumno alumno, GestorInstituto gestor) {
        if (!existeAlumno1(alumno, gestor)) {
            //PersistenciaDB.insert(alumno);
            gestor.listaAlumnos.add(alumno);
            return true;
        }

        return false;
    }


    private boolean existeAlumno1(Alumno alumno, GestorInstituto gestor) {
        //ArrayList<Alumno> listaAlumnosDB = PersistenciaDB.getAlumnos();

        return gestor.listaAlumnos.stream()
                .anyMatch(a->a.getDni().equals(alumno.getDni()));
    }

}
