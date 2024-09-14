package dominio;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

import exceptions.FechaNoValida;
import exceptions.PrincipalException;


public class Matricula {
	private GregorianCalendar fechaInscripcion ;
	private Curso cursoInscripto;
	SimpleDateFormat formato = new SimpleDateFormat("dd/MMM/yyyy");
	
		
	//constructor que no recibe la fecha actual
	public Matricula(Curso cursoInscripto) {
		this.cursoInscripto = cursoInscripto;
		this.fechaInscripcion = generarFechaActual();
	}
	
	
	public GregorianCalendar generarFechaActual() {
		GregorianCalendar hoy = (GregorianCalendar) GregorianCalendar.getInstance();
		return hoy;
	
	}
	public String obtenerFechaInscripcion(){
    	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(this.fechaInscripcion.getTime());
	}
	public GregorianCalendar getFechaInscripcion() {
		return fechaInscripcion;
	}
	
	public void setFechaInscripcion(GregorianCalendar fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	
	public Curso getCursoInscripto() {
		return cursoInscripto;
	}
	public void setCursoInscripto(Curso cursoInscripto) {
		this.cursoInscripto = cursoInscripto;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cursoInscripto);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		return Objects.equals(cursoInscripto, other.cursoInscripto);
	}


	@Override
	public String toString() {
		return  obtenerFechaInscripcion() + "\t" + cursoInscripto.getNombre();
	}




    


    

}
