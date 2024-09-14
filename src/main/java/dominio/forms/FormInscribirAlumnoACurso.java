package forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dominio.Alumno;
import dominio.Curso;
import dominio.Problema.GestorInstitutoOld;
import dominio.Matricula;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.MatriculaExistenteException;
import exceptions.PrincipalException;
import persistencia.PersistenciaDB;
import persistencia.PersistenciaDBCurso;


public class FormInscribirAlumnoACurso extends JFrame implements ActionListener{

	private JComboBox<String> listadoDNIAlumno = new JComboBox<String>();
	private JComboBox<String> listadoCurso = new JComboBox<String>();
	private JPanel panelDatos;
//	private JTextField txtDni;
	private JPanel botones;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnVolver;
	
	
	public FormInscribirAlumnoACurso() {
		super("Eliminar alumno");
		setSize(650,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.setLocation(450,350);
		agregarComponentes();
	}
	
	private void agregarComponentes() {
		JPanel panelPrincipal  = iniciarPanel();
		
		JPanel titulo =  new JPanel();
		titulo.setLayout(new FlowLayout());
		titulo.setBackground(new Color(250, 215, 160));
		JLabel tituloTexto = new JLabel("Inscribir alumno a curso");
		tituloTexto.setFont(new Font("Times New Roman", Font.PLAIN,25));
		titulo.add(tituloTexto);
		
		
		panelDatos = iniciarPanel();
		panelDatos.setLayout(new GridLayout(2,2, 80,30));
		JLabel aux = new JLabel("Seleccioná el DNI alumno");
		aux.setFont(new Font("Reboto", Font.PLAIN,15));
		panelDatos.add(aux);
		cargarListaAlumno();
		JLabel aux1 = new JLabel("Seleccioná un Curso ");
		aux1.setFont(new Font("Reboto", Font.PLAIN,15));
		panelDatos.add(aux1);
		cargarListaCurso();

		botones = iniciarPanel();
		botones.setLayout(new GridLayout(1,2,4,4));
		
		JPanel panelOperaciones=new JPanel();
		panelOperaciones.setLayout(new FlowLayout());
		btnAceptar =  iniciarBoton("Aceptar", panelOperaciones);
		botones.add(panelOperaciones);
		
		JPanel panelVolver=new JPanel();
		panelVolver.setLayout(new FlowLayout());
		btnVolver =  iniciarBoton("Atrás", panelVolver);
		botones.add(panelVolver);
		
		panelPrincipal.add(panelDatos, BorderLayout.CENTER);
		panelPrincipal.add(botones, BorderLayout.SOUTH);

		Container cp = getContentPane();
		cp.add(titulo, BorderLayout.NORTH);
		cp.add(panelPrincipal);
	}

	private JPanel iniciarPanel() {
		JPanel salida = new JPanel();
		return salida;
	}
	
	private JButton iniciarBoton(String dato, JPanel panel) {
		JButton boton = new JButton(dato);
		boton.setBackground(new Color(8,60,95));
		boton.setPreferredSize(new Dimension(100,30));
		boton.setFont(new Font("Roboto", Font.BOLD,15));
		boton.setForeground(Color.white);
		boton.addActionListener(this);
		panel.add(boton);
		return boton;
	}
	
	private void cargarListaAlumno() {
		ArrayList<Alumno> gp = PersistenciaDB.getAlumnos();
		for (Alumno a : gp){
			listadoDNIAlumno.addItem(a.getDni()+" - "+a.getNombre()+" "+a.getApellido());
		}
		panelDatos.add(listadoDNIAlumno);
	}
	private void cargarListaCurso() {
		ArrayList<Curso> gp = PersistenciaDBCurso.getCursos();
		for (Curso c : gp){
			listadoCurso.addItem(c.getNombre());
		}
		panelDatos.add(listadoCurso);
	}
	
	private void actualizarVacantesCurso(Curso curso) throws PrincipalException {
		Curso cursoNew = new Curso();
        cursoNew.setNombre(curso.getNombre());// Conserva los valores originales por defecto
        cursoNew.setNivel(curso.getNivel());  
        cursoNew.setTotalHoras(curso.getTotalHoras());
        //resta la vacante por el nuevo alumno inscripto.
        int vacantesRestantes = Integer.parseInt(curso.getCantidadAlumnos()) - 1 ;  
        cursoNew.setCantidadAlumnos(""+vacantesRestantes);
        cursoNew.setProfesorAsignado(curso.getProfesorAsignado());
        
        PersistenciaDBCurso.update(curso, cursoNew);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();

		if (source == btnAceptar) {
			//GestorInstitutoOld gp = GestorInstitutoOld.getInstancia();
			GestorInstituto gp = GestorInstituto.getInstancia();

			//ArrayList<Curso> cursos = gp.getListaCursos();//PersistenciaDBCurso.getCursos();
			ArrayList<Curso> cursos = gp.getCursosDefaultList();//PersistenciaDBCurso.getCursos();

			//ArrayList<Alumno> alumnos = gp.getListaAlumnos();
			ArrayList<Alumno> alumnos = gp.getAlumnoDefaultList();
			
			if (listadoDNIAlumno.getSelectedItem()==null) {
				JOptionPane.showMessageDialog(this, "Por favor seleccioná el DNI del alumno.","No se pudo completar la operación"
						, JOptionPane.ERROR_MESSAGE);
			}else if(listadoCurso.getSelectedItem()==null){
				JOptionPane.showMessageDialog(this, "Por favor seleccioná el curso.","No se pudo completar la operación"
						, JOptionPane.ERROR_MESSAGE);
			}else {
				try {
					
					String nombreAux = listadoCurso.getSelectedItem().toString();
			
					for (Curso curso : cursos) {
						if (curso.getNombre().equals(nombreAux)) {
							if (Integer.parseInt(curso.getCantidadAlumnos()) >= 1) {
								Matricula matriculaAux = new Matricula(curso);
								for (Alumno alumno : alumnos) {
									String[] partes = listadoDNIAlumno.getSelectedItem().toString().split("-"); // Suponiendo que el guión separa el nombre y el DNI
									String dniSeleccionado = partes[0].trim();
									if (dniSeleccionado.equals(alumno.getDni())) {//listadoDNIAlumno.getSelectedItem().toString().contains(alumno.getDni())) {
										try {
											boolean seAgrego = alumno.agregarMatricula(alumno, matriculaAux);
											if (seAgrego) {
												System.out.println("\nAgregue la inscripcion a la BD");
//											PersistenciaDB.insertCurso(alumno, curso.getNombre());
//											alumno.agregarMatricula(matriculaAux);
												// restar vacante a curso
												actualizarVacantesCurso(curso);
												JOptionPane.showMessageDialog(this, "¡Inscripción exitosa!",
														"Operación confirmada", JOptionPane.INFORMATION_MESSAGE);
												dispose();
											} 
//											else {
//												JOptionPane.showMessageDialog(this, "eror",
//														"No se pudo completar la operación", JOptionPane.ERROR_MESSAGE);
//											}

										} catch (MatriculaExistenteException e2) {
											// TODO: handle exception
											JOptionPane.showMessageDialog(this, e2.getMessage(),
													"No se pudo completar la operación", JOptionPane.ERROR_MESSAGE);
										}
									}
								}
							}else {
								JOptionPane.showMessageDialog(this, "No quedan vacantes","No se pudo completar la operación", JOptionPane.ERROR_MESSAGE);

							}
							
						}
						}
				} catch (PrincipalException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, e2.getMessage(),"No se pudo completar la operación", JOptionPane.ERROR_MESSAGE);

				}
			}
			
			
		}

		if (source == btnVolver) {
			dispose();
		}
	}
}
