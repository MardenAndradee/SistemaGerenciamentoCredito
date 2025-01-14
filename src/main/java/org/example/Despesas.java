package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Despesas {
    private double valor;
    private String categoria;
    String descricao;
    private static int contador = 1;
    private int id;
    private LocalDate dataDespesa;
    private int parcelas;
    private String forma;
    private int nparcela;

//

    Scanner scanner = new Scanner(System.in);

    public String getTipo() {
        return forma;
    }

    public void setTipo(String tipo) {
        this.forma = tipo;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNparcela() {
        return nparcela;
    }

    public void setNparcela(int nparcela) {
        this.nparcela = nparcela;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Despesas(int id, String categoria, String descricao, double valor, int parcelas, LocalDate dataDespesa, String forma, int nparcela) {
        this.id = this.contador;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.parcelas = parcelas;
        this.dataDespesa = dataDespesa;
        this.forma = forma;
        this.nparcela = nparcela;
    }

    public Despesas(){}

    List<Despesas> listaDespesas = new ArrayList<>();

    public void cadastrarDespesas(){
        System.out.println("debito ou credito?");
        String forma = scanner.nextLine();

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
        dataDespesa = LocalDate.parse(dataInformada,formatar);

        // O intuito dessa parte é em vez de cadastrar uma despesa para várias parcelas como está atualmente no branch principal,
        //  criar várias despesas para várias parcelas.Por exemplo uma despesa tem 5 parcelas, cria 5 despesas e adiciona as 5 a lista
        // Para ficar melhor a organização dos relatórios e banco de dados.

        //Criei a variavel nparcela que mostra qual o numero da parcela


        valor= valor/parcelas; //Para que cada despesa tenha o valor correto, vai dividir o valor pela parcela e deixar armazenado
        // Caso seja uma parcela, vai dividir por 1 e dar na mesma

        nparcela = 1; //define o valor da primeira parcela como 1, se tiver mais parcela vai incrementar no for


        // se a forma de pagamento for credito, mesmo que seja de uma vez, vai para a fatura do proximo mes
        //caso for debito vai para a fatura do mes que foi cadastrado a despesa
        if(forma.equalsIgnoreCase("credito")){
            dataDespesa = dataDespesa.plusMonths(1);
        }

        for(int i =1; i <= parcelas;i++) {   //Cria uma loop para criar as multiplas despesas relacionada ao numero de parcela e adiciona a lista

            Despesas despesas = new Despesas(id,categoria,descricao,valor,parcelas,dataDespesa,forma,nparcela);

            listaDespesas.add(despesas);

            nparcela++;
            dataDespesa = dataDespesa.plusMonths(1);// Adiciona um mês a mais para a proxima parcela
        }

        System.out.println("DESPESA CADASTRADA COM SUCESSO. ");
        contador++;
        return;
    }


    public void consultarDespesas(Despesas despesas){
        System.out.println("DESPESAS CADASTRADAS: ");
        System.out.println("----------------------------------------------------");
        for(Despesas listarDespesas : listaDespesas){
            System.out.println(listarDespesas);
            System.out.println("----------------------------------------------------");
        }
        return;
    }


    public void removerDespesas(Despesas despesas){
        for(Despesas despesas1 : listaDespesas){
            System.out.println(despesas1);
            System.out.println("----------------------------------------------------");
        }
        System.out.println("QUAL ID DESTAS DESPESAS VOCÊ QUER REMOVER: ");
        int remover = scanner.nextInt();
        listaDespesas.removeIf(despesas1 -> despesas1.getId() == remover);


        System.out.println("DESPESA REMOVIDA COM SUCESSO. ");
        return;
    }


    public void pesquisarPorCategoria(Despesas despesas){

        System.out.println("QUAL A CATEGORIA DA SUA DESPESA: ");
        String categorias = scanner.nextLine();
        System.out.println("DESPESAS DA CATEGORIA: " + categorias);
        System.out.println("----------------------------------------------------");
        for (Despesas despesas1 : listaDespesas){
            if(despesas1.getCategoria().equalsIgnoreCase(categorias)){
                System.out.println(despesas1);
                System.out.println("----------------------------------------------------");
                return;
            }
        }
    }


    public void relatorioMensal(){
        System.out.println("De que ano são as faturas: (yyyy) ");
        int anoEscolhido = scanner.nextInt();

        System.out.println("Você quer ver faturas de qual mês? (Digite o numero do mês, 1-12");
        int mesDesejado = scanner.nextInt();
        scanner.nextLine();

        for (Despesas despesas : listaDespesas){
            if(mesDesejado == despesas.dataDespesa.getMonthValue() && anoEscolhido == despesas.dataDespesa.getYear()){
                System.out.println(despesas);
                System.out.println("----------------------------------------------------");
            }
        }
    }



    @Override
    public String toString() {
        return  "ID da compra: " + id +
                "\nCategoria: " + categoria +
                "\nDescrição: " + descricao  +
                "\nValor: " + valor +
                "\nParcelas: " + parcelas +
                "\nData da despesa: " + dataDespesa +
                "\nForma: " + forma +
                "\nN° da Parcela " + nparcela;
    }
}
