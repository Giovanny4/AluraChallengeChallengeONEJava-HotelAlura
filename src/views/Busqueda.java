package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.hotel.controller.HuespedController;
import com.hotel.controller.ReservaController;
import com.hotel.model.Huesped;
import com.hotel.model.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private DefaultTableModel modeloHuesped;
	private DefaultTableModel modeloReserva;
	private JTable tbReservas;
	private JTable tbHuespedes;
	private HuespedController huespedController;
	private ReservaController reservaController;
	private ArrayList<Huesped> huespedes;
	private ArrayList<Reserva> reservas;
	private int tablaSeleccionada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				busqueda();
			}
		});
		contentPane.add(btnBuscar);

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		contentPane.add(btnEditar);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Búsqueda");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 42, 258, 42);
		contentPane.add(lblNewLabel_4);

		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(815, 416, 54, 41);
		contentPane.add(btnSalir);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		panel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				JTabbedPane pane = (JTabbedPane) evt.getSource();
				tablaSeleccionada = pane.getSelectedIndex();
			}
		});

		contentPane.add(panel);

		tbHuespedes = new JTable();
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("ID");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Teléfono");
		modeloHuesped.addColumn("Número de reserva");
		tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes,
				null);

		tbReservas = new JTable();
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		modeloReserva = (DefaultTableModel) tbReservas.getModel();
		modeloReserva.addColumn("ID");
		modeloReserva.addColumn("Fecha entrada");
		modeloReserva.addColumn("Fecha Salida");
		modeloReserva.addColumn("Valor");
		modeloReserva.addColumn("Forma de pago");
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas,
				null);

		JButton btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
		btnEliminar.setBackground(SystemColor.menu);
		btnEliminar.setBounds(651, 416, 54, 41);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		contentPane.add(btnEliminar);

		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(25, 10, 104, 107);
		contentPane.add(lblNewLabel_2);
		setResizable(false);

		this.huespedController = new HuespedController();
		obtenerHuespedes();

		this.reservaController = new ReservaController();
		obtenerReservas();
	}

	private void obtenerHuespedes() {
		this.huespedes = this.huespedController.listar();
		listarHuespedes(this.huespedes);
	}

	private void obtenerReservas() {
		this.reservas = (ArrayList<Reserva>) this.reservaController.listar();
		listarReservas(this.reservas);
	}

	private void busqueda() {
		if (tablaSeleccionada == 0) {
			busquedaHuesped();
		} else if (tablaSeleccionada == 1) {
			busquedaReserva();
		}
	}

	private void busquedaHuesped() {
		String palabraClave = txtBuscar.getText();
		ArrayList<Huesped> resultado = new ArrayList<Huesped>();
		this.huespedes.forEach(huesped -> {
			if (huesped.getApellido().equals(palabraClave)) {
				resultado.add(huesped);
			}
		});
		if (resultado.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No se encontró el huesped");
			listarHuespedes(this.huespedes);
		} else {
			listarHuespedes(resultado);
		}
	}

	private void busquedaReserva() {
		String palabraClave = txtBuscar.getText();
		ArrayList<Reserva> resultado = new ArrayList<Reserva>();
		this.reservas.forEach(reserva -> {
			if (reserva.getId().toString().equals(palabraClave)) {
				resultado.add(reserva);
			}
		});
		if (resultado.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No se encontró la reserva");
			listarReservas(this.reservas);
		} else {
			listarReservas(resultado);
		}
	}

	private void listarHuespedes(ArrayList<Huesped> huespedes) {
		modeloHuesped.getDataVector().clear();
		ArrayList<Huesped> huespedesTabla = huespedes;
		huespedesTabla.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
				huesped.getIdReserva() }));
		tbHuespedes.updateUI();
	}

	private void listarReservas(ArrayList<Reserva> reservas) {
		modeloReserva.getDataVector().clear();
		ArrayList<Reserva> reservasTabla = reservas;
		reservasTabla.forEach(reserva -> modeloReserva.addRow(new Object[] { reserva.getId(), reserva.getFechaEntrada(),
				reserva.getFechaSalida(), reserva.getValor(), reserva.getFormaPago() }));
		tbReservas.updateUI();
	}

	private void eliminar() {
		if (tablaSeleccionada == 0) {
			eliminarHuesped();
		} else if (tablaSeleccionada == 1) {
			eliminarReserva();
		}
	}

	private void eliminarHuesped() {
		Integer filaSeleccionada = tbHuespedes.getSelectedRow();

		Integer id = (Integer) modeloHuesped.getValueAt(filaSeleccionada, 0);

		var eliminado = this.huespedController.eliminado(id);
		obtenerHuespedes();
		JOptionPane.showMessageDialog(this, String.format("%d item eliminado!", eliminado));
	}
	
	private void eliminarReserva() {
		Integer filaSeleccionada = tbReservas.getSelectedRow();

		Integer id = (Integer) modeloReserva.getValueAt(filaSeleccionada, 0);
		var eliminado = this.reservaController.eliminado(id);
		obtenerReservas();
		JOptionPane.showMessageDialog(this, String.format("%d item eliminado con éxito!", eliminado));
	}
	
	private void modificar() {
		if (tablaSeleccionada == 0) {
			modificarHuesped();
		} else if (tablaSeleccionada == 1) {
			modificarReserva();
		}
	}

	private void modificarHuesped() {
		Integer filaSeleccionada = tbHuespedes.getSelectedRow();

		Integer id = (Integer) modeloHuesped.getValueAt(filaSeleccionada, 0);
		String nombre = (String) modeloHuesped.getValueAt(filaSeleccionada, 1);
		String apellido = (String) modeloHuesped.getValueAt(filaSeleccionada, 2);
		Date fechaNacimiento = java.sql.Date.valueOf((String) modeloHuesped.getValueAt(filaSeleccionada, 3));
		String nacionalidad = (String) modeloHuesped.getValueAt(filaSeleccionada, 4);
		String telefono = (String) modeloHuesped.getValueAt(filaSeleccionada, 5);
		Integer idReserva = (Integer) modeloHuesped.getValueAt(filaSeleccionada, 6);

		var modificado = this.huespedController.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
		obtenerHuespedes();
		JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", modificado));
	}
	
	private void modificarReserva() {
		Integer filaSeleccionada = tbReservas.getSelectedRow();

		Integer id = (Integer) modeloReserva.getValueAt(filaSeleccionada, 0);
		Date fechaEntrada = java.sql.Date.valueOf((String) modeloReserva.getValueAt(filaSeleccionada, 1));
		Date fechaSalida = java.sql.Date.valueOf((String) modeloReserva.getValueAt(filaSeleccionada, 2));
		String valor = (String) modeloReserva.getValueAt(filaSeleccionada, 3);
		String formaPago = (String) modeloReserva.getValueAt(filaSeleccionada, 4);

		var modificado = this.reservaController.modificar(id, fechaEntrada, fechaSalida, valor, formaPago);
		obtenerReservas();
		JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", modificado));
	}

}
