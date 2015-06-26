package com.model;

public enum Role {
	ADMINISTRADOR(1), 
	OPERADOR(2),
	CLIENTE(3);
	
	int id;
	
	private Role(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
