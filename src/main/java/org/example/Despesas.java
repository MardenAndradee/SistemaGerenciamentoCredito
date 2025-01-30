package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Despesas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    private double valor;
    private String categoria;
    private String formaPagamento;
    private LocalDate dataDespesa;
    private int parcelas;
    private int nparcela;
    private int receita;
    private int idUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDate getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(LocalDate dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public int getNparcela() {
        return nparcela;
    }

    public void setNparcela(int nparcela) {
        this.nparcela = nparcela;
    }

    public int getReceita() {
        return receita;
    }

    public void setReceita(int receita) {
        this.receita = receita;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Despesas(){}

    public Despesas(int id, String descricao, double valor, String categoria, String formaPagamento, LocalDate dataDespesa, int parcelas, int nparcela, int receita, int idUsuario) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.formaPagamento = formaPagamento;
        this.dataDespesa = dataDespesa;
        this.parcelas = parcelas;
        this.nparcela = nparcela;
        this.receita = receita;
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Despesas{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria='" + categoria + '\'' +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", dataDespesa=" + dataDespesa +
                ", parcelas=" + parcelas +
                ", nparcela=" + nparcela +
                '}';
    }
}
