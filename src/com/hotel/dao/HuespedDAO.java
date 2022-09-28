package com.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.hotel.model.Huesped;

public class HuespedDAO {
	final private Connection conexion;

	public HuespedDAO(Connection conexion) {
		this.conexion = conexion;
	}

	public void guardar(Huesped huesped) {
		try  {
			String consulta = "INSERT INTO hotel.huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, reservas_id) VALUES (?, ?, ?, ?, ?, ?)";
			try (PreparedStatement ps = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {
				ps.setString(1, huesped.getNombre());
				ps.setString(2, huesped.getApellido());
				ps.setDate(3, huesped.getFechaNacimiento());
				ps.setString(4, huesped.getNacionalidad());
				ps.setString(5, huesped.getTelefono());
				ps.setInt(6, huesped.getIdReserva());
				ps.execute();
				try (ResultSet resultado = ps.getGeneratedKeys()) {
					while (resultado.next()) {
						huesped.setId(resultado.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Huesped> listar() {
		ArrayList<Huesped> huespedes = new ArrayList<>();

		try  {
			String consulta = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, reservas_id FROM hotel.huespedes";
			try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
				ps.execute();
				try (ResultSet resultado = ps.getResultSet()) {
					while (resultado.next()) {
						huespedes.add(new Huesped(resultado.getInt("id"), resultado.getString("nombre"),
								resultado.getString("apellido"), resultado.getDate("fecha_nacimiento"),
								resultado.getString("nacionalidad"), resultado.getString("telefono"),
								resultado.getInt("reservas_id")));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return huespedes;
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva) {

		try  {
			String consulta = "UPDATE hotel.huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, reservas_id = ? WHERE id = ?";
			PreparedStatement ps = conexion.prepareStatement(consulta);
			try (ps) {
				ps.setString(1, nombre);
				ps.setString(2, apellido);
				ps.setDate(3, fechaNacimiento);
				ps.setString(4, nacionalidad);
				ps.setString(5, telefono);
				ps.setInt(6, idReserva);
				ps.setInt(7, id);
				ps.execute();
				
				int updateCount = ps.getUpdateCount();
                return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public int eliminar(Integer id) {
		try  {
			String consulta = "DELETE FROM hotel.huespedes WHERE id = ?";
			PreparedStatement ps = conexion.prepareStatement(consulta);
			try (ps) {
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
