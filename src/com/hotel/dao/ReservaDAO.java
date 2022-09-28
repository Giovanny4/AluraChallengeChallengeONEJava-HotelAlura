package com.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hotel.model.Reserva;

public class ReservaDAO {
	final private Connection conexion;

	public ReservaDAO(Connection conexion) {
		this.conexion = conexion;
	}

	public void guardar(Reserva reserva) {
		try {
			String consulta = "INSERT INTO hotel.reservas (fecha_entrada, fecha_salida, valor, forma_pago) VALUES (?, ?, ?, ?)";
			try (PreparedStatement ps = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {
				ps.setDate(1, reserva.getFechaEntrada());
				ps.setDate(2, reserva.getFechaSalida());
				ps.setString(3, reserva.getValor());
				ps.setString(4, reserva.getFormaPago());
				ps.execute();
				try (ResultSet resultado = ps.getGeneratedKeys()) {
					while (resultado.next()) {
						reserva.setId(resultado.getInt(1));
					}
				}
			}	
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Reserva> listar() {
		List<Reserva> reservas = new ArrayList<>();

		try {
			String consulta = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM hotel.reservas";
			try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
				ps.execute();
				try (ResultSet resultado = ps.getResultSet()) {
					while (resultado.next()) {
						reservas.add(new Reserva(
								resultado.getInt("id"),
								resultado.getDate("fecha_entrada"),
								resultado.getDate("fecha_salida"),
								resultado.getString("valor"),
								resultado.getString("forma_pago")
								));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservas;
	}

	public int modificar(Integer id, Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
		try {
			String consulta = "UPDATE hotel.reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ? WHERE id = ?";
			try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
				ps.setDate(1, fechaEntrada);
				ps.setDate(2, fechaSalida);
				ps.setString(3, valor);
				ps.setString(4, formaPago);
				ps.setInt(5, id);
				ps.execute();
				
				int updateCount = ps.getUpdateCount();
                return updateCount;
			}	
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public int eliminar(Integer id) {
		try {
			String consulta = "DELETE FROM hotel.reservas WHERE id = ?";
			try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
				ps.setInt(1, id);
				ps.execute();
				
				int updateCount = ps.getUpdateCount();
                return updateCount;
			}	
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
