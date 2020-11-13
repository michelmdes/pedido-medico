package com.dasa.pedidomedico;

import com.dasa.pedidomedico.domain.Paciente;
import com.dasa.pedidomedico.domain.enums.Sexo;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PacienteTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void naoDeveAceitarPacienteComCpfInvalido() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("Teste");
        paciente.setDtNascimento(new Date());
        paciente.setSexo(Sexo.MASCULINO);
        paciente.setCpf("123456789");
        Set<ConstraintViolation<Paciente>> violations = validator.validate(paciente);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void deveAceitarPacienteComCpfValido() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("Teste");
        paciente.setDtNascimento(new Date());
        paciente.setSexo(Sexo.MASCULINO);
        paciente.setCpf("12345678900");
        Set<ConstraintViolation<Paciente>> violations = validator.validate(paciente);
        assertTrue(violations.isEmpty());
    }
}
