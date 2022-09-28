package com.hotel.controller;


import java.sql.Date;
import java.util.List;

import com.hotel.dao.ReservaDAO;
import com.hotel.factory.CrearConexion;
import com.hotel.model.Reserva;


public class ReservaController {
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		CrearConexion conexion = new CrearConexion();
		this.reservaDAO = new ReservaDAO(conexion.crearConexion());
	}
	
	public void guardar(Reserva reserva) {
		this.reservaDAO.guardar(reserva);
	}

	public List<Reserva> listar() {
		return this.reservaDAO.listar();
	}

	public int modificar(Integer id, Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
		return this.reservaDAO.modificar(id, fechaEntrada, fechaSalida, valor, formaPago);
	}

	public int eliminado(Integer id) {
		return this.reservaDAO.eliminar(id);
	}

}
