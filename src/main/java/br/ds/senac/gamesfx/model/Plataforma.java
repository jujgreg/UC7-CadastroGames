package br.ds.senac.gamesfx.model;

import java.time.LocalDate;

public class Plataforma {
    private int id;
    private String nomePlataforma;
    private String fabricante;
    private String data_P;
    private double valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePlataforma() {
        return nomePlataforma;
    }

    public void setNomePlataforma(String nomePlataforma) {
        this.nomePlataforma = nomePlataforma;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getData_P() {
        return data_P;
    }

    public void setData_P(String data_P) {
        this.data_P = data_P;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nomePlataforma;
    }
}
