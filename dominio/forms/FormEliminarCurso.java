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

import dominio.Curso;
import dominio.Problema.GestorInstitutoOld;

public class FormEliminarCurso extends JFrame implements ActionListener{

	
	private JPanel panelDatos;
	private JComboBox<String> listadoCursos= new JComboBox<String>();
	private JPanel botones;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnVolver;
	
	
	
	public FormEliminarCurso(){
		super("Eliminar curso");
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
		JLabel tituloTexto = new JLabel("Eliminar curso");
		tituloTexto.setFont(new Font("Times New Roman", Font.PLAIN,25));
		titulo.add(tituloTexto);
		
		
		panelDatos = iniciarPanel();
		panelDatos.setLayout(new GridLayout(1,2, 20,3));
		JLabel aux = new JLabel("Seleccioná el curso ");
		aux.setFont(new Font("Roboto", Font.PLAIN,15));
		panelDatos.add(aux);
		cargarListadoCurso();
		
		
		botones = iniciarPanel();
		botones.setLayout(new GridLayout(1,2,4,4));
		
		JPanel panelOperaciones=new JPanel();
		panelOperaciones.setLayout(new FlowLayout());
		
		btnAceptar =  iniciarBoton("Aceptar", panelOperaciones);
		btnCancelar =  iniciarBoton("Cancelar", panelOperaciones);
		
		JPanel panelVolver=new JPanel();
		panelVolver.setLayout(new FlowLayout());
		btnVolver =  iniciarBoton("Atrás", panelVolver);
		
		botones.add(panelOperaciones);
		botones.add(panelVolver);
		
		panelPrincipal.add(panelDatos, BorderLayout.CENTER);
//		panelPrincipal.add(botones, BorderLayout.SOUTH);

		Container cp = getContentPane();
		cp.add(titulo, BorderLayout.NORTH);
		cp.add(panelPrincipal, BorderLayout.CENTER);
		cp.add(botones, BorderLayout.SOUTH);
	}
	
	private JPanel iniciarPanel() {
		JPanel salida = new JPanel();
		return salida;
	}
	
	private void cargarListadoCurso() {
		GestorInstitutoOld gp = GestorInstitutoOld.getInstancia();
		ArrayList<Curso> cursos = gp.getListaCursos() ;//PersistenciaDBCurso.getCursos();
		listadoCursos.removeAllItems();
		for (Curso curso : cursos) {
			listadoCursos.addItem(curso.getNombre());
		}
		panelDatos.add(listadoCursos);
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
		GestorInstitutoOld gp = GestorInstitutoOld.getInstancia();
		if (source == btnAceptar) {
			if (listadoCursos.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Por favor seleccioná el curso que querés eliminar", "Datos incorrectos",
						JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					 boolean seElimino = gp.eliminarCurso(listadoCursos.getSelectedItem().toString().trim());
					 if (seElimino) {
						 JOptionPane.showMessageDialog(this, "Curso eliminado",
									"Proceso finalizado", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, e2.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			cargarListadoCurso();
		}
	
	
	if(source==btnCancelar){
		dispose();
	}
	if(source==btnVolver){
		dispose();
	}
}

}
