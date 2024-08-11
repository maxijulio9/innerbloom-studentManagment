package dominio;

import java.util.Objects;

import exceptions.CantidadAlumnosException;
import exceptions.CantidadHorasException;
import exceptions.NivelVacioException;
import exceptions.NombreVacioException;
import exceptions.PrincipalException;

public class Curso implements Comparable<Curso> {
	private  int codigo;
	private static int codigo2 = 0;
	private String nombre;
	private String nivel;
	private String totalHoras;
	private String cantidadAlumnos;
	private String profesorAsignado;
	
	public Curso() throws PrincipalException {
	}

	public Curso(String nombre, String nivel, String totalHoras, String cantidadAlumnos,
			String profesorAsignado) throws PrincipalException {
		
		this.codigo = generarCodigo();
		setNombre(nombre);
		setNivel(nivel);
		setTotalHoras(totalHoras);
		setCantidadAlumnos(cantidadAlumnos);
		this.profesorAsignado = profesorAsignado;
//		codigo2++;
	}

	public Curso(int codigo, String nombre, String nivel, String totalHoras, String cantidadAlumnos,
			String profesorAsignado) throws PrincipalException {
		
		this.codigo = codigo;
		setNombre(nombre);
		setNivel(nivel);
		setTotalHoras(totalHoras);
		setCantidadAlumnos(cantidadAlumnos);
		this.profesorAsignado = profesorAsignado;
//		codigo2++;
	}
	private int generarCodigo(){
		int numero = (int)(Math.random()*500+1);
		return numero;
//		return codigo++;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) throws NombreVacioException {
		if (nombre.equals("")) {
			throw new NombreVacioException();
		}
		this.nombre = nombre.trim();
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) throws NivelVacioException{
		if (nivel.equals("")) {
			throw new NivelVacioException();
		}
		this.nivel = nivel;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTotalHoras() {
		return totalHoras;
	}
	public void setTotalHoras(String totalHoras) throws PrincipalException{
		if (validarDatoNumerico(totalHoras)) {
			throw new CantidadHorasException("Carga horaria inválida. Ingresá un valor válido positivo.");
		}
		this.totalHoras = totalHoras;
	}
	
	private boolean validarDatoNumerico(String txt) {
		try {
			int aux  = Integer.parseInt(txt);
			if (aux <0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return true;
		}
	}
	
	public String getCantidadAlumnos() {
		return cantidadAlumnos;
	}
	public void setCantidadAlumnos(String vacantes)throws PrincipalException{
		if (validarDatoNumerico(vacantes)) {
			throw new CantidadAlumnosException("Cantidad de alumnos inválida. Ingresá un valor válido positivo.");
		}
		this.cantidadAlumnos = vacantes;
	}
	public String getProfesorAsignado() {
		if(this.profesorAsignado == "Sin definir"){
			return "Sin profesor asignado.";
		}
		return this.profesorAsignado;
//		return profesorAsignado;
	}
	public void setProfesorAsignado(String profesorAsignado) {
		this.profesorAsignado = profesorAsignado;
	}
	public void designarProfesor(){
		this.profesorAsignado = "Sin definir";
	}

	
	
	
	@Override
	public String toString() {
		return  getCodigo() + "\t" + getNombre()+ "\t" + getNivel()+
				 "\t" + getTotalHoras() + "\t\t" + getCantidadAlumnos() + "\t\t"
				+ getProfesorAsignado()+"\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return codigo == other.codigo || nombre.equalsIgnoreCase(other.nombre);
	}
	@Override
	public int compareTo(Curso other) {
	   String a=new String(String.valueOf(this.getCodigo()));
	   String b=new String(String.valueOf(other.getCodigo()));
	   return a.compareTo(b);
   }
	
	
}
