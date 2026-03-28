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

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {
	@Autowired
	private DocumentoRepositorio repositorio;

	@GetMapping("/{id}")
	public Documento obterDocumento(@PathVariable Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@GetMapping("/todos")
	public List<Documento> obterDocumentos() {
		return repositorio.findAll();
	}

	@PostMapping("/cadastro")
	public void cadastrarDocumento(@RequestBody Documento documento) {
		repositorio.save(documento);
	}

	@PutMapping("/atualizar")
	public void atualizarDocumento(@RequestBody Documento atualizacao) {
		Documento documento = repositorio.findById(atualizacao.getId()).orElse(null);
		if (documento != null) {
			DocumentoAtualizador atualizador = new DocumentoAtualizador();
			atualizador.atualizar(documento, atualizacao);
			repositorio.save(documento);
		}
	}

	@DeleteMapping("/excluir/{id}")
	public void excluirDocumento(@PathVariable Long id) {
		repositorio.findById(id).ifPresent(repositorio::delete);
	}
}
