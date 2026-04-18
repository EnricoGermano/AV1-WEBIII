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

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.AdicionadorLinkEndereco;
import com.autobots.automanager.servicos.EnderecoServico;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControle {
	private final EnderecoServico servico;
	private final AdicionadorLinkEndereco adicionadorLink;

	public EnderecoControle(EnderecoServico servico, AdicionadorLinkEndereco adicionadorLink) {
		this.servico = servico;
		this.adicionadorLink = adicionadorLink;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> obterEndereco(@PathVariable long id) {
		Endereco endereco = servico.obterPorId(id);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}
		adicionadorLink.adicionarLink(endereco);
		return ResponseEntity.ok(endereco);
	}

	@GetMapping
	public ResponseEntity<List<Endereco>> obterEnderecos() {
		List<Endereco> enderecos = servico.obterTodos();
		adicionadorLink.adicionarLink(enderecos);
		return ResponseEntity.ok(enderecos);
	}

	@PostMapping
	public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody Endereco endereco) {
		if (endereco.getId() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		servico.cadastrar(endereco);
		adicionadorLink.adicionarLink(endereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Endereco> atualizarEndereco(@PathVariable long id, @RequestBody Endereco atualizacao) {
		Endereco endereco = servico.obterPorId(id);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}
		atualizacao.setId(id);
		servico.atualizar(atualizacao);
		Endereco atualizado = servico.obterPorId(id);
		adicionadorLink.adicionarLink(atualizado);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirEndereco(@PathVariable long id) {
		Endereco endereco = servico.obterPorId(id);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}
		servico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
