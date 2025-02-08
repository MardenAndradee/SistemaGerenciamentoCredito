package org.example.Despesas;

import org.example.Categorias.Categorias;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.time.LocalDate;
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

            System.out.println("Salvo com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void editar(int id, String descricao, double valor, Categorias categoria, LocalDate data){

        Despesas desp = em.find(Despesas.class, id);

        em.getTransaction().begin();

        desp.setDescricao(descricao);
        desp.setValor(valor);
        desp.setCategoria(categoria);
        desp.setDataDespesa(data);

        em.getTransaction().commit();

    }

    public Despesas buscar(int id){
        Despesas desp = em.find(Despesas.class,id);
        return desp;
    }

    public void excluir(int id){

        Despesas desp = em.find(Despesas.class,id);

        try {
            em.getTransaction().begin();
            em.remove(desp);
            em.getTransaction().commit();

            System.out.println("Excluído com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Object[]> listar(){

        List despesas = null;

        try{

            despesas = em.createQuery("SELECT d.id, d.descricao, d.valor, c.categoria, d.formaPagamento, d.nparcela, d.parcelas, d.identificador " +
                    "FROM  Despesas d, Categorias c " +
                    "WHERE d.categoria = c.id ").getResultList();

        }catch(Exception e){
            e.printStackTrace();
        }

        return despesas;

    }

    public List<Object[]> listarAgrupado(){

        List despesas = null;

        try{

            despesas = em.createQuery("SELECT d.id, d.descricao, SUM(d.valor) AS total_valor, c.categoria, d.formaPagamento, d.nparcela, d.parcelas, d.identificador " +
                    "FROM Despesas d " +
                    "JOIN Categorias c ON d.categoria = c.id " +
                    "GROUP BY d.identificador").getResultList();

        }catch(Exception e){
            e.printStackTrace();
        }

        return despesas;

    }

    //funções avulsas

    public List<Despesas> listarFiltro(String categoria, String formaPagamento, LocalDate dataInicial, LocalDate dataFinal){
        List despesas = null;

        try{

        String query = "FROM Despesas d WHERE d.categoria like :categoria and d.formaPagamento like :formaPagamento and d.dataDespesa >= :dataInicial and d.dataDespesa <= :dataFinal";

        despesas = em.createQuery(query)
                .setParameter("categoria", "%" + categoria + "%")
                .setParameter("formaPagamento", "%" + formaPagamento + "%")
                .setParameter("dataInicial",dataInicial)
                .setParameter("dataFinal",dataFinal)
                .getResultList();

        return despesas;

        }catch (NoResultException e) {
            System.out.println("\nDespesas não localizadas");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int identificador(int id){
        try{


            String query = "SELECT MAX(identificador) from Despesas";

            int identificador = (int) em.createQuery(query).getSingleResult();

            return identificador;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Despesas> buscarPorIdentificador(int identificador){

        List<Despesas> despesas = null;

        try{

            despesas = em.createQuery("FROM  Despesas d WHERE d.identificador = "+ identificador +" ").getResultList();

        }catch(Exception e){
            e.printStackTrace();
        }

        return despesas;

    }

    public void excluirPorIdentificador(int identificador){
        try{

            for(Despesas d: buscarPorIdentificador(identificador)){
                excluir(d.getId());
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
