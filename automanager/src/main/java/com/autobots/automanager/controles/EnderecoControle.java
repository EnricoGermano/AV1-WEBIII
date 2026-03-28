package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {
	@Autowired
	private EnderecoRepositorio repositorio;

	@GetMapping("/{id}")
	public Endereco obterEndereco(@PathVariable Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@GetMapping("/todos")
	public List<Endereco> obterEnderecos() {
		return repositorio.findAll();
	}

	@PostMapping("/cadastro")
	public void cadastrarEndereco(@RequestBody Endereco endereco) {
		repositorio.save(endereco);
	}

	@PutMapping("/atualizar")
	public void atualizarEndereco(@RequestBody Endereco atualizacao) {
		Endereco endereco = repositorio.findById(atualizacao.getId()).orElse(null);
		if (endereco != null) {
			EnderecoAtualizador atualizador = new EnderecoAtualizador();
			atualizador.atualizar(endereco, atualizacao);
			repositorio.save(endereco);
		}
	}

	@DeleteMapping("/excluir/{id}")
	public void excluirEndereco(@PathVariable Long id) {
		repositorio.findById(id).ifPresent(repositorio::delete);
	}
}
