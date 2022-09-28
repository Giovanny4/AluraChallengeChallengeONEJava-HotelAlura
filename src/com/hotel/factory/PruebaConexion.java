package com.hotel.factory;


import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {

	public static void main(String[] args) throws SQLException {
		CrearConexion conexiones = new CrearConexion();
		
		for (int i = 0; i < 20; i++) {
			Connection connection = conexiones.crearConexion();
			System.out.println("Conexion abierta " + (i + 1));
		}
	}

}
