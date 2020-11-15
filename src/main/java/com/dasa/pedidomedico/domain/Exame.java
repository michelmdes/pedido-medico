package com.dasa.pedidomedico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Exame extends GenericDomain<Long> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nome;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private CategoriaExame categoriaExame;

	@JsonIgnore
	@ManyToMany(mappedBy="exames")
	private List<Pedido> pedidos = new ArrayList<>();

	public Exame() {
	}

	public Exame(@NotNull String nome, @NotNull CategoriaExame categoriaExame) {
		this.nome = nome;
		this.categoriaExame = categoriaExame;
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

	public CategoriaExame getCategoriaExame() {
		return categoriaExame;
	}

	public void setCategoriaExame(CategoriaExame categoriaExame) {
		this.categoriaExame = categoriaExame;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
