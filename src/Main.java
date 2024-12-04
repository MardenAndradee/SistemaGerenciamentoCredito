import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Despesas> despesas = new ArrayList<Despesas>();

        int opc;

        do {


            System.out.println("MENU\n" +
                    "1.Cadastrar Despesa\n" +
                    "2.Consultar Gastos\n" +
                    "0.Sair");

            opc = sc.nextInt();

            switch (opc){

                case 1:
                    System.out.println("Digite o valor da despesa: ");
                    double valor = sc.nextDouble();

                    System.out.println("Digite a data: ");
                    sc.nextLine();
                    String data = sc.nextLine();

                    System.out.println("Digite a descrição: ");
                    String desc = sc.nextLine();

                    Despesas desp1 = new Despesas(valor,data,desc);
                    despesas.add(desp1);

                break;

                case 2:

                    double total = 0;

                    for (Despesas desp : despesas){
                        System.out.println(desp.toString());
                        total += desp.getValor();
                    }

                    System.out.println("TOTAL = R$" + total);

                    break;
            }

        }while (opc!=0);



    }
}