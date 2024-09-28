package dominio.SolucionSOLID;

import dominio.Alumno;
import dominio.Curso;
import dominio.SolucionSOLID.Alumno.*;
import dominio.SolucionSOLID.Curso.*;
import dominio.SolucionSOLID.Gestor.GestorAlumnos;
import dominio.SolucionSOLID.Gestor.GestorCursos;
import exceptions.PrincipalException;
import dominio.SolucionSOLID.GenericInterface.IGestor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class GestorInstituto {

	private static GestorInstituto instance;
	private ArrayList<Alumno> alumnosList;
	private ArrayList<Curso> cursosList;

	//SUBGESTORES
	private IGestor<Alumno> alumnoGestor;
	private IGestor<Curso> cursoGestor;
/*
//ALUMNOS
	private ICreation<Alumno> alumnoCreation;
	private IDeletion<Alumno> alumnoDelete;
	private IGetSortedList<Alumno> alumnoGetListSorted;
	private IGetFilteredList<Alumno> alumnoGetFiltered;
	private IGetDefaultList<Alumno> alumnoDefaultList;

	// CURSOS
	private ICreation<Curso> cursoCreation;
	private IDeletion<Curso> cursoDelete;
	private IGetSortedList<Curso> cursoGetSortedList;
	private IGetFilteredList<Curso> cursoGetFilteredList;
	private IGetDefaultList<Curso> cursoDefaultList;

	// TPODO Junto, ya no más
	private GestorInstituto(
			IGestor<Alumno> alumnoGestor,
			IGestor<Curso> cursoGestor,
			ICreation<Alumno> alumnoCreation,
			IDeletion<Alumno> alumnoDelete,
			IGetSortedList<Alumno> alumnoGetListSorted,
			IGetFilteredList<Alumno> alumnoGetFiltered,
			IGetDefaultList<Alumno> alumnoDefaultList,
			ICreation<Curso> cursoCreation,
			IDeletion<Curso> cursoDelete,
			IGetSortedList<Curso> cursoGetSortedList,
			IGetFilteredList<Curso> cursoGetFilteredList,
			IGetDefaultList<Curso> cursoDefaultList) {

		this.alumnoGestor = alumnoGestor;
		this.cursoGestor = cursoGestor;

		this.alumnoCreation = alumnoCreation;
		this.alumnoDelete = alumnoDelete;
		this.alumnoGetListSorted = alumnoGetListSorted;
		this.alumnoGetFiltered = alumnoGetFiltered;
		this.alumnoDefaultList = alumnoDefaultList;

		this.cursoCreation = cursoCreation;
		this.cursoDelete = cursoDelete;
		this.cursoGetSortedList = cursoGetSortedList;
		this.cursoGetFilteredList = cursoGetFilteredList;
		this.cursoDefaultList = cursoDefaultList;
	}

 */


	// Método estático parsa mantenerr la instancia unica de GestorInstituto
	public static synchronized GestorInstituto getInstance(IGestor<Alumno> alumnoGestor, IGestor<Curso> cursoGestor){
		/*IGestor<Alumno> alumnoGestor,
			IGestor<Curso> cursoGestor,
			ICreation<Alumno> alumnoCreation,
			IDeletion<Alumno> alumnoDelete,
			IGetSortedList<Alumno> alumnoGetListSorted,
			IGetFilteredList<Alumno> alumnoGetFiltered,
			IGetDefaultList<Alumno> alumnoDefaultList,
			ICreation<Curso> cursoCreation,
			IDeletion<Curso> cursoDelete,
			IGetSortedList<Curso> cursoGetSortedList,
			IGetFilteredList<Curso> cursoGetFilteredList,
			IGetDefaultList<Curso> cursoDefaultList)
		 */


		if (instance == null) {
			/*
			//Lo separé en dos gestoraes
			instance = new GestorInstituto(
					alumnoGestor,
					cursoGestor,
					alumnoCreation,
					alumnoDelete,
					alumnoGetListSorted,
					alumnoGetFiltered,
					alumnoDefaultList,
					cursoCreation,
					cursoDelete,
					cursoGetSortedList,
					cursoGetFilteredList,
					cursoDefaultList
			);
			 */
			instance = new GestorInstituto( alumnoGestor, cursoGestor);
		}
		return instance;
	}

	public GestorInstituto(IGestor<Alumno> alumnoGestor, IGestor<Curso> cursoGestor) {
		System.out.println("CREATING GestorInstituto");
		this.alumnosList = new ArrayList<Alumno>();
		this.cursosList = new ArrayList<Curso>();
		this.alumnoGestor = alumnoGestor;
		this.cursoGestor  = cursoGestor;
		/*
		this.alumnoGestor = new GestorAlumnos(alumnosList,new AlumnoCreation(),
															new AlumnoDelete(),
															new AlumnoGetSortedList(),
															new AlumnoGetFilteredList(),
															new AlumnoGetDefaultList());// alumnoGestor;
		this.cursoGestor = new GestorCursos(cursosList,new CursoCreation(),
														new CursoDelete(),
														new CursoGetSortedList(),
														new CursoGetFilteredList(),
														new CursoGetDefaultList());//cursoGestor;

		 */


	}

	public IGestor<Alumno> getGestorAlumnos() {
		return alumnoGestor;
	}

	public IGestor<Curso> getGestorCursos() {
		return cursoGestor;
	}


	// Métodos  de Alumnos

	public boolean addAlumnoToList(Alumno alumno) throws PrincipalException {
		//if (alumno == null) {
		//	throw new IllegalArgumentException("El alumno no puede ser nulo");
		//}
		//GestorAlumnos gestorAlumno = new GestorAlumnos(alumnosList,null,null,null,null,null);
		return alumnoGestor.addToList(alumno);
		//return alumnoGestor.addToList(alumno);//alumnoCreation.add(alumno);

	}
	public ArrayList<Alumno> getAlumnoDefaultList() {

		return alumnoGestor.getDefaultList();//alumnoDefaultList.getList();
	}

	public boolean deleteAlumnoFromList(String dni) throws PrincipalException {
		return alumnoGestor.deleteFromList(dni);
	}

	public ArrayList<Alumno> getAlumnosSortedList(Comparator<Alumno> comparatorAlumno) throws PrincipalException {
		return alumnoGestor.getSortedList(comparatorAlumno) ;//alumnoGetListSorted.getSortedList(comparatorAlumno);
	}

	public ArrayList<Alumno> getAlumnoFilteredList(Predicate<Alumno> filtroAlumno) throws PrincipalException {
		return alumnoGestor.getFilteredList(filtroAlumno);//alumnoGetFiltered.getFilteredList(filtroAlumno);
	}

	// Métodos  de Cursos

	public boolean addCursoToList(Curso curso) throws PrincipalException {
		if (curso == null) {
			System.out.println("El curso no puede ser nulo.");
			throw new IllegalArgumentException("El curso no puede ser nulo.");
		}
		System.out.println("Se registró el curso");
		return cursoGestor.addToList(curso);//cursoCreation.add(curso);
	}

	public boolean deleteCursoFromList(String nameCurso) throws PrincipalException {
		return cursoGestor.deleteFromList(nameCurso);//cursoDelete.delete(nameCurso);
	}

	public ArrayList<Curso> getCursoSortedList(Comparator<Curso> comparatorSort) throws PrincipalException {
		return cursoGestor.getSortedList(comparatorSort);//cursoGetSortedList.getSortedList(comparatorSort);
	}

	public ArrayList<Curso> getCursoFilteredList(Predicate<Curso> filterCurso) throws PrincipalException {
		return cursoGestor.getFilteredList(filterCurso); // cursoGetFilteredList.getFilteredList(filterCurso);
	}

	public ArrayList<Curso> getCursosDefaultList() {
		return cursoGestor.getDefaultList();//cursoDefaultList.getList();
	}



	// Listado de profesores
	public String[] getProfessorList() {
		return new String[]{"Sin definir", "Jeff Bezos", "Hedy Lamarr", "Marcos Galperin", "Jorge Tejada", "Mark Zuckerberg", "Elon Musk", "Mary Lee Woods"};
	}


}


//THIS IS OLD
/*
import dominio.Alumno;
import dominio.Curso;
import dominio.SolucionSOLID.Alumno.*;
import dominio.SolucionSOLID.Curso.*;
import exceptions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;


public class GestorInstituto {
	private static GestorInstituto gestor;
	public ArrayList<Alumno> listaAlumnos;
	public String[] listaProfesores = {"Sin definir", "Jeff Bezos", "Hedy Lamar", "Marcos Galperin", "Jorge Tejada", "Mark Zuckeberg", "Elon Musk", "Mary Lee Woods"};
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
	private ICursoGetSortedList cursoGetSortedList;
	private ICursoGetFilteredList cursoGetFilteredList;
	private ICursoGetDefaultList cursoGetDefaultList;

	//Old constructor

	private GestorInstituto() {
		System.out.println("CONSTRUCTOR : ");

		listaAlumnos = new ArrayList<Alumno>();
		// this.alumnoCreation = new AlumnoCreation();
		listaCurso = new ArrayList<Curso>();
	}
/*
	public static GestorInstituto getInstancia() {
		if (gestor == null) {
			gestor = new GestorInstituto();
		}
		return gestor;
	}


	//Getinstance constructor Alumno
	public static GestorInstituto getInstancia(IAlumnoGetDefaultList alumnosDefaultList,IAlumnoCreation alumnoCreating,IAlumnoDelete alumnoDeleting) {
		if (gestor == null) {
			System.out.println("Is not null");
			gestor = new GestorInstituto(alumnosDefaultList, alumnoCreating, alumnoDeleting);
		}
		return gestor;
	}
*/
/*
	//SOLID - inyecto Dependencias
	public GestorInstituto (IAlumnoPersistencia alumnoPersistencia,
							IAlumnoCreation alumnoCreating,
							IAlumnoDelete alumnoDeleting,
							IAlumnoGetDefaultList alumnosDefaultList,
							IAlumnoGetFiltered alumnoGetFiltered,
							IAlumnoGetListSorted alumnoGetListSorted,
							ICursoCreation cursoCreation,
							ICursoDelete cursoDelete,
							ICursoGetSortedList cursoGetSortedList,
							ICursoGetFilteredList cursoGetFilteredList,
							ICursoGetDefaultList cursoGetDefaultList) {
		this.alumnosDefaultList = alumnosDefaultList;
		this.listaAlumnos = alumnosDefaultList.getListAlumnos();
		this.alumnoCreation = alumnoCreating;
		this.alumnoDelete = alumnoDeleting;
		this.alumnosDefaultList = alumnosDefaultList;
		this.alumnoGetFiltered = alumnoGetFiltered;
		this.alumnoGetListSorted = alumnoGetListSorted;
		this.cursoCreation = cursoCreation;
		this.cursoDelete = cursoDelete;
		this.cursoGetSortedList = cursoGetSortedList;
		this.cursoGetFilteredList = cursoGetFilteredList;
		this.cursoGetDefaultList = cursoGetDefaultList;
	}

/*
	public GestorInstituto(IAlumnoGetDefaultList alumnosDefaultList,
						   IAlumnoCreation alumnoCreating,
						   IAlumnoDelete alumnoDeleting) {
		listaAlumnos = new ArrayList<Alumno>();


		this.alumnosDefaultList = alumnosDefaultList;
		this.listaAlumnos = alumnosDefaultList.getListAlumnos();
		System.out.println("CONSTRUCTOR ALUMNO: "+alumnoCreating);
		this.alumnoCreation = alumnoCreating;
		this.alumnoDelete = alumnoDeleting;
	}

	public GestorInstituto(ICursoCreation cursoCreation,
						   ICursoDelete cursoDelete,
						   ICursoGetSortedList cursoGetSortedList,
						   ICursoGetFilteredList cursoGetFilteredList,
						   ICursoGetDefaultList cursoGetDefaultList) {
		listaCurso = new ArrayList<Curso>();

		this.cursoCreation = cursoCreation;
		this.cursoDelete = cursoDelete;
		this.cursoGetSortedList = cursoGetSortedList;
		this.cursoGetFilteredList = cursoGetFilteredList;
		this.cursoGetDefaultList = cursoGetDefaultList;
		this.listaCurso = cursoGetDefaultList.getListCursos();
	}
	 */

/*
	public static GestorInstituto getInstancia() {
		if (gestor == null) {
			gestor = new GestorInstituto();  // Llama al constructor sin parámetros
		} else {
			System.out.println("Ya se  inicializo GestorInstituto. No se puede cambiar la configuración.");
		}
		return gestor;
	}

	public static GestorInstituto getInstancia(IAlumnoGetDefaultList alumnosDefaultList,
											   IAlumnoCreation alumnoCreating,
											   IAlumnoDelete alumnoDeleting) {
		if (gestor == null) {
			gestor = new GestorInstituto();
		} else {
			System.out.println("Ya se  inicializo GestorInstituto. No se puede cambiar la configuración.");
		}
		return gestor;
	}

	/*
	public static GestorInstituto getInstancia(ICursoCreation cursoCreation,
											   ICursoDelete cursoDelete,
											   ICursoGetSortedList cursoGetSortedList,
											   ICursoGetFilteredList cursoGetFilteredList,
											   ICursoGetDefaultList cursoGetDefaultList) {
		if (gestor == null) {
			gestor = new GestorInstituto(cursoCreation, cursoDelete, cursoGetSortedList, cursoGetFilteredList, cursoGetDefaultList);
		} else {
			System.out.println("Ya se  inicializo GestorInstituto. No se puede cambiar la configuración.");
		}
		return gestor;
	}

	 */
/*
	//S, O y D principñles applied
	//but IDK if do this is necessary.
	public ArrayList<Alumno> getAlumnoDefaultList() {

		return this.listaAlumnos;
	}

	public void setListAlumnos(ArrayList<Alumno> listAlumnos) {
		this.listaAlumnos = getAlumnoDefaultList();
		//this.listaAlumnos = listAlumnos;
	}

	//here i can change it and use the interface ICuirsoGetdefaultList- later
	public ArrayList<Curso> getCursosDefaultList() {
		return cursoGetDefaultList.getListCursos();
		//return PersistenciaDBCurso.getCursos();
	}

	public void setListCurso(ArrayList<Curso> listaCurso) {
		this.listaCurso = getCursosDefaultList();
		//this.listaCurso = listaCurso;
	}

	//Agregar alumnos ------------------------------

	//Principio S y D
	public boolean addAlumnoToList(Alumno alumno){
		//System.out.println("VLAOR: " +  alumnoCreation.addAlumno(alumno) );
		if (alumno == null) {
			throw new IllegalArgumentException("El alumno no puede ser nulo.");
		}

		if (this.listaAlumnos == null) {
			this.listaAlumnos = new ArrayList<>();
		}
		//aca rompo con solid:%
		if (this.alumnoCreation == null) {
			this.alumnoCreation = new AlumnoCreation();
		}

		return alumnoCreation.addAlumno(alumno);

		//return this.listaAlumnos.add(alumno);
	}
	//validar como agregar
	public boolean addAlumnoToList(String nombre, String apellido, String dni, String telefono) throws PrincipalException {
		//Alumno alumno =  new Alumno( nombre,  apellido,  dni,  telefono);
		return alumnoCreation.addAlumno( nombre,  apellido,  dni,  telefono);
	}



	//Principio S y D
	public boolean deleteAlumnoFromList(String dni) throws PrincipalException {
		return alumnoDelete.deleteAlumno(dni);
	}


	//- -.................CURSOS--------
	public boolean addCursoToList(Curso curso) throws PrincipalException {
		return cursoCreation.addCurso(curso);
	}


	public boolean deleteCursoFromList(String nameCurso) throws PrincipalException {
		return cursoDelete.deleteCurso(nameCurso);

	}



	//Not used
	public String[] obtenerListadoProfes() {
		return this.listaProfesores;
	}

	//Principio S y D
	public ArrayList<Alumno> getAlumnosSortedList(Comparator<Alumno> comparatorAlumno) throws PrincipalException{
		return alumnoGetListSorted.getListadoAlumnosOrdenado(comparatorAlumno);
	}


	//Cursos ordenados SOLID -  S y D only
	public ArrayList<Curso> getCursoSortedList(Comparator<Curso> comparatorSort){
		return cursoGetSortedList.getCursosSortedByComparator(comparatorSort);
	}


	//Cursos filtrados SOLID -  S y D only
	public ArrayList<Curso> getCursoFilteredList(Predicate<Curso> filterCurso){
		return cursoGetFilteredList.getListadoFiltrado(filterCurso);
	}

	//	Principio S y D aplicado
	public ArrayList<Alumno> getAlumnoFilteredList(Predicate<Alumno> filtroAlumno){
		return alumnoGetFiltered.getListadoFiltradoAlumno(filtroAlumno);
		//return getListadoFiltradoAlumno(filtroAlumno, ge);
	}




}

 */
