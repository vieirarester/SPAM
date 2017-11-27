/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.negocio;

import br.ifpe.garanhuns.spam.modelo.negocio.Aluno;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20141D12GR0076
 */
public class AlunoTest {

    public AlunoTest() {
    }

    @Test
    public void testSenhaCerta() {
        String senhaCerta = "oi";
        String senhaTeste = "oi";

        Aluno aluno = new Aluno();
        aluno.setSenha(senhaCerta);

        assertTrue(aluno.autenticar(senhaTeste));
    }

    @Test
    public void testSenhaErrada() {
        String senhaCerta = "oi";
        String senhaTeste = "oib";

        Aluno aluno = new Aluno();
        aluno.setSenha(senhaCerta);

        assertFalse(aluno.autenticar(senhaTeste));
    }

    @Test
    public void testSenhaVazia() {
        String senhaCerta = "oi";
        String senhaTeste = "";

        Aluno aluno = new Aluno();
        aluno.setSenha(senhaCerta);

        assertFalse(aluno.autenticar(senhaTeste));
    }

    @Test
    public void testSenhaNula() {
        String senhaCerta = "oi";
        String senhaTeste = null;

        Aluno aluno = new Aluno();
        aluno.setSenha(senhaCerta);

        assertFalse(aluno.autenticar(senhaTeste));
    }

}
