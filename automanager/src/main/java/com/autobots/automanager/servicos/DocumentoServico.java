package com.autobots.automanager.servicos;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@Service
public class DocumentoServico {
	private final DocumentoRepositorio repositorio;
	private final DocumentoAtualizador atualizador = new DocumentoAtualizador();

	public DocumentoServico(DocumentoRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	public Documento obterPorId(long id) {
		return repositorio.findById(id).orElse(null);
	}

	public List<Documento> obterTodos() {
		return repositorio.findAll();
	}

	public void cadastrar(Documento documento) {
		Objects.requireNonNull(documento);
		repositorio.save(documento);
	}

	public void atualizar(Documento atualizacao) {
		Long id = atualizacao.getId();
		if (id == null) {
			return;
		}
		Documento documento = repositorio.findById(id).orElse(null);
		if (documento != null) {
			atualizador.atualizar(documento, atualizacao);
			repositorio.save(documento);
		}
	}

	public void excluir(long id) {
		repositorio.findById(id).ifPresent(repositorio::delete);
	}
}
