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
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dominio.Conexion;
import dominio.GestorInstituto;
import exceptions.DniInvalidoException;
import exceptions.PrincipalException;
import persistencia.PersistenciaDB;

public class FormEliminarAlumno extends JFrame implements ActionListener{

	private JPanel panelDatos;
	private JTextField txtDni;
	private JPanel botones;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnVolver;
	
	public FormEliminarAlumno(){
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
		JLabel tituloTexto = new JLabel("Eliminar alumno");
		tituloTexto.setFont(new Font("Times New Roman", Font.PLAIN,25));
		titulo.add(tituloTexto);
		
		
		panelDatos = iniciarPanel();
		panelDatos.setLayout(new GridLayout(2,2, 20,8));
		JLabel aux = new JLabel("DNI alumno/a: ");
		aux.setFont(new Font("Roboto", Font.PLAIN,16));
		panelDatos.add(aux);
		txtDni = iniciarTextField();
		
		botones = iniciarPanel();
		botones.setLayout(new GridLayout(1,2,4,4));
		
		JPanel panelOperaciones=new JPanel();
		panelOperaciones.setLayout(new FlowLayout());
		
		btnAceptar =  iniciarBoton("Aceptar", panelOperaciones);
		btnCancelar =  iniciarBoton("Limpiar", panelOperaciones);
		
		
		JPanel panelVolver=new JPanel();
		panelVolver.setLayout(new FlowLayout());
		btnVolver =  iniciarBoton("Atrás", panelVolver);
		
		botones.add(panelOperaciones);
		botones.add(panelVolver);
		
		panelPrincipal.add(panelDatos, BorderLayout.CENTER);

		Container cp = getContentPane();
		cp.add(titulo, BorderLayout.NORTH);
		cp.add(panelPrincipal, BorderLayout.CENTER);
		cp.add(botones, BorderLayout.SOUTH);
	}
	
	private JPanel iniciarPanel() {
		JPanel salida = new JPanel();
		return salida;
	}
	
	private JTextField iniciarTextField() {
		JTextField salida = new JTextField(20);
		panelDatos.add(salida);
		return salida;
	}
	
	private JButton iniciarBoton(String dato, JPanel panel) {
		JButton boton = new JButton(dato);
		boton.setBackground(new Color(8,60,95));
		boton.setPreferredSize(new Dimension(100,30));
		boton.setFont(new Font("Robotos", Font.BOLD,15));
		boton.setForeground(Color.white);
		boton.addActionListener(this);
		panel.add(boton);
		return boton;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton source = (JButton) e.getSource();
		Conexion conexionTest = new Conexion();
		Connection cn = null;

		if (source == btnAceptar) {
			GestorInstituto gp = GestorInstituto.getInstancia();
			if (txtDni.getText() == "") {
				JOptionPane.showMessageDialog(this, "Ingresá el DNI.", "Se produjo un error",
						JOptionPane.ERROR_MESSAGE);
			} else {

				
				try {
					boolean seElimino = gp.eliminarAlumno(txtDni.getText().trim());
					if (seElimino) {
						txtDni.setText("");
						JOptionPane.showMessageDialog(this, "Se eliminó el alumno correctamente.",
								"Operación finalizada", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				} catch (DniInvalidoException e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Error en los datos",
							JOptionPane.ERROR_MESSAGE);
				} catch (PrincipalException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}

		}
		if (source==btnCancelar) {
			txtDni.setText("");
//			setVisible(false);
		}
		if (source == btnVolver) {
			dispose();
		}
	}
	
	
	
}
