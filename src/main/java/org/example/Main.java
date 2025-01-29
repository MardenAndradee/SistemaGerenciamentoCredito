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

        while(menus == true){
            System.out.println("""
                      \n1- Cadastrar Despesas
                      2- Consultar Despesas
                      3- Remover Despesas
                      4- Pesquisa por Categoria
                      5- Relatorio de despesas mensal
                      
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

                System.out.println("Digite a categoria da compra: ");
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
                    dataDespesa = dataDespesa.plusMonths(1);// Adiciona um mês a mais para a proxima parcela
                }

                System.out.println("DESPESA CADASTRADA COM SUCESSO. ");
                return;


            }if(menu == 2){

                for(Despesas d: despesasDAO.listar()){
                    System.out.println("\nID: " + d.getId());
                    System.out.println("Descrição: " + d.getDescricao());
                    System.out.println("Valor: " + d.getValor());
                    System.out.println("Categoria: " + d.getCategoria());
                    System.out.println("Forma de pagamento: " + d.getFormaPagamento());
                    System.out.println("n° parcela: " + d.getNparcela());
                }
            }
            if(menu == 3){
                //despesas.removerDespesas(despesas);
            }
            if(menu == 4){
                //despesas.pesquisarPorCategoria(despesas);
            }
            if(menu == 5){
                //despesas.relatorioMensal();

            }

        }
    }
}
