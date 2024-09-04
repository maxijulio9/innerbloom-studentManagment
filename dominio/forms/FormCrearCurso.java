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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dominio.Curso;
import dominio.Problema.GestorInstitutoOld;
import exceptions.CantidadAlumnosException;
import exceptions.CantidadHorasException;
import exceptions.NivelVacioException;
import exceptions.NombreVacioException;
import exceptions.PrincipalException;

public class FormCrearCurso extends JFrame implements ActionListener {
	
	private JPanel tituloPrincipal;
	private JPanel botones;
	private JPanel grillaDatos;
	private JTextField txtNombre;	
	private JComboBox<String> niveles;
	private JTextField txtCargaHoraria;
	private JTextField txtVacantes;
	private JComboBox<String> listadoProfes= new JComboBox<String>();
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnVolver;
	
	public FormCrearCurso() {
		super("Agregar alumno");
		setSize(650,400);
		this.setLocation(550,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		agregarComponentes();
	}
	
	private void agregarComponentes() {
		
		JPanel panelPrincipal  = iniciarPanel();
	
		tituloPrincipal = iniciarPanel();
		tituloPrincipal.setLayout(new FlowLayout());
		tituloPrincipal.setBackground(new Color(250, 215, 160));
		JLabel titulo  = new JLabel("Datos nuevo Curso");
		titulo.setFont(new Font("Times New Roman", Font.PLAIN,25));
		tituloPrincipal.add(titulo);
		
		grillaDatos = iniciarPanel();
		grillaDatos.setLayout(new GridLayout(1,1,5,5));
		
		JPanel panelLabel = iniciarPanel();
		panelLabel.setLayout(new GridLayout(5,1,10,5));
		iniciarLabel(panelLabel, "Nombre:*");
		iniciarLabel(panelLabel, "Nivel:*");
		iniciarLabel(panelLabel, "Carga Horaria:*");
		iniciarLabel(panelLabel, "Vacantes:*");
		iniciarLabel(panelLabel, "Profesor asignado:*");
		
		JPanel panelInput = iniciarPanel();
		panelInput.setLayout(new GridLayout(5,1,70,20));
		txtNombre = iniciarJTextField(panelInput);
		cargarNiveles(panelInput);
		txtCargaHoraria = iniciarJTextField(panelInput);
		txtVacantes = iniciarJTextField(panelInput);
		cargarListadoProfes(panelInput);
		
		grillaDatos.add(panelLabel);
		grillaDatos.add(panelInput);
		
		panelPrincipal.add(grillaDatos,  BorderLayout.CENTER);
		
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
		
//		panelPrincipal.add(botones,  BorderLayout.SOUTH);
		
		Container cp = getContentPane();		
		cp.add(tituloPrincipal, BorderLayout.NORTH);
		cp.add(panelPrincipal, BorderLayout.CENTER);
		cp.add(botones, BorderLayout.SOUTH);
//		cp.add(panelPrincipal);

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
		salida.setFont(new Font("Roboto", Font.PLAIN,16));
		panel.add(salida);
	}
	
	private JTextField iniciarJTextField(JPanel panel) {
		JTextField salida = new JTextField();
		panel.add(salida);
		return salida;
	}
	
	private void cargarListadoProfes(JPanel panelInput) {
		String[] gp = GestorInstitutoOld.getInstancia().obtenerListadoProfes();
		for (String profe : gp) {
			listadoProfes.addItem(profe);
		}
		panelInput.add(listadoProfes);
	}
	
	private void cargarNiveles(JPanel panelInput) {
		String nivel[] = {"","A1","A2","B1","B2","C1","C2"};
		niveles =  new JComboBox<String>(nivel);
		panelInput.add(niveles);
	}
	
	private void limpiarCampos() {
		txtNombre.setText("");
		txtCargaHoraria.setText("");
		txtVacantes.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();

		if (source == btnAceptar) {
			GestorInstitutoOld gp = GestorInstitutoOld.getInstancia();
			try {
				txtNombre.setBackground(Color.WHITE);
				txtCargaHoraria.setBackground(Color.WHITE);
				txtVacantes.setBackground(Color.WHITE);
				niveles.setBackground(Color.WHITE);


				Curso cursoAux = new Curso(txtNombre.getText(), niveles.getSelectedItem().toString(),txtCargaHoraria.getText(),
						txtVacantes.getText(), listadoProfes.getSelectedItem().toString());
				try {
					boolean seCreoCurso = gp.agregarCurso(cursoAux);
					if (seCreoCurso) {
//						PersistenciaDBCurso.insert(cursoAux);
						JOptionPane.showMessageDialog(this, "¡Nuevo curso registrado, que alegría crecer!",
								"Operación confirmada", JOptionPane.INFORMATION_MESSAGE);
						limpiarCampos();
						dispose();
					} else {
						JOptionPane.showMessageDialog(this, "Ya tenemos registrado el curso que intentas crear.",
								"¡Curso existente!", JOptionPane.ERROR_MESSAGE);
					}

				} catch (PrincipalException e2) {
					JOptionPane.showMessageDialog(this, e2.getMessage(), "Datos incorrectos",
							JOptionPane.ERROR_MESSAGE);
				}
//				}
			}catch (NombreVacioException e11) {
				txtNombre.requestFocusInWindow();
				txtNombre.setBackground(new Color(255,193, 214));
				JOptionPane.showMessageDialog(this, e11.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}catch (CantidadAlumnosException e11) {
				txtVacantes.requestFocusInWindow();
				txtVacantes.setBackground(new Color(255,193, 214));
				JOptionPane.showMessageDialog(this, e11.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}catch (CantidadHorasException e11) {
				txtCargaHoraria.requestFocusInWindow();
				txtCargaHoraria.setBackground(new Color(255,193, 214));
				JOptionPane.showMessageDialog(this, e11.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}catch (NivelVacioException e11) {
				niveles.requestFocusInWindow();
				niveles.setBackground(new Color(255,193, 214));
				JOptionPane.showMessageDialog(this, e11.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}catch (PrincipalException e11) {
				JOptionPane.showMessageDialog(this, e11.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if (source == btnCancelar) {
			limpiarCampos();
		}
		if (source == btnVolver) {
			dispose();
		}

		
	}
	

}
