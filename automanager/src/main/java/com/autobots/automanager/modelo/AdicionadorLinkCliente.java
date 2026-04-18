package com.autobots.automanager.modelo;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.ClienteControle;
import com.autobots.automanager.entidades.Cliente;

@Component
public class AdicionadorLinkCliente implements AdicionadorLink<Cliente> {
	@Override
	public void adicionarLink(List<Cliente> lista) {
		for (Cliente cliente : lista) {
			adicionarLink(cliente);
		}
	}

	@Override
	public void adicionarLink(Cliente cliente) {
		if (cliente == null || cliente.getId() == null) {
			return;
		}
		Link self = WebMvcLinkBuilder.linkTo(ClienteControle.class).slash(cliente.getId()).withSelfRel();
		Link collection = WebMvcLinkBuilder.linkTo(ClienteControle.class).withRel("clientes");
		cliente.add(self);
		cliente.add(collection);
		if (cliente.getEndereco() != null) {
			cliente.getEndereco().add(WebMvcLinkBuilder.linkTo(com.autobots.automanager.controles.EnderecoControle.class).slash(cliente.getEndereco().getId()).withSelfRel());
		}
		if (cliente.getDocumentos() != null) {
			for (var documento : cliente.getDocumentos()) {
				if (documento != null && documento.getId() != null) {
					documento.add(WebMvcLinkBuilder.linkTo(com.autobots.automanager.controles.DocumentoControle.class).slash(documento.getId()).withSelfRel());
				}
			}
		}
		if (cliente.getTelefones() != null) {
			for (var telefone : cliente.getTelefones()) {
				if (telefone != null && telefone.getId() != null) {
					telefone.add(WebMvcLinkBuilder.linkTo(com.autobots.automanager.controles.TelefoneControle.class).slash(telefone.getId()).withSelfRel());
				}
			}
		}
	}
}