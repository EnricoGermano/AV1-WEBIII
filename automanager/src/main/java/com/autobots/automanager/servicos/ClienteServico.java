package com.autobots.automanager.servicos;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelo.ClienteAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@Service
public class ClienteServico {
	private final ClienteRepositorio repositorio;
	private final ClienteAtualizador atualizador = new ClienteAtualizador();

	public ClienteServico(ClienteRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	public Cliente obterPorId(long id) {
		return repositorio.findById(id).orElse(null);
	}

	public List<Cliente> obterTodos() {
		return repositorio.findAll();
	}

	public void cadastrar(Cliente cliente) {
		Objects.requireNonNull(cliente);
		repositorio.save(cliente);
	}

	public void atualizar(Cliente atualizacao) {
		Long id = atualizacao.getId();
		if (id == null) {
			return;
		}
		Cliente cliente = repositorio.findById(id).orElse(null);
		if (cliente != null) {
			atualizador.atualizar(cliente, atualizacao);
			repositorio.save(cliente);
		}
	}

	public void excluir(long id) {
		repositorio.findById(id).ifPresent(repositorio::delete);
	}
}
