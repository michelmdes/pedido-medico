package com.dasa.pedidomedico.domain;

import com.dasa.pedidomedico.domain.enums.Sexo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Paciente extends GenericDomain<Long> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private Date dtNascimento;
	@NotNull
	private String sexo;
	private String nomeMae;
	private String telefone;
	private String email;
	private String rg;
	@Size(min = 11, max = 11)
	private String cpf;

	@ManyToOne
	@JoinColumn(name="id_endereco")
	private Endereco endereco;
	
	public Paciente() {
	}

	public Paciente(@NotNull String nome, @NotNull Date dtNascimento, @NotNull Sexo sexo, String nomeMae, String telefone,
					String email, String rg, @Size(min = 11, max = 11) String cpf, Endereco endereco) {
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.sexo = sexo.getCodigo();
		this.nomeMae = nomeMae;
		this.telefone = telefone;
		this.email = email;
		this.rg = rg;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Sexo getSexo() {
		return Sexo.toEnum(sexo);
	}

	public void setSexo(Sexo sexo) {
		if (sexo != null)
			this.sexo = sexo.getCodigo();
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
