package org.example;


import org.example.Categorias.Categorias;
import org.example.Categorias.CategoriasDAO;
import org.example.Despesas.Despesas;
import org.example.Despesas.DespesasDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();

        Despesas despesas = null;
        boolean menus = true;
        Scanner scanner = new Scanner(System.in);
        DespesasDAO despesasDAO = new DespesasDAO(em);
        CategoriasDAO categoriasDAO = new CategoriasDAO(em);
        Categorias categorias = null;

        //boolean menus = false;
        //fazer função de login, se retornar = true boolean menus = true;

        while (menus == true) {
            System.out.println("""
                    \n1-Funções Despesa
                    2-Funções Receita
                    3-Funções categoria
                    """);

            int menu = scanner.nextInt();

            if (menu == 1) {
                //BLOCO DESPESAS
                boolean menusDespesa = true;

                while (menusDespesa == true) {
                    System.out.println("""
                            \nFUNÇÕES DESPESA
                            \n1- Cadastrar Despesa
                            2- Consultar parcelas
                            3- Remover parcela
                            4- Remover despesa
                            5- Editar Despesa
                            6- Pesquisa com filtros
                            
                            """);

                    int menuDespesa = scanner.nextInt();

                    if (menuDespesa == 1) {
                        System.out.println("debito(dinheiro) ou credito?");
                        scanner.nextLine();
                        String forma = scanner.nextLine();

                        int parcelas = 1;
                        int nparcela = 1;

                        if (forma.equalsIgnoreCase("credito")) {
                            System.out.println("Em quantas parcelas: ");
                            parcelas = scanner.nextInt();
                            scanner.nextLine();
                        }

                        System.out.println("Qual a categoria da compra: ");
                        for (Categorias c : categoriasDAO.listar()) {
                            System.out.println("\nID: " + c.getId());
                            System.out.println("Categoria: " + c.getCategoria());
                        }
                        int idCategoria = scanner.nextInt();
                        Categorias categoria = categoriasDAO.buscar(idCategoria);

                        System.out.println("Nome despesa? ");
                        String descricao = scanner.nextLine();

                        System.out.println("Qual o valor da compra? ");
                        double valor = scanner.nextDouble();

                        System.out.println("Informe a data da despesa: (dd/MM/yy)");
                        String dataInformada = scanner.nextLine();
                        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataDespesa = LocalDate.parse(dataInformada, formatar);

                        valor = valor / parcelas;

                        if (forma.equalsIgnoreCase("credito")) {
                            dataDespesa = dataDespesa.plusMonths(1);
                        }

                        //o intuito do identificador é agrupar todas as despesas parceladas.
                        int identificador = despesasDAO.identificador(0) + 1;

                        for (int i = 1; i <= parcelas; i++) {

                            despesas = new Despesas(0, descricao, valor, categoria, forma, dataDespesa, parcelas, nparcela, identificador, 0);

                            despesasDAO.salvar(despesas);

                            nparcela++;
                            dataDespesa = dataDespesa.plusMonths(1);
                        }

                    }
                    if (menuDespesa == 2) {

                        for (Object[] obj : despesasDAO.listar()) {
                            System.out.println("\nID: " + obj[0]);
                            System.out.println("Descrição: " + obj[1]);
                            System.out.println("Valor: " + obj[2]);
                            System.out.println("Categoria: " + obj[3]);
                            System.out.println("Forma de pagamento: " + obj[4]);
                            System.out.println("n° parcela: " + obj[5] + "/" + obj[6]);
                        }
                    }
                    if (menuDespesa == 3) {
                        for (Object[] obj : despesasDAO.listar()) {
                            System.out.println("\nID: " + obj[0]);
                            System.out.println("Descrição: " + obj[1]);
                            System.out.println("Valor: " + obj[2]);
                            System.out.println("Categoria: " + obj[3]);
                            System.out.println("Forma de pagamento: " + obj[4]);
                            System.out.println("n° parcela: " + obj[5] + "/" + obj[6]);
                        }

                        System.out.println("\nQual parcela deseja excluir? ");
                        int id = scanner.nextInt();

                        despesasDAO.excluir(id);

                    }
                    if (menuDespesa == 4) {
                        for (Object[] obj : despesasDAO.listarAgrupado()) {
                            System.out.println("\nID: " + obj[0]);
                            System.out.println("Descrição: " + obj[1]);
                            System.out.println("Valor: " + obj[2]);
                            System.out.println("Categoria: " + obj[3]);
                            System.out.println("Forma de pagamento: " + obj[4]);
                            System.out.println("identificador: " + obj[7]);
                        }

                        System.out.println("\nSelecione o identificador que deseja excluir: ");
                        int id = scanner.nextInt();

                        despesasDAO.excluirPorIdentificador(id);

                    }
                    if (menuDespesa == 5) {
                        for (Object[] obj : despesasDAO.listar()) {
                            System.out.println("\nID: " + obj[0]);
                            System.out.println("Descrição: " + obj[1]);
                            System.out.println("Valor: " + obj[2]);
                            System.out.println("Categoria: " + obj[3]);
                            System.out.println("Forma de pagamento: " + obj[4]);
                            System.out.println("n° parcela: " + obj[5] + "/" + obj[6]);
                        }

                        System.out.println("Escolha a despesa que irá editar: ");
                        int id = scanner.nextInt();

                        System.out.println("O que você comprou? ");
                        scanner.nextLine();
                        String descricao = scanner.nextLine();

                        System.out.println("Digite a categoria da compra: ");
                        String categoria = scanner.nextLine();

                        System.out.println("Qual o valor da compra? ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("Qual a data da compra? ");
                        String dataInformada = scanner.nextLine();
                        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate data = LocalDate.parse(dataInformada, formatar);


                    }
                    if (menuDespesa == 6) {

                        System.out.println("\nFiltros");

                        System.out.println("\nDigite a categoria: ");
                        scanner.nextLine();
                        String categoria = scanner.nextLine();

                        System.out.println("Digite a forma de pagamento: ");
                        String forma = scanner.nextLine();

                        System.out.println("Data inicial: (dd/MM/yy)");
                        String dataInicialInformada = scanner.nextLine();
                        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataInicial = LocalDate.parse(dataInicialInformada, formatar);

                        System.out.println("Data final: (dd/MM/yy)");
                        String dataFinalInformada = scanner.nextLine();
                        LocalDate dataFinal = LocalDate.parse(dataFinalInformada, formatar);


                        for (Despesas d : despesasDAO.listarFiltro(categoria, forma, dataInicial, dataFinal)) {
                            System.out.println("\nID: " + d.getId());
                            System.out.println("Descrição: " + d.getDescricao());
                            System.out.println("Valor: " + d.getValor());
                            System.out.println("Categoria: " + d.getCategoria());
                            System.out.println("Forma de pagamento: " + d.getFormaPagamento());
                            System.out.println("n° parcela: " + d.getNparcela() + "/" + d.getParcelas());
                        }

                    }

                }
                if (menu == 2) {
                    //BLOCO RECEITA





                }
                if (menu == 3) {
                    //BLOCO CATEGORIAS
                    boolean menusCategoria = true;

                    while (menusCategoria == true) {
                        System.out.println("""
                                FUNÇÕES CATEGORIA
                                \n1- Cadastrar categoria
                                2- Listar categorias
                                3- Excluir categoria
                                """);

                        int menuCategoria = scanner.nextInt();

                        if (menuCategoria == 1) {
                            System.out.println("\nDigite o nome da categoria: ");
                            scanner.nextLine();
                            String categoria = scanner.nextLine();

                            categorias = new Categorias(0, categoria);

                            categoriasDAO.salvar(categorias);
                        }
                        if (menuCategoria == 2) {
                            for (Categorias c : categoriasDAO.listar()) {
                                System.out.println("\nID: " + c.getId());
                                System.out.println("Categoria: " + c.getCategoria());
                            }
                        }
                        if (menuCategoria == 3) {
                            for (Categorias c : categoriasDAO.listar()) {
                                System.out.println("\nID: " + c.getId());
                                System.out.println("Categoria: " + c.getCategoria());
                            }

                            System.out.println("Qual categoria deseja excluir? ");
                            int id = scanner.nextInt();

                            categoriasDAO.excluir(id);
                        }
                    }
                }

            }
        }

    }
}
