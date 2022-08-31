package com.tp1.controller;

import java.util.ArrayList;


import com.tp1.dao.PersonaDaoImplMySql;
import com.tp1.idao.IPersonaDao;
import com.tp1.model.Persona;
import com.tp1.vista.ViewPersona;

public class ControllerPersona {

	private ViewPersona vista = new ViewPersona();
	
	public ControllerPersona() {
		
	}
	
	public boolean registrar(Persona persona) {
		IPersonaDao dao = new PersonaDaoImplMySql();
		return dao.registrar(persona);
	}
	
	public boolean actualizar(Persona persona) {
		IPersonaDao dao = new PersonaDaoImplMySql();
		return dao.actualizarPersona(persona);
	}
	
	public void eliminar(Persona persona) {
		IPersonaDao dao = new PersonaDaoImplMySql();
		dao.eliminarPersona(persona);
	}
	
	public void listarPersonas(){
		IPersonaDao dao = new PersonaDaoImplMySql();
		vista.listarPersonas(dao.obtenerPersonas());
	}
	
	public ArrayList<Persona> obtenerPersonas(){
		IPersonaDao dao = new PersonaDaoImplMySql();
		return dao.obtenerPersonas();
	}
	
	
	
	
	
	
}
