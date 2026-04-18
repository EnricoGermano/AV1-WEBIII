package com.autobots.automanager.controles;

import java.util.List;

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

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.AdicionadorLinkTelefone;
import com.autobots.automanager.servicos.TelefoneServico;

@RestController
@RequestMapping("/telefones")
public class TelefoneControle {
	private final TelefoneServico servico;
	private final AdicionadorLinkTelefone adicionadorLink;

	public TelefoneControle(TelefoneServico servico, AdicionadorLinkTelefone adicionadorLink) {
		this.servico = servico;
		this.adicionadorLink = adicionadorLink;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Telefone> obterTelefone(@PathVariable long id) {
		Telefone telefone = servico.obterPorId(id);
		if (telefone == null) {
			return ResponseEntity.notFound().build();
		}
		adicionadorLink.adicionarLink(telefone);
		return ResponseEntity.ok(telefone);
	}

	@GetMapping
	public ResponseEntity<List<Telefone>> obterTelefones() {
		List<Telefone> telefones = servico.obterTodos();
		adicionadorLink.adicionarLink(telefones);
		return ResponseEntity.ok(telefones);
	}

	@PostMapping
	public ResponseEntity<Telefone> cadastrarTelefone(@RequestBody Telefone telefone) {
		if (telefone.getId() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		servico.cadastrar(telefone);
		adicionadorLink.adicionarLink(telefone);
		return ResponseEntity.status(HttpStatus.CREATED).body(telefone);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Telefone> atualizarTelefone(@PathVariable long id, @RequestBody Telefone atualizacao) {
		Telefone telefone = servico.obterPorId(id);
		if (telefone == null) {
			return ResponseEntity.notFound().build();
		}
		atualizacao.setId(id);
		servico.atualizar(atualizacao);
		Telefone atualizado = servico.obterPorId(id);
		adicionadorLink.adicionarLink(atualizado);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirTelefone(@PathVariable long id) {
		Telefone telefone = servico.obterPorId(id);
		if (telefone == null) {
			return ResponseEntity.notFound().build();
		}
		servico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
