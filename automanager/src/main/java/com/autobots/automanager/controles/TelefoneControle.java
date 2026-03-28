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

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
	@Autowired
	private TelefoneRepositorio repositorio;

	@GetMapping("/{id}")
	public Telefone obterTelefone(@PathVariable Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@GetMapping("/todos")
	public List<Telefone> obterTelefones() {
		return repositorio.findAll();
	}

	@PostMapping("/cadastro")
	public void cadastrarTelefone(@RequestBody Telefone telefone) {
		repositorio.save(telefone);
	}

	@PutMapping("/atualizar")
	public void atualizarTelefone(@RequestBody Telefone atualizacao) {
		Telefone telefone = repositorio.findById(atualizacao.getId()).orElse(null);
		if (telefone != null) {
			TelefoneAtualizador atualizador = new TelefoneAtualizador();
			atualizador.atualizar(telefone, atualizacao);
			repositorio.save(telefone);
		}
	}

	@DeleteMapping("/excluir/{id}")
	public void excluirTelefone(@PathVariable Long id) {
		repositorio.findById(id).ifPresent(repositorio::delete);
	}
}
