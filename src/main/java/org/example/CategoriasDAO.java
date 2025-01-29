package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class CategoriasDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
    EntityManager em = emf.createEntityManager();

    public CategoriasDAO(EntityManager em) {
        this.em = em;
    }

    //CRUD


    public void salvar(Categorias categ){

        try {
            em.getTransaction().begin();
            em.persist(categ);
            em.getTransaction().commit();

            System.out.println("Salvo com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void editar(int id, String categ){

        Categorias categorias = em.find(Categorias.class, id);

        em.getTransaction().begin();

        categorias.setCategoria(categ);

        em.getTransaction().commit();

    }

    public Categorias buscar(int id){
        Categorias categ = em.find(Categorias.class,id);
        return categ;
    }

    public void excluir(int id){

        Categorias categ = em.find(Categorias.class,id);

        try {
            em.getTransaction().begin();
            em.remove(categ);
            em.getTransaction().commit();

            System.out.println("Excluído com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Categorias> listar(){

        List<Categorias> categorias = null;

        try{

            categorias = em.createQuery("from Categorias c").getResultList();

        }catch(Exception e){
            e.printStackTrace();
        }

        return categorias;

    }


}
