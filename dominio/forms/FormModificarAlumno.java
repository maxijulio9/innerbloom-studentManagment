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
import javax.swing.JTextField;

import dominio.Alumno;
import exceptions.ApellidoVacioException;
import exceptions.NombreVacioException;
import exceptions.PrincipalException;
import persistencia.PersistenciaDB;

public class FormModificarAlumno extends JFrame implements ActionListener{

	private JPanel tituloPrincipal;
	private JPanel botones;
	private JPanel grillaDatos;	
	private JComboBox<String> listadoDNIAlumno = new JComboBox<String>();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnVolver;
	private JButton btnEliminarCurso;
	
	public FormModificarAlumno() {
		super("Modificar datos Alumno");
		setSize(800,350);
		this.setLocation(450,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		agregarComponentes();
	}
	private void agregarComponentes() {
		tituloPrincipal = iniciarPanel();
		tituloPrincipal.setLayout(new FlowLayout());
		tituloPrincipal.setBackground(new Color(250, 215, 160));
		JLabel titulo  = new JLabel("Modificar datos alumno");
		titulo.setFont(new Font("Times New Roman", Font.PLAIN,25));
		tituloPrincipal.add(titulo);
		
		JPanel panelPrincipal = iniciarPanel();
		
		grillaDatos = iniciarPanel();
		grillaDatos.setLayout(new GridLayout(1,1,5,5));
		
		JPanel panelLabel = iniciarPanel();
		panelLabel.setLayout(new GridLayout(4,1,5,5));	
		iniciarLabel(panelLabel, "Seleccioná el alumno: ");
		iniciarLabel(panelLabel, "Nombre: ");
		iniciarLabel(panelLabel, "Apellido: ");
		iniciarLabel(panelLabel, "Telefono: ");
		
		JPanel panelInput = iniciarPanel();
		panelInput.setLayout(new GridLayout(4,1,70,20));
		
		cargarListaAlumno(panelInput);
		txtNombre = iniciarJTextField(panelInput);
		txtApellido = iniciarJTextField(panelInput);
		txtTelefono = iniciarJTextField(panelInput);

		cargarDatosAlumno(listadoDNIAlumno.getSelectedItem().toString());
		grillaDatos.add(panelLabel);
		grillaDatos.add(panelInput);

		botones = iniciarPanel();
		botones.setLayout(new GridLayout(1,2,4,4));
		
		JPanel panelOperaciones=new JPanel();
		panelOperaciones.setLayout(new FlowLayout());
		
		btnAceptar =  iniciarBoton("Aceptar", panelOperaciones);
		btnCancelar =  iniciarBoton("Limpiar", panelOperaciones);
	
		JPanel panelVolver=new JPanel();
		panelVolver.setLayout(new GridLayout(2,1,15,5));
		btnEliminarCurso =  iniciarBoton("Dar baja curso", panelVolver);
		btnVolver =  iniciarBoton("Cancelar", panelVolver);
		
		botones.add(panelOperaciones);
		botones.add(panelVolver);
		
		panelPrincipal.add(grillaDatos, BorderLayout.CENTER);
		panelPrincipal.add(botones, BorderLayout.SOUTH);
		
		Container cp = getContentPane();		
		cp.add(tituloPrincipal, BorderLayout.NORTH);
		cp.add(panelPrincipal);
	}
	
	private JPanel iniciarPanel() {
		JPanel panel = new JPanel();
		return panel;
	}
	
	private void iniciarLabel(JPanel panel, String dato) {
		JLabel salida  =  new JLabel(dato);
		salida.setFont(new Font("Reboto", Font.PLAIN,15));
		panel.add(salida);
	}
	
	private JTextField iniciarJTextField(JPanel panel) {
		JTextField salida = new JTextField(20);
		panel.add(salida);
		return salida;
	}
	
	private JButton iniciarBoton(String dato, JPanel panel) {
		JButton boton = new JButton(dato);
		if (dato.contains("Dar")) {
			boton.setBackground(new Color(204,102,0));
		}else{
		boton.setBackground(new Color(8,60,95));}
		boton.setPreferredSize(new Dimension(100,30));
		boton.setFont(new Font("Roboto", Font.BOLD,15));
		boton.setForeground(Color.white);
		boton.addActionListener(this);
		panel.add(boton);
	
		return boton;
	}
	
	private void cargarListaAlumno(JPanel panelInput) {
		ArrayList<Alumno> gp = PersistenciaDB.getAlumnos();
		for (Alumno a : gp){
			listadoDNIAlumno.addItem(a.getDni()+" - "+a.getNombre()+" "+a.getApellido());
		}
		listadoDNIAlumno.addActionListener(this);
		panelInput.add(listadoDNIAlumno);
	}
	
	private void limpiarCampos() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");	
	}
	private void cargarDatosAlumno(String alumnoSeleccionado) {
		ArrayList<Alumno> alumnos = PersistenciaDB.getAlumnos();
		String[] partes = alumnoSeleccionado.split("-"); // Suponiendo que el guión separa el nombre y el DNI
		String dniSeleccionado = partes[0].trim(); // Tomamos la última parte como el DNI

		for (Alumno alumno : alumnos) {
//			System.out.println(alumno.toString());
			if (alumno.getDni().equals(dniSeleccionado)) {
				txtNombre.setText(alumno.getNombre());
				txtApellido.setText(alumno.getApellido());
				txtTelefono.setText(alumno.getTelefono());
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		JButton source = (JButton) e.getSource();
		Object source =  e.getSource();
		if (source == btnAceptar) {
			ArrayList<Alumno> alumnos = PersistenciaDB.getAlumnos();
			
			if (listadoDNIAlumno.getSelectedItem()==null) {
				JOptionPane.showMessageDialog(this, "Por favor seleccioná el DNI del alumno.","No se pudo completar la operación"
							, JOptionPane.ERROR_MESSAGE);
			}else {
				try {
				
					//alumno seleccionado
					String dniSeleccionadoCompleto = listadoDNIAlumno.getSelectedItem().toString();
					String[] partes = dniSeleccionadoCompleto.split("-"); // Suponiendo que el guión separa el nombre y el DNI
					String dniSeleccionado = partes[0].trim(); // Tomamos la última parte como el DNI
					

					for (Alumno alumno : alumnos) {
						if (alumno.getDni().equals(dniSeleccionado)) {
							txtNombre.setBackground(Color.WHITE);
							txtApellido.setBackground(Color.WHITE);
			                Alumno alumnoNew = new Alumno();
			                alumnoNew.setNombre(alumno.getNombre()); // Conserva los valores originales por defecto
			                alumnoNew.setApellido(alumno.getApellido());
			                alumnoNew.setTelefono(alumno.getTelefono());
			                alumnoNew.setFechaCreacionCortaString(alumno.getFechaCreacionCortaString());

			                try {
			                    if (!txtNombre.getText().isEmpty()) {
			                        alumnoNew.setNombre(txtNombre.getText());
			                    }
			                } catch (NombreVacioException e1) {
			                    // Conserva el valor original
			                	txtNombre.requestFocusInWindow();
			    				txtNombre.setBackground(new Color(255,193, 214));
			                    JOptionPane.showMessageDialog(this, e1.getMessage(), "Operación finalizada", JOptionPane.INFORMATION_MESSAGE);
			                }

			                try {
			                    if (!txtApellido.getText().isEmpty()) {
			                        alumnoNew.setApellido(txtApellido.getText());
			                    }
			                } catch (ApellidoVacioException e1) {
			                    // Conserva el valor original
			                	txtApellido.requestFocusInWindow();
			    				txtApellido.setBackground(new Color(255,193, 214));
			                    JOptionPane.showMessageDialog(this, e1.getMessage(), "Operación finalizada", JOptionPane.INFORMATION_MESSAGE);
			                }

			                try {
			                    if (!txtTelefono.getText().isEmpty()) {
			                        alumnoNew.setTelefono(txtTelefono.getText());
			                    }
			                } catch (PrincipalException e1) {
			                    // Conserva el valor original o maneja la excepción
			                    JOptionPane.showMessageDialog(this,e1.getMessage(), e1.getMessage(), JOptionPane.INFORMATION_MESSAGE);
			                }
			                PersistenciaDB.update(alumno, alumnoNew);
			               dispose();
			            }
						
					}
				}catch (PrincipalException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, e2.getMessage(),
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
				}
				
			}
				
		}
		
		if (source == btnCancelar) {
			limpiarCampos();
		}
		
		if (source == btnVolver) {
			dispose();
		}
		if (source == listadoDNIAlumno) {
			String alumnoSeleccionado = listadoDNIAlumno.getSelectedItem().toString();
			System.out.println(">>> "+alumnoSeleccionado);
			cargarDatosAlumno(alumnoSeleccionado);
			
		}
		if (source == btnEliminarCurso) {
			FormEliminarAlumnoDeCurso ventana = new FormEliminarAlumnoDeCurso(listadoDNIAlumno.getSelectedItem().toString());
			ventana.setVisible(true);
		}
	}
}
