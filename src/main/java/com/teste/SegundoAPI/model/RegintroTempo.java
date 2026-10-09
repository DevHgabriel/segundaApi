package com.teste.SegundoAPI.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity 
@Table(name = "tb_tempo")
public class RegintroTempo {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private OffsetDateTime tempo;
    @Positive 
    private double valor;

    public RegintroTempo(){

    }

    public RegintroTempo(Long id, OffsetDateTime tempo,double valor) {
        this.id = id;
        this.tempo = tempo;
        this.valor = valor;
    }


    public Long getId() {
        return id;
    }


    public OffsetDateTime getTempo() {
        return tempo;
    }

    public double getValor(){
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
}
