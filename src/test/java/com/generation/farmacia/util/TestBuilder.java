package com.generation.farmacia.util;

import com.generation.farmacia.model.Categoria;

public class TestBuilder {
	
	public static Categoria criarCategoria(Long id, String nome) {
		Categoria categoria = new Categoria();
		categoria.setId(id);
		categoria.setNome(nome);
		return categoria;
	}

}
