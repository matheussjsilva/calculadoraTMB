package com.example.calcTmb.model.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS_TMB")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_gen")
    @SequenceGenerator(name = "usuario_id_gen", sequenceName = "user_id_s", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "peso")
    private double peso;

    @Column(name = "altura")
    private double altura;

    @Column(name = "nivel_atividade")
    private String nivelAtividade;

    @Column(name = "tmb")
    private double tmb;

    @Column(name = "calorias_manter_peso")
    private double manterPeso;

    @Column(name = "calorias_perder_peso")
    private double perderPeso;

    @Column(name = "data_calculo")
    private LocalDateTime dataCalculo;

    public Usuarios() {
        this.dataCalculo = LocalDateTime.now();
    }

    public Usuarios(String nome, int idade, String sexo, double peso, double altura, String nivelAtividade) {

        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.nivelAtividade = nivelAtividade;
        /*
         * this.tmb = tmb;
         * this.manterPeso = manterPeso;
         * this.perderPeso = perderPeso;
         */
        this.dataCalculo = LocalDateTime.now();
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getNivelAtividade() {
        return nivelAtividade;
    }

    public void setNivelAtividade(String nivelAtividade) {
        this.nivelAtividade = nivelAtividade;
    }

    public double getTmb() {
        return tmb;
    }

    public void setTmb(double tmb) {
        this.tmb = tmb;
    }

    public double getManterPeso() {
        return manterPeso;
    }

    public void setManterPeso(double manterPeso) {
        this.manterPeso = manterPeso;
    }

    public double getPerderPeso() {
        return perderPeso;
    }

    public void setPerderPeso(double perderPeso) {
        this.perderPeso = perderPeso;
    }

    public LocalDateTime getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(LocalDateTime dataCalculo) {
        this.dataCalculo = dataCalculo;
    }

}
