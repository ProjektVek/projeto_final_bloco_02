package com.generation.farmacia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.farmacia.util.TestBuilder;
import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	private static final String BASE_URL_CATEGORIA = "/categorias";
	
	@BeforeAll
	void start() {
		categoriaRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Deve cadastrar um categoria com sucesso")
	public void deveCadastrarCategoria() {
		
		//Given
		Categoria categoria = TestBuilder.criarCategoria(null, "Tecnologia");
		
		//When
		HttpEntity<Categoria> requisicao = new HttpEntity<Categoria>(categoria);
		ResponseEntity<Categoria> resposta = testRestTemplate.exchange(
				BASE_URL_CATEGORIA, HttpMethod.POST, requisicao, Categoria.class);
		
		//Then
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals("Tecnologia", resposta.getBody().getNome());
		
	}
	
}
