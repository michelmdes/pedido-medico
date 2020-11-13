package com.dasa.pedidomedico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido extends GenericDomain<Long> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Date dtValidade;
	private String observacao;

	@NotNull
	@ManyToOne
	@JoinColumn(name="id_paciente")
	private Paciente paciente;

	@NotNull
	@ManyToOne
	@JoinColumn(name="id_medico")
	private Medico medico;

	@ManyToMany
	@JoinTable(name = "PEDIDO_EXAME",
			joinColumns = @JoinColumn(name = "id_pedido"),
			inverseJoinColumns = @JoinColumn(name = "id_exame")
	)
	private List<Exame> exames = new ArrayList<>();

	public Pedido() {
	}

	public Pedido(@NotNull Date dtValidade, String observacao, @NotNull Paciente paciente, @NotNull Medico medico) {
		this.dtValidade = dtValidade;
		this.observacao = observacao;
		this.paciente = paciente;
		this.medico = medico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtValidade() {
		return dtValidade;
	}

	public void setDtValidade(Date dtValidade) {
		this.dtValidade = dtValidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

}
