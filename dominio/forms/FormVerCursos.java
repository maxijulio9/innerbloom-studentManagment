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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dominio.Curso;
import dominio.Problema.GestorInstitutoOld;

public class FormVerCursos extends JFrame implements ActionListener {

	private JPanel panelListado;
	private JPanel botones;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private JTable tabla = new JTable(tableModel);
//	private JTextArea listado = new JTextArea();
	private JComboBox listadoProfe = new JComboBox();
	private JComboBox listadoNivel = new JComboBox();
	private JButton btnFiltroProfe;
	private JButton btnFiltroNivel;
	private JButton btnVerTodo;
	private JButton btnVolver;
	
	public FormVerCursos() {
		super("Listado cursos");
		this.setSize(900,600);
		this.setLocation(350,150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		agregarComponentes();
	}
	
	public void agregarComponentes() {
		JPanel titulo =  new JPanel();
		titulo.setLayout(new FlowLayout());
		titulo.setBackground(new Color(29,76,124));
		JLabel textoTitulo = new JLabel("Cursos Instituto Ineerbloom");
		textoTitulo.setFont(new Font("Times New Roman", Font.BOLD,25));
		titulo.setBackground(new Color(29,76,124));
		textoTitulo.setForeground(Color.white);
		titulo.add(textoTitulo);
		
		panelListado = iniciarPanel();
		panelListado.setBackground(new Color(214, 234, 248));
//	
		
		JScrollPane scrollPane = new JScrollPane(tabla);
		tabla.setBackground(new Color(254, 245, 231));
		tabla.setPreferredScrollableViewportSize(new Dimension(850, 300));
		panelListado.add(scrollPane);
//		ArrayList<Curso> cursos = GestorInstituto.getInstancia().getListaCurso();
		cargarDatosTabla();
		
		//agregar el listado por defecto
//		cargarTodosLosCursos();
		botones = iniciarPanel();
		botones.setLayout(new GridLayout(1,2,5,5));
		botones.setBackground(new Color(214, 234, 248));

		JPanel panelOperaciones=iniciarPanel();
		panelOperaciones.setLayout(new FlowLayout());
		panelOperaciones.setBackground(new Color(214, 234, 248));
		
		JPanel panelOperacionesSub = iniciarPanel();
		panelOperacionesSub.setLayout(new GridLayout(3,2,20,15));
		panelOperacionesSub.setBackground(new Color(214, 234, 248));

		JLabel textoProfe = new JLabel("Por Profesor");
		textoProfe.setFont(new Font("Roboto", Font.PLAIN,15));
		textoProfe.setHorizontalAlignment(SwingConstants.CENTER);
		panelOperacionesSub.add(textoProfe);
		
		JLabel textoNivel = new JLabel("Por Nivel");
		textoNivel.setFont(new Font("Roboto", Font.PLAIN,15));
		textoNivel.setHorizontalAlignment(SwingConstants.CENTER);
		panelOperacionesSub.add(textoNivel);
		
		cargarListadoProfes(panelOperacionesSub);
		cargarNiveles(panelOperacionesSub);
		btnFiltroProfe=  iniciarBoton("Filtrar", panelOperacionesSub);
		btnFiltroNivel=  iniciarBoton("Filtrar", panelOperacionesSub);
		panelOperaciones.add(panelOperacionesSub);
		
		JPanel panelVolver=iniciarPanel();
		panelVolver.setLayout(new FlowLayout());
		panelVolver.setBackground(new Color(214, 234, 248));
		
		JPanel panelVolverSub = iniciarPanel();
		panelVolverSub.setLayout(new GridLayout(2,1,50,20));
		panelVolverSub.setBackground(new Color(214, 234, 248));
		btnVolver =  iniciarBoton("Atrás", panelVolverSub);
		btnVerTodo =  iniciarBoton("Ver todo", panelVolverSub);
		panelVolver.add(panelVolverSub);
		
		botones.add(panelOperaciones);
		botones.add(panelVolver);
		
		Container cp = getContentPane();
		cp.add(titulo, BorderLayout.NORTH);
		cp.add(panelListado, BorderLayout.CENTER);
		cp.add(botones, BorderLayout.SOUTH);
	}
	private JPanel iniciarPanel() {
		JPanel panel =  new JPanel();
		return panel;
	}
	
	private JButton iniciarBoton(String dato, JPanel panel) {
		JButton boton = new JButton(dato);
		boton.setBackground(new Color(8,60,95));
		boton.setPreferredSize(new Dimension(150,30));
		boton.setFont(new Font("Roboto", Font.BOLD,12));
		boton.setForeground(Color.white);
		boton.addActionListener(this);
		panel.add(boton);
		return boton;
	}
	
	private void cargarDatosTabla() {
		
		
		ArrayList<Curso> cursos = GestorInstitutoOld.getInstancia().getListaCursos();

		tableModel.setColumnCount(0);
		
		tableModel.addColumn("Codigo");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Nivel");
        tableModel.addColumn("Carga Horaria");
        tableModel.addColumn("Vacantes");
        tableModel.addColumn("Profesor");
        
        tableModel.setRowCount(0); // Limpia la tabla antes de cargar nuevos datos
        
        for (Curso curso : cursos) {
            Object[] fila = {
               curso.getCodigo(),
               curso.getNombre(),
               curso.getNivel(),
               curso.getTotalHoras(),
               curso.getCantidadAlumnos(),
               curso.getProfesorAsignado()
            };
            tableModel.addRow(fila); // Agrega una nueva fila a la tabla
        }
	}
	
private void cargarDatosTabla(ArrayList<Curso> cursos) {
		

		tableModel.setColumnCount(0);
		
		tableModel.addColumn("Codigo");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Nivel");
        tableModel.addColumn("Carga Horaria");
        tableModel.addColumn("Vacantes");
        tableModel.addColumn("Profesor");
        
        tableModel.setRowCount(0); // Limpia la tabla antes de cargar nuevos datos
        
        for (Curso curso : cursos) {
            Object[] fila = {
               curso.getCodigo(),
               curso.getNombre(),
               curso.getNivel(),
               curso.getTotalHoras(),
               curso.getCantidadAlumnos(),
               curso.getProfesorAsignado()
            };
            tableModel.addRow(fila); // Agrega una nueva fila a la tabla
        }
	}
	
	
	private void cargarListadoProfes(JPanel panelInput) {
		String[] gp = GestorInstitutoOld.getInstancia().obtenerListadoProfes();
		for (String profe : gp) {
			listadoProfe.addItem(profe);
		}
		panelInput.add(listadoProfe);
	}
	private void cargarNiveles(JPanel panelInput) {
		String nivel[] = {"","A1","A2","B1","B2","C1","C2"};
		listadoNivel =  new JComboBox<String>(nivel);
		panelInput.add(listadoNivel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		GestorInstitutoOld gp = GestorInstitutoOld.getInstancia();
		
		//filro profe
		if (source == btnFiltroProfe) {
			ArrayList<Curso> cursos = gp.getListadoFiltrado(c->c.getProfesorAsignado().equals(listadoProfe.getSelectedItem().toString()));

			cargarDatosTabla(cursos);
			 
		}
		//filtro nivel
		if (source == btnFiltroNivel) {
			ArrayList<Curso> cursos = gp.getListadoFiltrado(c->c.getNivel().equals(listadoNivel.getSelectedItem().toString()));
			cargarDatosTabla(cursos);
		}
		if (source == btnVerTodo) {
			cargarDatosTabla();
		}
		if (source == btnVolver) {
			dispose();
		}
		
	}
	
}
