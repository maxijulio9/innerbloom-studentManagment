package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import dominio.Curso;
import exceptions.PrincipalException;
import dominio.Alumno;
import dominio.Conexion;

public class PersistenciaDBCurso {


	public static void insert(Curso curso) {
		Conexion conexionTest = new Conexion();
		Connection cn=null; 
		try {
			cn = conexionTest.Conectar();
			String query = "INSERT INTO curso (cursoCodigo, cursoNombre , cursoNivel, cursoCargaHoraria, cursoVacantes, cursoProfesor) VALUES (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStatement = cn.prepareStatement(query);
			// insertar los valores;n
			preparedStatement.setInt(1, curso.getCodigo());
			preparedStatement.setString(2, curso.getNombre());
			preparedStatement.setString(3, curso.getNivel());
			preparedStatement.setString(4, curso.getTotalHoras());
			preparedStatement.setString(5, curso.getCantidadAlumnos());
			preparedStatement.setString(6, curso.getProfesorAsignado());
			preparedStatement.executeUpdate();
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "Error al agregar el alumno a la base de datos: " + e2.toString());					
		}
	}
	
	public static void delete(String nombre) {
		Conexion conexionTest = new Conexion();
		Connection cn=null;
		
		try {
			cn = conexionTest.Conectar();
			String query = "DELETE FROM curso WHERE cursoNombre = ?";
			PreparedStatement preparedStatement = cn.prepareStatement(query);
	        preparedStatement.setString(1, nombre);
//	        preparedStatement.executeUpdate();
	        int filasAfectadas = preparedStatement.executeUpdate();
//	        
	        if (filasAfectadas > 0) {
//	            JOptionPane.showMessageDialog(null, "Curso eliminado correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No existe curso registrado con el nombre proporcionado.");
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar el curso a la base de datos: " + e.toString());
		}
	}
	
	
	public static void update(Curso cOld, Curso cNew) {
		Conexion conexionTest = new Conexion();
		Connection cn = null;
		try {
			cn = conexionTest.Conectar();
	        String query = "UPDATE curso SET cursoNombre = ?, cursoNivel = ?, cursoCargaHoraria = ?, cursoVacantes = ?, cursoProfesor = ? WHERE cursoCodigo = ?";
	        PreparedStatement preparedStatement = cn.prepareStatement(query);
	        
	        preparedStatement.setString(1, cNew.getNombre());
	        preparedStatement.setString(2, cNew.getNivel());
	        preparedStatement.setString(3, cNew.getTotalHoras());
	        preparedStatement.setString(4, cNew.getCantidadAlumnos());
	        preparedStatement.setString(5, cNew.getProfesorAsignado());

	        preparedStatement.setInt(6, cOld.getCodigo());
	        
	        int filasAfectadas = preparedStatement.executeUpdate();
	        
	        if (filasAfectadas > 0) {
//	            JOptionPane.showMessageDialog(null, "Cambios aplicados");

	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró el alumno en la base de datos.");
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al modificar el alumno en la base de datos: " + e.toString());
		}
	}
	
	public static void updateVacantesCurso(Curso curso, String nuevaVacantes) {
		Conexion conexionTest = new Conexion();
		Connection cn = null;
		try {
			cn = conexionTest.Conectar();
	        String query = "UPDATE curso SET cursoVacantes = ? WHERE cursoCodigo = ?";
	        PreparedStatement preparedStatement = cn.prepareStatement(query);
	        
	        preparedStatement.setString(1, nuevaVacantes);
	        preparedStatement.setInt(2, curso.getCodigo());

	        
	        int filasAfectadas = preparedStatement.executeUpdate();
	        
	        if (filasAfectadas > 0) {
//	            JOptionPane.showMessageDialog(null, "Cambios aplicados");

	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró el alumno en la base de datos.");
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al modificar el alumno en la base de datos: " + e.toString());
		}
	}
	
	
	public static ArrayList<Curso> getCursos(){
		ArrayList<Curso> listaAlumno = new ArrayList<Curso>();
		Conexion conexionTest = new Conexion();
		Connection cn = null;
		try {
			cn = conexionTest.Conectar();
			Statement statement = cn.createStatement();
			String query = "SELECT * FROM curso;";
			ResultSet resultSet = statement.executeQuery(query);
			 
			while (resultSet.next()) {
				int codigo = resultSet.getInt("cursoCodigo");
				String nombre = resultSet.getString("cursoNombre");
				String nivel = resultSet.getString("cursoNivel");
				String cargaHoraria = resultSet.getString("cursoCargaHoraria");
				String vacantes = resultSet.getString("cursoVacantes");
				String profe = resultSet.getString("cursoProfesor");

				Curso c = new Curso(codigo, nombre, nivel, cargaHoraria, vacantes, profe);
				listaAlumno.add(c);
			}
	            resultSet.close();
	            statement.close();
		 }catch (Exception e) {
			 JOptionPane.showMessageDialog(null, "Error al obtener cursos: " + e.toString());
			 System.out.println(e.toString());
		}
		return listaAlumno;
	}
	
//	public static ArrayList<Curso> getListaFiltrada(Predicate<Curso> filtro) throws PrincipalException {
//		// TODO Auto-generated method stub
//		Conexion conexionTest = new Conexion();
//		Connection cn = null;
//		Statement stm = null;
//		ResultSet rs = null;
//		ArrayList<Curso> listaFiltrada = new ArrayList<Curso>();
//		try {
//			cn = conexionTest.Conectar();
//			stm = cn.createStatement();
//			rs = stm.executeQuery("SELECT * FROM curso");
//			ArrayList<Curso> listaCurso = new ArrayList<Curso>();
//
//			// recorre rs
//			while (rs.next()) {
//
//				Curso cursoAux = new Curso(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(4),rs.getString(6));
//				listaCurso.add(cursoAux);
//			}
//			
//			listaFiltrada= listaCurso.stream()
//					.filter(filtro) 
//					.collect(Collectors.toCollection(ArrayList::new));
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("ERROR" + e.getMessage());
////			e.printStackTrace();
//		}
//		return listaFiltrada;
//		
//	}
	
}
