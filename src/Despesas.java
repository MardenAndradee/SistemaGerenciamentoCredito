import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Despesas {
    private double valor;
    private String categoria;
    String descricao;
    private static int contador = 1;
    private int id;

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

    public Despesas(int id,String categoria,String descricao,double valor) {
        this.id = contador++;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Despesas(){}

    List<Despesas> listaDespesas = new ArrayList<>();

    public void cadastrarDespesas(){
        System.out.println("Digite a categoria da compra: ");
        String categoria = scanner.nextLine();

        System.out.println("O que você comprou? ");
        String descricao = scanner.nextLine();

        System.out.println("Qual o valor da compra? ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Despesas despesas = new Despesas(id,categoria,descricao,valor);
        listaDespesas.add(despesas);

        System.out.println("DESPESA CADASTRADA COM SUCESSO. ");
        return;
    }

    public void consultarDespesas(Despesas despesas){
        System.out.println("DESPESAS DESTE MÊS: ");
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


    @Override
    public String toString() {
        return  "ID da compra: " + id +
                "\nCategoria: " + categoria +
                "\nDescrição: " + descricao  +
                "\nValor: " + valor;
    }
}
