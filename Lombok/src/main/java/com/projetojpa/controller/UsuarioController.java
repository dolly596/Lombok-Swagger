package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Usuario;
import com.projetojpa.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Usuarios", description = "API REST DE GERENCIAMENTO DE USÚARIOS")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController (UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Localiza usúario por ID")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id){
		Usuario usuario = usuarioService.buscaUsuarioId(id);
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	@Operation(summary = "Apresenta todos os usúarios")
	public ResponseEntity <List<Usuario>> buscaTodosUsuariosControl(){
		List<Usuario> Usuarios = usuarioService.buscaTodosUsuarios();
		return ResponseEntity.ok(Usuarios);
	}
	
	@PostMapping("/")
	@Operation(summary = "Cadastra um usúario")
	public ResponseEntity <Usuario> salvaUsuarioControl (@RequestBody @Valid Usuario Usuario){
		Usuario salvaUsuario = usuarioService.salvaUsuario(Usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuario);
	}
	@PutMapping ("/{id}")
	@Operation(summary = "Altera um usúario")
	public ResponseEntity<Usuario> alterarUsuarioControl (@PathVariable Long id, @RequestBody @Valid Usuario Usuario){
		Usuario alteraUsuario = usuarioService.alterarUsuario(id, Usuario);
		if(alteraUsuario != null) {
			return ResponseEntity.ok(Usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping ("/{id}")
	@Operation(summary = "Exclui um usuário")
	public ResponseEntity<String> apagaUsuarioControl (@PathVariable Long id){
		boolean apagar = usuarioService.apagarUsuario(id);
		if(apagar) {
			return ResponseEntity.ok().body("O Usuario foi excluído com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
