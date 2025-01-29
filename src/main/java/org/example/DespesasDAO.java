package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DespesasDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
    EntityManager em = emf.createEntityManager();

    public DespesasDAO(EntityManager em) {
        this.em = em;
    }

    //CRUD


    public void salvar(Despesas desp){

        try {
            em.getTransaction().begin();
            em.persist(desp);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void editar(int id,double valor){

        Despesas desp = em.find(Despesas.class, id);

        em.getTransaction().begin();

        desp.setValor(valor);

        em.getTransaction().commit();

    }

    public Despesas buscar(int id){
        Despesas desp = em.find(Despesas.class,id);
        return desp;
    }

    public void excluir(int id){

        Despesas desp = em.find(Despesas.class,id);

        em.getTransaction().begin();
        em.remove(desp);
        em.getTransaction().commit();

    }

    public List<Despesas> listar(){

        List<Despesas> despesas = null;

        try{

            despesas = em.createQuery("from Despesas d").getResultList();

        }catch(Exception e){
            e.printStackTrace();
        }

        return despesas;

    }

}
