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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dominio.Alumno;
import dominio.Curso;
import dominio.Problema.GestorInstitutoOld;
import exceptions.PrincipalException;
import persistencia.PersistenciaDB;
import persistencia.PersistenciaDBCurso;

public class FormEliminarAlumnoDeCurso extends JFrame implements ActionListener{

	private JComboBox listadoCursosInscriptos = new JComboBox();
	private JPanel panelPrincipal;
	private JPanel botones;
	private JButton btnAceptar;
	private JButton btnVolver;
	private String alumnoDNI;
	public FormEliminarAlumnoDeCurso(String alumnoDni) {
		super("Cursos");
		this.setSize(700, 400);
		this.setLocation(450, 250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		alumnoDNI = alumnoDni;
		agregarComponentes(alumnoDni);
	}
	private void agregarComponentes(String alumnoDni) {
		// TODO Auto-generated method stub
		
		JPanel titulo = iniciarPanel();
		JLabel textoTitulo = new JLabel("Dar de baja curso");
		titulo.setBackground(new Color(250, 215, 160));
		textoTitulo.setFont(new Font("Times New Roman", Font.BOLD,25));
		titulo.add(textoTitulo);
		
		JLabel textoCurso = new JLabel("Seleccioná el curso a eliminar:");
		textoCurso.setFont(new Font("Roboto", Font.PLAIN,15));
	
		JPanel panelGeneral = iniciarPanel();
		
		panelPrincipal = iniciarPanel();
		panelPrincipal.setLayout(new GridLayout(4,1,10,20));
		String[] partes = alumnoDni.split("-"); // Suponiendo que el guión separa el nombre y el DNI
		String nombreAlumnoSeleccionado = partes[1].trim();
		JLabel textoNombre= new JLabel(nombreAlumnoSeleccionado);
		textoNombre.setFont(new Font("Times New Roman", Font.PLAIN,30));

		obtenerCursosAlumno(alumnoDni);
		
		botones = iniciarPanel();
		
		JPanel panelOperaciones=new JPanel();
		panelOperaciones.setLayout(new FlowLayout());
	
		btnAceptar =  iniciarBoton("Aceptar", panelOperaciones);
	
		JPanel panelVolver=new JPanel();
		panelVolver.setLayout(new FlowLayout());

		btnVolver =  iniciarBoton("Cancelar", panelVolver);
		
		botones.add(panelOperaciones);
		botones.add(panelVolver);
		
		panelPrincipal.add(textoNombre);
		panelPrincipal.add(textoCurso);
		panelPrincipal.add(listadoCursosInscriptos);
		panelPrincipal.add(botones);
		
		panelGeneral.add(panelPrincipal);
		Container cp = getContentPane();		
		cp.add(titulo, BorderLayout.NORTH);
		cp.add(panelGeneral);
	}
	
	private JButton iniciarBoton(String dato, JPanel panel) {
		JButton boton = new JButton(dato);
		if (dato.contains("Dar")) {
			boton.setBackground(new Color(198,125,0));
		}else{
		boton.setBackground(new Color(8,60,95));}
		boton.setPreferredSize(new Dimension(100,30));
		boton.setFont(new Font("Roboto", Font.BOLD,15));
		boton.setForeground(Color.white);
		boton.addActionListener(this);
		panel.add(boton);
	
		return boton;
	}
	private void obtenerCursosAlumno(String alumnoDni) {
		if (listadoCursosInscriptos.getItemCount()>0 ) {
			listadoCursosInscriptos.removeAllItems();

		}
		
		ArrayList<Alumno> alumnos = PersistenciaDB.getAlumnos();
		String[] partes = alumnoDni.split("-"); // Suponiendo que el guión separa el nombre y el DNI
		String dniSeleccionado = partes[0].trim(); // Tomamos la última parte como el DNI

		for (Alumno alumno : alumnos) {
			if (alumno.getDni().equals(dniSeleccionado)) {
				String[] cursosInscriptos = PersistenciaDB.getCursosInscriptosDB(dniSeleccionado).split(", ");
				for (String curso : cursosInscriptos) {
					if (!curso.equals("Sin cursos") && !curso.trim().isEmpty()) {
						listadoCursosInscriptos.addItem(curso);
					}
				}
			}
		}
	}
	
	private JPanel iniciarPanel() {
		JPanel panel =  new JPanel();
		return panel;
	}
	
	private void modificarListaCursosAlumno(String cursoSeleccionado) {
		// TODO Auto-generated method stub
		ArrayList<Alumno> alumnos = GestorInstitutoOld.getInstancia().getListaAlumnos();
		String[] partes = alumnoDNI.split("-"); // Suponiendo que el guión separa el nombre y el DNI
		String dniSeleccionado = partes[0].trim();
		
		for (Alumno alumno : alumnos) {
		
			if (alumno.getDni().equals(dniSeleccionado)) {
			
				String cursosActuales = PersistenciaDB.getCursosInscriptosDB(dniSeleccionado);
		        List<String> cursosActualesLista = Arrays.asList(cursosActuales.split(",\\s*"));

		        System.out.println("Cursos DISPONIBLES>"+cursosActualesLista+"< TOTAL_:"+cursosActualesLista.size());
				String cursosActualizados =  cursosActualesLista.stream()
		                .filter(curso -> !curso.equalsIgnoreCase(cursoSeleccionado) && !curso.equalsIgnoreCase(""))
		                .collect(Collectors.joining(", "));
						//cursosActuales.replace((cursoSeleccionado+", "), "");
				
				
				System.out.println("ACTUALES>>>"+ cursosActuales);
				System.out.println("actualizados>>>"+ cursosActualizados);
				
				PersistenciaDB.updateCursosInscriptos(dniSeleccionado);
				PersistenciaDB.updateCursosActualizadosAlumnos(alumno, cursosActualizados);
				obtenerCursosAlumno(alumnoDNI);
				
			}
		}
	}
	
	private void modificarVacantesCurso(String cursoSeleccionado) throws PrincipalException {
		// TODO Auto-generated method stub
		ArrayList<Curso> cursos = GestorInstitutoOld.getInstancia().getListaCursos();
		for (Curso curso : cursos) {
			if (curso.getNombre().equalsIgnoreCase(cursoSeleccionado)) {
				Curso cursoNew = new Curso();
		        cursoNew.setNombre(curso.getNombre());// Conserva los valores originales por defecto
		        cursoNew.setNivel(curso.getNivel());  
		        cursoNew.setTotalHoras(curso.getTotalHoras());
		        //resta la vacante por el nuevo alumno inscripto.
		        int vacantesRestantes = Integer.parseInt(curso.getCantidadAlumnos()) + 1 ;
		        System.out.println("VACANTES:>"+vacantesRestantes+"  NOMBRE " +cursoSeleccionado);
		        cursoNew.setCantidadAlumnos(""+vacantesRestantes);
		        cursoNew.setProfesorAsignado(curso.getProfesorAsignado());
		        
		        PersistenciaDBCurso.update(curso, cursoNew);
			}
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton) e.getSource();
		if (source == btnAceptar) {
			if (listadoCursosInscriptos.getItemCount()>0 ) {
				String cursoSeleccionado = listadoCursosInscriptos.getSelectedItem().toString();
				if (cursoSeleccionado != "Sin cursos") {
					System.out.println("Error>"+cursoSeleccionado);
					modificarListaCursosAlumno(cursoSeleccionado);
					try {

							modificarVacantesCurso(cursoSeleccionado);
					} catch (PrincipalException e1) {

					}
					JOptionPane.showMessageDialog(this, "Se eliminó el alumno del curso.",
							"Proceso finalizado", JOptionPane.INFORMATION_MESSAGE);
				}else {
						JOptionPane.showMessageDialog(this, "No existe registro de cursos inscriptos.",
								"No hay registro", JOptionPane.ERROR_MESSAGE);
				}
						
				
			}else {
				JOptionPane.showMessageDialog(this, "No existe registro de cursos inscriptos.",
						"No hay registro", JOptionPane.ERROR_MESSAGE);
			}
			dispose();
			
		}
		if (source == btnVolver) {
			dispose();
		}
	}
	
	
}
