package dominio;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Objects;

import exceptions.MatriculaExistenteException;
import exceptions.PrincipalException;
import persistencia.PersistenciaDB;


public class Alumno extends Persona implements Comparable<Alumno>{

	private ArrayList<Matricula> cursosInscriptos = new ArrayList<Matricula>();
	private GregorianCalendar fechaCreacion;
	private String fechaCreacionCorta;
	private String cursosInscriptosNombres;

	public Alumno() throws PrincipalException{
		this.fechaCreacion = getFechaGregoriana();
		this.fechaCreacionCorta =  getFechaCreacionCorta();
		cursosInscriptos = new ArrayList<Matricula>();
	}
	public Alumno(String nombre, String apellido
			,  String dni, String telefono) throws PrincipalException{
		super(nombre, apellido,  dni, telefono);
		this.fechaCreacion = getFechaGregoriana();
		cursosInscriptos = new ArrayList<Matricula>();
		this.fechaCreacionCorta = getFechaCreacionCorta();
	}
	
	
	public Alumno(String nombre, String apellido
			,  String dni, String telefono, String fechaCreacionCorta) throws PrincipalException{
		super(nombre, apellido,  dni, telefono);
			this.fechaCreacionCorta =  fechaCreacionCorta;//getFechaCreacionCorta();
		cursosInscriptos = new ArrayList<Matricula>();
	}
	public Alumno(String nombre, String apellido
			,  String dni, String telefono, String fechaCreacionCorta, String cursosInscriptos) throws PrincipalException{
		super(nombre, apellido,  dni, telefono);
		
		this.fechaCreacionCorta =  fechaCreacionCorta;
		cursosInscriptosNombres = cursosInscriptos;
	}
	

	private GregorianCalendar getFechaGregoriana() {
		GregorianCalendar hoy = (GregorianCalendar) GregorianCalendar.getInstance();
		return hoy;
	}
	
	public String getFechaCreacionCorta() {
		return this.fechaCreacionCorta;
	}
	public String getFechaCreacionCortaString() {
		return this.fechaCreacionCorta;
	}
	public void setFechaCreacionCortaString(String fechaCreacion) {
		this.fechaCreacionCorta = fechaCreacion;
	}
	
	public ArrayList<Matricula> getMatriculas() {
		return cursosInscriptos;
	}
	
	
	public boolean agregarMatricula(Alumno alumno, Matricula nuevaMatricula) throws PrincipalException {
		String cursosInscriptosDB = PersistenciaDB.getCursosInscriptosDB(alumno.getDni()); 
		if (cursosInscriptosDB == "" || cursosInscriptosDB == null) {
			PersistenciaDB.insertCurso(this, nuevaMatricula.getCursoInscripto().getNombre());
			return this.cursosInscriptos.add(nuevaMatricula);
		}  
		if (existeMatricula(alumno, nuevaMatricula)) {
			throw new MatriculaExistenteException();
		}
		PersistenciaDB.insertCurso(this, nuevaMatricula.getCursoInscripto().getNombre());
		return true;
//		return this.cursosInscriptos.add(nuevaMatricula);
	}

	
	public boolean existeMatricula(Alumno alumno, Matricula matricula) {
		String cursosInscriptosDB = PersistenciaDB.getCursosInscriptosDB(alumno.getDni()); 
		System.out.println("\nLOS CURSOS ACTUALEs:  "+cursosInscriptosDB);
		if (cursosInscriptosDB.contains(matricula.getCursoInscripto().getNombre())) {
			return true;
		}
		return false;
	}
	
	
	public String getCursosNombre() {

		String cursosInscriptosDB = PersistenciaDB.getCursosInscriptosDB(this.dni);
		if (cursosInscriptosDB =="" || cursosInscriptosDB ==null) {
			return "Sin cursos";
		}
		return cursosInscriptosDB;
	}
	
	
	@Override
	  public int compareTo(Alumno otroAlumno) {

	        int comparacionApellido = this.getApellido().compareToIgnoreCase(otroAlumno.getApellido());

	        if (comparacionApellido != 0) {
	            return comparacionApellido;
	        }

	        return this.getDni().compareTo(otroAlumno.getDni());
	    }
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cursosInscriptos, fechaCreacion);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(this.getDni(), other.getDni());
	}


	@Override
	public String toString()  {
		return getFechaCreacionCorta()+"\t"+getDni()+ "\t" +getNombre() + "\t\t" +  getApellido()  + "\t\t"
				+ getTelefono()+"\t\t"+getCursosNombre()+"\n";
	}
}
