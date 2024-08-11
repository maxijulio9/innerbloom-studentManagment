package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;


import dominio.Alumno;
import dominio.Conexion;
import exceptions.PrincipalException;

public class PersistenciaDB {

	public static void insert(Alumno alumno) {
		Conexion conexionTest = new Conexion();
		Connection cn=null; 
		try {
			cn = conexionTest.Conectar();
			String query = "INSERT INTO alumno (alumnoFechaCreacion,alumnoNombre, alumnoApellido, alumnoDni, alumnoTelefono, alumnoCursosInscriptos) VALUES (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStatement = cn.prepareStatement(query);
			// insertar los valores;n
			preparedStatement.setString(1, alumno.getFechaCreacionCortaString());
			preparedStatement.setString(2, alumno.getNombre());
			preparedStatement.setString(3, alumno.getApellido());
			preparedStatement.setString(4, alumno.getDni());
			preparedStatement.setString(5, alumno.getTelefono());
			preparedStatement.setString(6, alumno.getCursosNombre());
			preparedStatement.executeUpdate();
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "Error al agregar el alumno a la base de datos: " + e2.toString());					
		}
	}
	
	public static void insertCurso(Alumno alumno, String nombreCurso) {
		Conexion conexionTest = new Conexion();
		Connection cn=null; 
		try {
			cn = conexionTest.Conectar();
			String query = "UPDATE alumno SET alumnoCursosInscriptos = ? WHERE alumnoDni = ?";
			PreparedStatement preparedStatement = cn.prepareStatement(query);
			// insertar los valores;n
			String cursosInscriptos = getCursosInscriptosDB(alumno.getDni());
			System.out.println(">>>"+cursosInscriptos+"<<<");
			if (cursosInscriptos.trim().equalsIgnoreCase("Sin cursos,")) {//si No contiene cursos, se borra registro y se setea el nuevo curso,
				cursosInscriptos = nombreCurso;
			}else {
				if (nombreCurso != " ") {
					cursosInscriptos += nombreCurso;
				}
//				cursosInscriptos += nombreCurso;
			}
			preparedStatement.setString(1, cursosInscriptos);
			preparedStatement.setString(2, alumno.getDni());
			preparedStatement.executeUpdate();
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "Error al agregar registrar curso inscripto en la Base de Datos: " + e2.toString());					
		}
	}
	
	public static void delete(String dni) {
		Conexion conexionTest = new Conexion();
		Connection cn=null;
		
		try {
			cn = conexionTest.Conectar();
			String query = "DELETE FROM alumno WHERE alumnoDni = ?";
			PreparedStatement preparedStatement = cn.prepareStatement(query);
	        preparedStatement.setString(1, dni);
	        preparedStatement.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar el alumno a la base de datos: " + e.toString());
		}
	}
	
	public static void updateCursosActualizadosAlumnos(Alumno alumno, String nombreCurso) {
		Conexion conexionTest = new Conexion();
		Connection cn=null; 
		try {
			cn = conexionTest.Conectar();
			String query = "UPDATE alumno SET alumnoCursosInscriptos = ? WHERE alumnoDni = ?";
			PreparedStatement preparedStatement = cn.prepareStatement(query);
			// insertar los valores;n
//			String cursosInscriptos = getCursosInscriptosDB(alumno.getDni());
//			System.out.println(">>>"+cursosInscriptos+"<<<");
//			if (cursosInscriptos.trim().equalsIgnoreCase("Sin cursos,")) {//si No contiene cursos, se borra registro y se setea el nuevo curso,
//				cursosInscriptos = nombreCurso;
//			}else {
//				cursosInscriptos += nombreCurso;
//			}
			preparedStatement.setString(1, nombreCurso);
			preparedStatement.setString(2, alumno.getDni());
			preparedStatement.executeUpdate();
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "Error al agregar registrar curso inscripto en la Base de Datos: " + e2.toString());					
		}
	}
	
	public static void updateCursosInscriptos (String dni) {
		Conexion conexionTest = new Conexion();
		Connection cn=null;
		
		try {
			cn = conexionTest.Conectar();
			String query = "UPDATE alumno SET alumnoCursosInscriptos = ? WHERE alumnoDni = ?";
			//UPDATE alumno SET alumnoNombre = ?, alumnoApellido = ?, alumnoTelefono = ? WHERE alumnoDni = ?";
			PreparedStatement preparedStatement = cn.prepareStatement(query);
			preparedStatement.setString(1, "");
			preparedStatement.setString(2, dni);
	        preparedStatement.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar el campo cursos inscriptos del alumno a la base de datos: " + e.toString());
		}
	}
	
	public static String getCursosInscriptosDB(String dni) {
		String cursosInscriptos = "";
		
		Conexion conexionTest = new Conexion();
		Connection cn = null;
		try {
			cn = conexionTest.Conectar();
			
			String query = "SELECT alumnoCursosInscriptos FROM alumno WHERE alumnoDni = ?";
			PreparedStatement preparedStatement = cn.prepareStatement(query);
	        preparedStatement.setString(1, dni); 

	        ResultSet rs = preparedStatement.executeQuery();

	            // Verificar si se encontró un resultado
	            if (rs.next()) {
	                // Obtener el valor del campo específico (en este caso, el nombre)
	                String nombresCursos = rs.getString("alumnoCursosInscriptos");
	                if (nombresCursos != "" && nombresCursos != "Sin cursos, ") {
	                	cursosInscriptos+=nombresCursos+", ";
					}
	                
	            } 
	            rs.close();
	            preparedStatement.close();
		 }catch (Exception e) {
			 JOptionPane.showMessageDialog(null, "Error al obtener alumnosssss: " + e.toString());
			 System.out.println(e.toString());
		}
		
		return cursosInscriptos; 
	}
	
	public static ArrayList<Alumno> getAlumnos(){
		ArrayList<Alumno> listaAlumno = new ArrayList<Alumno>();
		Conexion conexionTest = new Conexion();
		Connection cn = null;
		try {
			cn = conexionTest.Conectar();
			Statement statement = cn.createStatement();
			String query = "SELECT * FROM alumno;";
			ResultSet resultSet = statement.executeQuery(query);
			 
			while (resultSet.next()) {
				String dni = resultSet.getString("alumnoDni");
				String nombre = resultSet.getString("alumnoNombre");
				String apellido = resultSet.getString("alumnoApellido");
				String telefono = resultSet.getString("alumnoTelefono");
				String fechaCreacion = resultSet.getString("alumnoFechaCreacion");
				String cursosInscriptos = resultSet.getString("alumnoCursosInscriptos");
				
				Alumno a = new Alumno(nombre, apellido, dni, telefono, fechaCreacion, cursosInscriptos);
				listaAlumno.add(a);
			}
	            resultSet.close();
	            statement.close();
		 }catch (Exception e) {
			 JOptionPane.showMessageDialog(null, "Error al obtener alumnos:>: " + e.toString());
			 System.out.println(e.toString());
		}
		return listaAlumno;
	}
	
	
	public static void update(Alumno aOld, Alumno aNew) {
		Conexion conexionTest = new Conexion();
		Connection cn = null;
		try {
			cn = conexionTest.Conectar();
	        String query = "UPDATE alumno SET alumnoNombre = ?, alumnoApellido = ?, alumnoTelefono = ? WHERE alumnoDni = ?";
	        PreparedStatement preparedStatement = cn.prepareStatement(query);
	        
	        preparedStatement.setString(1, aNew.getNombre());
	        preparedStatement.setString(2, aNew.getApellido());
	        preparedStatement.setString(3, aNew.getTelefono());
//	        preparedStatement.setString(4, aNew.getFechaCreacionCortaString());
	        
	        preparedStatement.setString(4, aOld.getDni());
//	        System.out.println("\nEstoY ADENTRO DE LA DB");
	        int filasAfectadas = preparedStatement.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	            JOptionPane.showMessageDialog(null, "Proceso finalizado");

	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró el alumno en la base de datos.");
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al modificar el alumno en la base de datos: " + e.toString());
		}
	}

}
