package dominio.Problema;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import dominio.Alumno;
import dominio.Curso;
import exceptions.ApellidoVacioException;
import exceptions.DniInvalidoException;
import exceptions.NombreVacioException;
import exceptions.PrincipalException;
import exceptions.TelefonoInvalidoException;
import persistencia.PersistenciaDB;
import persistencia.PersistenciaDBCurso;


public class GestorInstitutoOld {
	private static GestorInstitutoOld ge;
	public ArrayList<Alumno> listaAlumnos;
	public String[] listaProfesores = {"Sin definir","Jeff Bezos","Hedy Lamar","Marcos Galperin", "Jorge Tejada","Mark Zuckeberg", "Elon Musk", "Mary Lee Woods"};
	public ArrayList<Curso> listaCurso;


	private GestorInstitutoOld() {
		listaAlumnos = new ArrayList<Alumno>();

		listaCurso = new ArrayList<Curso>();
	}
	
	public static GestorInstitutoOld getInstancia() {
		if (ge == null) {
			ge = new GestorInstitutoOld();
		}
		return ge;
	}

	public ArrayList<Alumno> getListaAlumnos() {
		
		return PersistenciaDB.getAlumnos();
	}

	public void setListaAlumnos(ArrayList<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}
	
	public ArrayList<Curso> getListaCursos() {
		return PersistenciaDBCurso.getCursos();
	}

	public void setListaCurso(ArrayList<Curso> listaCurso) {
		this.listaCurso = listaCurso;
	}
	
	//Agregar elementos
	public boolean agregarAlumno(Alumno alumno) {//no debe recibir alumno, sino los datos de los text field
		if (!existeAlumno1(alumno)) {
			PersistenciaDB.insert(alumno);
			this.listaAlumnos.add(alumno);
			return true;
		}
		return false;	
	}

	public boolean agregarAlumno(String nombre, String apellido, String dni, String telefono) throws PrincipalException {//no debe recibir alumno, sino los datos de los text field
//		Alumno alumno;
		
		try {
			Alumno alumno = new Alumno(nombre, apellido, dni, telefono);
		
			if (existeAlumno1(alumno)) {
				throw new PrincipalException("Ya se encuentra registrado.");
			}
			return this.listaAlumnos.add(alumno);
			
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
	
	private boolean existeAlumno1(Alumno alumno) {
		ArrayList<Alumno> listaAlumnosDB = PersistenciaDB.getAlumnos();
		
		return listaAlumnosDB.stream()
				.filter(a->a.getDni().equals(alumno.getDni()))
				.findAny().isPresent();
	}

	public boolean agregarCurso(Curso curso) throws PrincipalException {
		if (noExisteCurso1(curso)) {
			PersistenciaDBCurso.insert(curso);;
			return this.listaCurso.add(curso);
		}else {
			throw new PrincipalException("El curso ya se encuentra registrado.");
		}
	}
	
	//Buscar Elementos

	public boolean noExisteCurso1(Curso curso) {
		ArrayList<Curso> listadoCurso= PersistenciaDBCurso.getCursos();
	    for (Curso c : listadoCurso) {
	        if (c.getCodigo() == curso.getCodigo() || c.getNombre().trim().equalsIgnoreCase(curso.getNombre().trim())) {
	            return false; // El curso ya existe en la lista
	        }
	    }
	    return true; // El curso no existe en la lista
	}

	public boolean eliminarAlumno(String dni) throws PrincipalException {
		
		ArrayList<Alumno> listaAlumnosDB  =  PersistenciaDB.getAlumnos();
		
		for (Alumno alumno : listaAlumnosDB) {
			if (alumno.getDni().equalsIgnoreCase(dni)) {
				
				 int opcion = JOptionPane.showConfirmDialog(
			                null,
			                "¿Deseas eliminar " + alumno.getNombre() + " " + alumno.getApellido() + "?",
			                "Confirmar eliminación",
			                JOptionPane.YES_NO_OPTION
			            );

			     if (opcion == JOptionPane.YES_OPTION) {
			         PersistenciaDB.delete(alumno.getDni());
			            return true;
			     } else {
			         return false;
			     }
				
			}
		}
		throw new PrincipalException("No se encontró al alumno con el DNI ingresado");
	}
	
	public boolean eliminarCurso(String nombreCurso) throws PrincipalException {
		ArrayList<Curso> listaCurso =  PersistenciaDBCurso.getCursos();
		ArrayList<Alumno> listaAlumnos = PersistenciaDB.getAlumnos();
		
		for (Curso curso : listaCurso) {
			if (curso.getNombre().trim().equals(nombreCurso.trim()) && noContieneAlumnos(listaAlumnos, nombreCurso)==true) {
//				System.out.println("elimino");
				PersistenciaDBCurso.delete(nombreCurso);
				
				return true;
			}
		}
		throw new PrincipalException("El curso que intentas eliminar tiene alumnos registrados");
	}
	//valida q existe un alumno inscripto en el curso
	private boolean noContieneAlumnos(ArrayList<Alumno> alumnos, String nombreCurso) {
		System.out.println("\n - actual >"+ nombreCurso+"<");
		for (Alumno alumno2 : alumnos) {
			String[] cursos= alumno2.getCursosNombre().split(", ");
			
			for (String curso : cursos) {
//			    System.out.println("Longitud del curso: " + curso.length());
//			    System.out.println("Longitud del cursoNOMBRE: " + nombreCurso.length());
////				System.out.println(">>"+curso+"<<");
//				System.out.println("CURSOS: "+ curso);
                if (curso.trim().equals(nombreCurso.trim())) {
                    return false;
                }
            }
		}
		return true;

	}

	public String[] obtenerListadoProfes() {
		return this.listaProfesores;
	}

	
	public ArrayList<Alumno> getListadoAlumnosOrdenado(Comparator<Alumno> compa) throws PrincipalException{
		ArrayList<Alumno> alumnos = PersistenciaDB.getAlumnos();//PersistenciaDB.getListaOrdenadaAlumno(compa);

//		System.out.println("\nALUMNOS:  ");
//		alumnos.stream().forEach(a->System.out.println(a.toString()));
		alumnos = alumnos
				.stream()
				.sorted(compa)
				.collect(Collectors.toCollection(ArrayList<Alumno>::new));
		System.out.println("\n\nAfter ORDENAR");
		alumnos.stream().forEach(a->System.out.println(a.toString()));
		return alumnos;
	}

	public ArrayList<Curso> getListadoCursosOrdenado(Comparator<Curso> compa){
		ArrayList<Curso> cursos = listaCurso
				.stream()
				.sorted(compa)
				.collect(Collectors.toCollection(ArrayList<Curso>::new));
		return cursos;
	}
	//devuelve lista filtrada
	public ArrayList<Curso> getListadoFiltrado(Predicate<Curso> filtro){
		ArrayList<Curso> listaCursos = PersistenciaDBCurso.getCursos();
		return listaCursos.stream()
				.filter(filtro) 
				.collect(Collectors.toCollection(ArrayList::new));
	}
	public ArrayList<Alumno> getListadoFiltradoAlumno(Predicate<Alumno> filtro){
		ArrayList<Alumno> listaCursos = PersistenciaDB.getAlumnos();
		return listaCursos.stream()
				.filter(filtro) 
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	
}
