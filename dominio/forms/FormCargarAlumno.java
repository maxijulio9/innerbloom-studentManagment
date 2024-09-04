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
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dominio.Alumno;
import dominio.Problema.GestorInstitutoOld;
import exceptions.ApellidoVacioException;
import exceptions.DniInvalidoException;
import exceptions.NombreVacioException;
import exceptions.PrincipalException;
import exceptions.TelefonoInvalidoException;


public class FormCargarAlumno extends JFrame implements ActionListener {

	private JPanel tituloPrincipal;
	private JPanel botones;
	private JPanel grillaDatos;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDNI;
	private JTextField txtTelefono;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnVolver;

	
	public FormCargarAlumno() {
		super("Registrar alumno");
		setSize(650,350);
		this.setLocation(550,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setResizable(false);
		agregarComponentes();
	}
	private void agregarComponentes() {
		
		tituloPrincipal = iniciarPanel();
		tituloPrincipal.setLayout(new FlowLayout());
		tituloPrincipal.setBackground(new Color(250, 215, 160));
		
		JLabel titulo  = new JLabel("Datos nuevo alumno");
		titulo.setFont(new Font("Times New Roman", Font.PLAIN,25));
		tituloPrincipal.add(titulo);
		
		JPanel panelPrincipal = iniciarPanel();
		
		grillaDatos = iniciarPanel();
		grillaDatos.setLayout(new GridLayout(1,1,5,1));
		
		JPanel panelLabel = iniciarPanel();
		panelLabel.setLayout(new GridLayout(4,1,5,1));
		iniciarLabel(panelLabel, "Nombre:* ");
		iniciarLabel(panelLabel, "Apellido:* ");
		iniciarLabel(panelLabel, "DNI:*");
		iniciarLabel(panelLabel, "Telefono: ");
		

		JPanel panelInput = iniciarPanel();
		panelInput.setLayout(new GridLayout(4,1,70,20));
		txtNombre = iniciarJTextField(panelInput);
		txtApellido = iniciarJTextField(panelInput);
		txtDNI = iniciarJTextField(panelInput);
		txtDNI.addFocusListener(null);
		txtTelefono = iniciarJTextField(panelInput);

		grillaDatos.add(panelLabel);
		grillaDatos.add(panelInput);

		JPanel panelOperaciones=new JPanel();
		panelOperaciones.setLayout(new FlowLayout());
		
		botones = iniciarPanel();
		botones.setLayout(new GridLayout(1,2,10,15));
		btnAceptar =  iniciarBoton("Aceptar", panelOperaciones);
		btnCancelar =  iniciarBoton("Limpiar", panelOperaciones);
		botones.add(panelOperaciones);
		
		JPanel panelVolver=new JPanel();
		panelVolver.setLayout(new FlowLayout());
		btnVolver =  iniciarBoton("Atrás", panelVolver);
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
		salida.setHorizontalAlignment(SwingConstants.LEADING);
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
		boton.setBackground(new Color(8,60,95));
		boton.setPreferredSize(new Dimension(100,30));
		boton.setFont(new Font("Roboto", Font.BOLD,15));
		boton.setForeground(Color.white);
		boton.addActionListener(this);
		panel.add(boton);
	
		return boton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source ==  btnAceptar) {
			GestorInstitutoOld gp = GestorInstitutoOld.getInstancia();
			
			try {
				txtNombre.setBackground(Color.WHITE);
				txtDNI.setBackground(Color.WHITE);
				txtApellido.setBackground(Color.WHITE);
				
				Alumno alumnoAux = new Alumno(txtNombre.getText(), txtApellido.getText(), txtDNI.getText(), txtTelefono.getText(),getFechaGregoriana());
				boolean seAgrego = gp.agregarAlumno(alumnoAux);
				if (seAgrego) {
					JOptionPane.showMessageDialog(this, "¡Alumno registrado correctamente!");
					dispose();
//					limpiarCampos();
				}else {
					txtDNI.requestFocusInWindow();
					txtDNI.setBackground(new Color(255,193, 214));
					txtDNI.setText("");
					JOptionPane.showMessageDialog(this,"El alumno ya se encuentra registrado" );
					
				}				
			}catch (DniInvalidoException e2) {
				txtDNI.requestFocusInWindow();
				txtDNI.setBackground(new Color(255,193, 214));
				JOptionPane.showMessageDialog(this, e2.getMessage(),
						"Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}catch (NombreVacioException e2) {
				txtNombre.requestFocusInWindow();
				txtNombre.setBackground(new Color(255,193, 214));
				JOptionPane.showMessageDialog(this, e2.getMessage(),
						"Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}catch (ApellidoVacioException e2) {
				txtApellido.requestFocusInWindow();
				txtApellido.setBackground(new Color(255,193, 214));
				JOptionPane.showMessageDialog(this, e2.getMessage(),
						"Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}catch (TelefonoInvalidoException e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage(),
						"Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}catch (PrincipalException e11) {
				JOptionPane.showMessageDialog(this, e11.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (source == btnCancelar) {
			limpiarCampos();
//			dispose();
		}
		if (source == btnVolver) {
			dispose();
		}

	}
	private String getFechaGregoriana() {
		GregorianCalendar hoy = (GregorianCalendar) GregorianCalendar.getInstance();
	    int mesActual = hoy.get(Calendar.MONTH) + 1; 
	    return hoy.get(Calendar.DAY_OF_MONTH) + "/" + mesActual + "/" + hoy.get(Calendar.YEAR);
	}
	private void limpiarCampos() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtDNI.setText("");
		txtTelefono.setText("");	
	}

}
