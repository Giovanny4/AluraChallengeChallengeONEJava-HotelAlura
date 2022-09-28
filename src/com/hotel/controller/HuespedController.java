package com.hotel.controller;

import java.sql.Date;
import java.util.ArrayList;
import com.hotel.dao.HuespedDAO;
import com.hotel.factory.CrearConexion;
import com.hotel.model.Huesped;

public class HuespedController {
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		CrearConexion conexion = new CrearConexion();
		this.huespedDAO = new HuespedDAO(conexion.crearConexion());
	}
	
	public void guardar(Huesped huesped) {
		this.huespedDAO.guardar(huesped);
	}
	
	public ArrayList<Huesped> listar() {
		return this.huespedDAO.listar();
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva) {
		return this.huespedDAO.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
	}

	public int eliminado(Integer id) {
		return this.huespedDAO.eliminar(id);
	}

}
