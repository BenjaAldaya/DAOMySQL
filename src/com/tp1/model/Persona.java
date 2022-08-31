package com.tp1.model;

public class Persona {
	private int id;
	private int edad;
	private String nombre;
	
	public Persona(int id, String nombre, int edad) {
		this.id = id;
		this.edad = edad;
		this.nombre = nombre;
	}

	public Persona() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		return this.id+", "+this.nombre+", "+this.edad+".";
	}
	
	
}
