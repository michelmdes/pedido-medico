package com.dasa.pedidomedico;

import com.dasa.pedidomedico.domain.*;
import com.dasa.pedidomedico.domain.enums.Sexo;
import com.dasa.pedidomedico.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class PedidoMedicoApplication implements CommandLineRunner {

	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private MedicoRepository medicoRepository;
	@Autowired
	private CategoriaExameRepository categoriaExameRepository;
	@Autowired
	private ExameRepository exameRepository;

	public static void main(String[] args) {
		SpringApplication.run(PedidoMedicoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		persistirCargaInicial();
	}

	private void persistirCargaInicial() {

		Estado est1 = new Estado("Bahia");
		Estado est2 = new Estado("São Paulo");

		Cidade c1 = new Cidade("Salvador", est1);
		Cidade c2 = new Cidade("São Paulo", est2);
		Cidade c3 = new Cidade("Osasco", est2);

		Endereco e1 = new Endereco("Av José Lopes Lázaro", "490", "Apto 165 Torre 2", "Presidente Altino", "06210030", c2);
		Endereco e2 = new Endereco("Rua Vicente Celestino", "530", "Apto 303", "Jardim", "38220834", c1);
		Endereco e3 = new Endereco("Av dos Autonomistas", "150", "Ed. Paris", "Vila Yara", "06210030", c3);

		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

		Date dtNasc = new Date();
		Paciente p1 = new Paciente("Michel Mendes", dtNasc, Sexo.MASCULINO, "Valeria Moura", "71 988716861", "michelmdes@gmail.com", "2345678", "11111111111", e1);
		Paciente p2 = new Paciente("Maria Mota", dtNasc, Sexo.FEMININO, "Creuza Mota", "71 98888888", "maria@gmail.com", "8765432", "22222222222", e2);

		pacienteRepository.saveAll(Arrays.asList(p1, p2));

		Medico m1 = new Medico("Felipe Santos Caliari", Sexo.MASCULINO, "0012", "SP", "CRM", e3);
		Medico m2 = new Medico("Alice Santos Caliari", Sexo.FEMININO, "6238", "SP", "CRM", e3);

		medicoRepository.saveAll(Arrays.asList(m1, m2));

		CategoriaExame cat1 = new CategoriaExame("Laboratório");
		CategoriaExame cat2 = new CategoriaExame("Imagem");

		Exame ex1 = new Exame("Hemograma", cat1);
		Exame ex2 = new Exame("Colesterol", cat1);
		Exame ex3 = new Exame("Ureia", cat1);
		Exame ex4 = new Exame("Creatinina", cat1);
		Exame ex5 = new Exame("Glicemia", cat1);
		Exame ex6 = new Exame("PCR", cat1);
		Exame ex7 = new Exame("Beta HCG", cat1);
		Exame ex8 = new Exame("teste do pezinho", cat1);

		Exame ex9 = new Exame("Raio X", cat2);
		Exame ex10 = new Exame("Mamografia", cat2);
		Exame ex11 = new Exame("Densitometria óssea", cat2);
		Exame ex12 = new Exame("Tomografia computadorizada", cat2);
		Exame ex13 = new Exame("Ultrassonografia", cat2);
		Exame ex14 = new Exame("Ressonância magnética", cat2);

		categoriaExameRepository.saveAll(Arrays.asList(cat1, cat2));
		exameRepository.saveAll(Arrays.asList(ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12, ex13, ex14));
	}
}
