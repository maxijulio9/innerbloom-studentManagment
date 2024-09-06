package dominio.Solucion;

import dominio.Alumno;
import dominio.Curso;
import exceptions.*;
import persistencia.PersistenciaDB;
import persistencia.PersistenciaDBCurso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class GestorInstituto {
	private static GestorInstituto ge;
	public ArrayList<Alumno> listaAlumnos;
	public String[] listaProfesores = {"Sin definir","Jeff Bezos","Hedy Lamar","Marcos Galperin", "Jorge Tejada","Mark Zuckeberg", "Elon Musk", "Mary Lee Woods"};
	public ArrayList<Curso> listaCurso;

	//interfaces ALUMNOS
	private IAlumnoCreation alumnoCreation;
	private IAlumnoDelete alumnoDelete;
	private IAlumnoGetListSorted alumnoGetListSorted;
	private IAlumnoGetFiltered alumnoGetFiltered;
	private IAlumnoGetDefaultList alumnosDefaultList;

	//interfaces CURSO
	private ICursoCreation cursoCreation;
	private ICursoDelete cursoDelete;


	private GestorInstituto() {
		listaAlumnos = new ArrayList<Alumno>();

		listaCurso = new ArrayList<Curso>();
	}
	
	public static GestorInstituto getInstancia() {
		if (ge == null) {
			ge = new GestorInstituto();
		}
		return ge;
	}

	//SOLID - inyecto Dependencias
	public GestorInstituto (AlumnoCreation alumnoCreating,
							IAlumnoDelete alumnoDeleting,
							IAlumnoGetDefaultList alumnosDefaultList,
							IAlumnoGetFiltered alumnoGetFiltered,
							IAlumnoGetListSorted alumnoGetListSorted,
							ICursoCreation cursoCreation, ICursoDelete cursoDelete) {
		this.alumnoCreation = alumnoCreating;
		this.alumnoDelete = alumnoDeleting;
		this.alumnosDefaultList = alumnosDefaultList;
		this.alumnoGetFiltered = alumnoGetFiltered;
		this.alumnoGetListSorted = alumnoGetListSorted;
		this.cursoCreation = cursoCreation;
		this.cursoDelete = cursoDelete;
	}

	//S, O y D principñles applied
	//but IDK if do this is necessary. ASK
	public ArrayList<Alumno> getListaAlumnos() {

		return alumnosDefaultList.getListAlumnos();
	}
	/*
	//old
	public ArrayList<Alumno> getListaAlumnos() {
		
		return PersistenciaDB.getAlumnos();
	}
	 */

	public void setListaAlumnos(ArrayList<Alumno> listAlumnos) {

		this.listaAlumnos = listAlumnos;
	}

	//here i can change it and use the interface ICuirsoGetdefaultList- later
	public ArrayList<Curso> getListCursos() {
		return PersistenciaDBCurso.getCursos();
	}

	public void setListaCurso(ArrayList<Curso> listaCurso) {
		this.listaCurso = listaCurso;
	}
	
	//Agregar alumnos ------------------------------

	//Principio S y D
	public boolean agregarAlumnoAlListado(Alumno alumno){
		return alumnoCreation.addAlumno(alumno, ge);

		//return this.listaAlumnos.add(alumno);
	}
	//validar como agregar
	public boolean agregarAlumnoAlListado(String nombre, String apellido, String dni, String telefono) throws PrincipalException {
		//Alumno alumno =  new Alumno( nombre,  apellido,  dni,  telefono);
		return alumnoCreation.addAlumno( nombre,  apellido,  dni,  telefono, ge);
	}

	/*
	public boolean agregarAlumno(Alumno alumno) {//no debe recibir alumno, sino los datos de los text field
		if (!existeAlumno1(alumno)) {
			PersistenciaDB.insert(alumno);
			this.listaAlumnos.add(alumno);
			return true;
		}
		return false;	
	}


	public boolean agregarAlumno(String nombre, String apellido, String dni, String telefono) throws PrincipalException {//no debe recibir alumno, sino los datos de los text field
		
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
	*/

	/*
	//This method servía en la old version de gestor para validar si existía el alumno in the arraylist listadoAlumnos
	private boolean existeAlumno1(Alumno alumno) {
		ArrayList<Alumno> listaAlumnosDB = PersistenciaDB.getAlumnos();
		
		return listaAlumnosDB.stream()
				.filter(a->a.getDni().equals(alumno.getDni()))
				.findAny().isPresent();
	}
	 */

	//Principio S y D
	public boolean eliminarAlumno(String dni, GestorInstituto ge) throws PrincipalException {
		return alumnoDelete.deleteAlumno(dni, ge);
	}

	/*
	//This is a old method el cual eliminaba el alumno si lo encontraba luego de recorrer.
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

	 */

	//- -.................CURSOS--------
	public boolean agregarCurso(Curso curso) throws PrincipalException {
		return cursoCreation.addCurso(curso);
	}
	/*
	public boolean agregarCurso(Curso curso) throws PrincipalException {
		if (noExisteCurso1(curso)) {
			PersistenciaDBCurso.insert(curso);;
			return this.listaCurso.add(curso);
		}else {
			throw new PrincipalException("El curso ya se encuentra registrado.");
		}
	}

	 */
	/*
	//Buscar Elementos: old
	public boolean noExisteCurso1(Curso curso) {
		ArrayList<Curso> listadoCurso= PersistenciaDBCurso.getCursos();
	    for (Curso c : listadoCurso) {
	        if (c.getCodigo() == curso.getCodigo() || c.getNombre().trim().equalsIgnoreCase(curso.getNombre().trim())) {
	            return false; // El curso ya existe en la lista
	        }
	    }
	    return true; // El curso no existe en la lista
	}

	 */

	public boolean deleteCurso(String nameCurso) throws PrincipalException {
		return cursoDelete.deleteCurso(nameCurso);

	}


	/*
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
*/
	//Not used
	public String[] obtenerListadoProfes() {
		return this.listaProfesores;
	}

	//Principio S y D
	public ArrayList<Alumno> getListadoAlumnosOrdenado(Comparator<Alumno> comparatorAlumno, GestorInstituto ge) throws PrincipalException{
		return alumnoGetListSorted.getListadoAlumnosOrdenado(comparatorAlumno, ge);
	}

	/*
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

	 */


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
		return listaCurso.stream()
				.filter(filtro) 
				.collect(Collectors.toCollection(ArrayList::new));
	}

	//	Principio S y D aplicado
	public ArrayList<Alumno> getListadoFiltradoAlumno(Predicate<Alumno> filtroAlumno, GestorInstituto ge){
		return alumnoGetFiltered.getListadoFiltradoAlumno(filtroAlumno, ge);
		//return getListadoFiltradoAlumno(filtroAlumno, ge);
	}

	/*
	public ArrayList<Alumno> getListadoFiltradoAlumno(Predicate<Alumno> filtro){
		ArrayList<Alumno> listaCursos = PersistenciaDB.getAlumnos();
		return listaAlumnos.stream()
				.filter(filtro) 
				.collect(Collectors.toCollection(ArrayList::new));
	}

	 */
	
	
}
