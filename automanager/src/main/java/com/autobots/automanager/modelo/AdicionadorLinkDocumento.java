package com.autobots.automanager.modelo;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.DocumentoControle;
import com.autobots.automanager.entidades.Documento;

@Component
public class AdicionadorLinkDocumento implements AdicionadorLink<Documento> {
	@Override
	public void adicionarLink(List<Documento> lista) {
		for (Documento documento : lista) {
			adicionarLink(documento);
		}
	}

	@Override
	public void adicionarLink(Documento documento) {
		if (documento == null || documento.getId() == null) {
			return;
		}
		Link self = WebMvcLinkBuilder.linkTo(DocumentoControle.class).slash(documento.getId()).withSelfRel();
		Link collection = WebMvcLinkBuilder.linkTo(DocumentoControle.class).withRel("documentos");
		documento.add(self);
		documento.add(collection);
	}
}