package com.dasa.pedidomedico.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Cidade extends GenericDomain<Long> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nome;

	@NotNull
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;
	
	public Cidade() {
	}

	public Cidade(@NotNull String nome, @NotNull Estado estado) {
		this.nome = nome;
		this.estado = estado;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
