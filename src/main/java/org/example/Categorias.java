package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public Categorias(){}

    public Categorias(int id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Categorias{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
