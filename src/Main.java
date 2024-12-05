import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
           Despesas despesas = new Despesas();
           boolean menus = true;
           Scanner scanner = new Scanner(System.in);

           while(menus == true){
             System.out.println("""
                      1- Cadastrar Despesas: 
                      2- Consultar Despesas: 
                      3- Remover Despesas:
                      4- Pesquisa por Categoria:
                      
                       """);
            int menu = scanner.nextInt();

            if(menu == 1){
                despesas.cadastrarDespesas();

            }if(menu == 2){
                despesas.consultarDespesas(despesas);
               }
            if(menu == 3){
                despesas.removerDespesas(despesas);
            }
            if(menu == 4){
                despesas.pesquisarPorCategoria(despesas);
            }

           }
    }
}