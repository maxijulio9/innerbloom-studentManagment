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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


public class FormPrincipal extends JFrame implements ActionListener{
	
	private JPanel panelAlumno;
	private JPanel panelCurso;
	private JButton btnCrearAlumno;
	private JButton btnEliminarAlumno;
	private JButton btnModificarDatosAlumno;
	private JButton btnBuscarAlumnos;
	private JButton btnInscribirAlumnoCurso; 
	private JButton btnCrearCurso;
	private JButton btnEliminarCurso;
	private JButton btnModificarCurso;
	private JButton btnBuscarCurso;
	
	public FormPrincipal() {
//		super("Bienvenida/o al Insituto Innerbloom");
		setSize(1000,600);
		this.setLocation(300,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		agregarComponentes();
	}
	
	public void agregarComponentes() {
		JPanel tituloInstituto = new JPanel();
		tituloInstituto.setLayout(new BorderLayout());
		tituloInstituto.setPreferredSize(new Dimension(500,70));
		JLabel textoTitulo = new JLabel("INSTITUTO  INNERBLOOM");
		textoTitulo.setFont(new Font("Times New Roman", Font.BOLD,30));
		tituloInstituto.setBackground(new Color(1,59,110));
		textoTitulo.setForeground(Color.white);	
		textoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		tituloInstituto.add(textoTitulo, BorderLayout.CENTER);
//		tituloInstituto.setFont(new Font("Roboto", Font.BOLD,30));
		
		JPanel panelPrincipal = iniciarPanel();
		panelPrincipal.setLayout(new GridLayout(1,2, 20,20));
		panelPrincipal.setBackground(new Color(169,202,228));
		
		panelAlumno =  iniciarPanel();
		panelAlumno.setLayout(new GridLayout(6,1,10,20));
		panelAlumno.setBackground(new Color(169,202,228));
		JLabel tituloAlumnos = new JLabel("Alumnado");
		tituloAlumnos.setFont(new Font("Times New Roman", Font.PLAIN,30));
		tituloAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		tituloAlumnos.setForeground(new Color(8,60,95));
		panelAlumno.add(tituloAlumnos);
		btnCrearAlumno =  iniciarBoton("Registrar alumno", panelAlumno);
		btnEliminarAlumno =  iniciarBoton("Eliminar alumno", panelAlumno);
		btnModificarDatosAlumno = iniciarBoton("Modificar datos alumno",panelAlumno);
		btnBuscarAlumnos =  iniciarBoton("Ver alumnos", panelAlumno);
		btnInscribirAlumnoCurso =  iniciarBoton("Inscribir alumno a curso", panelAlumno);

		panelCurso = iniciarPanel();
		panelCurso.setLayout(new GridLayout(6,1,10,20));
		panelCurso.setBackground(new Color(169,202,228));
		JLabel tituloCursos = new JLabel("Cursos");
		tituloCursos.setFont(new Font("Times New Roman", Font.PLAIN,30));
		tituloCursos.setHorizontalAlignment(SwingConstants.CENTER);
		tituloCursos.setForeground(new Color(8,60,95));
		panelCurso.add(tituloCursos);
		btnCrearCurso = iniciarBoton("Registrar nuevo curso", panelCurso);
		btnEliminarCurso = iniciarBoton("Eliminar curso", panelCurso);
		btnModificarCurso = iniciarBoton("Modificar datos curso", panelCurso);
		btnBuscarCurso = iniciarBoton("Ver cursos disponibles", panelCurso);


		panelPrincipal.add(panelAlumno);
		panelPrincipal.add(panelCurso);
		
		
		JPanel footer = iniciarPanel();
		footer.setPreferredSize(new Dimension(500,20));
		footer.setBackground(new Color(52, 73, 94));
		JPanel footer2 = iniciarPanel();
		footer2.setPreferredSize(new Dimension(20,500));
		footer2.setBackground(new Color(169,202,228));
		JPanel footer3 = iniciarPanel();
		footer3.setPreferredSize(new Dimension(20,500));
		footer3.setBackground(new Color(169,202,228));
		
		Container cp =  getContentPane();
		cp.add(tituloInstituto, BorderLayout.NORTH);
		cp.add(panelPrincipal);
		cp.add(footer2, BorderLayout.EAST);
		cp.add(footer3, BorderLayout.WEST);
		cp.add(footer, BorderLayout.SOUTH);
		
	}

	private JPanel iniciarPanel() {
		JPanel panel = new JPanel();
		return panel;
	}
	private JButton iniciarBoton(String dato, JPanel botones) {
		JButton boton = new JButton(dato);
		boton.setBackground(new Color(21, 67, 96));
		boton.setPreferredSize(new Dimension(250,150));
		boton.setFont(new Font("Roboto", Font.BOLD,20));
		boton.setForeground(Color.white);
		boton.addActionListener(this);
		botones.add(boton);
		return boton;
	}
	
	public static void main(String[] args) {
		FormPrincipal ventana = new FormPrincipal();
		ventana.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== btnCrearAlumno) {
			FormCargarAlumno ventana = new FormCargarAlumno();
			ventana.setVisible(true);
		}
		if (e.getSource() == btnEliminarAlumno) {
			FormEliminarAlumno ventana =  new FormEliminarAlumno();
			ventana.setVisible(true);
		}
		
		if (e.getSource() == btnBuscarAlumnos) {
			FormVerAlumnos ventana = new FormVerAlumnos();
			ventana.setVisible(true);
			
		}
		if (e.getSource() == btnModificarDatosAlumno) {
			FormModificarAlumno ventana =  new FormModificarAlumno();
			ventana.setVisible(true);
		}
		if (e.getSource() == btnInscribirAlumnoCurso) {
			FormInscribirAlumnoACurso ventana =  new FormInscribirAlumnoACurso();
			ventana.setVisible(true);
		}
		if (e.getSource() == btnCrearCurso) {
			FormCrearCurso ventana =  new FormCrearCurso();
			ventana.setVisible(true);
		}
		if (e.getSource() == btnEliminarCurso) {
			FormEliminarCurso ventana =  new FormEliminarCurso();
			ventana.setVisible(true);
		}
		if (e.getSource() == btnBuscarCurso) {
			FormVerCursos ventana =  new FormVerCursos();
			ventana.setVisible(true);
		}
		if (e.getSource() == btnModificarCurso) {
			FormModificarCurso ventana =  new FormModificarCurso();
			ventana.setVisible(true);
		}
		
		
	}
	
}
