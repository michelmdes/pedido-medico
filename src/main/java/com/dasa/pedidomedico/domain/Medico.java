package com.dasa.pedidomedico.domain;

import com.dasa.pedidomedico.domain.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Medico extends GenericDomain<Long> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private String sexo;
	@NotNull
	private String numeroConselho;
	@NotNull
	private String estadoConselho;
	@NotNull
	private String tipoConselho;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_endereco")
	private Endereco endereco;

	public Medico() {
	}

	public Medico(@NotNull String nome, @NotNull Sexo sexo, @NotNull String numeroConselho, @NotNull String estadoConselho, @NotNull String tipoConselho, Endereco endereco) {
		this.nome = nome;
		this.sexo = sexo.getCodigo();
		this.numeroConselho = numeroConselho;
		this.estadoConselho = estadoConselho;
		this.tipoConselho = tipoConselho;
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

	public Sexo getSexo() {
		return Sexo.toEnum(sexo);
	}

	public void setSexo(Sexo sexo) {
		if (sexo != null)
			this.sexo = sexo.getCodigo();
	}

	public String getNumeroConselho() {
		return numeroConselho;
	}

	public void setNumeroConselho(String numeroConselho) {
		this.numeroConselho = numeroConselho;
	}

	public String getEstadoConselho() {
		return estadoConselho;
	}

	public void setEstadoConselho(String estadoConselho) {
		this.estadoConselho = estadoConselho;
	}

	public String getTipoConselho() {
		return tipoConselho;
	}

	public void setTipoConselho(String tipoConselho) {
		this.tipoConselho = tipoConselho;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
