package com.autobots.automanager;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@SpringBootApplication
public class AutomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Component
	public static class Runner implements ApplicationRunner {
		@Autowired
		public ClienteRepositorio repositorio;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			Calendar calendario = Calendar.getInstance();
			calendario.set(2004, 07, 24);

			Cliente cliente = new Cliente();
			cliente.setNome("Enrico de Chiara Germano");
			cliente.setDataCadastro(Calendar.getInstance().getTime());
			cliente.setDataNascimento(calendario.getTime());
			cliente.setNomeSocial("Enrica");
			
			Telefone telefone = new Telefone();
			telefone.setDdd("12");
			telefone.setNumero("981234579");
			cliente.getTelefones().add(telefone);
			
			Endereco endereco = new Endereco();
			endereco.setEstado("SP");
			endereco.setCidade("São Paulo");
			endereco.setBairro("Santana");
			endereco.setRua("Teodoro Sampaio");
			endereco.setNumero("337");
			endereco.setCodigoPostal("12243500");
			endereco.setInformacoesAdicionais("Depois da quadra");
			cliente.setEndereco(endereco);
			
			Documento rg = new Documento();
			rg.setTipo("RG");
			rg.setNumero("42");
			
			Documento cpf = new Documento();
			cpf.setTipo("CPF");
			cpf.setNumero("424242424242");
			
			cliente.getDocumentos().add(rg);
			cliente.getDocumentos().add(cpf);
			
			repositorio.save(cliente);
		}
	}

}
