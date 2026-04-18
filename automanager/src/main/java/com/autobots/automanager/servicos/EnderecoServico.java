package com.autobots.automanager.servicos;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@Service
public class EnderecoServico {
	private final EnderecoRepositorio repositorio;
	private final EnderecoAtualizador atualizador = new EnderecoAtualizador();

	public EnderecoServico(EnderecoRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	public Endereco obterPorId(long id) {
		return repositorio.findById(id).orElse(null);
	}

	public List<Endereco> obterTodos() {
		return repositorio.findAll();
	}

	public void cadastrar(Endereco endereco) {
		Objects.requireNonNull(endereco);
		repositorio.save(endereco);
	}

	public void atualizar(Endereco atualizacao) {
		Long id = atualizacao.getId();
		if (id == null) {
			return;
		}
		Endereco endereco = repositorio.findById(id).orElse(null);
		if (endereco != null) {
			atualizador.atualizar(endereco, atualizacao);
			repositorio.save(endereco);
		}
	}

	public void excluir(long id) {
		repositorio.findById(id).ifPresent(repositorio::delete);
	}
}
