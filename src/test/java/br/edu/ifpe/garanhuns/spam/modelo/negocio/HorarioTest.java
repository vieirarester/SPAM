/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.modelo.negocio;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author João Witor
 */
public class HorarioTest {
    
    public HorarioTest() {
    }

    @Test
    public void testRetornoHorasDaFormaCorreta() {
        //Thu Jan 01 04:00:00 GMT-03:00 1970
        Horario horarioFalso = new Horario(1,"Segunda","Thu Jan 01 04:00:00 GMT-03:00 1970","Thu Jan 01 05:00:00 GMT-03:00 1970");
        Assert.assertEquals("04:00", horarioFalso.getHoraInicio());
        Assert.assertEquals("05:00", horarioFalso.getHoraFim());
    }


    @Test
    public void testValidarHoraQuandoNulo() {
        Horario horarioFalso = new Horario(1,"Segunda",null,null);
        String horaResultado = horarioFalso.getHoraInicio();
        Assert.assertFalse(horarioFalso.validarHora(horaResultado));
    }
    
    @Test
    public void testValidarHoraQuandoVazio() {
        Horario horarioFalso = new Horario();
        String horaResultado = horarioFalso.getHoraInicio();
        Assert.assertFalse(horarioFalso.validarHora(horaResultado));
    }
    
    @Test
    public void testValidarHoraQuandoCorreto() {
        Horario horarioFalso = new Horario(1,"Segunda","Thu Jan 01 04:00:00 GMT-03:00 1970","Thu Jan 01 05:00:00 GMT-03:00 1970");
        String horaResultado = horarioFalso.getHoraInicio();
        Assert.assertTrue(horarioFalso.validarHora(horaResultado));
    }
}
