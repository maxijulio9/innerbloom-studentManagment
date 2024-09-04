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
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dominio.Alumno;
import dominio.Problema.GestorInstitutoOld;
import exceptions.PrincipalException;

public class FormVerAlumnos extends JFrame implements ActionListener{

	private DefaultTableModel tableModel = new DefaultTableModel();
	private JTable tabla = new JTable(tableModel);
	private JPanel panelListado;
	private JPanel botones;
	private JButton btnOrdDNI;
	private JButton btnOrdApellido;
	private JTextField busquedaAlumno;
	private JRadioButton filtroSinCursos;
	private JButton btnVolver;
	private JButton btnVerTodo;

	public FormVerAlumnos() {
		super("Listado de alumnos");
		this.setSize(900, 600);
		this.setLocation(350, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		agregarComponentes();
	}
	
	public void agregarComponentes() {
		JPanel titulo =  new JPanel();
		titulo.setLayout(new FlowLayout());
		titulo.setBackground(new Color(29,76,124));
		JLabel textoTitulo = new JLabel("Alumnos Instituto Ineerbloom");
		textoTitulo.setFont(new Font("Times New Roman", Font.BOLD,25));
		textoTitulo.setForeground(Color.white);
		titulo.add(textoTitulo);
		 
		panelListado = iniciarPanel();
		panelListado.setBackground(new Color(214, 234, 248));
		
		GestorInstitutoOld gp = GestorInstitutoOld.getInstancia();
		ArrayList<Alumno> alumnos = gp.getListaAlumnos();//PersistenciaDB.getAlumnos();
		JScrollPane scrollPane = new JScrollPane(tabla);
		tabla.setBackground(new Color(254, 245, 231));
		tabla.setPreferredScrollableViewportSize(new Dimension(860, 300));
	    panelListado.add(scrollPane);
		cargarDatosTablaInicial(alumnos);
		
		botones = iniciarPanel();
		botones.setBackground(new Color(214, 234, 248));
		botones.setLayout(new GridLayout(1,2,10,5));
		
		
		JPanel panelOperaciones=iniciarPanel();
		panelOperaciones.setLayout(new GridLayout(4,2,30,10));
		panelOperaciones.setBackground(new Color(214, 234, 248));
		
		agregarTitulosBotones(panelOperaciones);
		agregarTitulosBotones(panelOperaciones);
		
		btnOrdDNI =  iniciarBoton("DNI", panelOperaciones);
		btnOrdApellido =  iniciarBoton("Apellido", panelOperaciones);
		cargarRadioButton(panelOperaciones);
		cargarFiltroBusqueda(panelOperaciones);
		
		JPanel panelVolver=new JPanel();
		panelVolver.setBackground(new Color(214, 234, 248));
		panelVolver.setLayout(new FlowLayout());
		btnVerTodo =  iniciarBoton("Ver todos", panelVolver);
		btnVolver =  iniciarBoton("Volver", panelVolver);
		
		
		botones.add(panelOperaciones);
		botones.add(panelVolver);
		
	
		Container cp = getContentPane();
		
		cp.add(titulo, BorderLayout.NORTH);
		cp.add(panelListado, BorderLayout.CENTER);
		cp.add(botones, BorderLayout.SOUTH);

	
	}
	private void agregarTitulosBotones(JPanel panelOperaciones) {
		// TODO Auto-generated method stub
		JPanel botonTitulo = iniciarPanel();
		JLabel texto = new JLabel("Ordenar por");
		texto.setFont(new Font("Roboto", Font.PLAIN,15));
		botonTitulo.setBackground(new Color(214, 234, 248));
		botonTitulo.add(texto);
		panelOperaciones.add(botonTitulo);
	}

	private void cargarFiltroBusqueda(JPanel panelOperaciones) {
		busquedaAlumno = new JTextField(20);
		busquedaAlumno.setToolTipText("Buscá alumno por apellido");
		panelOperaciones.add(busquedaAlumno);
		busquedaAlumno.addActionListener(this);
		
	}

	private void cargarRadioButton(JPanel panelOperaciones) {
		filtroSinCursos = new JRadioButton("Ver alumnos sin cursos");
		filtroSinCursos.setBackground(new Color(214, 234, 248));
		filtroSinCursos.setFont(new Font("Roboto", Font.PLAIN,15));
		panelOperaciones.add(filtroSinCursos);
		filtroSinCursos.addActionListener(this);
	}
	
	private JPanel iniciarPanel() {
		JPanel panel =  new JPanel();
		return panel;
	}
	
	private JButton iniciarBoton(String dato, JPanel panel) {
		JButton boton = new JButton(dato);
		if (dato.equals("DNI") || dato.equals("Apellido") ) {
			boton.setBackground(new Color(204,102,0));
		}else {	boton.setBackground(new Color(8,60,95));}
		boton.setPreferredSize(new Dimension(150,30));
		boton.setFont(new Font("Roboto", Font.BOLD,15));
		boton.setForeground(Color.white);
		boton.addActionListener(this);
		panel.add(boton);
		return boton;
	}
	
	private void cargarDatosTablaInicial(ArrayList<Alumno> alumnos) {
		
		
		tableModel.setColumnCount(0);
		
		tableModel.addColumn("Fecha Creación");
        tableModel.addColumn("DNI");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido");
        tableModel.addColumn("Telefono");
        tableModel.addColumn("Cursos inscriptos");

        tableModel.setRowCount(0); // Limpia la tabla antes de cargar nuevos datos
       
        
        for (Alumno alumno : alumnos) {
            Object[] fila = {
                alumno.getFechaCreacionCortaString(),
                alumno.getDni(),
                alumno.getNombre(),
                alumno.getApellido(),
                alumno.getTelefono(),
                alumno.getCursosNombre(),
           
            };

            tableModel.addRow(fila); // Agrega una nueva fila a la tabla
        }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		JButton source = (JButton)e.getSource();
		Object source =  e.getSource();
		GestorInstitutoOld gp = GestorInstitutoOld.getInstancia();
		ArrayList<Alumno> alumnos = null;
		if (source == btnOrdApellido) {
			
			try {
				alumnos = gp.getListadoAlumnosOrdenado(new Comparator<Alumno>() {
							@Override
							public int compare(Alumno a1, Alumno a2) {
								return (a1.compareTo(a2));
							}
						});
			} catch (PrincipalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cargarDatosTablaInicial(alumnos);
		}
		
		if (source == btnOrdDNI) {
			
			try {
				alumnos=  gp.getListadoAlumnosOrdenado(new Comparator<Alumno>() {
								@Override
								public int compare(Alumno a1, Alumno a2) {
									return (Integer.parseInt(a1.getDni()) - Integer.parseInt(a2.getDni()));
								}
							});
			} catch (PrincipalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cargarDatosTablaInicial(alumnos);
		}
		if (source  == btnVerTodo) {
			alumnos = gp.getListaAlumnos();
			cargarDatosTablaInicial(alumnos);
		}
		if (source  == btnVolver) {
			dispose();
		}
		
		if (source == filtroSinCursos) {
			System.out.println("Muestrame los alumnos");
			 if (filtroSinCursos.isSelected()) {
	                // Si el filtro está seleccionado, muestra solo los alumnos sin cursos
	                alumnos = gp.getListadoFiltradoAlumno(a->a.getCursosNombre().contains("Sin cursos"));
	            } else {
	                // Si el filtro no está seleccionado, muestra la lista original de alumnos
	            	alumnos = gp.getListaAlumnos();
	          }
			

			cargarDatosTablaInicial(alumnos);
		}
		if (source == busquedaAlumno) {
			System.out.println("I'm writting");
            alumnos = gp.getListadoFiltradoAlumno(a->a.getApellido().toLowerCase().contains(busquedaAlumno.getText().toLowerCase()));
            cargarDatosTablaInicial(alumnos);
		}
		
	}
	
	
}
