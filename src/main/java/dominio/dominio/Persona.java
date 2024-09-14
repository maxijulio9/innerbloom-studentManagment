package dominio;

import java.util.Objects;

import exceptions.ApellidoVacioException;
import exceptions.DniInvalidoException;
import exceptions.NombreVacioException;
import exceptions.PrincipalException;
import exceptions.TelefonoInvalidoException;

public class Persona {//implements Comparable<Persona>{

	protected String nombre;
	protected String apellido;
	protected String dni;
	protected String telefono;

	//constructor
	public Persona(String nombre, String apellido
			, String dni, String telefono) throws PrincipalException{
		
		setNombre(nombre);
		setApellido(apellido);
		setDni(dni);
		setTelefono(telefono);
	}
	public Persona() throws PrincipalException{
		
	}
	
	//getters and setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws NombreVacioException {
		if (nombre.trim().length()==0) {
			throw new NombreVacioException();
		}
		
		this.nombre = nombre.trim();
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) throws ApellidoVacioException {
		if (apellido.trim().length()==0) {
			throw new ApellidoVacioException();
		}
		this.apellido = apellido.trim();
	}


	public String getDni() {
		return dni;
	}

	public void setDni(String dni)throws PrincipalException {
	
		if (validarDNI(dni)){
			throw new DniInvalidoException();
		}	
		this.dni = dni;
		
	}
	private boolean validarDNI(String txt) {
		try {
			long aux  = (long)Long.parseLong(txt);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) throws PrincipalException{
		if (telefono =="") {
			throw new TelefonoInvalidoException();
		}
		this.telefono = telefono;
	}

//
//	@Override
//	public int compareTo(Persona otraPersona) {
//		long comparacionApellido = this.apellido.compareToIgnoreCase(otraPersona.getApellido());
//
//		if (comparacionApellido != 0) {
//			return (int) comparacionApellido;
//		}
//
//		return (this.dni.compareTo(otraPersona.getDni()));
//	}

	//hashcode and equals
	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, nombre, telefono);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return dni == other.dni;
	}
	

}
