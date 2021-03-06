package com.lucianoortizsilva.crud.configuracao;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lucianoortizsilva.crud.cliente.model.Cliente;
import com.lucianoortizsilva.crud.cliente.repository.ClienteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile(value = "test")
public class ProfileTestConfig {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public void inicializar() {
		log.info("################ Profile=test inicializado ################");
		final Cliente clientes[] = {
				new Cliente("Luciano Ortiz", "84365299877", LocalDate.of(1984, 01, 31), "luciano@gmail.com", this.bCryptPasswordEncoder.encode("12345")),
				new Cliente("Liziane Ortiz", "65464465454", LocalDate.of(1989, 06, 8), "liziane@gmail.com", this.bCryptPasswordEncoder.encode("12345")),
				new Cliente("Mariana Ortiz", "12548664545", LocalDate.of(2011, 11, 28), "mariana@gmail.com", this.bCryptPasswordEncoder.encode("12345")),
				new Cliente("Rafaela Ortiz", "46544557554", LocalDate.of(2011, 07, 28), "rafaela@gmail.com", this.bCryptPasswordEncoder.encode("12345")),
				new Cliente("Mikaela Ortiz", "78895455624", LocalDate.of(1990, 05, 19), "mikaela@gmail.com", this.bCryptPasswordEncoder.encode("12345")), };

		log.info("Populando a base com dados de [CLIENTE]");
		final List<Cliente> clientesCadastrados = this.clienteRepository.saveAll(Arrays.asList(clientes));
		clientesCadastrados.forEach(c -> log.info("Cliente: {}", c));

	}

}