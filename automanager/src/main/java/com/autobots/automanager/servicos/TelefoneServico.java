package com.autobots.automanager.servicos;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@Service
public class TelefoneServico {
	private final TelefoneRepositorio repositorio;
	private final TelefoneAtualizador atualizador = new TelefoneAtualizador();

	public TelefoneServico(TelefoneRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	public Telefone obterPorId(long id) {
		return repositorio.findById(id).orElse(null);
	}

	public List<Telefone> obterTodos() {
		return repositorio.findAll();
	}

	public void cadastrar(Telefone telefone) {
		Objects.requireNonNull(telefone);
		repositorio.save(telefone);
	}

	public void atualizar(Telefone atualizacao) {
		Long id = atualizacao.getId();
		if (id == null) {
			return;
		}
		Telefone telefone = repositorio.findById(id).orElse(null);
		if (telefone != null) {
			atualizador.atualizar(telefone, atualizacao);
			repositorio.save(telefone);
		}
	}

	public void excluir(long id) {
		repositorio.findById(id).ifPresent(repositorio::delete);
	}
}
