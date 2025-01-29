package org.example;


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

        while(menus == true){
            System.out.println("""
                      \n1- Cadastrar Despesa
                      2- Consultar Despesa
                      3- Remover Despesa
                      4- Editar Despesa
                      5- Pesquisa com filtros
                      
                      6- Cadastrar categoria
                      7- Listar categorias
                      8- Editar categoria
                      
                       """);

            int menu = scanner.nextInt();

            if(menu == 1){
                System.out.println("debito ou credito?");
                scanner.nextLine();
                String forma = scanner.nextLine();

                int parcelas = 1;

                if(forma.equalsIgnoreCase("credito")) {
                    System.out.println("Em quantas parcelas: ");
                    parcelas = scanner.nextInt();
                    scanner.nextLine();

                } else if (forma.equalsIgnoreCase("debito")) {
                    parcelas = 1;
                }

                System.out.println("Qual a categoria da compra: ");
                for(Categorias c: categoriasDAO.listar()){
                    System.out.println("\nID: " + c.getId());
                    System.out.println("Categoria: " + c.getCategoria());
                }
                String categoria = scanner.nextLine();

                System.out.println("O que você comprou? ");
                String descricao = scanner.nextLine();

                System.out.println("Qual o valor da compra? ");
                double valor = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("Numero de parcelas: " + parcelas);

                System.out.println("Informe a data da despesa: (dd/MM/yy)");
                String dataInformada = scanner.nextLine();
                DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataDespesa = LocalDate.parse(dataInformada,formatar);

                valor= valor/parcelas;

                int nparcela = 1;

                if(forma.equalsIgnoreCase("credito")){
                    dataDespesa = dataDespesa.plusMonths(1);
                }

                for(int i =1; i <= parcelas;i++) {

                    despesas = new Despesas(0,descricao,valor,categoria,forma,dataDespesa,parcelas,nparcela);

                    despesasDAO.salvar(despesas);

                    nparcela++;
                    dataDespesa = dataDespesa.plusMonths(1);
                }

            }if(menu == 2){

                for(Object[] obj: despesasDAO.listar()){
                    System.out.println("\nID: " + obj[0]);
                    System.out.println("Descrição: " + obj[1]);
                    System.out.println("Valor: " + obj[2]);
                    System.out.println("Categoria: " + obj[3]);
                    System.out.println("Forma de pagamento: " + obj[4]);
                    System.out.println("n° parcela: " + obj[5] + "/" + obj[6]);
                }
            }
            if(menu == 3){
                for(Object[] obj: despesasDAO.listar()){
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
            if(menu == 4){
                for(Object[] obj: despesasDAO.listar()){
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
                LocalDate data = LocalDate.parse(dataInformada,formatar);


            }
            if(menu == 5){

                System.out.println("\nFiltros");

                System.out.println("\nDigite a categoria: ");
                scanner.nextLine();
                String categoria = scanner.nextLine();

                System.out.println("Digite a forma de pagamento: ");
                String forma = scanner.nextLine();

                System.out.println("Data inicial: (dd/MM/yy)");
                String dataInicialInformada = scanner.nextLine();
                DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataInicial = LocalDate.parse(dataInicialInformada,formatar);

                System.out.println("Data final: (dd/MM/yy)");
                String dataFinalInformada = scanner.nextLine();
                LocalDate dataFinal = LocalDate.parse(dataFinalInformada,formatar);


                for(Despesas d: despesasDAO.listarFiltro(categoria, forma, dataInicial, dataFinal)){
                    System.out.println("\nID: " + d.getId());
                    System.out.println("Descrição: " + d.getDescricao());
                    System.out.println("Valor: " + d.getValor());
                    System.out.println("Categoria: " + d.getCategoria());
                    System.out.println("Forma de pagamento: " + d.getFormaPagamento());
                    System.out.println("n° parcela: " + d.getNparcela() + "/" + d.getParcelas());
                }

            }
            if(menu == 6){
                System.out.println("\nDigite o nome da categoria: ");
                scanner.nextLine();
                String categoria = scanner.nextLine();

                categorias = new Categorias(0,categoria);

                categoriasDAO.salvar(categorias);
            }
            if(menu == 7){
                for(Categorias c: categoriasDAO.listar()){
                    System.out.println("\nID: " + c.getId());
                    System.out.println("Categoria: " + c.getCategoria());
                }
            }
            if(menu == 8){
                for(Categorias c: categoriasDAO.listar()){
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
