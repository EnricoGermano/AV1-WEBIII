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

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.AdicionadorLinkDocumento;
import com.autobots.automanager.servicos.DocumentoServico;

@RestController
@RequestMapping("/documentos")
public class DocumentoControle {
	private final DocumentoServico servico;
	private final AdicionadorLinkDocumento adicionadorLink;

	public DocumentoControle(DocumentoServico servico, AdicionadorLinkDocumento adicionadorLink) {
		this.servico = servico;
		this.adicionadorLink = adicionadorLink;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Documento> obterDocumento(@PathVariable long id) {
		Documento documento = servico.obterPorId(id);
		if (documento == null) {
			return ResponseEntity.notFound().build();
		}
		adicionadorLink.adicionarLink(documento);
		return ResponseEntity.ok(documento);
	}

	@GetMapping
	public ResponseEntity<List<Documento>> obterDocumentos() {
		List<Documento> documentos = servico.obterTodos();
		adicionadorLink.adicionarLink(documentos);
		return ResponseEntity.ok(documentos);
	}

	@PostMapping
	public ResponseEntity<Documento> cadastrarDocumento(@RequestBody Documento documento) {
		if (documento.getId() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		servico.cadastrar(documento);
		adicionadorLink.adicionarLink(documento);
		return ResponseEntity.status(HttpStatus.CREATED).body(documento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Documento> atualizarDocumento(@PathVariable long id, @RequestBody Documento atualizacao) {
		Documento documento = servico.obterPorId(id);
		if (documento == null) {
			return ResponseEntity.notFound().build();
		}
		atualizacao.setId(id);
		servico.atualizar(atualizacao);
		Documento atualizado = servico.obterPorId(id);
		adicionadorLink.adicionarLink(atualizado);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirDocumento(@PathVariable long id) {
		Documento documento = servico.obterPorId(id);
		if (documento == null) {
			return ResponseEntity.notFound().build();
		}
		servico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
