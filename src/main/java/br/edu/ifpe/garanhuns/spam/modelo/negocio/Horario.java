package br.edu.ifpe.garanhuns.spam.modelo.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ester
 */
@Table
@Entity
public class Horario {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String dia;
    @Column
    private String horaInicio;
    @Column
    private String horaFim;

    public Horario() {

    }

    public Horario(long id, String dia, String horaInicio, String horaFim) {
        this.id = id;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public long getId() {
        return id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        //Thu Jan 01 04:00:00 GMT-03:00 1970
        String horaResultado = horaInicio;
        try {
            if (validarHora(horaInicio)) {
                String[] array = horaInicio.split(" ");
                horaResultado = array[3];
                String[] array2 = horaResultado.split(":");
                horaResultado = ""+array2[0]+":"+array2[1]; 
            }
        } catch (Exception e) {
        }
        return horaResultado;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        String horaResultado = horaFim;
        try {
            if (validarHora(horaFim)) {
                String[] array = horaFim.split(" ");
                horaResultado = array[3];
                String[] array2 = horaResultado.split(":");
                horaResultado = ""+array2[0]+":"+array2[1]; 
            }
        } catch (Exception e) {
        }
        return horaResultado;
    }

    public Boolean validarHora(String horaResultado) {
        if (horaResultado == null || horaResultado.isEmpty()) {
            return false;
        }
        return true;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

}
