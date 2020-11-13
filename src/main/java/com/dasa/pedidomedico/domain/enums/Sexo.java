package com.dasa.pedidomedico.domain.enums;

public enum Sexo {

    MASCULINO("M", "Masculino"),
    FEMININO("F", "Feminino"),
    OUTRO("O", "Outro");

    private String codigo;
    private String descricao;

    private Sexo(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Sexo toEnum(String codigo) {
        if (codigo == null)
            return null;

        for (Sexo s : Sexo.values()) {
            if (s.getCodigo().equalsIgnoreCase(codigo))
                return s;
        }

        throw new IllegalArgumentException("O código '" + codigo + "' não existe no enum Sexo");
    }

    @Override
    public String toString() {
        return this.getCodigo();
    }
}
