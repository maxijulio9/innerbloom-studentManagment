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
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dominio.Alumno;
import dominio.Curso;
import dominio.GestorInstituto;
import exceptions.ApellidoVacioException;
import exceptions.CantidadAlumnosException;
import exceptions.CantidadHorasException;
import exceptions.NivelVacioException;
import exceptions.NombreVacioException;
import exceptions.PrincipalException;
import persistencia.PersistenciaDB;
import persistencia.PersistenciaDBCurso;

public class FormModificarCurso extends JFrame implements ActionListener{

	private JPanel tituloPrincipal;
	private JPanel botones;
	private JPanel grillaDatos;
	
	private JComboBox<String> listadoCursos= new JComboBox<String>();
	private JTextField txtNombre;	
	private JComboBox<String> niveles;
	private JTextField txtCargaHoraria;
	private JTextField txtVacantes;
	private JComboBox<String> listadoProfes= new JComboBox<String>();
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnVolver;
	
	public FormModificarCurso() {
		super("Agregar alumno");
		setSize(650,450);
		this.setLocation(450,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		agregarComponentes();
	}
	
	private void agregarComponentes() {
		
		JPanel panelPrincipal  = iniciarPanel();

		tituloPrincipal = iniciarPanel();
		tituloPrincipal.setLayout(new FlowLayout());
		tituloPrincipal.setBackground(new Color(250, 215, 160));
		
		JLabel titulo  = new JLabel("Modificar datos de curso");
		titulo.setFont(new Font("Times New Roman", Font.PLAIN,25));
		tituloPrincipal.add(titulo);
		
		grillaDatos = iniciarPanel();
		grillaDatos.setLayout(new GridLayout(1,1,5,5));
		
		JPanel panelLabel = iniciarPanel();
		panelLabel.setLayout(new GridLayout(6,1,5,5));
		iniciarLabel(panelLabel, "Seleccioná el nombre del curso ");
		iniciarLabel(panelLabel, "Nuevo nombre: ");
		iniciarLabel(panelLabel, "Nivel: ");
		iniciarLabel(panelLabel, "Carga Horaria: ");
		iniciarLabel(panelLabel, "Vacantes: ");
		iniciarLabel(panelLabel, "Profesor asignado: ");
		
		JPanel panelInput = iniciarPanel();
		panelInput.setLayout(new GridLayout(6,1,70,20));
		
		cargarListadoCurso(panelInput);
		txtNombre = iniciarJTextField(panelInput);
		cargarNiveles(panelInput);
		txtCargaHoraria = iniciarJTextField(panelInput);
		txtVacantes = iniciarJTextField(panelInput);
		cargarListadoProfes(panelInput);
		
		cargarDatosCurso(listadoCursos.getSelectedItem().toString());
		grillaDatos.add(panelLabel);
		grillaDatos.add(panelInput);
		
		
		botones = iniciarPanel();
		botones.setLayout(new GridLayout(1,2,4,4));
		
		JPanel panelOperaciones=new JPanel();
		panelOperaciones.setLayout(new FlowLayout());
		btnAceptar =  iniciarBoton("Aceptar", panelOperaciones);
		btnCancelar =  iniciarBoton("Limpiar", panelOperaciones);
		
		JPanel panelVolver=new JPanel();
		panelVolver.setLayout(new FlowLayout());
		btnVolver =  iniciarBoton("Cancelar", panelVolver);
		botones.add(panelOperaciones);
		botones.add(panelVolver);
		
		panelPrincipal.add(grillaDatos,  BorderLayout.CENTER);
		
		Container cp = getContentPane();		
		cp.add(tituloPrincipal, BorderLayout.NORTH);
		cp.add(panelPrincipal, BorderLayout.CENTER);
		cp.add(botones, BorderLayout.SOUTH);

	}
	
	private JPanel iniciarPanel() {
		JPanel ventana = new JPanel();
		return ventana;
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
	private void iniciarLabel(JPanel panel, String dato) {
		JLabel salida  =  new JLabel(dato);
		salida.setFont(new Font("Roboto", Font.PLAIN,15));
		panel.add(salida);
	}
	
	private JTextField iniciarJTextField(JPanel panel) {
		JTextField salida = new JTextField();
		panel.add(salida);
		return salida;
	}
	
	private void cargarListadoCurso(JPanel panelInput) {
		ArrayList<Curso> cursos = PersistenciaDBCurso.getCursos();
		for (Curso curso : cursos) {
			listadoCursos.addItem(curso.getNombre());
		}
		listadoCursos.addActionListener(this);
		panelInput.add(listadoCursos);
	}
	
	private void cargarListadoProfes(JPanel panelInput) {
		String[] gp = GestorInstituto.getInstancia().obtenerListadoProfes();
		
		for (String profe : gp) {
			listadoProfes.addItem(profe);
		}
		panelInput.add(listadoProfes);
	}
	private String[] cargarListadoProfes1(JPanel panelInput) {
		String[] gp = GestorInstituto.getInstancia().obtenerListadoProfes();
		
		for (String profe : gp) {
			listadoProfes.addItem(profe);
		}
		return gp;
	}
	
	private void cargarNiveles(JPanel panelInput) {
		String nivel[] = {"","A1","A2","B1","B2","C1","C2"};
		niveles =  new JComboBox<String>(nivel);
		panelInput.add(niveles);
	}
	
	private void limpiarCampos() {
		txtCargaHoraria.setText("");
		txtVacantes.setText("");
		txtNombre.setText("");
	}
	
	private void cargarDatosCurso(String nombre) {
		ArrayList<Curso> cursos = PersistenciaDBCurso.getCursos();
		for (Curso curso : cursos) {
			if (curso.getNombre().equalsIgnoreCase(nombre)){//q.equals(curso.getNombre())) {
				txtNombre.setText(curso.getNombre());
				txtCargaHoraria.setText(curso.getTotalHoras());
				txtVacantes.setText(curso.getCantidadAlumnos());
				niveles.setSelectedItem(curso.getNivel());
				listadoProfes.setSelectedItem(curso.getProfesorAsignado());
				
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source =  e.getSource();
		
		if (source == btnAceptar) {
			GestorInstituto gp = GestorInstituto.getInstancia();
			ArrayList<Curso> cursos = gp.getListaCursos();
	

			if (listadoCursos.getSelectedItem()==null) {
				JOptionPane.showMessageDialog(this, "Por favor seleccioná Curso que querés modificar.","No se pudo completar la operación"
							, JOptionPane.ERROR_MESSAGE);
			}else {
				try {
					
					for (Curso curso : cursos) {
						if (curso.getNombre().equals(listadoCursos.getSelectedItem().toString())) {
							txtCargaHoraria.setBackground(Color.WHITE);
							txtVacantes.setBackground(Color.WHITE);
							Curso cursoNew = new Curso();
			                cursoNew.setNombre(curso.getNombre());// Conserva los valores originales por defecto
			                cursoNew.setNivel(curso.getNivel());  
			                cursoNew.setTotalHoras(curso.getTotalHoras());
			                cursoNew.setCantidadAlumnos(curso.getCantidadAlumnos());
			                cursoNew.setProfesorAsignado(curso.getProfesorAsignado());
			                
			                boolean seModifico = validarDatosIngresados(cursoNew);
			                /*
			                try {
			                    if (!txtNombre.getText().isEmpty()) {
			                        cursoNew.setNombre(txtNombre.getText());
			                    }
			                } catch (NombreVacioException e1) {
			                    // Conserva el valor original
			                    JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			                }
			                
			                try {
			                    if (niveles.getSelectedItem() != "") {
			                        cursoNew.setNivel(niveles.getSelectedItem().toString() );
			                    }
			                } catch (NivelVacioException e1) {
			                    // Conserva el valor original
			                	JOptionPane.showMessageDialog(this, e1.getMessage(),
										"Operación finalizada", JOptionPane.ERROR_MESSAGE);			                
			                }
			                
			                try {
			                    if (!txtCargaHoraria.getText().isEmpty()) {
			                        cursoNew.setTotalHoras(txtCargaHoraria.getText());
			                    }
			                } catch (CantidadAlumnosException e1) {
			                    // Conserva el valor original
			                	txtCargaHoraria.requestFocusInWindow();
			        			txtCargaHoraria.setBackground(new Color(255,193, 214));
			                    JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			                }
			                try {
			                    if (!txtVacantes.getText().isEmpty()) {
			                        cursoNew.setCantidadAlumnos(txtVacantes.getText());
			                    }
			                } catch (CantidadAlumnosException e1) {
			                    // Conserva el valor original
			                	txtVacantes.requestFocusInWindow();
			        			txtVacantes.setBackground(new Color(255,193, 214));
			                    JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			                }
			                
			                try{
								cursoNew.setProfesorAsignado(listadoProfes.getSelectedItem().toString());
//								dispose();
							}catch (Exception e2) {
								JOptionPane.showMessageDialog(this, "Error",
										"Operación finalizada", JOptionPane.ERROR_MESSAGE);
							}
							*/
			                if (seModifico) {
								PersistenciaDBCurso.update(curso, cursoNew);
								//JOptionPane.showMessageDialog(this, "Proceso finalizado. Cambios guardados.",
									//	"Operación finalizada", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
						}
					}

					
				}catch (PrincipalException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, e2.getMessage(),
							"Errorcito", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		if (source == btnCancelar) {
			limpiarCampos();
			
		}
		if (source == btnVolver) {
			dispose();
		}
		
		if (e.getSource() == listadoCursos) {
//			String[] listadoNombresCombo ;
			String cursoSeleccionado = listadoCursos.getSelectedItem().toString();
			System.out.println(" " + cursoSeleccionado);
			cargarDatosCurso(cursoSeleccionado);
		}
	}

	private boolean validarDatosIngresados(Curso cursoNew) throws PrincipalException {
		// TODO Auto-generated method stub
		try {
            if (!txtNombre.getText().isEmpty()) {
                cursoNew.setNombre(txtNombre.getText());
            }
        } catch (NombreVacioException e1) {
            JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            if (niveles.getSelectedItem() != "") {
                cursoNew.setNivel(niveles.getSelectedItem().toString() );
            }
        } catch (NivelVacioException e1) {
            // Conserva el valor original
        	JOptionPane.showMessageDialog(this, e1.getMessage(),
					"Operación finalizada", JOptionPane.ERROR_MESSAGE);	
        	return false;
        }
        
        try {
            if (!txtCargaHoraria.getText().isEmpty()) {
                cursoNew.setTotalHoras(txtCargaHoraria.getText());
            }
        } catch (CantidadHorasException e1) {
            // Conserva el valor original
        	txtCargaHoraria.requestFocusInWindow();
			txtCargaHoraria.setBackground(new Color(255,193, 214));
            JOptionPane.showMessageDialog(this, e1.getMessage(), "Errorte", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            if (!txtVacantes.getText().isEmpty()) {
                cursoNew.setCantidadAlumnos(txtVacantes.getText());
            }
        } catch (CantidadAlumnosException e1) {
            // Conserva el valor original
        	txtVacantes.requestFocusInWindow();
			txtVacantes.setBackground(new Color(255,193, 214));
            JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try{
			cursoNew.setProfesorAsignado(listadoProfes.getSelectedItem().toString());
//			dispose();
		}catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "Error",
					"Operación finalizada", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}

