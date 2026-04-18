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

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelo.AdicionadorLinkCliente;
import com.autobots.automanager.servicos.ClienteServico;

@RestController
@RequestMapping("/clientes")
public class ClienteControle {
	private final ClienteServico servico;
	private final AdicionadorLinkCliente adicionadorLink;

	public ClienteControle(ClienteServico servico, AdicionadorLinkCliente adicionadorLink) {
		this.servico = servico;
		this.adicionadorLink = adicionadorLink;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obterCliente(@PathVariable long id) {
		Cliente cliente = servico.obterPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		adicionadorLink.adicionarLink(cliente);
		return ResponseEntity.ok(cliente);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> obterClientes() {
		List<Cliente> clientes = servico.obterTodos();
		adicionadorLink.adicionarLink(clientes);
		return ResponseEntity.ok(clientes);
	}

	@PostMapping
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
		if (cliente.getId() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		servico.cadastrar(cliente);
		adicionadorLink.adicionarLink(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable long id, @RequestBody Cliente atualizacao) {
		Cliente cliente = servico.obterPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		atualizacao.setId(id);
		servico.atualizar(atualizacao);
		Cliente atualizado = servico.obterPorId(id);
		adicionadorLink.adicionarLink(atualizado);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirCliente(@PathVariable long id) {
		Cliente cliente = servico.obterPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		servico.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/resposta")
	public int resposta() {
		return 42;
	}
}
