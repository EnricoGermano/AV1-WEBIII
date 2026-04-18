package com.autobots.automanager.modelo;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.TelefoneControle;
import com.autobots.automanager.entidades.Telefone;

@Component
public class AdicionadorLinkTelefone implements AdicionadorLink<Telefone> {
	@Override
	public void adicionarLink(List<Telefone> lista) {
		for (Telefone telefone : lista) {
			adicionarLink(telefone);
		}
	}

	@Override
	public void adicionarLink(Telefone telefone) {
		if (telefone == null || telefone.getId() == null) {
			return;
		}
		Link self = WebMvcLinkBuilder.linkTo(TelefoneControle.class).slash(telefone.getId()).withSelfRel();
		Link collection = WebMvcLinkBuilder.linkTo(TelefoneControle.class).withRel("telefones");
		telefone.add(self);
		telefone.add(collection);
	}
}