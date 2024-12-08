
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Despesas {
    private double valor;
    private String categoria;
    String descricao;
    private static int contador = 1;
    private int id;
    private LocalDate dataDespesa;
    private int parcelas;
    private List<LocalDate> datasParcelas = new ArrayList<>();


    Scanner scanner = new Scanner(System.in);

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


    public Despesas(int id, String categoria, String descricao, double valor, int parcelas, LocalDate dataDespesa ) {
        this.id = contador++;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.parcelas = parcelas;
        this.dataDespesa = dataDespesa;


        for (int i = 1; i <= parcelas ; i++) {
            datasParcelas.add(dataDespesa.plusMonths(i));
        }
    }

    public Despesas(){}


    List<Despesas> listaDespesas = new ArrayList<>();

    public void cadastrarDespesas(){
        System.out.println("Despesa parcelada? (sim/não)");
        String escolher = scanner.nextLine();

        if(escolher.equalsIgnoreCase("sim")) {
            System.out.println("Em quantas parcelas: ");
            parcelas = scanner.nextInt();
            scanner.nextLine();

        } else if (escolher.equalsIgnoreCase("nao")) {
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

        Despesas despesas = new Despesas(id,categoria,descricao,valor,parcelas,dataDespesa);
        listaDespesas.add(despesas);

        System.out.println("DESPESA CADASTRADA COM SUCESSO. ");
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
        System.out.println("QUAL DESTAS DESPESAS VOCÊ QUER REMOVER: ");
        int remover = scanner.nextInt();
        listaDespesas.remove(remover - 1);
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

        for (Despesas despesas : listaDespesas) {

            for (LocalDate dataparcela : despesas.datasParcelas) {
                if (dataparcela.getMonthValue() == mesDesejado && dataparcela.getYear() == anoEscolhido) {
                    System.out.println(despesas);
                    System.out.println("----------------------------------------------------");
                }
            }
        }
    }



    @Override
    public String toString() {
        return  "ID da compra: " + id +
                "\nCategoria: " + categoria +
                "\nDescrição: " + descricao  +
                "\nValor: " + valor/parcelas +
                "\nParcelas: " + parcelas +
                "\nData da despesa: " + dataDespesa;
    }
}
