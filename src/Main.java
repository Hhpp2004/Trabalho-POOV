import java.util.Scanner;

import Menu.MenuDoacao;
import Menu.MenuDoador;


public class Main {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int menu = 0;
        do {           
                System.out.println("1 - Doador\n2 - Doação\n3 - Sair");
                System.out.printf("Opção: ");
                menu = Integer.parseInt(entrada.nextLine());  
            if (menu == 1) {
                MenuDoador.doador(entrada);
            } else if (menu == 2) {
                MenuDoacao.doacao(entrada);
            }
            else if(menu > 3)
            {
                System.err.println("Opção não disponivel\n");
            }
        } while (menu != 3);
        System.out.println("Até logo");
        entrada.close();
    }
}
