package com.tp1.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tp1.connection.ConexionMySql;
import com.tp1.idao.IPersonaDao;
import com.tp1.model.Persona;

public class PersonaDaoImplMySql implements IPersonaDao {
	ConexionMySql ctmp;
	
	public PersonaDaoImplMySql() {
		this.ctmp = new ConexionMySql();
	}
	
	public void crear() throws SQLException{
		try {
			String table = "CREATE TABLE IF NOT EXISTS persona(" 
					+ "id INT NULL AUTO_INCREMENT, " 
					+ "nombre VARCHAR(500),"
					+ "edad INT,"
					+ "PRIMARY KEY(id))";
			this.ctmp.getConnection().prepareStatement(table).execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dropTable() throws SQLException{
		String dropTable = "DROP TABLE persona";
		try {
			this.ctmp.getConnection().prepareStatement(dropTable).execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public ArrayList<Persona> obtenerPersonas() {
		ArrayList<Persona> listaPersonas = new ArrayList<>();
		String select = "SELECT * FROM persona";
		PreparedStatement ps;
		try {
			ps = this.ctmp.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Persona tmp = new Persona(rs.getInt(1), rs.getString(2), rs.getInt(3));
				listaPersonas.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPersonas;
	}

	@Override
	public Persona obtenerPersona(int id) {
		Persona tmp = new Persona();
		String sql = "SELECT * FROM persona WHERE id ="+id;
		try {
			PreparedStatement ps = this.ctmp.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			tmp.setId(rs.getInt(1));
			tmp.setNombre(rs.getString(2));
			tmp.setEdad(rs.getInt(3));
		}
		catch (SQLException e) {
			System.out.println("Error: Clase PersonaDaoImple, metodo obtener por id");
			e.printStackTrace();
		}
		return tmp;
	}

	@Override
	public boolean actualizarPersona(Persona persona) {
		if(eliminarPersona(persona)&&registrar(persona))
			return true;
		else
			return false;
	}

	@Override
	public boolean eliminarPersona(Persona persona) {
		String delete = "DELETE FROM persona WHERE id = ?";
		Boolean eliminar = false;
		try {
			PreparedStatement tmp = this.ctmp.getConnection().prepareStatement(delete);
			tmp.setInt(1,persona.getId());
			tmp.executeUpdate();
			eliminar = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return eliminar;
	}

	public boolean registrar(Persona p) {
		String insert = "INSERT INTO persona (nombre, edad, id) VALUES(?, ?, ?)";
		Boolean registrar = false;
		try {
			PreparedStatement ps = this.ctmp.getConnection().prepareStatement(insert);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setInt(3, p.getId());
			ps.executeUpdate();
			registrar = true;
			ps.close();
			this.ctmp.getConnection().commit();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return registrar;
	}
}
