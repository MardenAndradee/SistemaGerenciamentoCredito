package org.example.Despesas;

import org.example.Categorias.Categorias;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Despesas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    private double valor;
    @ManyToOne
    @JoinColumn(name = "categoria")
    private Categorias categoria;
    private String formaPagamento;
    private LocalDate dataDespesa;
    private int parcelas;
    private int nparcela;
    private int identificador;
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

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
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
        return identificador;
    }

    public void setReceita(int receita) {
        this.identificador = receita;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Despesas(){}

    public Despesas(int id, String descricao, double valor, Categorias categoria, String formaPagamento, LocalDate dataDespesa, int parcelas, int nparcela, int identificador, int idUsuario) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.formaPagamento = formaPagamento;
        this.dataDespesa = dataDespesa;
        this.parcelas = parcelas;
        this.nparcela = nparcela;
        this.identificador = identificador;
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
